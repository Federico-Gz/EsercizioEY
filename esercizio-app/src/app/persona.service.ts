import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Persona } from './models/persona';
import { Residenza } from './models/residenza';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  private apiUrl = 'http://localhost:8080/api/persone';

  constructor(private http: HttpClient) {}

  //Recupera tutte le persone
  getPersone(): Observable<Persona[]> {
    return this.http.get<Persona[]>(this.apiUrl);
  }

  //Recupera una persona tramite UUID
  getPersona(uuid: string): Observable<Persona> {
    return this.http.get<Persona>(`${this.apiUrl}/${uuid}`);
  }

  //Crea una nuova persona
  createPersona(persona: Persona): Observable<Persona> {
    return this.http.post<Persona>(this.apiUrl, persona);
  }

  //Aggiorna una persona
  updatePersona(uuid: string, persona: Persona): Observable<Persona> {
    return this.http.put<Persona>(`${this.apiUrl}/${uuid}`, persona);
  }

  //Aggiornamento parziale
  partialUpdatePersona(uuid: string, persona: Partial<Persona>): Observable<Persona> {
    return this.http.patch<Persona>(`${this.apiUrl}/${uuid}`, persona);
  }

  //Elimina una persona
  deletePersona(uuid: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${uuid}`);
  }

  //Aggiungi o modifica la residenza di una persona
  updateResidenza(uuid: string, residenza: Residenza): Observable<Residenza> {
    return this.http.post<Residenza>(`${this.apiUrl}/${uuid}/residenza`, residenza);
  }

  //Elimina la residenza di una persona
  deleteResidenza(uuid: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${uuid}/residenza`);
  }

  /// Ricerca persone per indirizzo
   ricerca(indirizzo: string): Observable<Persona[]> {
     return this.http.get<Persona[]>(`${this.apiUrl}/ricerca`, {
       params: { indirizzo }
     });
   }


}
