import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonaService } from '../../persona.service';
import { Persona } from '../../models/persona';
import { Residenza } from '../../models/residenza';
import { PersonaFormComponent } from '../persona-form/persona-form.component';
import { ResidenzaFormComponent } from '../residenza-form/residenza-form.component';

@Component({
  selector: 'app-persona-detail',
  standalone: true,
  imports: [CommonModule,FormsModule, PersonaFormComponent, ResidenzaFormComponent],
  templateUrl: './persona-detail.component.html',
  styleUrls: ['./persona-detail.component.css']
})
export class PersonaDetailComponent implements OnInit {
  persona: Persona | null = null;
  error: string | null = null;
  loading = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private personaService: PersonaService
  ) {}

  ngOnInit(): void {
    const uuid = this.route.snapshot.paramMap.get('uuid');
    if (uuid) {
      this.getPersona(uuid);
    }
  }

  getPersona(uuid: string): void {
    this.loading = true;
    this.personaService.getPersona(uuid).subscribe({
      next: (data) => {
        this.persona = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Errore durante il caricamento della persona';
        this.loading = false;
      }
    });
  }

  savePersona(updated: Persona): void {
    if (!this.persona) return;
    this.personaService.updatePersona(this.persona.uuid, updated).subscribe({
      next: (data) => this.persona = data,
      error: () => this.error = 'Errore durante il salvataggio della persona'
    });
  }

  saveResidenza(updated: Residenza): void {
    if (!this.persona) return;
    this.personaService.updateResidenza(this.persona.uuid, updated).subscribe({
      next: (data) => {
        if (this.persona) this.persona.residenza = data;
      },
      error: () => this.error = 'Errore durante il salvataggio della residenza'
    });
  }

deletePersona(): void {
  if (!this.persona) return;
  if (!confirm('Sei sicuro di voler eliminare questa persona?')) return;

  this.personaService.deletePersona(this.persona.uuid).subscribe({
    next: () => this.router.navigate(['/persone']),
    error: () => this.error = 'Errore durante l\'eliminazione della persona'
  });
}

  deleteResidenza(): void {
    if (!this.persona || !this.persona.residenza) return;
    if (!confirm('Sei sicuro di voler eliminare la residenza?')) return;

    this.personaService.deleteResidenza(this.persona.uuid).subscribe({
      next: () => {
        if (this.persona) this.persona.residenza = undefined;
      },
      error: () => this.error = 'Errore durante l\'eliminazione della residenza'
    });
  }
}
