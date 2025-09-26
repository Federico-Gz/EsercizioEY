package it.federicoGraziani.esercizio.residenza.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResidenzaDTO {

    private String uuid;

    @NotEmpty
    @NotNull
    private String indirizzo;

    @NotEmpty
    @NotNull
    private String cap;

    @NotEmpty
    @NotNull
    private String citta;

    @NotEmpty
    private String personaUuid;
}
