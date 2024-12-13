package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.ShopReservation;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<ShopReservation, Integer>{
    List<ShopReservation> findByMemid(String memid);

    Optional<ShopReservation> findByReservationidxAndMemid(Integer reservationidx, String memid);  
}

