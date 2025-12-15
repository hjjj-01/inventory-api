package org.example.repository;

import org.example.model.BackGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BackGoodsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<BackGoods> checkCompany(){
        String sql = "select * from company_table";
        try{
            return jdbcTemplate.query(sql,(rs,rowNum)->{
                BackGoods backGoods = new BackGoods();
                backGoods.setCompanyId(rs.getString("company_id"));
                backGoods.setCompanyName(rs.getString("company_name"));
                backGoods.setCompanyId(rs.getString("company_id"));
                backGoods.setCompanyShortEn(rs.getString("company_short_en"));
                return backGoods;
            });
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
    public int commitOrders(List<BackGoods> backGoods){
        String addorderSql = "CALL insert_auto_backorder_code(?,?,?)";
        String insertGoodsSql = "insert into back_orders_sku_table(sku,skuNum,backOrders) values(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            String backOrders = jdbcTemplate.execute((ConnectionCallback<String>) connection -> {
                CallableStatement cs = connection.prepareCall(addorderSql);
                cs.setString(1, backGoods.get(0).getCompanyName());
                cs.setString(2,backGoods.get(0).getCreateTime());
                cs.registerOutParameter(3, java.sql.Types.VARCHAR);
                cs.execute();
                return cs.getString(3);
            });
            if(backOrders == null){
                throw new RuntimeException("生成退货单号失败");
            }
            int resultrow = 0;
            for(BackGoods backGood:backGoods){
                jdbcTemplate.update(insertGoodsSql,backGood.getSku(),backGood.getSkuNum(),backOrders);
                resultrow ++;
            }
            if(resultrow == 0){
                throw new RuntimeException("没有插入数据");
            }else{
                System.out.print(resultrow);
                return resultrow;

            }

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public List<BackGoods> getOrdersList(){
        String sql = "select * from backorders_table where complete = '未复核' or complete = '已复核'";
        try{
            return jdbcTemplate.query(sql,(rs,rowNum)->{
                BackGoods backGoods = new BackGoods();
                backGoods.setBackOrders(rs.getString("backOrders"));
                backGoods.setCreateTime(rs.getString("createTime"));
                backGoods.setCompanyName(rs.getString("companyName"));
                backGoods.setComplete(rs.getString("complete"));

                return backGoods;
            });
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
    public List<BackGoods> getOrderDetail(BackGoods backGoods){
        String sql = "select * from back_orders_list_view where backOrders = ?";
        try{
            return jdbcTemplate.query(sql,(rs,rowNum)->{
                BackGoods backGood = new BackGoods();

                backGood.setBackOrders(rs.getString("backOrders"));
                backGood.setSku(rs.getString("sku"));
                backGood.setSkuNum(rs.getLong("skuNum"));
                backGood.setCompanyName(rs.getString("companyName"));
                backGood.setCompanyId(rs.getString("companyId"));
                backGood.setCompanyShort(rs.getString("companyShort"));
                backGood.setCompanyShortEn(rs.getString("companyShortEn"));
                return backGood;
            },backGoods.getBackOrders());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public int verify(BackGoods backGoods){
        String sql = "update backorders_table set complete = '已复核' where backOrders = ?";
        try{
            int result = jdbcTemplate.update(sql,backGoods.getBackOrders());
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }




}
