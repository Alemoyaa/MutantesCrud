import { Component, OnInit } from '@angular/core';
import { Persona } from 'src/app/entidades/persona';
import { Router, ActivatedRoute } from '@angular/router';
import { PersonaService } from 'src/app/servicio/persona.service';

@Component({
  selector: 'app-agregar',
  templateUrl: './agregar.component.html',
  styleUrls: ['./agregar.component.css']
})
export class AgregarComponent implements OnInit {

  persona: Persona = {
    id: 0,
    nombre: '',
    apellido: '',
    edad: 0,
    mutant: false,
    adn: []
  };

  cadena: string;
  largoCadena: number;

  constructor(private servicio: PersonaService,
              private rutaActiva: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.rutaActiva.params.subscribe(data => {
      if (data.id !== '0') {
        console.log('Verifico que no es un alta ' + data.id);
        this.getOne(data.id);
      }
    });
  }

  getOne(id: number) {
    this.servicio.getOne(id).subscribe((data) => {
      this.persona = data;
    });
  }

  save(idPersona: Persona) {
    this.rutaActiva.params.subscribe((data) => {
      if (data.id === '0') {
        this.cargarCadenaAdn();
        this.add(this.persona);
      } else {
        this.cargarCadenaAdn();
        this.update(idPersona.id);
      }
    });
  }

  add(persona: Persona) {
    this.servicio.post(persona).subscribe((data) => {
      this.persona = data;
      this.router.navigate(['/']);
    });
  }

  update(id: number) {
    this.servicio.put(id, this.persona).subscribe((data) => {

      this.persona = data;
      this.router.navigate(['']);
    });
  }

  cargarCadenaAdn() {
    const separador = ',';
    let arrayDeCadenas;
    arrayDeCadenas  = this.cadena.split(separador);

    for (let index = 0; index < arrayDeCadenas.length; index++) {
      this.persona.adn[index] = arrayDeCadenas[index];
    }
  }

  cargarCadenaConComas() { // TODO: cargar cadenas y agregarle una coma

  }

}
