
    alter table if exists ingredients 
       drop constraint if exists FKa2kaxeb4oipu1lhwavshjxikk;

    alter table if exists steps 
       drop constraint if exists FKk46rxjhfbf9q0ca7pylxuy9ad;

    drop table if exists ingredients cascade;

    drop table if exists keys cascade;

    drop table if exists Recipes cascade;

    drop table if exists steps cascade;

    drop table if exists users cascade;
