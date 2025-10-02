package it.federicoGraziani.esercizio.persona.handler;

import it.federicoGraziani.esercizio.persona.exception.PersonaNotFoundException;
import it.federicoGraziani.esercizio.persona.exception.ResidenzaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PersonaGlobalException{

    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerPersonaNotFoundException(PersonaNotFoundException e){
        return new ResponseEntity<>(getRet("404", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResidenzaNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerResidenzaNotFoundException(ResidenzaNotFoundException e){
        return new ResponseEntity<>(getRet("404", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    private Map<String, Object> getRet(String errorCode, String errorMessage){
        Map<String, Object> ret = new HashMap<>();
        ret.put("timestamp", LocalDateTime.now());
        ret.put("error", errorCode);
        ret.put("message", errorMessage);
        return ret;
    }
}
