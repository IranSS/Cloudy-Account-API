package project.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.Model.User;
import project.service.UserService;
import project.service.impl.UserServiceImpl;


import java.net.URI;

@RequestMapping(value = "/user")
@RestController
public class userController {

    private final UserService userService;

    public userController(UserServiceImpl userService) {this.userService = userService;}

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        var user =  userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate){
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return  ResponseEntity.ok(userToCreate);
    }
}
