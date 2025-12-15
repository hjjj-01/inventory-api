package org.example.model;

public class Sku {
    private Long logs_id;
    private Long hc_db_id;
    private String sku;
    private String shop_name;
    private String shop_style;
    private String stock;
    private Long numbers;
    private Long sysNumber;
    private Long realNumber;
    private String operator;
    private String remark;
    private String dateTime;
    private String boxNo;
    private String mantissa;
    private String complete;

    public void setLogs_id(Long logs_id) {
        this.logs_id = logs_id;
    }
    public Long getLogs_id() {
        return logs_id;
    }
    public void setHc_db_id(Long hc_db_id) {
        this.hc_db_id = hc_db_id;
    }
    public Long getHc_db_id() {
        return hc_db_id;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_style(String shop_style) {
        this.shop_style = shop_style;
    }

    public String getShop_style() {
        return shop_style;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStock() {
        return stock;
    }
    public void setNumbers(Long numbers) {
        this.numbers = numbers;
    }

    public Long getNumbers() {
        return numbers;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getOperator() {
        return operator;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public String getDateTime() {
        return dateTime;
    }
    public void setSysNumber(Long sysNumber) {
        this.sysNumber = sysNumber;
    }
    public Long getSysNumber() {
        return sysNumber;
    }
    public void setRealNumber(Long realNumber) {
        this.realNumber = realNumber;
    }
    public Long getRealNumber() {
        return realNumber;
    }
    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }
    public String getBoxNo() {
        return boxNo;
    }
    public void setMantissa(String mantissa) {
        this.mantissa = mantissa;
    }
    public String getMantissa() {
        return mantissa;
    }
    public String getComplete() {
        return complete;
    }
    public void setComplete(String complete) {
        this.complete = complete;
    }
}
