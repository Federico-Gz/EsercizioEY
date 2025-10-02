package it.federicoGraziani.esercizio.persona.exception;

public class PersonaNotFoundException extends RuntimeException {
    public PersonaNotFoundException(String uuid) {
        super("Persona con uuid " + uuid + " non presente");
    }
}
