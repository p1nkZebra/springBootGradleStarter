package com.company.springBootTemplate.repository;


import com.company.springBootTemplate.domain.GoogleAccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthUserRepository extends JpaRepository<GoogleAccountUser, String> {

}

