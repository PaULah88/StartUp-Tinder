import { SelectionModel } from '@angular/cdk/collections';
import {
  AfterViewInit, Component, ElementRef, OnInit, ViewChild
} from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { fromEvent, merge, Observable, Observer } from 'rxjs';
import { debounceTime, distinctUntilChanged, tap } from 'rxjs/operators';
import { StartupsDataSource } from 'src/app/model/datasource/startup.datasource';
import { AnyField, AnyPageFilter, SortFilter } from 'src/app/model/rest/filter';
import { Startup } from 'src/app/model/startup';
import { StartupService } from 'src/app/services/startup.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { CreateStartupComponent } from './create-startup/create-startup.component';

@Component({
  selector: 'app-startup',
  templateUrl: './startup.component.html',
  styleUrls: ['./startup.component.scss']
})
export class StartupComponent implements OnInit, AfterViewInit {

  dataSource: StartupsDataSource;
  displayedColumns = [
    'select',
    'name',
    'email',
    'idBusinessSector',
    'idStartUpState',
    'anualInvoicing',
    'fundationYear',
  ];
  fields = ['name', 'email', 'description', 'idBusinessSector', 'idStartUpState','anualInvoicing','fundationYear','idEntrepreneur'];

  selection = new SelectionModel<Startup>(true, []);
  error = false;

  @ViewChild('edit') editTemplate: any;
  highlightedRow: Startup;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('input') input: ElementRef;

  constructor(
    private startupService: StartupService,
    private translate: TranslateService,
    private router: Router,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.dataSource = new StartupsDataSource(this.startupService);
    const pageFilter = new AnyPageFilter(
      '',
      this.fields.map((field) => new AnyField(field)),
      0,
      20,
      'name'
    );
    this.dataSource.getStartups(pageFilter);
  }

  ngAfterViewInit(): void {
    // server-side search
    fromEvent(this.input.nativeElement, 'keyup')
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          this.paginator.pageIndex = 0;
          this.loadStartupsPage();
        })
      )
      .subscribe();

    // reset the paginator after sorting
    this.sort.sortChange.subscribe(() => {
      this.paginator.pageIndex = 0;
      this.selection.clear();
    });

    // on sort or paginate events, load a new page
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        tap(() => {
          this.loadStartupsPage();
        })
      )
      .subscribe();
  }

  loadStartupsPage() {
    this.selection.clear();
    this.error = false;
    const pageFilter = new AnyPageFilter(
      this.input.nativeElement.value,
      this.fields.map((field) => new AnyField(field)),
      this.paginator.pageIndex,
      this.paginator.pageSize
    );
    pageFilter.order = [];
    pageFilter.order.push(
      new SortFilter(this.sort.active, this.sort.direction.toString())
    );
    this.dataSource.getStartups(pageFilter);
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.startupsSubject.value.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSource.startupsSubject.value.forEach((row) =>
        this.selection.select(row)
      );
  }

  onDelete() {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '350px',
      data: this.translate.instant('delete-element-confirmation'),
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.delete();
        return new Observable((observer: Observer<boolean>) =>
          observer.next(true)
        );
      } else {
        return new Observable((observer: Observer<boolean>) =>
          observer.next(false)
        );
      }
    });
  }

  delete() {
    const startup = this.selection.selected[0];
    this.selection.deselect(startup);
    if (this.selection.selected && this.selection.selected.length === 0) {
      this.startupService.deleteStartup(startup.id).subscribe((response) => {
        console.log(response)
        if (response.responseCode !== 'OK') {
          this.error = true;
        } else {
          this.loadStartupsPage();
        }
      });
    } else {
      this.startupService.deleteStartup(startup.id).subscribe((response) => {
        console.log(response);
        if (response.responseCode !== 'OK') {
          this.error = true;
        }
        this.delete();
      });
    }
  }

  onAdd() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    this.dialog.open(CreateStartupComponent, dialogConfig);
  }

  onEdit(row: Startup) {
    this.highlightedRow = row;
    this.router.navigate(['/startup/edit/' + row.id]);
  }

    onClear() {
  }

  changeProgresValue(row: string) {
    if (row == 'Pre-Seed') {
      return 20;
    } else if (row == 'Seed') {
      return 40;
    } else if (row == 'Early Stage') {
      return 60;
    } else if (row == 'Growth Stage') {
      return 80;
    } else if (row == 'Scaleup') {
      return 90;
    } else {
      return 100;
    }
  }

}
