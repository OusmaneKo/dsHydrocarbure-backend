package com.hg1.dsHydrocabure.controllers.params;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.params.Roles;
import com.hg1.dsHydrocabure.services.params.RolesServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("RolesController")
@CrossOrigin
public class RolesController {
    private final RolesServices rolesServices;

    public RolesController(RolesServices rolesServices) {
        this.rolesServices = rolesServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Roles r) {
        ResponseWrapper<Roles> response = rolesServices.create(r);
        if (response.isStatus()) {
            return ResponseEntity.ok(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Roles r) {
        ResponseWrapper<Roles> response = rolesServices.update(id, r);

        if (response.isStatus()) {
            return ResponseEntity.ok(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseWrapper<Roles> response = rolesServices.delete(id);
        if (response.isStatus()) {
            return ResponseEntity.ok(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        return ResponseEntity.ok(rolesServices.getRoles(id));
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(rolesServices.list());
    }

}
