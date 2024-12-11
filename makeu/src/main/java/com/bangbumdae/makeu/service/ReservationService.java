package com.bangbumdae.makeu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bangbumdae.makeu.model.ShopReservation;
import com.bangbumdae.makeu.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ShopInfoService shopInfoService;
    public void addReservation(ShopReservation r) {
        reservationRepository.save(r);
    }

    public List<ShopReservation> getReservations(String memid) {
        List<ShopReservation> reservations = reservationRepository.findByMemid(memid);
        for (ShopReservation r : reservations) {
            r.setShopname(shopInfoService.getShopname(r.getShopidx()));
        }
        return reservationRepository.findByMemid(memid);
    }
}
