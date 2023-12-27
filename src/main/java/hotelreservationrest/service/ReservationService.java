package hotelreservationrest.service;

import hotelreservationrest.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    void addReservation(ReservationDto reservationDto);
    void updateReservation(ReservationDto newReservation);
    void deleteReservation(Long id);
    List<ReservationDto> getReservations();

    ReservationDto getReservationById(Long id);
}
