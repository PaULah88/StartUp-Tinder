import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';
import { BusinessSector } from 'src/app/model/businessSector';
import { Entrepreneur } from 'src/app/model/entrepreneur';
import { StartupService } from 'src/app/services/startup.service';
import { Startup } from 'src/app/model/startup';
import { StartupState } from 'src/app/model/startupState';
import { BusinessSectorService } from 'src/app/services/business-sector.service';
import { EntrepreneureService } from 'src/app/services/entrepreneur.service';
import { LoggerService } from 'src/app/services/logger.service';
import { StartupStateService } from 'src/app/services/startup-state.service';

@Component({
  selector: 'app-edit-startup',
  templateUrl: './edit-startup.component.html',
  styleUrls: ['./edit-startup.component.scss'],
})
export class EditStartupComponent implements OnInit {
  idStartup: number;

  startupForm: FormGroup;
  startup: Startup;
  errores: string[];
  startupStates: StartupState[];
  businessSectors: BusinessSector[];
  entrepreneurs:Entrepreneur[];
  constructor(
    private businessSectorService: BusinessSectorService,
    private startupStateService:StartupStateService,
    private entrepreneurService:EntrepreneureService,
    private startupService: StartupService,
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private logger: LoggerService
  ) {
    this.startup = new Startup();
  }

  ngOnInit(): void {
    this.getBusinesSectors();
    this.getStartupState();
    this.getEntrepreneurs();
    this.createFormGroup();
    this.idStartup = this.route.snapshot.params['id'];
    if (this.idStartup) {
      this.startupService
        .getStartup(this.idStartup)
        .subscribe((response) => {
          this.startup = response;
          this.startupForm.patchValue(this.startup, {
            emitEvent: false,
            onlySelf: false,
          });
          this.logger.info(this.startup);
        });
    }
  }

  getBusinesSectors(){
    this.businessSectorService.getBusinessSectors().subscribe(response=>this.businessSectors=response)
      }

  getStartupState(){
    this.startupStateService.getStartupState().subscribe(response=>this.startupStates=response)
      }

  getEntrepreneurs(){
    this.entrepreneurService.getEntrepreneurs().subscribe(response=>this.entrepreneurs=response)
      }    

  onFormChanges() {
    this.startupForm.valueChanges.subscribe((val) => {});
  }

  createFormGroup() {
    this.startupForm = this.fb.group({
      id: [this.startup.id],
      name: [this.startup.name, Validators.required],
      email: [
        this.startup.email,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$'),
      ],
      description:[this.startup.description],
      idBusinessSector: [this.startup.idBusinessSector],
      idStartupState: [this.startup.idStartupState],
      anualInvoicing:[this.startup.anualInvoicing],
      fundationYear:[this.startup.fundationYear],
      idEntrepreneur: [this.startup.idEntrepreneur],
    });
  }

  save() {
    const newStartup: Startup = Object.assign({}, this.startupForm.value);
    //para saber se os IDs se pasanse como int
    console.log( this.startupForm.value)
    if (newStartup.id) {
      this.startupService.editStartup(newStartup).subscribe((response) => {
        this.redirectList(response);
             });
    } else {
      this.startupService.createStartup(newStartup).subscribe((response) => {
        this.redirectList(response);
      });
    }
  }

  redirectList(response: any) {
    if (response.responseCode === 'OK') {
      this.router.navigate(['/startup']);
    } else {
      console.log(response);
    }
  }

  compareObjects(o1: any, o2: any): boolean {
    if (o1 && o2) {
      return o1.id === o2.id;
    } else {
      return false;
    }
  }

  cancel() {
    this.router.navigate(['/startup']);
  }
}
