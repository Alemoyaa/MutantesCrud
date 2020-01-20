import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TablaComponent } from './componentes/tabla/tabla.component';
import { AgregarComponent } from './componentes/agregar/agregar.component';


const routes: Routes = [
  { path: '', component: TablaComponent },
  {path: 'persona/:id', component: AgregarComponent},
  { path: '**', redirectTo: '/tabla', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
