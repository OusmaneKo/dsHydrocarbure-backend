package com.hg1.dsHydrocabure.services.cores;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.Factures;
import com.hg1.dsHydrocabure.repositories.cores.FactureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FacturesServices {
    private final FactureRepository factureRepository;

    public FacturesServices(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    public ResponseWrapper<Factures> create(Factures fac){
        try {
            factureRepository.saveAndFlush(fac);
            return ResponseWrapper.ok(fac);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private Optional<Factures> findById(Long id){
        try {
            Optional opt= Optional.ofNullable(id).map(factureRepository::findById);
            return opt;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return Optional.empty();
         }
    }

    public ResponseWrapper<Factures> updateOld(Factures old, Factures fa){
        try {
            factureRepository.saveAndFlush(fa);
            return ResponseWrapper.ok(fa);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<Factures> update(Long id, Factures fac){
        try {
            return factureRepository.findById(id).map(facOld-> updateOld(facOld, fac)).orElseGet(()-> ResponseWrapper.ko("Mise a jour Impossible"));
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public Factures getOne(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Factures> liste() {
        try {
            return factureRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }


    public ResponseWrapper delete(Long id) {
        try {
            Optional<Factures> foundFacture = factureRepository.findById(id);
            if (!foundFacture.isPresent()) {
                return ResponseWrapper.ok("supression realisé");
            }
            Factures fa = foundFacture.get();
            factureRepository.delete(fa);
            return ResponseWrapper.ok(fa);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }


}
