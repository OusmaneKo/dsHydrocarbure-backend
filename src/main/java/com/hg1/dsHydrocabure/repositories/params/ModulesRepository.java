package com.hg1.dsHydrocabure.repositories.params;

import com.hg1.dsHydrocabure.models.params.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModulesRepository extends JpaRepository<Modules, String> {
}
