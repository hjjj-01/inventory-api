package org.example.service;

import org.example.model.Sku;
import org.example.model.SubOrder;

import java.util.List;

public interface SubService {
    public boolean commit(SubOrder suborder);
    public List<SubOrder> check(SubOrder suborder);
}

