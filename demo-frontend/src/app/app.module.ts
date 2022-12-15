import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AuthModule } from './auth/auth.module';
import { MatSnackBarComponent } from './components/mat-snack-bar/mat-snack-bar.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NgxSpinnerModule } from 'ngx-spinner';
import { NgIdleKeepaliveModule } from '@ng-idle/keepalive';
import { AuthService } from './auth/auth.service';
import { MainModule } from './main/main.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { ConfirmationDialogComponent } from './shared/confirmation-dialog/confirmation-dialog.component';
import { InterceptService } from './services/intercept.service';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { CustomMatPaginatorIntl } from './model/custom-mat-paginator';
import { CoreModule } from './core/core.module';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { EditInversorComponent } from './main/inversores/invesores/edit-inversor/edit-inversor.component';
import { CreateInversorComponent } from './main/inversores/invesores/create-inversor/create-inversor.component';
import { CreateStartupComponent } from './main/startup/create-startup/create-startup.component';
import { RegisterComponent } from './register/register.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PopUpStartupComponent } from './register/pop-up-startup/pop-up-startup.component';
import { PopUpInversorComponent } from './register/pop-up-inversor/pop-up-inversor.component';
import { PopUpEntrepreneurComponent } from './register/pop-up-entrepreneur/pop-up-entrepreneur.component';
import { UserComponent } from './register/user/user.component';
import { PopUpUserComponent } from './register/user/pop-up-user/pop-up-user.component';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioModule } from '@angular/material/radio';
import { MatDividerModule } from '@angular/material/divider';
import { RegistrationFormComponent } from './main/registration/registration-form/registration-form.component';
import { NgxPaginationModule } from 'ngx-pagination';





@NgModule({
  declarations: [
    AppComponent,
    ConfirmationDialogComponent,
    MatSnackBarComponent,

    LandingPageComponent,
    RegisterComponent,
    PopUpStartupComponent,
    PopUpInversorComponent,
    PopUpEntrepreneurComponent,
    UserComponent,
    PopUpUserComponent,


  ],
  imports: [
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    MatDividerModule,
    MatRadioModule,
    ReactiveFormsModule,
    BrowserModule,
    MatPaginatorModule,
    BrowserAnimationsModule,
    MdbModalModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FlexLayoutModule,
    MainModule,
    AuthModule,
    MatDialogModule,
    MatButtonModule,
    CoreModule,
    MatSnackBarModule,
    NgxSpinnerModule,
    MatFormFieldModule,
    MatIconModule,
    NgxPaginationModule,
    NgIdleKeepaliveModule.forRoot()
  ],
  entryComponents: [
    ConfirmationDialogComponent,
    EditInversorComponent,
    CreateInversorComponent,
    CreateStartupComponent,
    PopUpUserComponent,
    RegistrationFormComponent
  ],
  exports: [
    TranslateModule
  ],
  providers: [AuthService, MatSnackBarComponent, InterceptService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptService,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    {
      provide: MatPaginatorIntl,
      useFactory: (translate) => {
        const service = new CustomMatPaginatorIntl();
        service.injectTranslateService(translate);
        return service;
      },
      deps: [TranslateService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

// required for AOT compilation
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}
