
    alter table permissions 
       drop 
       foreign key if exists FKp6c9fhqxlx8d8u920x1qfuyig;

    drop table if exists accounts;

    drop table if exists permissions;

    alter table permissions 
       drop 
       foreign key if exists FKp6c9fhqxlx8d8u920x1qfuyig;

    drop table if exists accounts;

    drop table if exists permissions;

    create table accounts (
       id integer not null auto_increment,
        email varchar(254) not null,
        email_validation varchar(16) not null,
        pwd_hash varchar(128) not null,
        status varchar(10) not null,
        token varchar(1024) not null,
        primary key (id)
    ) engine=InnoDB;

    create table permissions (
       account_id integer not null,
        permission varchar(255)
    ) engine=InnoDB;

    alter table accounts 
       add constraint UK_n7ihswpy07ci568w34q0oi8he unique (email);

    alter table permissions 
       add constraint FKp6c9fhqxlx8d8u920x1qfuyig 
       foreign key (account_id) 
       references accounts (id);
