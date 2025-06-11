package com.hg1.dsHydrocabure.services.cores;

import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.Clients;
import com.hg1.dsHydrocabure.repositories.cores.ClientRepository;
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

    public ResponseWrapper<Clients> create(Clients c) {
        try {
            clientRepository.saveAndFlush(c);
            return ResponseWrapper.ok(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper<Clients> updateOld(Clients old, Clients c) {
        try {
            clientRepository.saveAndFlush(c);
            return ResponseWrapper.ok(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<Clients> update(Long id, Clients c) {
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

    private Optional<Clients> findById(Long id) {
        try {
            Optional<Clients> opti = Optional.ofNullable(id).flatMap(clientRepository::findById);
            return opti;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public Clients getClient(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Clients> liste() {
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<Clients> opt = clientRepository.findById(id);
            if (!opt.isPresent()) {
                return ResponseWrapper.ok("suppression reussie");
            }
            Clients c = opt.get();
            clientRepository.delete(c);
            return ResponseWrapper.ok(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
}
