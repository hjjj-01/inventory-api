package org.example.repository;

import org.example.model.BackGoods;
import org.example.model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HCRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int commit(Sku sku){
        String insertGoodsSql = "insert into hc_db_table(sku,boxNo,realNumber,dateTime,remark,operator,mantissa,complete) values(?,?,?,?,?,?,?,?)";
        try{
            return jdbcTemplate.update(insertGoodsSql,sku.getSku(),sku.getBoxNo(),sku.getRealNumber(),sku.getDateTime(),sku.getRemark(),sku.getOperator(),sku.getMantissa(),sku.getComplete());

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public List<Sku> check(Sku sku){
        String sql = "select * from hc_db_view";
        try{
            return jdbcTemplate.query(sql,(rs,rowNum)->{
                Sku s = new Sku();
                s.setHc_db_id(rs.getLong("hc_db_id"));
                s.setBoxNo(rs.getString("boxNo"));
                s.setSku(rs.getString("sku"));
                s.setShop_name(rs.getString("shop_name"));
                s.setRealNumber(rs.getLong("realNumber"));
                s.setDateTime(rs.getString("dateTime"));
                s.setRemark(rs.getString("remark"));
                s.setOperator(rs.getString("operator"));
                s.setMantissa(rs.getString("mantissa"));
                return s;
            });
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
    public int delete(Sku sku){
        String sql = "delete from hc_db_table where hc_db_id = ?";
        try{
            return jdbcTemplate.update(sql,sku.getHc_db_id());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int deleteChaos(){
        String sql = "update hc_db_table set complete = '是' where mantissa = '是'";
        try{
            return jdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int deleteAll(){
        String sql = "update hc_db_table set complete = '是' where mantissa = '否'";
        try{
            return jdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }




}
