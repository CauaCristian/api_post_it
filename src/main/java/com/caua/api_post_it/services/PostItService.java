package com.caua.api_post_it.services;

import com.caua.api_post_it.dto.PostItDTO;
import com.caua.api_post_it.mappers.PostItMapper;
import com.caua.api_post_it.models.UserModel;
import com.caua.api_post_it.repositories.PostItRepository;
import com.caua.api_post_it.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PostItService {

    @Autowired
    PostItRepository postItRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostItMapper postItMapper;

    public List<PostItDTO> findAll(){
        return postItMapper.mapListModelToListDTO(postItRepository.findAll());
    }
    public PostItDTO findById(long id){
        return postItMapper.mapModelToDTO(postItRepository.findById(id).orElseThrow());
    }
    public List<PostItDTO> findByAuthor(Long authorId){
        UserModel author = userRepository.findById(authorId).orElse(null);
        if (author == null) {
            return Collections.emptyList();
        }
        return postItMapper.mapListModelToListDTO(postItRepository.findByAuthor(author));
    }
    public List<PostItDTO> findByTitle(String title){
        return postItMapper.mapListModelToListDTO(postItRepository.findByTitle(title));
    }
    public PostItDTO create(PostItDTO postItDTO){
        return postItMapper.mapModelToDTO(postItRepository.save(postItMapper.mapDTOToModel(postItDTO)));
    }
    public PostItDTO update(PostItDTO postItDTO){
        return postItMapper.mapModelToDTO(postItRepository.save(postItMapper.mapDTOToModel(postItDTO)));
    }
    public void delete(PostItDTO postItDTO){
        postItRepository.delete(postItMapper.mapDTOToModel(postItDTO));
    }

}
