import { Adn } from './adn';

export class Persona {
    id: number;
    nombre: string;
    apellido: string;
    edad: number;
    mutant: boolean;
    adn: Adn[];
}
