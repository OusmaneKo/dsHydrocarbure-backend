package com.hg1.dsHydrocabure.controllers.params;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.params.Users;
import com.hg1.dsHydrocabure.services.params.UsersServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("Users")
@CrossOrigin
public class UsersController {
    private UsersServices usersServices;

    public UsersController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Users us) {
        ResponseWrapper<Users> response = usersServices.create(us);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(us);
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Users us) {
        ResponseWrapper<Users> response = usersServices.update(id, us);
        if (response.isStatus()) {
            return ResponseEntity.ok().body(response.getEntite());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getUsers(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usersServices.getUser(id));
    }

    @GetMapping
    public ResponseEntity liste() {
        return ResponseEntity.ok(usersServices.liste());
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseWrapper<Users> reponse = usersServices.delete(id);
        if (reponse.isStatus()) {
            return ResponseEntity.ok().body(reponse.getEntite());
        } else {
            return ResponseEntity.badRequest().body(reponse.getMessage());
        }
    }
}
