package com.hg1.dsHydrocabure.repositories;

import com.hg1.dsHydrocabure.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
