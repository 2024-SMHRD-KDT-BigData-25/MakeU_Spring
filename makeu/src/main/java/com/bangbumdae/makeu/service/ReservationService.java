package com.bangbumdae.makeu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public boolean cancelReservation(Integer reservationId, String memid) {
        Optional<ShopReservation> reservation = reservationRepository.findByReservationidxAndMemid(reservationId, memid);
    
        if (reservation.isPresent()) {
            reservationRepository.delete(reservation.get());
            return true;
        }
        return false;
    }
}
