package hotelreservationrest.controller;

import hotelreservationrest.dto.ReservationDto;
import hotelreservationrest.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/{id}")
    ResponseEntity<ReservationDto> getReservationById(@PathVariable("id")Long id){
        ReservationDto reservationDto=reservationService.getReservationById(id);
        System.out.println("Getting reservation "+reservationDto.getId());
        return new ResponseEntity<>(reservationDto,HttpStatus.OK);
    }

    @PostMapping("/new")
    ResponseEntity<ReservationDto> addNewReservation(@RequestBody ReservationDto reservationDto){
        if(reservationDto!=null){
            reservationService.addReservation(reservationDto);
//            Map<String, String> response = Collections.singletonMap("message", "Reservation added successfully!");
            return ResponseEntity.ok(reservationDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Map<String,String>> updateReservation(@PathVariable("id")Long id,@RequestBody ReservationDto reservationDto){
        reservationDto.setId(id);
        if(reservationDto!=null){
            reservationService.updateReservation(reservationDto);
            Map<String, String> response = Collections.singletonMap("message", "Reservation id:+"+reservationDto.getId() + " updated successfully!");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Map<String, String>> deleteAppointment(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("Deleting reservation with ID: " + id);
        reservationService.deleteReservation(id);

        Map<String, String> response = Collections.singletonMap("message", "Appointment id:" + id + " deleted successfully!");
        return ResponseEntity.ok().body(response);
    }
}
