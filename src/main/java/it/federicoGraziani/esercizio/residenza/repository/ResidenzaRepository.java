package it.federicoGraziani.esercizio.residenza.repository;

import it.federicoGraziani.esercizio.residenza.model.Residenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenzaRepository extends JpaRepository<Residenza, Long> {

}
