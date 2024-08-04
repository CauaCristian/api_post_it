package com.caua.api_post_it.services;

import com.caua.api_post_it.dto.ResponseLoginDTO;
import com.caua.api_post_it.dto.UserDTO;
import com.caua.api_post_it.mappers.UserMapper;
import com.caua.api_post_it.models.UserModel;
import com.caua.api_post_it.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenService tokenService;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    public ResponseEntity login(UserDTO userDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        UserModel userModel = (UserModel) auth.getPrincipal();
        String token = tokenService.generateToken(userModel);
        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO(false,"logged with sucess",token,userMapper.mapModelToDTO(userModel));
        return ResponseEntity.ok(responseLoginDTO);
    }
    public ResponseEntity register(UserDTO userDTO) {
        if(userRepository.findByUsername(userDTO.getUsername()) != null)return ResponseEntity.badRequest().build();
        var encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        UserModel userModel = new UserModel(userDTO.getUsername(), encryptedPassword);
        this.userRepository.save(userModel);
        String token = tokenService.generateToken(userModel);
        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO(false,"registered with sucess",token,userMapper.mapModelToDTO(userModel));
        return ResponseEntity.ok(responseLoginDTO);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
