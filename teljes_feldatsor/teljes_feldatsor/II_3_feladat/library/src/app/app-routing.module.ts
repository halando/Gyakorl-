import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { KolcsonzokComponent } from './kolcsonzok/kolcsonzok.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'kolcsonzok', component: KolcsonzokComponent }
];
// a path az az ami az alap url után van írva, a '' azt jelenti hogy ha nincs semmi írva akkor a home-ra menjen
// a másik kettő pedig az url szerint irányítja a megfelelő komponneshez
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

