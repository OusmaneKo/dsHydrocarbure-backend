package com.hg1.dsHydrocabure.controllers.cores;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.FicheInterventions;
import com.hg1.dsHydrocabure.services.cores.FicheInterventionsServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("FicheInterventions")
@CrossOrigin
public class FicheInterventionsController {

    private FicheInterventionsServices ficheInterventionsServices;

    public FicheInterventionsController(FicheInterventionsServices ficheInterventionsServices) {
        this.ficheInterventionsServices = ficheInterventionsServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody FicheInterventions finter) {
        ResponseWrapper<FicheInterventions> response = ficheInterventionsServices.create(finter);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(finter);
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody FicheInterventions finter) {
        ResponseWrapper<FicheInterventions> response = ficheInterventionsServices.update(id, finter);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getFicheIntervention(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ficheInterventionsServices.getFicheInterventions(id));
    }

    @GetMapping
    public ResponseEntity liste() {
        return ResponseEntity.ok(ficheInterventionsServices.liste());
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseWrapper<FicheInterventions> reponse = ficheInterventionsServices.delete(id);
        if (reponse.isStatus()) {
            return ResponseEntity.ok().body(reponse.getEntite());
        } else {
            return ResponseEntity.badRequest().body(reponse.getMessage());
        }
    }
}
