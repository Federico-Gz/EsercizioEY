import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Persona } from '../../models/persona';

@Component({
  selector: 'app-persona-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './persona-form.component.html',
  styleUrls: ['./persona-form.component.css']
})
export class PersonaFormComponent {
  @Input() persona: Persona = {} as Persona;
  @Output() save = new EventEmitter<Persona>();

  onSubmit() {
    this.save.emit(this.persona);
  }
}
