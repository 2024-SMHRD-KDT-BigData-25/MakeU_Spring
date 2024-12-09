package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.FaceType;
import com.bangbumdae.makeu.model.Members;
import com.bangbumdae.makeu.model.PersonalColor;

import java.util.List;


@Repository
public interface memberRepository extends JpaRepository<Members, String> {
    List<Members> findByMemidAndMempw(String memid, String mempw);
    @Query("SELECT f FROM Members m JOIN FaceType f on m.facetypeidx = f.facetypeidx WHERE m.memid = :memid")
    FaceType findFacetypeByMemid(String memid);
    @Query("SELECT p FROM Members m JOIN PersonalColor p on m.personalcoloridx = p.personalcoloridx WHERE m.memid = :memid")
    PersonalColor findPersonalColorByMemid(String memid);
}
