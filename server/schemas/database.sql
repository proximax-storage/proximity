
    alter table accounts_contract_assignments 
       drop 
       foreign key if exists FKc57o3bi2fxb20t41ksp2ki2sw;

    alter table accounts_contract_assignments 
       drop 
       foreign key if exists FKr751xb71ohucfyau4pe2p47y2;

    drop table if exists accounts;

    drop table if exists accounts_contract_assignments;

    drop table if exists contract_assignments;

    alter table accounts_contract_assignments 
       drop 
       foreign key if exists FKc57o3bi2fxb20t41ksp2ki2sw;

    alter table accounts_contract_assignments 
       drop 
       foreign key if exists FKr751xb71ohucfyau4pe2p47y2;

    drop table if exists accounts;

    drop table if exists accounts_contract_assignments;

    drop table if exists contract_assignments;

    create table accounts (
       id integer not null auto_increment,
        email varchar(254) not null,
        email_validation varchar(16) not null,
        pwd_hash varchar(128) not null,
        status varchar(10) not null,
        token varchar(1024) not null,
        primary key (id)
    ) engine=InnoDB;

    create table accounts_contract_assignments (
       Account_id integer not null,
        contracts_id integer not null
    ) engine=InnoDB;

    create table contract_assignments (
       id integer not null auto_increment,
        cid varchar(64) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table accounts 
       add constraint UK_n7ihswpy07ci568w34q0oi8he unique (email);

    alter table accounts_contract_assignments 
       add constraint UK_cq3ohmpcjd361j3j2udi9xdrl unique (contracts_id);

    alter table accounts_contract_assignments 
       add constraint FKc57o3bi2fxb20t41ksp2ki2sw 
       foreign key (contracts_id) 
       references contract_assignments (id);

    alter table accounts_contract_assignments 
       add constraint FKr751xb71ohucfyau4pe2p47y2 
       foreign key (Account_id) 
       references accounts (id);
