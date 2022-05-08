package com.mgp.web.appmods.spamod.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "company")
public class Company  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
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

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="oid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Owner owners;
}
