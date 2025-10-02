import { Routes } from '@angular/router';
import {PersonaListComponent} from './persona/persona-list/persona-list.component';
import {PersonaDetailComponent} from './persona/persona-detail/persona-detail.component';

export const routes: Routes = [
  {path: '', redirectTo: '/persone', pathMatch: 'full'},
  {path: 'persone', component:PersonaListComponent},
  {path: 'persone/:uuid', component: PersonaDetailComponent},
  {path: '**', redirectTo: '/persone'}
  ];
