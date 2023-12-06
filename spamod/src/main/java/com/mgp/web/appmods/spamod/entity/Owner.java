package com.mgp.web.appmods.spamod.entity;

import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;
import java.io.Serializable;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "owner", uniqueConstraints = {@UniqueConstraint(columnNames = {"companyId"})})
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @Column(name = "oid")
    private String oid;

    @NonNull
    @Column(name = "ownerName")
    private String ownerName;

    @NonNull
    @Column(name = "SSN")
    private String ssn;


    @ManyToOne
    @JoinColumn(name="companyId")
    private Company company;

    public Owner() {}

    public Owner(@NonNull String oid, String ownerName, String ssn, Company c) {
        this.oid = oid;
        this.ownerName = ownerName;
        this.ssn  = ssn;
        this.company = c;
    }
}
