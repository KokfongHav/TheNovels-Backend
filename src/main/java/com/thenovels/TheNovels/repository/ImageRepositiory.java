package com.thenovels.TheNovels.repository;

import com.thenovels.TheNovels.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepositiory extends JpaRepository<Image, Long> {

}
