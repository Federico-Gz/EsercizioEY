package it.federicoGraziani.esercizio.persona.dto;

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

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String cognome;

    @NotNull
    @NotEmpty
    private String codiceFiscale;

    @NotNull
    private LocalDate annoNascita;

    @NotEmpty
    private String residenzaUuid;
}
