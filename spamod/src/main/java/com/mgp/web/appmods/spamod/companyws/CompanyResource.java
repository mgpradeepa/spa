package com.mgp.web.appmods.spamod.companyws;

import com.mgp.web.appmods.spamod.dao.CompanyDaoService;
import com.mgp.web.appmods.spamod.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyResource {

    @Autowired
    CompanyDaoService companyDaoService;


    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Company>> getAllCompanies() {
        try{
            List<Company> companies = companyDaoService.findAll();
            if(companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(companies,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(produces = "application/json"  )
    public ResponseEntity<Company> createCompany (@RequestBody Company company) {
        try {
            Company comp = null;
            comp = companyDaoService.save(new Company(company.getCompanyId(), company.getCompanyName(), company.getCountry(), company.getPhoneNumber()));

            return new ResponseEntity<>(comp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{cid}" , produces = "application/json")
    public ResponseEntity<Company> findCompany(@PathParam("cid") String cid) {
        try {
            Optional<Company> company = companyDaoService.findByCompanyId(cid);

            return company.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{cid}", produces = "application/json")
    public ResponseEntity<Company> updateCompany(@PathParam("cid") String cid, @RequestBody Company  comp) {
        Optional<Company> company = companyDaoService.findByCompanyId(cid);


        if(company.isPresent() ) {
            Company updateCompany = company.get();
            updateCompany.setCompanyName(comp.getCompanyName());
            updateCompany.setCountry(comp.getCountry());
            updateCompany.setPhoneNumber(comp.getPhoneNumber());
            return new ResponseEntity<>(companyDaoService.save(updateCompany), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
