import { Residenza } from './residenza';


export class Persona {
  uuid!: string;
  nome!: string;
  cognome!: string;
  codiceFiscale!: string;
  dataNascita!: string;
  residenza?: Residenza;
}
