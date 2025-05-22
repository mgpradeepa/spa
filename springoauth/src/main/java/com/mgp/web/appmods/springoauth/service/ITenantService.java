package com.mgp.web.appmods.springoauth.service;

import com.mgp.web.appmods.springoauth.controller.entity.TenantEntity;
import org.springframework.stereotype.Service;

@Service
public interface ITenantService {
    public TenantEntity save(TenantEntity te);
}
