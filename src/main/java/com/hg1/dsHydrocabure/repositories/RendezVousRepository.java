package com.hg1.dsHydrocabure.repositories;

import com.hg1.dsHydrocabure.models.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
}
