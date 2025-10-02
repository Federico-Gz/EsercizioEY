package it.federicoGraziani.esercizio.persona.repository;

import it.federicoGraziani.esercizio.persona.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findAll();

    Optional<Persona> findByUuid(String uuid);

    // Ricerca per indirizzo
    @Query("SELECT p FROM Persona p JOIN p.residenza r WHERE LOWER(r.indirizzo) LIKE LOWER(CONCAT('%', :indirizzo, '%'))")
    List<Persona> findByResidenzaIndirizzoContainingIgnoreCase(@Param("indirizzo") String indirizzo);
}
