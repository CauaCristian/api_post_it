package com.caua.api_post_it.services;

import com.caua.api_post_it.dto.UserDTO;
import com.caua.api_post_it.mappers.UserMapper;
import com.caua.api_post_it.models.UserModel;
import com.caua.api_post_it.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    public List<UserDTO> findAll(){
        List<UserModel> listUserModel = userRepository.findAll();
        return userMapper.mapListModelToListDTO(listUserModel);
    }
    public UserDTO findById(Long id) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return userMapper.mapModelToDTO(userModel);
    }
    public UserDTO update(UserDTO userDTO){
        var encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        UserModel userModel = userMapper.mapDTOToModel(userDTO);
        userModel.setPassword(encryptedPassword);
        return userMapper.mapModelToDTO(userRepository.save(userModel));
    }
    public void delete(UserDTO userDTO){
        userRepository.delete(userMapper.mapDTOToModel(userDTO));
    }
}
