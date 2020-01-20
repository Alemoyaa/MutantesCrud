import { Component, OnInit } from '@angular/core';
import { Persona } from 'src/app/entidades/persona';
import { PersonaService } from 'src/app/servicio/persona.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.css']
})
export class TablaComponent implements OnInit {

  personas: Persona[] = [];
  constructor(private servicio: PersonaService, private router: Router) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.servicio.getAll().subscribe((data) => {
      this.personas = data;
      console.log(this.personas);
    });
  }

  getOne(id: number) {
    this.servicio.getOne(id).subscribe((data) => { });
  }

  delete(id: number) {
    const opcion = confirm('¿Esta seguro que lo desea eliminar?');
    if (opcion) {
      this.servicio.delete(id).subscribe((data) => {
        console.log(data);
        alert('Registro eliminado');
        window.location.reload();
      });
    } else {
      alert('Eliminacion cancelada.');
    }
  }

  update(idPersona: number) {
    this.router.navigate(['persona/' + idPersona]);
  }

  agregar(idPersona: number) {
    this.router.navigate(['persona/' + idPersona]);
  }

}
