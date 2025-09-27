package it.federicoGraziani.esercizio.residenza.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResidenzaDTO {

    private String uuid;

    private String indirizzo;

    private String cap;

    private String citta;

    private String personaUuid;
}
