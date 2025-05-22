package com.mgp.web.appmods.springoauth.service;

import com.mgp.web.appmods.springoauth.controller.entity.TenantEntity;
import org.springframework.stereotype.Service;

@Service
public class TenantSetvice implements ITenantService{
    @Override
    public TenantEntity save(TenantEntity te) {
        System.out.println("Tenant is " + te);
        return te;


    }
}
