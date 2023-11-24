package com.chslcompany.picpaysimplificado.repositories;

import com.chslcompany.picpaysimplificado.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByDocument(String document);
    Optional<Users> findUserById(Long id);
}
