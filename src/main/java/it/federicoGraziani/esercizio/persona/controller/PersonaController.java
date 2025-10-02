package it.federicoGraziani.esercizio.persona.controller;

import it.federicoGraziani.esercizio.persona.dto.PersonaDTO;
import it.federicoGraziani.esercizio.persona.service.PersonaService;
import it.federicoGraziani.esercizio.residenza.dto.ResidenzaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persone")
@RequiredArgsConstructor
@Slf4j

public class PersonaController {

    private final PersonaService personaService;

    //RECUPERA TUTTE LE PERSONE (OK)
    @GetMapping
    public List<PersonaDTO> getAllPersone(){
        return personaService.findAll();
    }

    //RECUPERA UNA PERSONA TRAMITE UUID (OK)
    @GetMapping("/{uuid}")
    public PersonaDTO getPersona(@PathVariable String uuid){
        return personaService.findByUuid(uuid);
    }

    //CREA UNA NUOVA PERSONA (OK)
    @PostMapping
    public PersonaDTO createPersona(@RequestBody @Valid PersonaDTO personaDTO){
        return personaService.save(personaDTO);
    }

    //AGGIORNAMENTO PERSONA (OK)
    @PutMapping("/{uuid}")
    public PersonaDTO updatePersona(@PathVariable String uuid, @RequestBody @Valid PersonaDTO personaDTO){
        return personaService.update(uuid, personaDTO);
    }

    //AGGIORNAMENTO PARZIALE PERSONA (OK)
    @PatchMapping("/{uuid}")
    public PersonaDTO partialUpdatePersona(@PathVariable String uuid, @RequestBody @Valid PersonaDTO personaDTO){
        return personaService.partialUpdate(uuid, personaDTO);
    }

    //ELIMINA PERSONA (OK)
    @DeleteMapping("/{uuid}")
    public void deletePersona(@PathVariable String uuid){
        personaService.deleteByUuid(uuid);
    }

    //AGGIUNGE O MODIFICA LA RESIDENZA DI UNA PERSONA (OK)
    @PostMapping("/{uuid}/residenza")
    public ResidenzaDTO addOrUpdateResidenza(@PathVariable String uuid, @RequestBody @Valid ResidenzaDTO residenzaDTO){
        return personaService.addOrUpdateResidenza(uuid, residenzaDTO);
    }

    //ELIMINA LA RESIDENZA DI UNA PERSONA (OK)
    @DeleteMapping("{uuid}/residenza")
    public void deleteResidenza(@PathVariable String uuid){
        personaService.deleteResidenza(uuid);
    }

    //RECUPERA PERSONE IN BASE A INDIRIZZO
    @GetMapping("/ricerca")
    public List<PersonaDTO> ricercaPersone(@RequestParam(required = false) String indirizzo) {
        return personaService.ricerca(indirizzo);
    }
}
