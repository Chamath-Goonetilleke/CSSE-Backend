package com.csse.backend.UserAndOrderManagement.repositories;

import com.csse.backend.UserAndOrderManagement.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
