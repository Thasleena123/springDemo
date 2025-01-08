package Bookingapp.bookingapp.Controller;

import Bookingapp.bookingapp.Entity.Theater;
import Bookingapp.bookingapp.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @GetMapping(path = "theater", produces = "application/json")
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }

    @GetMapping(path = "theater/{id}", produces = "application/json")
    public Theater getTheaterById(@PathVariable("id") int id) {
        return theaterService.getTheaterById(id);
    }

    @DeleteMapping(path = "theater/{id}", produces = "application/json")
    public String deleteTheatreById(@PathVariable("id") int id) {
        return theaterService.deleteTheatreById(id);
    }

    @PostMapping(path = "theater", produces = "application/json")
    public String addTheater(@RequestBody Theater theater) {
        return theaterService.addTheater(theater);
    }

    @PutMapping(path = "theater/{id}", produces = "application/json")
    public String updateTheater(@RequestBody Theater theater,@PathVariable("id") int id) {
        return theaterService.updateTheater(theater, id);
    }

    @PatchMapping(path = "theater/{id}", produces = "application/json")
    public String partialupdate(@RequestBody Theater theater,@PathVariable("id") int id) {
        return theaterService.partialUpdate(theater,id);
    }
}
