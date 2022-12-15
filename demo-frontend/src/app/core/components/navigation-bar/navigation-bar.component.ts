import { Component, EventEmitter, Output } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from 'src/app/auth/auth.service';
import { LoggerService } from 'src/app/services/logger.service';
import { NavComponent } from '../nav/nav.component';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss'],
})
export class NavigationBarComponent {
  @Output() toggleSidenav = new EventEmitter<void>();

  returnUrl = '/';
  selectedLanguage = this.translateService.currentLang;
  userName: string;
  rutas: any;
  pageTitle: string;
  pageIcon: string;

  constructor(
    private authService: AuthService,
    private router: Router,
    private logger: LoggerService,
    private translateService: TranslateService,
    navComponent: NavComponent
  ) {
    this.rutas = navComponent.allowedRoutes;
    this.userName = authService.getUserName();
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.returnUrl = event.url;
        this.logger.info('NavigationBarComponent returnUrl: ' + this.returnUrl);
        //Lembrar engadir else if a medida que se engaden novas páxinas o sidnav !
        if (this.returnUrl == '/inversores') {
          this.pageIcon = 'people';
          this.pageTitle = 'menu.investers';
        } else if (this.returnUrl == '/main') {
          this.pageIcon = 'home';
          this.pageTitle = 'menu.home';
        } else if (this.returnUrl == '/stadistics') {
          this.pageIcon = 'trending_up';
          this.pageTitle = 'menu.stadistics';
        } else if (this.returnUrl == '/startup') {
          this.pageIcon = 'trending_up';
          this.pageTitle = 'menu.startup';
        } else if (this.returnUrl == '/startup-user') {
          this.pageIcon = 'trending_up';
          this.pageTitle = 'menu.startup-user';
        } else if (this.returnUrl == '/inversor-user') {
          this.pageIcon = 'trending_up';
          this.pageTitle = 'menu.investers-user';
        }
      }
    });
  }

  /**
   * Se encarga de no mostrar navBar cuando estamos logueados en la landing-page
   *@return devuelve la navbar no visible
   */
  get navBarView() {
    let navVisible: boolean = true;
    if (this.router.url === '/landing-page') {
      navVisible = false;
    }
    return navVisible;
  }
}
