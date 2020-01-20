import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TablaComponent } from './componentes/tabla/tabla.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PersonaService } from './servicio/persona.service';
import { EncabezadoComponent } from './componentes/encabezado/encabezado.component';
import { AgregarComponent } from './componentes/agregar/agregar.component';

@NgModule({
  declarations: [
    AppComponent,
    TablaComponent,
    EncabezadoComponent,
    AgregarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PersonaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
