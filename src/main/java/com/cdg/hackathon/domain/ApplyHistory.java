package com.cdg.hackathon.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplyHistory extends BaseEntity{

    @Column(name = "job_posting_id")
    private Long jobPostingId;

    @Column(name = "user_id")
    private Long userId;
}
