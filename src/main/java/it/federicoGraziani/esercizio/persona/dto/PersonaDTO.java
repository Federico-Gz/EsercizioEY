package it.federicoGraziani.esercizio.persona.dto;

import it.federicoGraziani.esercizio.residenza.dto.ResidenzaDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PersonaDTO {

    private String uuid;

    private String nome;

    private String cognome;

    private String codiceFiscale;

    private LocalDate dataNascita;

    private ResidenzaDTO residenza;
}
