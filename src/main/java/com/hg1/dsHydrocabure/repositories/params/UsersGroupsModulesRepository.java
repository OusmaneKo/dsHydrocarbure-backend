package com.hg1.dsHydrocabure.repositories.params;

import com.hg1.dsHydrocabure.models.params.UsersGroupsModules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersGroupsModulesRepository extends JpaRepository<UsersGroupsModules, Long> {
}
