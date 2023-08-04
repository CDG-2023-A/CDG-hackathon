# API 스펙 (공통 api 엔드포인트 : /api/v1)
## 채용 공고 등록
* 메서드 타입 : POST
* api 엔드포인트 : /api/v1/job-postings
* request : 
```json
{
  "company_id": 회사_id,
  "employ_position":"백엔드 주니어 개발자",
  "reward":1000000,
  "content":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "tech_stack":"Python"
}
```

* response 201

## 채용 공고 수정
* 메서드 타입 : PATCH
* api 엔드포인트 : /api/v1/job-postings/{postId}
* request :
```json
{
  "employ_position":"백엔드 주니어 개발자",
  "reward":1000000,
  "content":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "tech_stack":"Django"
}
```
* response: 200

## 채용 공고 삭제
* 메서드 타입 : DELETE
* api 엔드포인트 : /api/v1/job-postings/{postId}
* request
* response 204

## 채용 공고 목록 조회 + 검색
* 메서드 타입 : GET
* api 엔드포인트 : /api/v1/job-postings?search={keyword}
* request
* response
```json
[
	{
      "채용공고_id": 채용공고_id,
	  "회사명":"원티드랩",
	  "국가":"한국",
	  "지역":"서울",
	  "채용포지션":"백엔드 주니어 개발자",
	  "채용보상금":1500000,
	  "사용기술":"Python"
	},
	{
      "채용공고_id": 채용공고_id,
	  "회사명":"네이버",
	  "국가":"한국",
	  "지역":"판교",
	  "채용포지션":"Django 백엔드 개발자",
	  "채용보상금":1000000,
	  "사용기술":"Django"
	},
  ...
]
```

## 채용 상세 페이지 조회
* 메서드 타입 : GET
* api 엔드포인트 : /api/v1/job-postings/{postId}
* response
```json
{
  "채용공고_id": 채용공고_id,
  "회사명":"원티드랩",
  "국가":"한국",
  "지역":"서울",
  "채용포지션":"백엔드 주니어 개발자",
  "채용보상금":1500000,
  "사용기술":"Python",
  "채용내용": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "회사가올린다른채용공고":[채용공고_id, 채용공고_id, ..] # id List (선택사항 및 가산점요소).
}
```

## 채용 공고 지원
* 메서드 타입 : POST
* api 엔드포인트 : /api/v1/job-postings/apply
* request
```json
{
  "채용공고_id": 채용공고_id,
  "사용자_id": 사용자_id
};
```
* response 200

## 역할 분배
- [ ] 상세 조회 -> 민엽
- [ ] 수정 -> 동우
- [ ] 삭제 -> 동우
- [ ] 리스트 조회, 검색 -> 종현
- [ ] 지원 -> 민엽