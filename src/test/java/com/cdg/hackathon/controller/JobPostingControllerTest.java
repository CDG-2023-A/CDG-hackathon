package com.cdg.hackathon.controller;

import com.cdg.hackathon.dto.request.CreateJobPostingRequest;
import com.cdg.hackathon.service.JobPostingData;
import com.cdg.hackathon.service.JobPostingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = JobPostingController.class) // controller 테스트 환경을 만들어주는 녀석
class JobPostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobPostingService jobPostingService;

    @Autowired
    private ObjectMapper objectMapper;// 자바 객체 -> JSON // JSON -> 자바 객체

    @DisplayName("채용공고와 회사이름을 조회한다.")
    @Test
    void getJobPostings() throws Exception {
        // given
        List<JobPostingData> result = List.of();

        Mockito.when(jobPostingService.getJobPostings(null)).thenReturn(result);

        // when // then
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/job-postings")
        )
                .andDo(MockMvcResultHandlers.print())// 요청에 대한 로그를 더 자세하게 확인 가능
                .andExpect(MockMvcResultMatchers.status().isOk()) //Status 상태코드 검증
                .andExpect(jsonPath("$.createJobPostingData").isArray());


    }

    @DisplayName("신규 채용공고를 등록할 때 상품 판매상태는 필수값이다.")
    @Test
    void createProductWithOutSellingStatus() throws Exception {
        // given
        CreateJobPostingRequest request = CreateJobPostingRequest.builder()
                .companyId(1L)
//                .techStack("Python")
                .reward(1000000)
                .content("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .position("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .build();

        // when // then
        // PostMapping의 경우 바디에 데이터를 넣어서 보내는데 이경우 JSON 형태로 보내므로 request 객체를 직렬화/역직렬화를 도와주는 ObjectMapper가 필요함.
        mockMvc.perform(
                        post("/api/v1/job-postings")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print()) // 요청에 대한 로그를 더 자세하게 확인 가능
                .andExpect(status().isBadRequest()); //Status 상태코드 검증
//                .andExpect(jsonPath("$.message").value("techStack은 필수 값 입니다."));

    }

}