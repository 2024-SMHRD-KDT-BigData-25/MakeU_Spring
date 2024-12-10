package com.bangbumdae.makeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangbumdae.makeu.model.ShopReservation;

@Repository
public interface ReservationRepository extends JpaRepository<ShopReservation, Integer>{

}
