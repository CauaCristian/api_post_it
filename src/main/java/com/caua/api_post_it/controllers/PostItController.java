package com.caua.api_post_it.controllers;

import com.caua.api_post_it.dto.PostItDTO;
import com.caua.api_post_it.services.PostItService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/postIt")
public class PostItController {
    @Autowired
    PostItService postItService;
    @GetMapping(value = "/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostItDTO> findAll(){
        return postItService.findAll();
    }
    @GetMapping(value="/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public PostItDTO findById(@PathVariable Long id){
        return postItService.findById(id);
    }
    @GetMapping(value = "/findByTitle/{title}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostItDTO> findByTitle(@PathVariable String title){
        return postItService.findByTitle(title);
    }
    @GetMapping(value = "/findByAuthorId/{authorId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostItDTO> findByAuthorId(@PathVariable Long authorId){
        return postItService.findByAuthor(authorId);
    }
    @PostMapping(value="/create",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostItDTO create(@RequestBody PostItDTO postItDTO){
        return postItService.create(postItDTO);
    }
    @PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostItDTO update(@RequestBody PostItDTO postItDTO){
        return postItService.update(postItDTO);
    }
    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody PostItDTO postItDTO){
        postItService.delete(postItDTO);
    }
}
