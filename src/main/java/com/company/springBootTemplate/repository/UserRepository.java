package com.company.springBootTemplate.repository;

import com.company.springBootTemplate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {

}
