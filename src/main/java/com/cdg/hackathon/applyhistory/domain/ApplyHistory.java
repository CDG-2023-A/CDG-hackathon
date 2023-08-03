package com.cdg.hackathon.applyhistory.domain;

import com.cdg.hackathon.jobposting.domain.BaseEntity;
import com.cdg.hackathon.jobposting.dto.request.DoApplyRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "APPLY_HISTORY")
public class ApplyHistory extends BaseEntity {

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
