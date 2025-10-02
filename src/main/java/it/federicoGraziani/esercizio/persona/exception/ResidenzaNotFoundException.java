package it.federicoGraziani.esercizio.persona.exception;

public class ResidenzaNotFoundException extends RuntimeException {
    public ResidenzaNotFoundException(String personaUuid) {
        super("La residenza per la persona con uuid " + personaUuid + " non è stata trovata");
    }
}
