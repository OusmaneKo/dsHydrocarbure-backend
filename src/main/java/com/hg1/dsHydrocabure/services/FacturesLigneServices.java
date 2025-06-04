package com.hg1.dsHydrocabure.services;

import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.FacturesLignes;
import com.hg1.dsHydrocabure.repositories.FactureLigneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class FacturesLigneServices {
    private final FactureLigneRepository factureLigneRepository;

    public FacturesLigneServices(FactureLigneRepository factureLigneRepository) {
        this.factureLigneRepository = factureLigneRepository;
    }

    public ResponseWrapper<FacturesLignes> create(FacturesLignes fl) {
        try {
            factureLigneRepository.saveAndFlush(fl);
            return ResponseWrapper.ok(fl);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public List<FacturesLignes> list() {
        try {
            return factureLigneRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private Optional<FacturesLignes> findById(Long id) {
        try {
            Optional<FacturesLignes> op = Optional.ofNullable(id).flatMap(factureLigneRepository::findById);
            return op;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public FacturesLignes getOne(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    private ResponseWrapper<FacturesLignes> updateOld(FacturesLignes ols, FacturesLignes fac) {
        try {
            factureLigneRepository.saveAndFlush(fac);
            return ResponseWrapper.ok(fac);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<FacturesLignes> update(Long id, FacturesLignes fac) {
        try {

            return factureLigneRepository
                    .findById(id)
                    .map(oldFactureLigne -> updateOld(oldFactureLigne, fac))
                    .orElseGet(() -> ResponseWrapper.ko("mise a jour impossible"));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<FacturesLignes> opt = factureLigneRepository.findById(id);
            if (!opt.isPresent()) {
                return ResponseWrapper.ko("suppression reussie");

            }
            FacturesLignes fl = opt.get();
            factureLigneRepository.delete(fl);
            return ResponseWrapper.ok(fl);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
}
