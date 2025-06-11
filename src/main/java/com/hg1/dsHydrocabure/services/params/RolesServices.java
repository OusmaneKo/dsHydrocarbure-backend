package com.hg1.dsHydrocabure.services.params;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.params.Roles;
import com.hg1.dsHydrocabure.repositories.cores.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RolesServices {
    private final RolesRepository rolesRepository;

    public RolesServices(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public ResponseWrapper<Roles> create(Roles r) {
        try {
            rolesRepository.saveAndFlush(r);
            return ResponseWrapper.ok(r);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper<Roles> updateOld(Roles oldRoles, Roles r) {
        try {
            rolesRepository.saveAndFlush(r);
            return ResponseWrapper.ok(r);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<Roles> update(Long id, Roles r) {
        try {
            return rolesRepository
                    .findById(id)
                    .map(oldRoles -> updateOld(oldRoles, r))
                    .orElseGet(() -> ResponseWrapper.ko("Mise a jour impossible"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private Optional<Roles> findById(Long id) {
        try {
            Optional<Roles> opti = Optional.ofNullable(id).flatMap(rolesRepository::findById);
            return opti;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public Roles getRoles(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Roles> list() {
        try {
            return rolesRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<Roles> opt = rolesRepository.findById(id);
            if (!opt.isPresent()) {
                return ResponseWrapper.ok("suppression reussie");
            }
            Roles r = opt.get();
            rolesRepository.delete(r);
            return ResponseWrapper.ok(r);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
}
