import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { UserComponent } from './register/user/user.component';
import { PopUpStartupComponent } from './register/pop-up-startup/pop-up-startup.component';
import { PopUpInversorComponent } from './register/pop-up-inversor/pop-up-inversor.component';

const routes: Routes = [

  { path: 'login', component: LoginComponent,  pathMatch: 'full'},

  { path: 'list-startups', component:PopUpStartupComponent},
  { path: 'list-inversores', component:PopUpInversorComponent },

  { path: 'startup-user', component:PopUpStartupComponent},
  { path: 'inversor-user', component:PopUpInversorComponent },

  { path: '', redirectTo: 'landing-page', pathMatch: 'full' },
  { path: 'contacts', loadChildren: () => import('./main/contacts/contacts.module').then(x => x.ContactsModule) },
  { path: 'landing-page', component:LandingPageComponent },
  { path: 'registration',  loadChildren: () => import('./main/registration/registration.module').then(x => x.RegistrationModule) },
  { path: 'user', component: UserComponent },
  { path: 'inversores', loadChildren: () => import('./main/inversores/invesores/inversores.module').then(x => x.InversoresModule) },
  { path: 'startup', loadChildren: () => import('./main/startup/startup.module').then(x => x.StartupModule) }
];

@NgModule({
  //Importo anchorScrolling xa q me funcionen los enlaces en el landingpage.html (no va)
  imports: [RouterModule.forRoot(routes,{ anchorScrolling: 'enabled'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
