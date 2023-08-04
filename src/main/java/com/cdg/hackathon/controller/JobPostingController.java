package com.cdg.hackathon.controller;

import com.cdg.hackathon.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.dto.request.DoApplyRequest;
import com.cdg.hackathon.dto.request.UpdateJobPostingRequest;
import com.cdg.hackathon.dto.response.GetJobPostingsResponse;
import com.cdg.hackathon.service.JobPostingData;
import com.cdg.hackathon.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping("/job-postings")
    public ResponseEntity<Long> createJobPosting(@Valid @RequestBody CreateJobPostingRequest request) {

        Long jobPosting = jobPostingService.createJobPosting(request.toServiceData());
        return ResponseEntity.status(HttpStatus.OK).body(jobPosting);
    }

    @GetMapping("/job-postings")
    public ResponseEntity<GetJobPostingsResponse> getJobPostings(@RequestParam(value = "search", required = false) String keyword) {

        List<JobPostingData> jobPostingData = jobPostingService.getJobPostings(keyword);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetJobPostingsResponse(jobPostingData));
    }

//    @GetMapping("/job-postings/{postId}")
//    public ResponseEntity<GetJobPostingResponse> getJobPosting(@PathVariable("postId") Long postId) {
//
//        GetJobPostingResponse getJobPostingResponse = jobPostingService.getJobPosting(postId);
//
//        return new ResponseEntity(getJobPostingResponse, HttpStatus.OK);
//    }


    @PatchMapping("/job-postings/{postId}")
    public ResponseEntity updateJobPosting(@PathVariable("postId") Long postId, @Valid @RequestBody UpdateJobPostingRequest request) {

        jobPostingService.updateJobPosting(postId, request);

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/job-postings/{postId}")
    public ResponseEntity deleteJobPosting(@PathVariable("postId") Long postId) {

        jobPostingService.deleteJobPosting(postId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/job-postings/apply")
    public ResponseEntity doApply(@Valid @RequestBody DoApplyRequest request) {

        jobPostingService.doApply(request);

        return new ResponseEntity(HttpStatus.OK);

    }

}
