package com.caua.api_post_it.repositories;

import com.caua.api_post_it.models.PostItModel;
import com.caua.api_post_it.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostItRepository extends JpaRepository<PostItModel,Long> {
    List<PostItModel> findByAuthor(UserModel author);
    List<PostItModel> findByTitle(String title);
}
