package hotelreservationrest.service.impl;

import hotelreservationrest.dto.ReservationDto;
import hotelreservationrest.entity.Reservation;
import hotelreservationrest.exception.ReservationNotFoundException;
import hotelreservationrest.repository.ReservationRepository;
import hotelreservationrest.service.ReservationService;
import hotelreservationrest.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final MapperUtil mapperUtil;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(MapperUtil mapperUtil, ReservationRepository reservationRepository) {
        this.mapperUtil = mapperUtil;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void addReservation(ReservationDto reservationDto) {
        Reservation reservation = mapperUtil.convert(reservationDto, new Reservation());
        reservationRepository.save(reservation);


    }

    @Override
    public void updateReservation(ReservationDto newReservationDto) {
        System.out.println("reservation id: to update"+newReservationDto.getId());
        Reservation savedReservation = reservationRepository.findById(newReservationDto.getId()).orElseThrow(() -> new ReservationNotFoundException("Reservation not found, cannot be updated"));
        Reservation newReservation = (mapperUtil.convert(newReservationDto, new Reservation()));
        newReservation.setId(savedReservation.getId());
        reservationRepository.save(newReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation does not exist, cannot be deleted"));
        reservationRepository.delete(reservation);

    }

    @Override
    public List<ReservationDto> getReservations() {
        return reservationRepository.findAll().stream()
                .map(reservation -> mapperUtil.convert(reservation, new ReservationDto()))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto getReservationById(Long id) {
        if (id == null) return null;
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation does not exist!"));
        if (reservation != null) {
            return mapperUtil.convert(reservation, new ReservationDto());
        }
        return null;
    }
}
