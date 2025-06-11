package com.hg1.dsHydrocabure.services.params;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.params.Users;
import com.hg1.dsHydrocabure.repositories.params.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersServices {
    private final UsersRepository usersRepository;

    public UsersServices(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public ResponseWrapper<Users> create(Users u) {
        try {
            usersRepository.saveAndFlush(u);
            return ResponseWrapper.ok(u);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper<Users> updateOld(Users oldUsr, Users u) {
        try {
            usersRepository.saveAndFlush(u);
            return ResponseWrapper.ok(u);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<Users> update(Long id, Users u) {
        try {
            return usersRepository
                    .findById(id)
                    .map(oldUsr -> updateOld(oldUsr, u))
                    .orElseGet(() -> ResponseWrapper.ko("Mise a jour impossible"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private Optional<Users> findById(Long id) {
        try {
            Optional<Users> opti = Optional.ofNullable(id).flatMap(usersRepository::findById);
            return opti;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public Users getUser(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Users> liste() {
        try {
            return usersRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<Users> opt = usersRepository.findById(id);
            if (!opt.isPresent()) {
                return ResponseWrapper.ok("suppression reussie");
            }
            Users u = opt.get();
            usersRepository.delete(u);
            return ResponseWrapper.ok(u);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

}
