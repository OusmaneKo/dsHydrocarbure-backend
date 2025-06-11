package com.hg1.dsHydrocabure.controllers.cores;

import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.Factures;
import com.hg1.dsHydrocabure.services.cores.FacturesServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("Factures")
@CrossOrigin
public class FactureController {
    private FacturesServices facturesServices;

    public FactureController(FacturesServices facturesServices) {
        this.facturesServices = facturesServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Factures fact) {
        ResponseWrapper<Factures> response = facturesServices.create(fact);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity liste() {
        return ResponseEntity.ok(facturesServices.liste());
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Factures fact) {
        ResponseWrapper<Factures> response = facturesServices.update(id, fact);
        if (response.isStatus()) {
            return ResponseEntity.ok(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getEntite());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseWrapper<Factures> response = facturesServices.delete(id);
        if (response.isStatus()) {
            return ResponseEntity.ok(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findByid(@PathVariable("id") Long id) {
        return ResponseEntity.ok(facturesServices.getOne(id));
    }


}
