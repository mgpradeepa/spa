package com.mgp.web.appmods.spamod.dto;

import lombok.NonNull;

import jakarta.persistence.*;

public class CompanyDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "companyId")
    private String companyId;

    @NonNull
    @Column(name = "companyName")
    private String companyName;

    @NonNull
    @Column(name = "country")
    private String country;

    @NonNull
    @Column(name = "phoneNumber")
    private Long phoneNumber;

}
