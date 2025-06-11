package com.hg1.dsHydrocabure.controllers.cores;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.Pieces;
import com.hg1.dsHydrocabure.services.cores.PiecesServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("Pieces")
@CrossOrigin
public class PiecesController {
    private PiecesServices piecesServices;

    public PiecesController(PiecesServices piecesServices) {
        this.piecesServices = piecesServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Pieces pcs) {
        ResponseWrapper<Pieces> response = piecesServices.create(pcs);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(pcs);
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Pieces pcs) {
        ResponseWrapper<Pieces> response = piecesServices.update(id, pcs);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getPiece(@PathVariable("id") Long id) {
        return ResponseEntity.ok(piecesServices.getPiece(id));
    }

    @GetMapping
    public ResponseEntity liste() {
        return ResponseEntity.ok(piecesServices.liste());
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseWrapper<Pieces> reponse = piecesServices.delete(id);
        if (reponse.isStatus()) {
            return ResponseEntity.ok().body(reponse.getEntite());
        } else {
            return ResponseEntity.badRequest().body(reponse.getMessage());
        }
    }


}
