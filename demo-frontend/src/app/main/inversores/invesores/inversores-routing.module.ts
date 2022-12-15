import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateInversorComponent } from './create-inversor/create-inversor.component';
import { EditInversorComponent } from './edit-inversor/edit-inversor.component';
import { InversorLayoutComponent } from './inversor-layout/inversor-layout.component';
import { InvesoresComponent } from './invesores.component';

const routes: Routes = [
  {
    path: '',
    component: InversorLayoutComponent,
    children: [
      { path: '', component: InvesoresComponent },
      { path: 'add', component: CreateInversorComponent },
      { path: 'edit/:id', component: EditInversorComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class InversorRoutingModule {}
