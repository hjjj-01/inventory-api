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
}

