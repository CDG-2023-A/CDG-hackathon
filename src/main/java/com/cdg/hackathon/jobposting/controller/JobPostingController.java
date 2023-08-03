package com.cdg.hackathon.jobposting.controller;

import com.cdg.hackathon.jobposting.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.jobposting.dto.request.DoApplyRequest;
import com.cdg.hackathon.jobposting.dto.request.UpdateJobPostingRequest;
import com.cdg.hackathon.jobposting.dto.response.GetDetailJobPostingResponse;
import com.cdg.hackathon.jobposting.dto.response.GetJobPostingResponse;
import com.cdg.hackathon.jobposting.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/job-postings")
    public ResponseEntity<GetJobPostingResponse> getJobPostings(@RequestParam(value = "keyword", required = false) String keyword, int page, int size) {

        List<GetJobPostingResponse> getJobPostingResponses = jobPostingService.getJobPostings(keyword,page,size);
        return new ResponseEntity(getJobPostingResponses, HttpStatus.OK);
    }

    @GetMapping("/job-postings/{postId}")
    public ResponseEntity<GetDetailJobPostingResponse> getDetailJobPosting(@PathVariable("postId") Long postId) {

        GetDetailJobPostingResponse getDetailJobPostingResponse = jobPostingService.getJobPosting(postId);

        return new ResponseEntity(getDetailJobPostingResponse, HttpStatus.OK);
    }


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
