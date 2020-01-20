import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Observer } from 'rxjs';
import { Persona } from '../entidades/persona';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  _url: string = 'http://localhost:8008/api/v1/persona-adn/';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Persona[]> {
    return this.http.get<Persona[]>(this._url);
  }

  getOne(id: number): Observable<Persona>{
    return this.http.get<Persona>(this._url + id);
  }

  post(persona: Persona): Observable<Persona>{
    return this.http.post<Persona>(this._url, persona);
  }

  put(id: number, persona: Persona): Observable<Persona>{
    return this.http.put<Persona>(this._url + id, persona);
  }

  delete(id: number): Observable<any>{
    return this.http.delete(this._url + id);
  }

}
