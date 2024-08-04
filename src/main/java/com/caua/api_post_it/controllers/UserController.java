package com.caua.api_post_it.controllers;
import com.caua.api_post_it.dto.UserDTO;
import com.caua.api_post_it.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping(value = "/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> findAll(){
        return userService.findAll();
    }
    @GetMapping(value = "/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO findById(@PathVariable Long id){
        return userService.findById(id);
    }
    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO update(@RequestBody UserDTO userDTO){
        return userService.update(userDTO);
    }
    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody UserDTO userDTO){
        userService.delete(userDTO);
    }
}
