package com.example.jdbcpostgresql;

public interface AlienContract {

    record AddAlienReq(
            String cnic,
            String name,
            Integer age,
            Character gender
    ) {}

    record UpdateAlienReq(
            String name,
            Integer age,
            Character gender
    ) {}

}
