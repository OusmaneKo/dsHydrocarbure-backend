package com.hg1.dsHydrocabure.services;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.RendezVous;
import com.hg1.dsHydrocabure.models.Vehicule;
import com.hg1.dsHydrocabure.repositories.RendezVousRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RendezVousServices {
    private RendezVousRepository rendezVousRepository;

    public RendezVousServices(RendezVousRepository rendezVousRepository) {
        this.rendezVousRepository = rendezVousRepository;
    }

    public ResponseWrapper<RendezVous> create(RendezVous rdv){
        try {
            rendezVousRepository.saveAndFlush(rdv);
            return ResponseWrapper.ok(rdv);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private Optional<RendezVous> findById(Long id){
        try {
            Optional opt = Optional.ofNullable(id).flatMap(rendezVousRepository::findById);
            return opt;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public ResponseWrapper<RendezVous> updateOld(RendezVous oldrdv, RendezVous rdv){
        try {
            rendezVousRepository.saveAndFlush(rdv);
            return ResponseWrapper.ok(rdv);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<RendezVous> update(Long id, RendezVous rdv){
        try {
            return rendezVousRepository.findById(id).map(oldRdv-> updateOld(oldRdv, rdv)).orElseGet(()-> ResponseWrapper.ko("Mise a jour impossible "));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public Vehicule getRendezVous(Long id){
        try {
            return findById(id).orElse(null);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Vehicule> liste(){
        try {
            return rendezVousRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e );
            return Collections.emptyList();
        }
    }

    public ResponseWrapper<RendezVous> delete(Long id){
        try {
            Optional<RendezVous> opt= rendezVousRepository.findById(id);
            if (!opt.isPresent()){
                return ResponseWrapper.ko("suppression réussie");
            }
            RendezVous rdv = opt.get();
            rendezVousRepository.delete(rdv);
            return ResponseWrapper.ok(rdv);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return  ResponseWrapper.ko(e.getMessage());
        }

    }



}
