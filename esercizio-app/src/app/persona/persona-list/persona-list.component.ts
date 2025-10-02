import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PersonaService } from '../../persona.service';
import { Persona } from '../../models/persona';

@Component({
  selector: 'app-persona-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './persona-list.component.html',
  styleUrls: ['./persona-list.component.css']
})
export class PersonaListComponent implements OnInit {
  persone: Persona[] = [];
  loading = false;
  error: string | null = null;
  indirizzoFiltro: string = '';

  constructor(private personaService: PersonaService, private router: Router) {}

  ngOnInit(): void {
    this.getPersone();
  }

  getPersone(): void {
    this.loading = true;
    this.personaService.getPersone().subscribe({
      next: (data) => {
        this.persone = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Errore durante il caricamento delle persone';
        this.loading = false;
      }
    });
  }

  filtraPersone(): void {
    this.loading = true;
    this.personaService.ricerca(this.indirizzoFiltro).subscribe({
      next: (data) => {
        this.persone = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Errore durante la ricerca';
        this.loading = false;
      }
    });
  }

  goToDetail(uuid: string): void {
    this.router.navigate(['/persone', uuid]);
  }

  deletePersona(uuid: string): void {
    this.personaService.deletePersona(uuid).subscribe({
      next: () => this.getPersone(),
      error: () => this.error = 'Errore durante l\'eliminazione'
    });
  }
}
