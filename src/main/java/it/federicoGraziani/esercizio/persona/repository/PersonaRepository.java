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
    List<Persona> findByResidenza_Indirizzo(String indirizzo);
    List<Persona> findByResidenza_Citta(String citta);
    List<Persona> findByResidenza_Cap(String cap);

    @Query("SELECT p FROM Persona p WHERE " +
            "(:indirizzo IS NULL OR p.residenza.indirizzo = :indirizzo) AND " +
            "(:citta IS NULL OR p.residenza.citta = :citta) AND " +
            "(:cap IS NULL OR p.residenza.cap = :cap)")
    List<Persona> ricerca(@Param("indirizzo") String indirizzo,
                          @Param("citta") String citta,
                          @Param("cap") String cap);


    Optional<Persona> findByUuid(String uuid);

}
