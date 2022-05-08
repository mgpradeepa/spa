package com.mgp.web.appmods.spamod.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "owner")
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


}
