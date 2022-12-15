import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BusinessSector } from 'src/app/model/businessSector';
import { Inversor } from 'src/app/model/inversor';
import { RangeInvester } from 'src/app/model/rangeInvester';
import { StartupState } from 'src/app/model/startupState';
import { BusinessSectorService } from 'src/app/services/business-sector.service';
import { InversorService } from 'src/app/services/inversor.service';
import { LoggerService } from 'src/app/services/logger.service';
import { RangeInvesterService } from 'src/app/services/range-invester.service';
import { StartupStateService } from 'src/app/services/startup-state.service';

@Component({
  selector: 'app-create-inversor',
  templateUrl: './create-inversor.component.html',
  styleUrls: ['./create-inversor.component.scss']
})
export class CreateInversorComponent implements OnInit {
  idInversor: number;
  inversorForm: FormGroup;
  inversor: Inversor;
  errores: string[];
rangeInvesters: RangeInvester[];
businessSectors: BusinessSector[];
startupStates:StartupState[];
  constructor(
    private rangeInvesterService: RangeInvesterService,
    private businessSectorService: BusinessSectorService,
    private startupStateService:StartupStateService,
    private fb: FormBuilder,
    private inversorService: InversorService,
    private router: Router,
    private route: ActivatedRoute,
    private logger: LoggerService
  ) {
    this.inversor = new Inversor();
  }
  ngOnInit(): void {
    this.getRangeInvesters();
    this.getBusinesSectors();
    this.getStartupState();
    this.createFormGroup();
    this.idInversor = this.route.snapshot.params['id'];
    if (this.idInversor) {
      this.inversorService
        .getInversor(this.idInversor)
        .subscribe((response) => {
          this.inversor = response;
          this.inversorForm.patchValue(this.inversor, {
            emitEvent: false,
            onlySelf: false,
          });
          this.logger.info(this.inversor);
        });
    }
  }
  getRangeInvesters(){
this.rangeInvesterService.getRangeInvesters().subscribe(response=>this.rangeInvesters=response)
  }
  getBusinesSectors(){
    this.businessSectorService.getBusinessSectors().subscribe(response=>this.businessSectors=response)
      }
  getStartupState(){
    this.startupStateService.getStartupState().subscribe(response=>this.startupStates=response)
      }
  onFormChanges() {
    this.inversorForm.valueChanges.subscribe((val) => {});
  }
  createFormGroup() {
    this.inversorForm = this.fb.group({
      id: [this.inversor.id],
      name: [this.inversor.name, Validators.required],
      email: [
        this.inversor.email,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$'),
      ],
      idInvesterRange: [this.inversor.idInvesterRange],
      idBusinessSector: [this.inversor.idBusinessSector],
      idStartUpState: [this.inversor.idStartUpState],
    });
  }
  save() {
    const newInversor: Inversor = Object.assign({}, this.inversorForm.value);
    //para saber se os IDs se pasanse como int
    console.log( this.inversorForm.value)
    if (newInversor.id) {
      this.inversorService.editInversor(newInversor).subscribe((response) => {
        this.redirectList(response);
             });
    } else {
      this.inversorService.createInversor(newInversor).subscribe((response) => {
        this.redirectList(response);
      });
    }
  }
  redirectList(response: any) {
    if (response.responseCode === 'OK') {
      this.router.navigate(['/inversores']);
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
    this.router.navigate(['/inversores']);
  }
}
