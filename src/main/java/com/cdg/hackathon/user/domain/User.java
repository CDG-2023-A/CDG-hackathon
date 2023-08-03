package com.cdg.hackathon.user.domain;

import com.cdg.hackathon.jobposting.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {
    private String name;

    public User(String name) {
        this.name = name;
    }


}
