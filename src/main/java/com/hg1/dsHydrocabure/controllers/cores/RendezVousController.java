package com.hg1.dsHydrocabure.controllers.cores;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.RendezVous;
import com.hg1.dsHydrocabure.services.cores.RendezVousServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("RendezVous")
@CrossOrigin
public class RendezVousController {

    private RendezVousServices rendezVousServices;

    public RendezVousController(RendezVousServices rendezVousServices) {
        this.rendezVousServices = rendezVousServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody RendezVous rdv) {
        ResponseWrapper<RendezVous> response = rendezVousServices.create(rdv);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(rdv);
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody RendezVous rdv) {
        ResponseWrapper<RendezVous> response = rendezVousServices.update(id, rdv);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getRendezVous(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rendezVousServices.getRendezVous(id));
    }

    @GetMapping
    public ResponseEntity liste() {
        return ResponseEntity.ok(rendezVousServices.liste());
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseWrapper<RendezVous> reponse = rendezVousServices.delete(id);
        if (reponse.isStatus()) {
            return ResponseEntity.ok().body(reponse.getEntite());
        } else {
            return ResponseEntity.badRequest().body(reponse.getMessage());
        }
    }
}
