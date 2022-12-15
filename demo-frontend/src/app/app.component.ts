import { HttpErrorResponse } from '@angular/common/http';
import {
  Component,
  ChangeDetectorRef,
  AfterContentChecked,
} from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
//import { Entrepreneur } from './model/entrepreneur';
//import { EntrepreneureService } from './services/entrepreneur.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements AfterContentChecked {
  title = 'firma-admin-web';
  selectedLanguage = 'es';

  constructor(
    private translateService: TranslateService,
    private cd: ChangeDetectorRef,
    //private entrepreneurService: EntrepreneureService
  ) {
    this.translateService.setDefaultLang(this.selectedLanguage);
    this.translateService.use(this.selectedLanguage);
  }

  ngAfterContentChecked() {
    this.cd.detectChanges();
  }
}
