package com.saspes.admin.repository;

import com.saspes.admin.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    
}
