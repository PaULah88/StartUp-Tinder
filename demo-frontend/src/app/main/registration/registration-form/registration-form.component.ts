import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatStepper } from '@angular/material/stepper';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Profile } from 'src/app/model/profile';
import { User } from 'src/app/model/user';
import { LoggerService } from 'src/app/services/logger.service';
import { UserService } from 'src/app/services/user.service';

interface Tipo {
  name: string;
  value: number;
}

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.scss'],
})

export class RegistrationFormComponent implements OnInit {
@ViewChild('stepper') stepper: MatStepper;
  idUser: number;
  userForm: FormGroup;
  typesForm: FormControl;
  user: User;
  errores: string[];
  selectedOption: string;
  printedOption: string;
  title = 'newMat';
  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  profiles: Profile[];

  options = [
    { name: 'startup', value: 1 },
    { name: 'inversor', value: 2 },
  ];

  profilesOption: Tipo[] = [
    { name: 'inversor', value: 2 },
    { name: 'startup', value: 3 },
  ];
  ngSelect = 0;

  constructor(
    private translate: TranslateService,
    private router: Router,
    private dialogRefUser: MatDialog,
    private fb: FormBuilder,
    private userService: UserService,
    private route: ActivatedRoute,
    private logger: LoggerService,
    private _formBuilder: FormBuilder
  ) {
    this.user = new User();
  }

  ngOnInit(): void {
    this.createFormGroup();
    this.idUser = this.route.snapshot.params['id'];
    if (this.idUser) {
      this.userService.getUser(this.idUser).subscribe((response) => {
        this.user = response;
        this.userForm.patchValue(this.user, {
          emitEvent: false,
          onlySelf: false,
        });
        this.logger.info(this.user);
      });
    }

    this.firstFormGroup = this.userForm;
  }

  submit() {
    console.log(this.firstFormGroup.value);
    console.log(this.secondFormGroup.value);
  }

  createFormGroup() {
    this.userForm = this.fb.group({
      id: [this.user.id],
      name: [this.user.name],
      surname1: [this.user.surname1],
      surname2: [this.user.surname2],
      login: [this.user.login, Validators.required],
      password: [this.user.password, Validators.required],
      profiles: [this.user.profiles, Validators.required],
    });
  }

  save() {
    const newUser: User = Object.assign({}, this.userForm.value);
    //para saber se os IDs se pasanse como int
    console.log(this.userForm.value);
    if (newUser.id) {
      this.userService.editUser(newUser).subscribe((response) => {
        this.redirectList(response);
      });
    } else {
      this.userService.createUser(newUser).subscribe((response) => {
        this.redirectList(response);
      });
    }
  }
  redirectList(response: any) {
    if (response.responseCode === 'OK') {
      this.router.navigate(['/users']);
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
}
