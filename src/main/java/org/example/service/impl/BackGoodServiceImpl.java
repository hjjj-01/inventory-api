package org.example.service.impl;

import org.example.model.BackGoods;
import org.example.model.SubOrder;
import org.example.repository.BackGoodsRepository;
import org.example.repository.SubRepository;
import org.example.service.BackGoodsService;
import org.example.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackGoodServiceImpl implements BackGoodsService {
    @Autowired
    private BackGoodsRepository backgoodsRepository;
    @Override
    public List<BackGoods> checkCompany() {
        return backgoodsRepository.checkCompany();
    }
    public List<BackGoods> getOrdersList() {
        return backgoodsRepository.getOrdersList();
    }
    public boolean commitOrders(List<BackGoods> backGoodsList) {
        int result = backgoodsRepository.commitOrders(backGoodsList);
        return result > 0;
    }
    public boolean verify(BackGoods backGoods) {
        int result = backgoodsRepository.verify(backGoods);
        return result > 0;
    }
    public List<BackGoods> getOrderDetail(BackGoods backGoods) {
        return backgoodsRepository.getOrderDetail(backGoods);
    }

}
