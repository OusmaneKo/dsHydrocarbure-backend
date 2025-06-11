package com.hg1.dsHydrocabure.services.cores;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.Vehicule;
import com.hg1.dsHydrocabure.repositories.cores.VehiculeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VehiculeServices {
    private final VehiculeRepository vehiculeRepository;

    public VehiculeServices(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }


    public ResponseWrapper<Vehicule> create(Vehicule v){
        try {
            vehiculeRepository.saveAndFlush(v);
            return ResponseWrapper.ok(v);
        } catch (Exception e ){
            log.error(e.getMessage(), e );
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper<Vehicule> updateOld(Vehicule oldVehicule, Vehicule v){
        try {
            vehiculeRepository.saveAndFlush(v);
            return ResponseWrapper.ok(v);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<Vehicule> update(Long id, Vehicule v){
        try {
            return vehiculeRepository.findById(id).map(oldVehicule -> updateOld(oldVehicule, v)).orElseGet(()-> ResponseWrapper.ko(("Mise a jour impossible ")));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
    private Optional<Vehicule> findById(Long id){
            try {
                Optional<Vehicule> option= Optional.ofNullable(id).flatMap(vehiculeRepository::findById);
                return option;
            } catch (Exception e ){
                log.error(e.getMessage(), e);
                return Optional.empty();
            }
        }
    public Vehicule getVehicule(Long id){
            try {
                return findById(id).orElse(null);
            } catch (Exception e){
                log.error(e.getMessage(), e);
                return null;
        }
    }

    public List<Vehicule> liste(){
        try {
            return vehiculeRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e );
            return Collections.emptyList();
        }
    }

    public ResponseWrapper<Vehicule> delete(Long id){
        try {
            Optional<Vehicule> opt= vehiculeRepository.findById(id);
            if (!opt.isPresent()){
                return ResponseWrapper.ko("suppression réussie");
            }
            Vehicule v = opt.get();
            vehiculeRepository.delete(v);
            return ResponseWrapper.ok(v);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return  ResponseWrapper.ko(e.getMessage());
        }

        }

}
