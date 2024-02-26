package com.cherryTest.backendpaycontrol.repositories;

import com.cherryTest.backendpaycontrol.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
