package com.company.springBootTemplate.repository;


import com.company.springBootTemplate.domain.DbAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DbAuthUserRepository extends JpaRepository<DbAuthUser, Long> {

    @Query(value = "select u from DbAuthUser as u where u.userName = :name")
    DbAuthUser findByUsername(@Param("name") String userName);

}

