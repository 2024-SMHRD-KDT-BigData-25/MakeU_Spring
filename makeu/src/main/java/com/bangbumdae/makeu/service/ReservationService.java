package com.bangbumdae.makeu.service;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ShopReservation;
import com.bangbumdae.makeu.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public void addReservation(ShopReservation r) {
        reservationRepository.save(r);
    }
}
