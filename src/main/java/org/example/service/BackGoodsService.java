package org.example.service;

import org.example.model.BackGoods;
import org.example.model.SubOrder;

import java.util.List;

public interface BackGoodsService {
    public List<BackGoods> checkCompany();
    public List<BackGoods> getOrdersList();
    public boolean commitOrders(List<BackGoods> backGoodsList);
    public boolean verify(BackGoods backGoods);
    public List<BackGoods> getOrderDetail(BackGoods backGoods);
}

