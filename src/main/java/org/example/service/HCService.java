package org.example.service;

import org.example.model.BackGoods;
import org.example.model.Sku;

import java.util.List;

public interface HCService {
    public boolean commit(Sku sku);
    public List<Sku> check(Sku sku);
    public boolean delete(Sku sku);
    public boolean deleteChaos();
    public boolean deleteAll();
    public boolean commitInventory(Sku sku);
    public List<Sku> checkInventory(Sku sku);
    public boolean InventoryDelete(List<Sku> skulist);
    public boolean InventoryChangeName(Long logsId,String preName);
    public boolean InventoryChangeNumber(Long logsId,String preNumber);
}

