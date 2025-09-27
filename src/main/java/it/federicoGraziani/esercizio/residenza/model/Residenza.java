package it.federicoGraziani.esercizio.residenza.model;

import it.federicoGraziani.esercizio.persona.model.Persona;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "Residenza")
public class Residenza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @Column(name = "indirizzo", nullable = false, length = 255)
    private String indirizzo;

    @Column(name = "cap", nullable = false, length = 5)
    private String cap;

    @Column(name = "citta", nullable = false, length = 255)
    private String citta;

    @OneToOne
    @JoinColumn(name = "id_anagrafica", referencedColumnName = "id", nullable = false, unique = true)
    private Persona persona;

}
