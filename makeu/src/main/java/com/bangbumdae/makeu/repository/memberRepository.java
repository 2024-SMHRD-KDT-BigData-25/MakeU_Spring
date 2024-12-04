package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.Members;

@Repository
public interface memberRepository extends JpaRepository<Members, String> {

}
