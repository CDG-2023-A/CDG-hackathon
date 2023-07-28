DROP TABLE IF EXISTS apply_history;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS job_posting;
DROP TABLE IF EXISTS company;

create table company
(
    id      bigint      not null auto_increment
        primary key,
    name    varchar(20) not null,
    country varchar(10) not null,
    region  varchar(50) not null
);

create table job_posting
(
    id         bigint auto_increment
        primary key,
    position   varchar(100) not null,
    reward     int          not null,
    tech_stack varchar(20)  not null,
    content    text         not null,
    company_id bigint       not null,
    foreign key (company_id) references company (id)
);


create table users
(
    id   bigint auto_increment
        primary key,
    name varchar(10) null
);

create table apply_history
(
    id             bigint auto_increment
        primary key,
    user_id        bigint not null,
    job_posting_id bigint not null,
    foreign key (user_id) references users (id),
    foreign key (job_posting_id) references job_posting (id)
);