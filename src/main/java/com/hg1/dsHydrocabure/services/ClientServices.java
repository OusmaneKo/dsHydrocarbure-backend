package com.hg1.dsHydrocabure.services;

import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.Client;
import com.hg1.dsHydrocabure.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;



@Slf4j
@Service
public class ClientServices {

    private final ClientRepository clientRepository;

    public ClientServices(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseWrapper<Client> create(Client c) {
        try {
            clientRepository.saveAndFlush(c);
            return ResponseWrapper.ok(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper<Client> updateOld(Client old, Client c) {
        try {
            clientRepository.saveAndFlush(c);
            return ResponseWrapper.ok(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<Client> update(Long id, Client c) {
        try {
            return clientRepository
                    .findById(id)
                    .map(oldClient -> updateOld(oldClient, c))
                    .orElseGet(() -> ResponseWrapper.ko("Mise a jour impossible"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private Optional<Client> findById(Long id) {
        try {
            Optional<Client> opti = Optional.ofNullable(id).flatMap(clientRepository::findById);
            return opti;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public Client getClient(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Client> liste() {
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<Client> opt = clientRepository.findById(id);
            if (!opt.isPresent()) {
                return ResponseWrapper.ok("suppression reussie");
            }
            Client c = opt.get();
            clientRepository.delete(c);
            return ResponseWrapper.ok(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
}
