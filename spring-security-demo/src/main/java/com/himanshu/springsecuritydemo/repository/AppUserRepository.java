package com.himanshu.springsecuritydemo.repository;

import com.himanshu.springsecuritydemo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    @Query("""
            SELECT u FROM AppUser u WHERE u.username = :username
            """)
    AppUser findAppUserByUsername(String username);
}
