package fr.kira.formation.spring.cinema.seances;

import fr.kira.formation.spring.cinema.films.Film;
import fr.kira.formation.spring.cinema.films.FilmService;
import fr.kira.formation.spring.cinema.salles.Salle;
import fr.kira.formation.spring.cinema.salles.SalleService;
import fr.kira.formation.spring.cinema.tickets.Ticket;
import fr.kira.formation.spring.cinema.tickets.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SeanceService {

    private final SeanceRepository repository;
    private final FilmService filmService;
    private final SalleService salleService;
    private final TicketRepository ticketRepository;


    public SeanceService(SeanceRepository repository, FilmService filmService, SalleService salleService, TicketRepository ticketRepository) {
        this.repository = repository;
        this.filmService = filmService;
        this.salleService = salleService;
        this.ticketRepository = ticketRepository;
    }

    public List<Seance> findAll() {
        return repository.findAll();
    }

    public Seance save(Seance entity) {
        return repository.save(entity);
    }

    public Seance findById(Integer integer) {
        return repository.findById(integer).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    public void addFilm(Integer id, Film film){
        Seance seance = repository.findById(id).orElseThrow();
        Film filmAjouter = this.filmService.findOrInsert(film);
        seance.setFilm(filmAjouter);
        repository.save(seance);
    }

    public void addFilmById(Integer id, Integer idFilm){
        Film film = new Film();
        film.setId(idFilm);
        addFilm(id, film);
    }

    public void addSalle(Integer id, Salle salle){
        Seance seance = repository.findById(id).orElseThrow();
        Salle salleAjouter = this.salleService.findOrInsert(salle);
        seance.setSalle(salleAjouter);
        repository.save(seance);
    }

    public void addSalleById(Integer id, Integer idsalle){
        Salle salle = new Salle();
        salle.setId(idsalle);
        addSalle(id, salle);
    }

    public Seance findOrInsert(Seance seance){
        return this.repository.findById(seance.getId()).orElseGet(()->this.repository.save(seance));
    }

    public List<Ticket> findTicketSeance(Integer id){
        return this.ticketRepository.findBySeanceId(id);
    }
}
