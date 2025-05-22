package com.mgp.web.appmods.springoauth.controller.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TenantEntity {
    @JsonProperty("shortName")
    private String tenantShortName;

    @JsonProperty("tenantName")
    private String tenantName;

    @JsonProperty("description")
    private String tenantDescription;

}
