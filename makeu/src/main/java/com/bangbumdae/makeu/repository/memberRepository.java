package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.Members;
import java.util.List;


@Repository
public interface memberRepository extends JpaRepository<Members, String> {
    List<Members> findByMemIdAndMemPw(String mem_id, String mem_pw);
}
