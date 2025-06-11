package com.hg1.dsHydrocabure.controllers.cores;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.Vehicule;
import com.hg1.dsHydrocabure.services.cores.VehiculeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("Vehicules")
@CrossOrigin
public class VehiculeController {
    private VehiculeServices vehiculeServices;

    public VehiculeController(VehiculeServices vehiculeServices) {
        this.vehiculeServices = vehiculeServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Vehicule vh) {
        ResponseWrapper<Vehicule> response = vehiculeServices.create(vh);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(vh);
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Vehicule vh) {
        ResponseWrapper<Vehicule> response = vehiculeServices.update(id, vh);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getVehicule(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vehiculeServices.getVehicule(id));
    }

    @GetMapping
    public ResponseEntity liste() {
        return ResponseEntity.ok(vehiculeServices.liste());
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseWrapper<Vehicule> reponse = vehiculeServices.delete(id);
        if (reponse.isStatus()) {
            return ResponseEntity.ok().body(reponse.getEntite());
        } else {
            return ResponseEntity.badRequest().body(reponse.getMessage());
        }
    }
}
