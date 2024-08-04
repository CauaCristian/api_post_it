package com.caua.api_post_it.mappers;
import com.caua.api_post_it.dto.UserDTO;
import com.caua.api_post_it.models.UserModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class UserMapper {
    public UserModel mapDTOToModel(UserDTO userDTO){
        UserModel usermodel = new UserModel();
        usermodel.setId(userDTO.getId());
        usermodel.setUsername(userDTO.getUsername());
        usermodel.setPassword(userDTO.getPassword());
        return usermodel;
    }
    public UserDTO mapModelToDTO(UserModel userModel){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setUsername(userModel.getUsername());
        userDTO.setPassword(userModel.getPassword());
        return userDTO;
    }
    public List<UserDTO> mapListModelToListDTO(List<UserModel> listUserModel){
        List<UserDTO> listUserDTO = new ArrayList<>();
        for (int i = 0; i < listUserModel.size(); i++) {
            listUserDTO.add(mapModelToDTO(listUserModel.get(i)));
        }
        return listUserDTO;
    }
}
