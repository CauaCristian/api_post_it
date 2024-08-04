package com.caua.api_post_it.mappers;

import com.caua.api_post_it.dto.PostItDTO;
import com.caua.api_post_it.models.PostItModel;
import com.caua.api_post_it.models.UserModel;
import com.caua.api_post_it.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PostItMapper {

    @Autowired
    private UserRepository userRepository;

    public PostItDTO mapModelToDTO(PostItModel postItModel) {
        PostItDTO postItDTO = new PostItDTO();
        postItDTO.setId(postItModel.getId());
        postItDTO.setTitle(postItModel.getTitle());
        postItDTO.setDescription(postItModel.getDescription());
        postItDTO.setAuthor(postItModel.getAuthor().getId());
        return postItDTO;
    }

    public PostItModel mapDTOToModel(PostItDTO postItDTO) {
        PostItModel postItModel = new PostItModel();
        postItModel.setId(postItDTO.getId());
        postItModel.setTitle(postItDTO.getTitle());
        postItModel.setDescription(postItDTO.getDescription());
        Optional<UserModel> authorOptional = userRepository.findById(postItDTO.getAuthor());
        UserModel author = authorOptional.orElseThrow(() -> new RuntimeException("Author not found"));
        postItModel.setAuthor(author);

        return postItModel;
    }

    public List<PostItDTO> mapListModelToListDTO(List<PostItModel> listPostItModel) {
        List<PostItDTO> listPostItDTO = new ArrayList<>();
        for (PostItModel postItModel : listPostItModel) {
            listPostItDTO.add(mapModelToDTO(postItModel));
        }
        return listPostItDTO;
    }
}
