package com.hg1.dsHydrocabure.repositories.cores;

import com.hg1.dsHydrocabure.models.cores.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
}
