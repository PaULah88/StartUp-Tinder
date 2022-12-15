import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { EditStartupComponent } from './edit-startup/edit-startup.component';
import { StartupLayoutComponent } from './startup-layout/startup-layout.component';
import { StartupComponent } from './startup.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { TranslateModule } from '@ngx-translate/core';
import { CreateStartupComponent } from './create-startup/create-startup.component';
import { StartupRoutingModule } from './startup-routing.module';
import { MatDialogModule } from '@angular/material/dialog';




@NgModule({
  declarations: [
    EditStartupComponent,
    StartupComponent,
    StartupLayoutComponent,
    CreateStartupComponent
  ],
  imports: [
    MatDialogModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatDatepickerModule,
    MatSelectModule,
    FormsModule,
    MatRadioModule,
    MatCardModule,
    MatGridListModule,
    CommonModule,
    StartupRoutingModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    MatTableModule,
    MatCardModule,
    MatCheckboxModule,
    TranslateModule,

  ]
})
export class StartupModule { }
