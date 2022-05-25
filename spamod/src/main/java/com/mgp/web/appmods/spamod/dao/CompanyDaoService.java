package com.mgp.web.appmods.spamod.dao;

import com.mgp.web.appmods.spamod.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyDaoService  extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyId(String cid ); // variable name should be same as the column name


}
