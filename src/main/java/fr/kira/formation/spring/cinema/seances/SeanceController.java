package fr.kira.formation.spring.cinema.seances;

import fr.kira.formation.spring.cinema.films.Film;
import fr.kira.formation.spring.cinema.salles.Salle;
import fr.kira.formation.spring.cinema.tickets.Ticket;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seances")
public class SeanceController {

    private final SeanceService service;

    public SeanceController(SeanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Seance> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Seance findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Seance save(@RequestBody Seance seance) {
        return service.save(seance);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PostMapping("{id}/films")
    public void addFilm(@PathVariable Integer id, @RequestBody Film film){
        this.service.addFilm(id, film);
    }

    @PostMapping("{id}/films/{idFilm}")
    public void addFilmById(@PathVariable Integer id, @PathVariable Integer idFilm){
        this.service.addFilmById(id, idFilm);
    }

    @PostMapping("{id}/salles")
    public void addSalle(@PathVariable Integer id, @RequestBody Salle salle){
        this.service.addSalle(id, salle);
    }

    @PostMapping("{id}/salles/{idSalle}")
    public void addSalleById(@PathVariable Integer id, @PathVariable Integer idSalle){
        this.service.addFilmById(id, idSalle);
    }

    @GetMapping("{id}/ticket")
    public List<Ticket> findTicketSeance(@PathVariable Integer id){
        return this.service.findTicketSeance(id);
    }

}
