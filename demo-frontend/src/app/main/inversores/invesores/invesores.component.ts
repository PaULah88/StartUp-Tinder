import { SelectionModel } from '@angular/cdk/collections';
import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { fromEvent, merge, Observable, Observer } from 'rxjs';
import { debounceTime, distinctUntilChanged, tap } from 'rxjs/operators';
import { InversoresDataSource } from 'src/app/model/datasource/inversores.datasource';
import { Inversor } from 'src/app/model/inversor';
import { AnyField, AnyPageFilter, SortFilter } from 'src/app/model/rest/filter';
import { InversorService } from 'src/app/services/inversor.service';
import { ConfirmationDialogComponent } from 'src/app/shared/confirmation-dialog/confirmation-dialog.component';
import { CreateInversorComponent } from './create-inversor/create-inversor.component';

@Component({
  selector: 'app-invesores',
  templateUrl: './invesores.component.html',
  styleUrls: ['./invesores.component.scss'],
})
export class InvesoresComponent implements OnInit, AfterViewInit {
  dataSource: InversoresDataSource;
  displayedColumns = [
    'select',
    //'id',
    'name',
    'email',
    'idInvesterRange',
    'idBusinessSector',
    'idStartUpState',
  ];
  fields = [
    'id',
    'name',
    'email',
    'idInvesterRange',
    'idBusinessSector',
    'idStartUpState',
  ];

  selection = new SelectionModel<Inversor>(true, []);
  error = false;

  @ViewChild('edit') editTemplate: any;
  highlightedRow: Inversor;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('input') input: ElementRef;

  constructor(
    private investorService: InversorService,
    private translate: TranslateService,
    private router: Router,
    private dialog: MatDialog
  ) {}

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
  }

  ngAfterViewInit(): void {
    // server-side search
    fromEvent(this.input.nativeElement, 'keyup')
      .pipe(
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          this.paginator.pageIndex = 0;
          this.loadInversoresPage();
          console.log('Resultado do input:' + this.input.nativeElement.value);
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
          this.loadInversoresPage();
        })
      )
      .subscribe();
  }

  loadInversoresPage() {
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
    this.dataSource.getInversores(pageFilter);
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.inversorsSubject.value.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSource.inversorsSubject.value.forEach((row) =>
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
    const inversor = this.selection.selected[0];
    this.selection.deselect(inversor);
    if (this.selection.selected && this.selection.selected.length === 0) {
      this.investorService.deleteInversor(inversor.id).subscribe((response) => {
        console.log(response);
        if (response.responseCode !== 'OK') {
          this.error = true;
        } else {
          this.loadInversoresPage();
        }
      });
    } else {
      this.investorService.deleteInversor(inversor.id).subscribe((response) => {
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
    this.dialog.open(CreateInversorComponent, dialogConfig);
  }

  onEdit(row: Inversor) {
    this.highlightedRow = row;
    this.router.navigate(['/inversores/edit/' + row.id]);
  }

  onClear() {
  }

  changeProgresValue(row: string) {
    if (row == 'Pre-Seed') {
      return "20";
    } else if (row == 'Seed') {
      return "40";
    } else if (row == 'Early Stage') {
      return "60";
    } else if (row == 'Growth Stage') {
      return "80";
    } else if (row == 'Scaleup') {
      return "90";
    } else {
      return "100";
    }
  }
}
