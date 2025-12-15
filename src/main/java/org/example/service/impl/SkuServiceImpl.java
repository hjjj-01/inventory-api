package org.example.service.impl;

import org.example.controller.SkuController;
import org.example.model.Sku;
import org.example.repository.SkuRepository;
import org.example.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuRepository skuRepository;
    @Override
    public boolean commit(Sku sku){
        int AffectRows = skuRepository.commit(sku);
        return AffectRows>0;
    }
    public boolean ReCommit(Sku sku){
        int AffectRows = skuRepository.ReCommit(sku);
        return AffectRows>0;
    }
    public List<Sku> check(Sku sku){
        return skuRepository.check(sku);
    }
    public List<Sku> Recheck(Sku sku){
        return skuRepository.Recheck(sku);
    }
    public boolean changeName(Long logsId,String preName){
        int AffectRows = skuRepository.changeName(logsId, preName);
        return AffectRows>0;
    }
    public boolean changeNumber(Long logsId,String preNumber){
        int AffectRows = skuRepository.changeNumber(logsId, preNumber);
        return AffectRows>0;
    }
    public boolean delete(List<Sku> skulist){
        int AffectRows = skuRepository.delete(skulist);
        return AffectRows>0;
    }
    public boolean Redelete(List<Sku> skulist){
        int AffectRows = skuRepository.Redelete(skulist);
        return AffectRows>0;
    }
    public Sku checkSkuStyle(String sku){
        return skuRepository.checkSkuStyle(sku);
    }
    public List<Sku> YcBoxCheck(Sku sku){
        return skuRepository.YcBoxCheck(sku);
    }
    public List<Sku> YcBoxCheckDel(Sku sku){
        return skuRepository.YcBoxCheckDel(sku);
    }
    public boolean YcBoxAdd(List<Sku> sku){
        int AffectRows = skuRepository.YcBoxAdd(sku);
        return AffectRows>0;
    }
}
