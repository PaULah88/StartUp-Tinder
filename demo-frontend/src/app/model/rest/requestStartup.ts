
import { BusinessSector } from '../businessSector';
import { Entrepreneur } from '../entrepreneur';
import { StartupState } from '../startupState';
import { Startup } from '../startup';

export class QuerySortPaginationRequest {
  query: string;
  pageIndex: number;
  pageSize: number;
  sortDirection: string;
  sortColumn: string;

  constructor(query: string, pageIndex: number, pageSize: number, sortDirection: string, sortColumn: string) {
    this.query = query;
    this.pageIndex = pageIndex;
    this.pageSize = pageSize;
    this.sortDirection = sortDirection;
    this.sortColumn = sortColumn;
  }
}

export class CreateStartupRequest {
  id: number;
  name: string;
  email: string;
  description?: string;
  idBusinessSector?: BusinessSector;
  idStartupState?:StartupState;
  anualInvoicing?: number;
  fundationYear?: Date;
  idEntrepreneur?: Entrepreneur;

  constructor(startup: Startup) {
    this.id = startup.id;
    this.name = startup.name;
    this.email = startup.email;
    this.description = startup.description;
    this.idBusinessSector = startup.idBusinessSector;
    this.idStartupState = startup.idStartupState;
    this.anualInvoicing = startup.anualInvoicing;
    this.fundationYear = startup.fundationYear;
    this.idEntrepreneur = startup.idEntrepreneur;
  }
}

export class EditStartupRequest extends CreateStartupRequest {
  override id: number;

  constructor(startup: Startup) {
    super(startup);
    this.id = startup.id;
  }
}
