package com.mgp.web.appmods.spamod.dao;

import com.mgp.web.appmods.spamod.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerDaoService extends JpaRepository<Owner, Long> {
    Optional<Owner> findByOid(String oid );

}
