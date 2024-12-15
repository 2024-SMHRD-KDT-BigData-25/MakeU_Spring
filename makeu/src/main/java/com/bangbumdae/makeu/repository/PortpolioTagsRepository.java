package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.PortpolioTags;

@Repository
public interface PortpolioTagsRepository extends JpaRepository<PortpolioTags, Integer>  {

}
