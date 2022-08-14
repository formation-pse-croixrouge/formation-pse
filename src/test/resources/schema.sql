create sequence hibernate_sequence start 1 increment 1;

create table users
(
    id         int8         not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    nivol      varchar(255),
    password   varchar(255),
    primary key (id)
);

create table trainings
(
    id                             int8 not null,
    address_city                   varchar(255),
    address_label                  varchar(255),
    address_postal_code            int4,
    end_date                       date,
    start_date                     date,
    creator_id                     int8 not null references users,
    primary key (id)
);

create table trainings_attendees
(
    training_jpa_id      int8 not null references trainings,
    first_name           varchar(255),
    last_name            varchar(255),
    technical_assessment_evaluation json
);

create table trainings_users
(
    training_id int8 not null references trainings,
    user_id     int8 not null references users,
    primary key (training_id, user_id)
);