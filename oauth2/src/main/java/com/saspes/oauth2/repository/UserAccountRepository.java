package com.saspes.oauth2.repository;

import com.saspes.oauth2.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    @Query("SELECT u FROM UserAccount u WHERE LOWER(u.username) = LOWER(:username)")
    UserAccount findByUsernameCaseInsensitive(@Param("username") String username);
    
}
