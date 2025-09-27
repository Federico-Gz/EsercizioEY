package it.federicoGraziani.esercizio.persona.model;

import it.federicoGraziani.esercizio.residenza.model.Residenza;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @Column(name= "nome", nullable = false, length = 255)
    private String nome;

    @Column(name= "cognome", nullable = false, length = 255)
    private String cognome;

    @Column(name= "codice_fiscale", nullable = false, length = 255)
    private String codiceFiscale;

    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    //se elimino una persona, viene eliminata anche la sua residenza
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private Residenza residenza;
}

