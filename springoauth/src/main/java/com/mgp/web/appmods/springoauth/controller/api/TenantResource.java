package com.mgp.web.appmods.springoauth.controller.api;

import com.mgp.web.appmods.springoauth.controller.entity.TenantEntity;
import com.mgp.web.appmods.springoauth.service.ITenantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spa/v1/tenants")
public class TenantResource {

    @Autowired
    ITenantService its;

    @PostMapping(value="/create", produces = "application/json")
    public ResponseEntity<TenantEntity> createTenant(@RequestBody TenantEntity tenant, HttpServletRequest request) throws Exception{

        TenantEntity te = its.save(tenant);
        return ResponseEntity.ok(te);


    }
}
