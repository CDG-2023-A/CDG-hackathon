INSERT INTO company (id, created_date, modified_date, country , name, region)
VALUES (100, now(), now(), '한국' , '원티드랩' , '강남');
INSERT INTO company (id, created_date, modified_date, country , name, region)
VALUES (200, now(), now(), '한국' , '인프런' , '판교');

INSERT INTO job_posting (id, created_date, modified_date, company_id, content, position, reward, tech_stack)
VALUES (1321, now(), now(), 100, '원티드랩에서 백엔드 주니어 개발자를 적극 채용합니다. 자격요건은..' , '백엔드 주니어 개발자' , 1500000 ,'SpringBoot');
INSERT INTO job_posting (id, created_date, modified_date, company_id, content, position, reward, tech_stack)
VALUES (2000, now(), now(), 200, '인프런에서 백엔드 시니어 개발자를 적극 채용합니다. 자격요건은..' , '백엔드 시니어 개발자' , 2000000 ,'SpringBoot');
INSERT INTO job_posting (id, created_date, modified_date, company_id, content, position, reward, tech_stack)
VALUES (1032, now(), now(), 100, '원티드랩에서 프론트엔드 시니어 개발자를 적극 채용합니다. 자격요건은..' , '프론트엔드 시니어 개발자' , 2000000 ,'React');

