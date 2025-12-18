package org.example.repository;

import org.example.model.BackGoods;
import org.example.model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
    public int commitInventory(Sku sku){
        try{
            String sql = "INSERT INTO hc_stock_inventory_table (stock, sku, numbers,operator,remark,dateTime,complete) VALUES (?,?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, sku.getStock(), sku.getSku(), sku.getNumbers(),sku.getOperator(),sku.getRemark(), sku.getDateTime(),sku.getComplete());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
    public List<Sku> checkInventory(Sku sku){
        StringBuilder sql = new StringBuilder( "SELECT * FROM hc_sku_storage_view WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        if(sku.getSku() != null && !sku.getSku().trim().isEmpty()) {
            sql.append(" AND sku LIKE ?");
            params.add("%"+sku.getSku()+"%");
        }
        if(sku.getStock() != null && !sku.getStock().trim().isEmpty()) {
            sql.append(" AND stock LIKE ?");
            params.add("%" + sku.getStock() + "%");
        }
        sql.append(" ORDER BY logs_id DESC");
        try{
            return jdbcTemplate.query(
                    sql.toString(),
                    (rs,r) -> {
                        Sku s = new Sku();
                        s.setLogs_id(rs.getLong("logs_id"));
                        s.setSku(rs.getString("sku"));
                        s.setStock(rs.getString("stock"));
                        s.setNumbers(rs.getLong("numbers"));
                        s.setShop_name(rs.getString("shop_name"));
                        s.setShop_style(rs.getString("shop_style"));
                        s.setOperator(rs.getString("operator"));
                        s.setRemark(rs.getString("remark"));
                        s.setDateTime(rs.getString("dateTime"));
                        return s;
                    },
                    params.toArray()
            );

        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();}
    }
    public int InventoryChangeName(Long logsId,String preName){
        String sql = "UPDATE hc_stock_inventory_table SET operator = ? WHERE logs_id = ?";
        try{
            return jdbcTemplate.update(sql, preName,logsId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int InventoryChangeNumber(Long logsId,String preNumber){
        String sql = "UPDATE hc_stock_inventory_table SET numbers = ? WHERE logs_id = ?";
        try{
            return jdbcTemplate.update(sql, preNumber,logsId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int InventoryDelete(List<Sku> skulist){
        String sql = "UPDATE hc_stock_inventory_table SET complete = '已完成' WHERE logs_id = ?";
        try{
            int[] results = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(java.sql.PreparedStatement ps, int i) throws java.sql.SQLException {
                    Sku sku = skulist.get(i);
                    ps.setLong(1, sku.getLogs_id());
                }

                @Override
                public int getBatchSize() {
                    return skulist.size();
                }
            });

            // 返回成功更新的记录数
            int successCount = 0;
            for (int result : results) {
                if (result > 0) {
                    successCount++;
                }
            }
            System.out.println("批量备注完成，成功更新: " + successCount + " 条记录");
            return successCount;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }



}
