import { DataSource } from '@angular/cdk/table';
import { BehaviorSubject } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { StartupService } from 'src/app/services/startup.service';

import { Startup } from '../startup';
import { AnyPageFilter } from '../rest/filter';

export class StartupsDataSource extends DataSource<Startup> {
  startupsSubject = new BehaviorSubject<Startup[]>([]);
  loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  public totalElements: number;

  constructor(private startupService: StartupService) {
    super();
  }

  getStartups(pageFilter: AnyPageFilter) {
    this.startupsSubject.next([]);
    this.loadingSubject.next(true);
    this.startupService.getStartups(pageFilter).pipe(
      finalize(() => this.loadingSubject.next(false))
    ).subscribe(
      response => {
        this.totalElements = response.totalElements;
        this.startupsSubject.next(response.data);
      }
    );
  }

  connect(): BehaviorSubject<Startup[]> {
    return this.startupsSubject;
  }

  disconnect(): void {
    this.startupsSubject.complete();
    this.loadingSubject.complete();
  }
}
