package org.example.service.impl;

import org.example.model.BackGoods;
import org.example.model.Sku;
import org.example.repository.BackGoodsRepository;
import org.example.repository.HCRepository;
import org.example.service.BackGoodsService;
import org.example.service.HCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HCServiceImpl implements HCService {
    @Autowired
    private HCRepository hcRepository;
    @Override
    public boolean commit(Sku sku) {
        int result = hcRepository.commit(sku);
        return result > 0;
    }

    public List<Sku> check(Sku sku) {
        return hcRepository.check(sku);
    }
    public boolean delete(Sku sku) {
        int result = hcRepository.delete(sku);
        return result > 0;
    }
    public boolean deleteChaos() {
        int result = hcRepository.deleteChaos();
        return result > 0;
    }
    public boolean deleteAll(){
        int result = hcRepository.deleteAll();
        return result > 0;
    }
    public boolean commitInventory(Sku sku) {
        int result = hcRepository.commitInventory(sku);
        return result > 0;
    }
    public List<Sku> checkInventory(Sku sku) {
        return hcRepository.checkInventory(sku);
    }
    public boolean InventoryChangeName(Long logsId,String preName){
        int AffectRows = hcRepository.InventoryChangeName(logsId, preName);
        return AffectRows>0;
    }
    public boolean InventoryChangeNumber(Long logsId,String preNumber){
        int AffectRows = hcRepository.InventoryChangeNumber(logsId, preNumber);
        return AffectRows>0;
    }
    public boolean InventoryDelete(List<Sku> skulist){
        int AffectRows = hcRepository.InventoryDelete(skulist);
        return AffectRows>0;
    }

}
