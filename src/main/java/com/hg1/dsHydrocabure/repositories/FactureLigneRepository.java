package com.hg1.dsHydrocabure.repositories;

import com.hg1.dsHydrocabure.models.FacturesLignes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FactureLigneRepository extends JpaRepository<FacturesLignes, Long> {
}
