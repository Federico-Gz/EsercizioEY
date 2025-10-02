import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Residenza } from '../../models/residenza';
import { PersonaService } from '../../persona.service';

@Component({
  selector: 'app-residenza-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './residenza-form.component.html',
  styleUrls: ['./residenza-form.component.css']
})
export class ResidenzaFormComponent implements OnInit {

  @Input() residenza?: Residenza;  // residenza può essere undefined
  @Input() personaUuid!: string;  // sempre presente
  @Output() save = new EventEmitter<Residenza>();

  constructor(private personaService: PersonaService) {}

  ngOnInit(): void {
    // Se la residenza non esiste, ne viene inizializzata una vuota
    if (!this.residenza) {
      this.residenza = {
        uuid: '',
        indirizzo: '',
        citta: '',
        cap: '',
        personaUuid: this.personaUuid
      };
    }
  }

  onSave(): void {
    if (this.residenza) {
      this.personaService.updateResidenza(this.personaUuid, this.residenza)
        .subscribe({
          next: (res: Residenza) => this.save.emit(res),
          error: err => console.error(err)
        });
    }
  }
}
