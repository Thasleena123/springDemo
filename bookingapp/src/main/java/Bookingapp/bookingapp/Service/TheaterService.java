package Bookingapp.bookingapp.Service;

import Bookingapp.bookingapp.DAO.TheaterRepo;
import Bookingapp.bookingapp.Entity.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    TheaterRepo theaterRepo;

    @Autowired
    public TheaterService(TheaterRepo theaterRepo) {
        this.theaterRepo = theaterRepo;
    }

    public List<Theater> getAllTheaters() {

        return theaterRepo.getAllTheaters();
    }

    public Theater getTheaterById(int id) {
        return theaterRepo.getTheaterById(id);
    }

    public String deleteTheatreById(int id) {
        return theaterRepo.deleteTheatreById(id);
    }

    public String addTheater(Theater theater) {
        return theaterRepo.addTheater(theater);
    }

    public String updateTheater(Theater theater, int id) {
        return theaterRepo.updateTheater(theater, id);
    }

    public String partialUpdate(Theater theater,int id) {
        Theater orginal= getTheaterById(id);
        if(theater.getName()==null){
            theater.setName(orginal.getName());
        }
        if(theater.getLocation()==null){
            theater.setName(orginal.getLocation());
        }
        return theaterRepo.updateTheater(theater,id);
    }
}