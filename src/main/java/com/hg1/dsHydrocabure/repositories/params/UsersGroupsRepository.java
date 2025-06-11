package com.hg1.dsHydrocabure.repositories.params;

import com.hg1.dsHydrocabure.models.params.UsersGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersGroupsRepository extends JpaRepository<UsersGroups, Long> {
    Optional<UsersGroups> findByNom(String non);
}
