package org.example.repository;

import org.example.controller.SkuController;
import org.example.model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SkuRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int commit(Sku sku){
        try{
            String sql = "INSERT INTO stock_inventory_table (stock, sku, numbers,operator,remark,dateTime) VALUES (?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, sku.getStock(), sku.getSku(), sku.getNumbers(),sku.getOperator(),sku.getRemark(), sku.getDateTime());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
    public int ReCommit(Sku sku){
        try{
            String sql = "INSERT INTO stock_re_inventory_table (stock,sysNumber, realNumber,operator,remark,dateTime) VALUES (?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, sku.getStock(), sku.getSysNumber(), sku.getRealNumber(),sku.getOperator(),sku.getRemark(), sku.getDateTime());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
    public List<Sku> check(Sku sku){
        StringBuilder sql = new StringBuilder( "SELECT * FROM sku_storage_view WHERE 1=1 ");
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
    public List<Sku> Recheck(Sku sku){
        StringBuilder sql = new StringBuilder( "SELECT * FROM stock_re_inventory_table WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if(sku.getStock() != null && !sku.getStock().trim().isEmpty()) {
            sql.append(" AND stock LIKE ?");
            params.add("%" + sku.getStock() + "%");
        }
        sql.append(" ORDER BY stock_re_inventory_id DESC");
        try{
            return jdbcTemplate.query(
                    sql.toString(),
                    (rs,r) -> {
                        Sku s = new Sku();
                        s.setLogs_id(rs.getLong("stock_re_inventory_id"));
                        s.setStock(rs.getString("stock"));
                        s.setRealNumber(rs.getLong("realNumber"));
                        s.setSysNumber(rs.getLong("sysNumber"));
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

    public int changeName(Long logsId,String preName){
        String sql = "UPDATE stock_inventory_table SET operator = ? WHERE logs_id = ?";
        try{
            return jdbcTemplate.update(sql, preName,logsId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int changeNumber(Long logsId,String preNumber){
        String sql = "UPDATE stock_inventory_table SET numbers = ? WHERE logs_id = ?";
        try{
            return jdbcTemplate.update(sql, preNumber,logsId);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int delete(List<Sku> skulist){
        String sql = "DELETE FROM stock_inventory_table WHERE logs_id = ?";
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
    public int Redelete(List<Sku> skulist){
        String sql = "DELETE FROM stock_re_inventory_table WHERE stock_re_inventory_id= ?";
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
    public Sku checkSkuStyle(String sku) {
        String sql = "SELECT shop_style,shop_name FROM sku_table WHERE sku = ?";

        // 清理sku参数
        if (sku != null) {
            sku = sku.trim();
        }

        List<Sku> result = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Sku s = new Sku();
            s.setShop_style(rs.getString("shop_style"));
            s.setShop_name(rs.getString("shop_name"));
            return s;
        }, sku);
        System.out.print(result.get(0).getShop_style());
        return result.isEmpty() ? null : result.get(0);
    }
    public List<Sku> YcBoxCheck(Sku sku) {
        StringBuilder sql = new StringBuilder("select * from yc_box_view where 1=1");
        List<Object> params = new ArrayList<>();
        try{
            if (sku.getBoxNo() != null && !sku.getBoxNo().trim().isEmpty()) {
                sql.append(" and boxNo = ?");
                params.add(sku.getBoxNo());
            }
            if (sku.getSku() != null && !sku.getSku().trim().isEmpty()) {
                sql.append(" and sku = ?");
                params.add(sku.getSku());
            }
            if(sku.getShop_name() != null && !sku.getShop_name().trim().isEmpty()){
                sql.append(" and shop_name like ?");
                params.add("%"+sku.getShop_name()+"%");
            }

            return jdbcTemplate.query(sql.toString(),
                    (rs, rowNum) -> {
                        Sku s = new Sku();
                        s.setBoxNo(rs.getString("boxNo"));
                        s.setSku(rs.getString("sku"));
                        s.setShop_name(rs.getString("shop_name"));
                        s.setRealNumber(rs.getLong("realNumber"));
                        return s;
                    }
                    ,params.toArray());

        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
    public List<Sku> YcBoxCheckDel(Sku sku) {
        StringBuilder sql = new StringBuilder("select * from yc_box_del_view where 1=1");
        List<Object> params = new ArrayList<>();
        try{
            if (sku.getBoxNo() != null && !sku.getBoxNo().trim().isEmpty()) {
                sql.append(" and boxNo = ?");
                params.add(sku.getBoxNo());
            }
            if (sku.getSku() != null && !sku.getSku().trim().isEmpty()) {
                sql.append(" and sku = ?");
                params.add(sku.getSku());
            }
            if(sku.getShop_name() != null && !sku.getShop_name().trim().isEmpty()){
                sql.append(" and shop_name like ?");
                params.add("%"+sku.getShop_name()+"%");
            }

            return jdbcTemplate.query(sql.toString(),
                    (rs, rowNum) -> {
                        Sku s = new Sku();
                        s.setBoxNo(rs.getString("boxNo"));
                        s.setSku(rs.getString("sku"));
                        s.setShop_name(rs.getString("shop_name"));
                        s.setRealNumber(rs.getLong("realNumber"));
                        s.setDateTime(rs.getString("dateTime"));
                        return s;
                    }
                    ,params.toArray());

        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
    public int YcBoxAdd(List<Sku> sku){
        String sql = "INSERT INTO yc_box_table (boxNo,sku,realNumber,dateTime) VALUES (?,?,?,?)";
        try{
            int count = 0;
            for(Sku s:sku){
                jdbcTemplate.update(sql, s.getBoxNo(),s.getSku(),s.getRealNumber(),s.getDateTime());
                count++;
            }
            if(count == sku.size()){
                return count;
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


}
