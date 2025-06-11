package com.hg1.dsHydrocabure.repositories.cores;

import com.hg1.dsHydrocabure.models.cores.Pieces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiecesRepository extends JpaRepository<Pieces, Long> {
}
