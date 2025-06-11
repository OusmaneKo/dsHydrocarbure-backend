package com.hg1.dsHydrocabure.services.cores;

import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.FicheInterventions;
import com.hg1.dsHydrocabure.repositories.cores.FicheInterventionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FicheInterventionsServices {
    private final FicheInterventionsRepository ficheInterventionsRepository;

    public FicheInterventionsServices(FicheInterventionsRepository ficheInterventionsRepository) {
        this.ficheInterventionsRepository = ficheInterventionsRepository;
    }

    public ResponseWrapper<FicheInterventions> create(FicheInterventions fint){
        try {
            ficheInterventionsRepository.saveAndFlush(fint);
            return ResponseWrapper.ok(fint);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private Optional<FicheInterventions> findById(Long id){
        try {
            Optional opt = Optional.ofNullable(id).flatMap(ficheInterventionsRepository::findById);
            return opt;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public ResponseWrapper<FicheInterventions> updateOld(FicheInterventions oldFint, FicheInterventions fint){
        try {
            ficheInterventionsRepository.saveAndFlush(fint);
            return ResponseWrapper.ok(fint);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<FicheInterventions> update(Long id, FicheInterventions fint){
        try {
            return ficheInterventionsRepository.findById(id).map(oldFint-> updateOld(oldFint, fint)).orElseGet(()-> ResponseWrapper.ko("Mise a jour impossible "));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public FicheInterventions getFicheInterventions(Long id){
        try {
            return findById(id).orElse(null);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<FicheInterventions> liste(){
        try {
            return ficheInterventionsRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e );
            return Collections.emptyList();
        }
    }

    public ResponseWrapper<FicheInterventions> delete(Long id){
        try {
            Optional<FicheInterventions> opt= ficheInterventionsRepository.findById(id);
            if (!opt.isPresent()){
                return ResponseWrapper.ko("suppression réussie");
            }
            FicheInterventions fint = opt.get();
            ficheInterventionsRepository.delete(fint);
            return ResponseWrapper.ok(fint);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return  ResponseWrapper.ko(e.getMessage());
        }

    }



}
