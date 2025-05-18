package com.example.jdbcpostgresql;

public interface AlienContract {

    record AddAlienReq(
            String cnic,
            String name,
            Integer age
    ) {}

    record UpdateAlienReq(
            String name,
            Integer age
    ) {}

    record AlienResp(
            Integer id,
            String cnic,
            String name,
            Integer age
    ) {}

}
