package com.hg1.dsHydrocabure.controllers.cores;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.FacturesLignes;
import com.hg1.dsHydrocabure.services.cores.FacturesLignesServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("FactureLignes")
@CrossOrigin
public class FactureLigneController {

    private FacturesLignesServices facturesLignesServices;

    public FactureLigneController(FacturesLignesServices facturesLignesServices) {
        this.facturesLignesServices = facturesLignesServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody FacturesLignes fl) {
        ResponseWrapper<FacturesLignes> response = facturesLignesServices.create(fl);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(facturesLignesServices.getOne(id));
    }

    @GetMapping
    public ResponseEntity liste() {
        return ResponseEntity.ok().body(facturesLignesServices.list());
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody FacturesLignes fl) {
        ResponseWrapper<FacturesLignes> response = facturesLignesServices.update(id, fl);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseWrapper<FacturesLignes> response = facturesLignesServices.delete(id);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }
}
