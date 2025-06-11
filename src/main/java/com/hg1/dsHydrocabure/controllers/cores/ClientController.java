package com.hg1.dsHydrocabure.controllers.cores;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.Clients;
import com.hg1.dsHydrocabure.services.cores.ClientServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("Clients")
@CrossOrigin
public class ClientController {
    private final ClientServices clientServices;

    public ClientController(ClientServices clientServices) {
        this.clientServices = clientServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Clients c) {
        ResponseWrapper<Clients> response = clientServices.create(c);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(c);
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Clients c) {
        ResponseWrapper<Clients> response = clientServices.update(id, c);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getClient(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clientServices.getClient(id));
    }

    @GetMapping
    public ResponseEntity liste() {
        return ResponseEntity.ok(clientServices.liste());
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseWrapper<Clients> reponse = clientServices.delete(id);
        if (reponse.isStatus()) {
            return ResponseEntity.ok().body(reponse.getEntite());
        } else {
            return ResponseEntity.badRequest().body(reponse.getMessage());
        }
    }
}
