package com.cigi.facturation.controller;

import com.cigi.facturation.entity.User;
import com.cigi.facturation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserRepository userRespository;
@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User userData){
       System.out.println(userData);
    User user=userRespository.findByUserId(userData.getUserId());
       if(user.getPassword().equals(userData.getPassword()))
           return  ResponseEntity.ok(user);
        return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }
}
