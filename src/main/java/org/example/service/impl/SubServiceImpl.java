package org.example.service.impl;

import org.example.model.SubOrder;
import org.example.repository.SubRepository;
import org.example.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubServiceImpl implements SubService {
    @Autowired
    private SubRepository subRepository;
    @Override
    public boolean commit(SubOrder suborder) {
        int AffectRows = subRepository.commit(suborder);
        return AffectRows>0;
    }
    public List<SubOrder> check(SubOrder suborder){
        return subRepository.check(suborder);
    }

}
