package com.cdg.hackathon.domain;

import com.cdg.hackathon.dto.request.DoApplyRequest;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    private ApplyHistory(Long jobPostingId, Long userId) {
        this.jobPostingId = jobPostingId;
        this.userId = userId;
    }

    public ApplyHistory(DoApplyRequest request){
        this.jobPostingId = request.getJobPostingId();
        this.userId = request.getUserId();
    }

}
