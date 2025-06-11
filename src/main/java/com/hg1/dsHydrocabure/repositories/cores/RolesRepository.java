package com.hg1.dsHydrocabure.repositories.cores;

import com.hg1.dsHydrocabure.models.params.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
