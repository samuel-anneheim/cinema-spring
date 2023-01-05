package fr.kira.formation.spring.cinema.tickets;

import fr.kira.formation.spring.cinema.seances.Seance;
import fr.kira.formation.spring.cinema.seances.SeanceRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository repository;
    private final SeanceRepository seanceRepository;

    public TicketService(TicketRepository repository, SeanceRepository seanceRepository) {
        this.repository = repository;
        this.seanceRepository = seanceRepository;
    }

    public Ticket findById(Integer integer) {
        return repository.findById(integer).orElseThrow();
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    public Iterable<Ticket> findAll() {
        return repository.findAll();
    }

    public Ticket save(Ticket ticket){
        Seance seance = seanceRepository.findById(ticket.getSeance().getId()).orElseThrow();
        seance.setNombrePlace(seance.getNombrePlace() - ticket.getNombrePlace());
        ticket.setSeance(seance);
        return repository.save(ticket);
    }


}
