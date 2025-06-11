package com.hg1.dsHydrocabure.repositories.params;

import com.hg1.dsHydrocabure.models.params.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
}
