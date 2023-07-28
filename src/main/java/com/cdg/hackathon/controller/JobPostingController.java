package com.cdg.hackathon.controller;

import com.cdg.hackathon.dto.JobPostingData;
import com.cdg.hackathon.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.dto.request.DoApplyRequest;
import com.cdg.hackathon.dto.request.UpdateJobPostingRequest;
import com.cdg.hackathon.dto.response.GetJobPostingResponse;
import com.cdg.hackathon.dto.response.GetJobPostingsResponse;
import com.cdg.hackathon.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@RestController
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping("/job-postings")
    public ResponseEntity createJobPosting(@Valid @RequestBody CreateJobPostingRequest request) {

        jobPostingService.createJobPosting(request);

        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping("/job-postings/{keyword}")
//    public ResponseEntity<GetJobPostingsResponse> getJobPostings(@PathVariable("keyword") String keyword) {
//
//        Page<JobPostingData> jobPostingData = jobPostingService.getJobPostings(keyword);
//        return new ResponseEntity(new GetJobPostingsResponse(jobPostingData), HttpStatus.OK);
//    }

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
