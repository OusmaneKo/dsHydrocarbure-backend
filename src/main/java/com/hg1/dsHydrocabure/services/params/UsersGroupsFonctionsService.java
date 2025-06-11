package com.hg1.dsHydrocabure.services.params;

import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.params.UsersGroupsFonctions;
import com.hg1.dsHydrocabure.repositories.params.UsersGroupsFonctionsRepostory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UsersGroupsFonctionsService {
    private final UsersGroupsFonctionsRepostory usersGroupsFonctionsRepostory;

    public UsersGroupsFonctionsService(UsersGroupsFonctionsRepostory usersGroupsFonctionsRepostory) {
        this.usersGroupsFonctionsRepostory = usersGroupsFonctionsRepostory;
    }

    public ResponseWrapper<UsersGroupsFonctions> create(UsersGroupsFonctions usg) {
        try {
            usersGroupsFonctionsRepostory.saveAndFlush(usg);
            return ResponseWrapper.ok(usg);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<UsersGroupsFonctions> op = usersGroupsFonctionsRepostory.findById(id);
            if (!op.isPresent()) {
                return ResponseWrapper.ok("Suppression Reussie");
            }
            UsersGroupsFonctions ugrpf = op.get();
            usersGroupsFonctionsRepostory.delete(ugrpf);
            return ResponseWrapper.ok(ugrpf);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
}
