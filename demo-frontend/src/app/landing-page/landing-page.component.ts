import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatStepper } from '@angular/material/stepper';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { RegistrationFormComponent } from '../main/registration/registration-form/registration-form.component';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss'],
})
export class LandingPageComponent implements OnInit {

  @ViewChild('stepper') stepper: MatStepper;

  @Output() toggleSidenav = new EventEmitter<void>();

  selectedLanguage = this.translateService.currentLang;

  constructor(
    public router: Router,
    private translateService: TranslateService,
    private dialog: MatDialog
  ) {}

  openDialogUser() {
    const dialogConfig = new MatDialogConfig();
    //dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    this.dialog.open(RegistrationFormComponent, dialogConfig);
  }

  toogleLanguage(lang: string) {
    this.selectedLanguage = lang;
    this.translateService.use(lang);
  }
  // metodo para que funcionen los enlaces dentro de la misma landing page
  public onClick(elementId: string): void {
    document.getElementById(elementId).scrollIntoView({
      behavior: 'smooth',
      block: 'start',
      inline: 'nearest',
    });
  }

  ngOnInit(): void {}
}
