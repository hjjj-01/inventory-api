package org.example.repository;

import org.example.model.Sku;
import org.example.model.SubOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int commit(SubOrder suborder){
        try{
            String sql = "INSERT INTO subOrder_table (inStockCode,Stock) VALUES (?,?)";
            return jdbcTemplate.update(sql, suborder.getInStockCode(),suborder.getStock());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
    public List<SubOrder> check(SubOrder suborder){
        StringBuilder sql = new StringBuilder( "SELECT * FROM subOrder_table WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if(suborder.getInStockCode() != null && !suborder.getInStockCode().trim().isEmpty()) {
            sql.append(" AND inStockCode LIKE ?");
            params.add("%"+suborder.getInStockCode()+"%");
        }
        if(suborder.getStock() != null && !suborder.getStock().trim().isEmpty()) {
            sql.append(" AND stock LIKE ?");
            params.add("%" + suborder.getStock() + "%");
        }
        try{
            return jdbcTemplate.query(
                    sql.toString(),
                    (rs,r) -> {
                        SubOrder s = new SubOrder();
                        s.setInStockCode(rs.getString("inStockCode"));
                        s.setStock(rs.getString("stock"));
                        return s;
                    },
                    params.toArray()
            );

        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();}
    }




}
