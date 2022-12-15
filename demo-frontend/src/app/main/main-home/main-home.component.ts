import { Component } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';
import { environment } from 'src/environments/environment';
import {Idle, DEFAULT_INTERRUPTSOURCES} from '@ng-idle/core';
import { Keepalive } from '@ng-idle/keepalive';
import { InversoresDataSource } from '../../model/datasource/inversores.datasource';
import { InversorService } from '../../services/inversor.service';
import { AnyField, AnyPageFilter, SortFilter } from 'src/app/model/rest/filter';
import { StartupsDataSource } from 'src/app/model/datasource/startup.datasource';
import { StartupService } from 'src/app/services/startup.service';

@Component({
  selector: 'app-main-home',
  templateUrl: './main-home.component.html',
  styleUrls: ['./main-home.component.scss']
})
export class MainHomeComponent {

  idleState = 'Not started.';
  timedOut = false;
  lastPing?: Date = null;
  counter?: number;
  dataSource: InversoresDataSource;
  displayedColumns = [
    'select',
    //'id',
    'name',
    'email',
    'idInvesterRange',
    'idBusinessSector',
    'idStartUpState'
  ];
  fields = ['id', 'name', 'email', 'idInvesterRange', 'idBusinessSector', 'idStartUpState'];

  dataSourceStartup: StartupsDataSource;
  displayedColumnsStartup = [
    'select',
    //'id',
    'name',
    'email',
    'description',
    'idBusinessSector',
    'idStartUpState',
    'anualInvoicing',
    'fundationYear',
    //'idEntrepreneur'
  ];
  fieldsStartup = ['name', 'email', 'description', 'idBusinessSector', 'idStartUpState','anualInvoicing','fundationYear','idEntrepreneur'];




  constructor(private idle: Idle, private keepalive: Keepalive, private authService: AuthService, private investorService: InversorService, private startupService:StartupService) {

   //console.log('Counter:  ',this.counter = inversoresDataSource.totalElements);


    // sets an idle timeout of X seconds, for testing purposes.
    idle.setIdle(environment.idle);
    // sets a timeout period of Y seconds. after X+Y seconds of inactivity, the user will be considered timed out.
    idle.setTimeout(environment.idleTimeout);
    // sets the default interrupts, in this case, things like clicks, scrolls, touches to the document
    idle.setInterrupts(DEFAULT_INTERRUPTSOURCES);

    idle.onIdleEnd.subscribe(() => this.idleState = 'No longer idle.');
    idle.onTimeout.subscribe(() => {
      this.idleState = 'Timed out!';
      this.timedOut = true;

      this.authService.redirectLoginSessionExpiration();
    });
    idle.onIdleStart.subscribe(() => this.idleState = 'You\'ve gone idle!');
    idle.onTimeoutWarning.subscribe((countdown) => {
      this.idleState = 'You will time out in ' + countdown + ' seconds!';
    });

    // sets the ping interval to 15 seconds
    keepalive.interval(environment.idlePingInterval);

    keepalive.onPing.subscribe(() => this.lastPing = new Date());

    this.reset();

  }

  reset() {
    this.idle.watch();
    this.idleState = 'Started.';
    this.timedOut = false;

  }

  ngOnInit() {
    this.dataSource = new InversoresDataSource(this.investorService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      20,
      'name'
    );
    this.dataSource.getInversores(pageFilter);


    this.dataSourceStartup = new StartupsDataSource(this.startupService);
    const pageFilterStartup = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      20,
      'name'
    );
    this.dataSourceStartup.getStartups(pageFilterStartup);
  }
 }
