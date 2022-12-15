import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../auth/auth.guard';
import { PopUpInversorComponent } from '../register/pop-up-inversor/pop-up-inversor.component';
import { PopUpStartupComponent } from '../register/pop-up-startup/pop-up-startup.component';
import { ContactsComponent } from './contacts/contacts.component';
import { InvesoresComponent } from './inversores/invesores/invesores.component';
import { MainHomeComponent } from './main-home/main-home.component';
import { StartupComponent } from './startup/startup.component';

const routes: Routes = [
  {
    path: 'main',
    component: MainHomeComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['CONTACTS'],
    },
  },
  {
    path: 'contacts',
    component: ContactsComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['CONTACTS'],
    },
  },
  {
    path: 'inversores',
    component: InvesoresComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['CONTACTS'],
    },
  },
  {
    path: 'startup',
    component: StartupComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['CONTACTS'],
    },
  },
  {
    path: 'startup-user',
    component: PopUpStartupComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['STARTUPS'],
    },
  },
  {
    path: 'inversor-user',
    component: PopUpInversorComponent,
    canActivate: [AuthGuard],
    data: {
      allowedRoles: ['INVESTORS'],
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainRoutingModule {}
