package it.federicoGraziani.esercizio.persona.service;

import it.federicoGraziani.esercizio.persona.dto.PersonaDTO;
import it.federicoGraziani.esercizio.persona.exception.PersonaNotFoundException;
import it.federicoGraziani.esercizio.persona.exception.ResidenzaNotFoundException;
import it.federicoGraziani.esercizio.persona.model.Persona;
import it.federicoGraziani.esercizio.persona.repository.PersonaRepository;
import it.federicoGraziani.esercizio.residenza.dto.ResidenzaDTO;
import it.federicoGraziani.esercizio.residenza.model.Residenza;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRepository;

    //RESTITUISCE TUTTE LE PERSONE
    @Override
    public List<PersonaDTO> findAll() {
        return personaRepository.findAll()
                .stream()
                .map(this::modelToDto)
                .toList();
    }

    //RESTITUISCE UNA PERSONA IN BASE ALL'UUID
    @Override
    public PersonaDTO findByUuid(String uuid) {
        Optional<Persona> persona= personaRepository.findByUuid(uuid);
        return persona.map(this::modelToDto).orElseThrow(() -> new PersonaNotFoundException(uuid));
    }

    //CREAZIONE PERSONA
    @Override
    public PersonaDTO save(PersonaDTO personaDTO) {
        personaDTO.setUuid(UUID.randomUUID().toString());
        Persona persona = dtoToModel(personaDTO);
        return modelToDto(personaRepository.save(persona));
    }

    //AGGIUNTA/MODIFICA RESIDENZA AD UNA PERSONA ESISTENTE
    @Override
    public ResidenzaDTO addOrUpdateResidenza(String personaUuid, ResidenzaDTO residenzaDTO){
        Persona persona = personaRepository.findByUuid(personaUuid).orElseThrow(() -> new PersonaNotFoundException(personaUuid));

        Residenza residenza = persona.getResidenza();
        if(residenza != null){
            if(residenzaDTO.getIndirizzo() != null)
                residenza.setIndirizzo(residenzaDTO.getIndirizzo());
            if(residenzaDTO.getCitta() != null)
                residenza.setCitta(residenzaDTO.getCitta());
            if(residenzaDTO.getCap() != null)
                residenza.setCap(residenzaDTO.getCap());
        }else{
            residenzaDTO.setUuid(UUID.randomUUID().toString());
            residenza = Residenza.builder()
                    .uuid(residenzaDTO.getUuid())
                    .indirizzo(residenzaDTO.getIndirizzo())
                    .cap(residenzaDTO.getCap())
                    .citta(residenzaDTO.getCitta())
                    .persona(persona)
                    .build();
            persona.setResidenza(residenza);
        }

        personaRepository.save(persona);

        //RESTITUISCE UN DTO A PARTIRE DAL MODELLO AGGIORNATO
        return ResidenzaDTO.builder()
                .uuid(residenza.getUuid())
                .personaUuid(persona.getUuid())
                .indirizzo(residenza.getIndirizzo())
                .citta(residenza.getCitta())
                .cap(residenza.getCap())
                .build();
    }


    //ELIMINAZIONE RESIDENZA DI UNA PERSONA ESISTENTE
    @Override
    public void deleteResidenza(String personaUuid) {
        Persona persona = personaRepository.findByUuid(personaUuid).orElseThrow(() -> new PersonaNotFoundException(personaUuid));

        Residenza residenza = persona.getResidenza();
        if (residenza != null) {
            // scollego la residenza dalla persona
            persona.setResidenza(null);//JPA elimina automaticamente la residenza per via di orphanRemoval = true
            personaRepository.save(persona);
        } else {
            throw new ResidenzaNotFoundException(personaUuid);
        }
    }

    @Override
    public PersonaDTO update(String uuid, PersonaDTO persona) {
        Persona personaToUpdate = personaRepository.findByUuid(uuid).orElseThrow();
        personaToUpdate.setNome(persona.getNome());
        personaToUpdate.setCognome(persona.getCognome());
        personaToUpdate.setCodiceFiscale(persona.getCodiceFiscale());
        personaToUpdate.setDataNascita(persona.getDataNascita());

        return modelToDto(personaRepository.save(personaToUpdate));
    }

    @Override
    public PersonaDTO partialUpdate(String uuid, PersonaDTO personaDTO) {
        Persona personaToUpdate = personaRepository.findByUuid(uuid).orElseThrow();

        if(personaDTO.getNome() != null)
            personaToUpdate.setNome(personaDTO.getNome());

        if(personaDTO.getCognome() != null)
            personaToUpdate.setCognome(personaDTO.getCognome());

        if(personaDTO.getCodiceFiscale() != null)
            personaToUpdate.setCodiceFiscale(personaDTO.getCodiceFiscale());

        if(personaDTO.getDataNascita() != null)
            personaToUpdate.setDataNascita(personaDTO.getDataNascita());

        return modelToDto(personaRepository.save(personaToUpdate));
    }

    @Override
    public void deleteByUuid(String uuid) {

        Persona persona = personaRepository.findByUuid(uuid).orElseThrow();
        personaRepository.delete(persona);
    }

    @Override
    public List<PersonaDTO> ricerca(String indirizzo) {
        if (indirizzo == null || indirizzo.isEmpty()) {
            return findAll(); // ritorna tutte se vuoto
        }
        List<Persona> persone = personaRepository.findByResidenzaIndirizzoContainingIgnoreCase(indirizzo);
        return persone.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }





    private PersonaDTO modelToDto(Persona persona){
        return PersonaDTO.builder()
                .uuid(persona.getUuid())
                .nome(persona.getNome())
                .cognome(persona.getCognome())
                .codiceFiscale(persona.getCodiceFiscale())
                .dataNascita(persona.getDataNascita())
                .residenza(persona.getResidenza() != null
                        ? ResidenzaDTO.builder()
                            .uuid(persona.getResidenza().getUuid())
                            .indirizzo(persona.getResidenza().getIndirizzo())
                            .citta(persona.getResidenza().getCitta())
                            .cap(persona.getResidenza().getCap())
                            .personaUuid(persona.getUuid())
                            .build()
                        : null
                )
                .build();
    }

//    private Persona dtoToModel(PersonaDTO personaDTO, Residenza residenza){
//        return Persona.builder()
//                .uuid(personaDTO.getUuid())
//                .nome(personaDTO.getNome())
//                .cognome(personaDTO.getCognome())
//                .codiceFiscale(personaDTO.getCodiceFiscale())
//                .dataNascita(personaDTO.getDataNascita())
//                .residenza(residenza)
//                .build();
//    }

    private Persona dtoToModel(PersonaDTO personaDTO) {
        return Persona.builder()
                .uuid(personaDTO.getUuid())
                .nome(personaDTO.getNome())
                .cognome(personaDTO.getCognome())
                .codiceFiscale(personaDTO.getCodiceFiscale())
                .dataNascita(personaDTO.getDataNascita())
                .residenza(null)
                .build();
    }
}
