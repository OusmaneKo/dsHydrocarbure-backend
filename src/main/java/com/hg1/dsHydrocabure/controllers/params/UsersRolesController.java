package com.hg1.dsHydrocabure.controllers.params;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.params.UsersRoles;
import com.hg1.dsHydrocabure.services.params.UsersRolesServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("UsersRolesServices")
@CrossOrigin
public class UsersRolesController {
    private final UsersRolesServices usersRolesServices;

    public UsersRolesController(UsersRolesServices usersRolesServices) {
        this.usersRolesServices = usersRolesServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UsersRoles usR) {
        ResponseWrapper<UsersRoles> response = usersRolesServices.create(usR);
        if (response.isStatus()) {
            return ResponseEntity.ok(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UsersRoles usRo) {
        ResponseWrapper<UsersRoles> response = usersRolesServices.update(id, usRo);

        if (response.isStatus()) {
            return ResponseEntity.ok(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseWrapper<UsersRoles> response = usersRolesServices.delete(id);
        if (response.isStatus()) {
            return ResponseEntity.ok(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        return ResponseEntity.ok(usersRolesServices.findOne(id));
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(usersRolesServices.list());
    }

}
