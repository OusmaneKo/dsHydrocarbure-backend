package com.hg1.dsHydrocabure.services.params;

import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.params.UsersGroupsModules;
import com.hg1.dsHydrocabure.repositories.params.UsersGroupsModulesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UsersGroupsModulesServices {
    private final UsersGroupsModulesRepository usersGroupsModulesRepository;

    public UsersGroupsModulesServices(UsersGroupsModulesRepository usersGroupsModulesRepository) {
        this.usersGroupsModulesRepository = usersGroupsModulesRepository;
    }


    public ResponseWrapper<UsersGroupsModules> create(UsersGroupsModules ugm) {
        try {
            usersGroupsModulesRepository.saveAndFlush(ugm);
            return ResponseWrapper.ok(ugm);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<UsersGroupsModules> optional = usersGroupsModulesRepository.findById(id);
            if (!optional.isPresent()) {
                return ResponseWrapper.ok("Suppression realisee avec succes");
            }
            UsersGroupsModules um = optional.get();
            usersGroupsModulesRepository.delete(um);
            return ResponseWrapper.ok(um);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
}
