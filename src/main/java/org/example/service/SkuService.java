package org.example.service;

import org.example.controller.SkuController;
import org.example.model.Sku;

import java.util.List;

public interface SkuService {
    public boolean commit(Sku sku);
    public boolean ReCommit(Sku sku);
    public List<Sku> check(Sku sku);
    public List<Sku> Recheck(Sku sku);

    public boolean delete(List<Sku> skulist);
    public boolean Redelete(List<Sku> skulist);
    public boolean changeName(Long logsId,String preName);
    public boolean changeNumber(Long logsId,String preNumber);

    public Sku checkSkuStyle(String sku);
    //异常
    public List<Sku> YcBoxCheck(Sku sku);
    public List<Sku> YcBoxCheckDel(Sku sku);
    public boolean YcBoxAdd(List<Sku> sku);
}

