package com.bangbumdae.makeu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bangbumdae.makeu.model.ViewEyesCreator;

public interface ViewEyesCreatorRepository extends JpaRepository<ViewEyesCreator, Integer>{
    List<ViewEyesCreator> findByCreatorgender(String creatorgender);
}
