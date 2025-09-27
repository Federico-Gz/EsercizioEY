package it.federicoGraziani.esercizio.persona.service;

import it.federicoGraziani.esercizio.persona.dto.PersonaDTO;
import it.federicoGraziani.esercizio.residenza.dto.ResidenzaDTO;

import java.util.List;

public interface PersonaService {

    List<PersonaDTO> findAll();
    PersonaDTO findByUuid(String uuid);
    PersonaDTO save(PersonaDTO persona);

    //AGGIUNTA/MODIFICA RESIDENZA AD UNA PERSONA ESISTENTE
    ResidenzaDTO addOrUpdateResidenza(String personaUuid, ResidenzaDTO residenzaDTO);

    //ELIMINAZIONE RESIDENZA DI UNA PERSONA ESISTENTE
    void deleteResidenza(String personaUuid);

    List<PersonaDTO> ricerca(String indirizzo, String citta, String cap);
    PersonaDTO update(String uuid, PersonaDTO persona);
    PersonaDTO partialUpdate(String uuid, PersonaDTO persona);
    void deleteByUuid(String uuid);
}
