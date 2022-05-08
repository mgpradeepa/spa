package com.mgp.web.appmods.spamod.ownerws;


import com.mgp.web.appmods.spamod.dao.OwnerDaoService;
import com.mgp.web.appmods.spamod.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerResource {

    @Autowired
    OwnerDaoService ownerDaoService;


    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Owner> create (@RequestBody Owner owner ) {
        try {
           Owner createdOwner = ownerDaoService.save(new Owner(owner.getOid(),owner.getOwnerName(), owner.getSsn()));
           return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
        }
        catch(Exception e ) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Owner>> getAllOwners () {
        try {
            List<Owner> owners = ownerDaoService.findAll();
            if(owners.isEmpty()) {
                return new ResponseEntity<> (HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(owners, HttpStatus.OK);
        }
        catch(Exception e ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
