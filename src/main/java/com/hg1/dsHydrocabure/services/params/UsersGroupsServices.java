package com.hg1.dsHydrocabure.services.params;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.params.UsersGroups;
import com.hg1.dsHydrocabure.repositories.params.UsersGroupsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsersGroupsServices {
    private final UsersGroupsRepository usersGroupsRepository;

    public UsersGroupsServices(UsersGroupsRepository usersGroupsRepository) {
        this.usersGroupsRepository = usersGroupsRepository;
    }

    public List<UsersGroups> list() {
        try {
            return usersGroupsRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public ResponseWrapper<UsersGroups> create(UsersGroups usg) {
        try {
            usersGroupsRepository.saveAndFlush(usg);
            return ResponseWrapper.ok(usg);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<UsersGroups> update(Long id, UsersGroups usg) {
        try {
            return usersGroupsRepository
                    .findById(id)
                    .map(oldUsG -> updateWithOld(oldUsG, usg))
                    .orElseGet(() -> ResponseWrapper.ko("Mise a jour Impossible"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper<UsersGroups> updateWithOld(UsersGroups oldUsG, UsersGroups usg) {
        try {
            usersGroupsRepository.saveAndFlush(usg);
            return ResponseWrapper.ok(usg);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private Optional<UsersGroups> findById(Long id) {
        try {
            Optional<UsersGroups> op = Optional.ofNullable(id).flatMap(usersGroupsRepository::findById);
            return op;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public UsersGroups getOne(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public ResponseWrapper delete(Long id) {
        try {
            Optional<UsersGroups> op = usersGroupsRepository.findById(id);
            if (!op.isPresent()) {
                return ResponseWrapper.ok("Suppression Reussie");
            }
            UsersGroups oldUsG = op.get();
            usersGroupsRepository.delete(oldUsG);
            return ResponseWrapper.ok(oldUsG);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
}
