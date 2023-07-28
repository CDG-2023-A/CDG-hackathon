package com.cdg.hackathon.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseEntity {

    private String name;

    private String country;

    private String region;

    public Company(String name, String country, String region) {
        this.name = name;
        this.country = country;
        this.region = region;
    }
}
