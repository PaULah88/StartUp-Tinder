import { DataSource } from '@angular/cdk/table';
import { BehaviorSubject } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { InversorService } from 'src/app/services/inversor.service';

import { Inversor } from '../inversor';
import { AnyPageFilter } from '../rest/filter';

export class InversoresDataSource extends DataSource<Inversor> {
  inversorsSubject = new BehaviorSubject<Inversor[]>([]);
  loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  public totalElements: number;

  constructor(private inversorService: InversorService) {
    super();
  }

  getInversores(pageFilter: AnyPageFilter) {
    this.inversorsSubject.next([]);
    this.loadingSubject.next(true);
    this.inversorService.getInversores(pageFilter).pipe(
      finalize(() => this.loadingSubject.next(false))
    ).subscribe(
      response => {
        this.totalElements = response.totalElements;
        this.inversorsSubject.next(response.data);
      }
    );
  }

  connect(): BehaviorSubject<Inversor[]> {
    return this.inversorsSubject;
  }

  disconnect(): void {
    this.inversorsSubject.complete();
    this.loadingSubject.complete();
  }
}
