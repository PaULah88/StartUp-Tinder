import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { User } from 'src/app/model/user';
import { LoggerService } from 'src/app/services/logger.service';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-pop-up-user',
  templateUrl: './pop-up-user.component.html',
  styleUrls: ['./pop-up-user.component.scss'],
})
export class PopUpUserComponent implements OnInit {
  idUser: number;
  userForm: FormGroup;
  typesForm: FormControl;
  user: User;
  errores: string[];
  selectedOption: string;
  printedOption: string;


  options = [
    { name: "Escolle unha opción", value: 0 },
    { name: "startup", value: 1 },
    { name: "inversor", value: 2 }
  ]

  ngSelect = 0;


  constructor(
    private translate: TranslateService,
    private router: Router,
    private dialogRefUser: MatDialog,
    private fb: FormBuilder,
    private userService: UserService,
    private route: ActivatedRoute,
    private logger: LoggerService
  ) {
    this.user = new User();
  }

  openDialogUser() {
    this.dialogRefUser.open(PopUpUserComponent);
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
  }

  createFormGroup() {
    this.userForm = this.fb.group({
      id: [this.user.id],
      name: [this.user.name],
      surname1: [this.user.name],
      surname2: [this.user.name],
      login: [this.user.name],
      password: [this.user.password],
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