package com.hg1.dsHydrocabure.services.params;

import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.params.UsersRoles;
import com.hg1.dsHydrocabure.repositories.params.UsersRolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsersRolesServices {

    private final UsersRolesRepository usersRolesRepository;

    public UsersRolesServices(UsersRolesRepository usersRolesRepository) {
        this.usersRolesRepository = usersRolesRepository;
    }

    public ResponseWrapper<UsersRoles> create(UsersRoles usR) {
        try {
            usersRolesRepository.saveAndFlush(usR);
            return ResponseWrapper.ok(usR);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<UsersRoles> update(Long id, UsersRoles usR) {
        try {
            return usersRolesRepository
                    .findById(id)
                    .map(oldUserRol -> updateWithOld(oldUserRol, usR))
                    .orElseGet(() -> ResponseWrapper.ko("Impossible de mettre a jour"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper updateWithOld(UsersRoles oldUserRol, UsersRoles usR) {
        try {

            usersRolesRepository.saveAndFlush(usR);
            return ResponseWrapper.ok(usR);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public List<UsersRoles> list() {
        try {
            return usersRolesRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }

    private Optional<UsersRoles> findById(Long id) {
        try {
            Optional<UsersRoles> o = Optional.ofNullable(id).flatMap(usersRolesRepository::findById);
            return o;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public UsersRoles findOne(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public ResponseWrapper delete(Long id) {
        try {
            Optional<UsersRoles> optional = usersRolesRepository.findById(id);
            if (!optional.isPresent()) {
                return ResponseWrapper.ok("Suppression realisee avec succes");
            }
            UsersRoles usR = optional.get();
            usersRolesRepository.delete(usR);
            return ResponseWrapper.ok(usR);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
}
