package hotelreservationrest.controller;

import hotelreservationrest.dto.ReservationDto;
import hotelreservationrest.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/list")
    ResponseEntity<List<ReservationDto>> getReservationList(){
        return new ResponseEntity<>(reservationService.getReservations(),HttpStatus.OK);
    }

    @PostMapping("/new")
    ResponseEntity<String> addNewReservation(ReservationDto reservationDto){
        if(reservationDto!=null){
            reservationService.addReservation(reservationDto);
            return ResponseEntity.ok("Reservation added successfully!");
        }
        else  return new ResponseEntity<>("Invalid reservation data", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    ResponseEntity<String> updateReservation(ReservationDto reservationDto){
        if(reservationDto!=null){
            reservationService.updateReservation(reservationDto);
            return ResponseEntity.ok("Reservation added successfully!");
        }
        else  return new ResponseEntity<>("Invalid reservation data", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteReservation(@PathVariable("id") Long id){
        if(id!=null){
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("Reservation deleted with success");
        }
        else return new ResponseEntity<>("Invalid reservation id", HttpStatus.BAD_REQUEST);
    }

}
