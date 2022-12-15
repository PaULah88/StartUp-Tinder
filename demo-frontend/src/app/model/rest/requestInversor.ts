
import { BusinessSector } from '../businessSector';
import { Inversor } from '../inversor';
import { RangeInvester } from '../rangeInvester';
import { StartupState } from '../startupState';

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

export class CreateInversorRequest {
  id:number;
  name:string;
  email:string;
  idInvesterRange?: RangeInvester;
  idBusinessSector?: BusinessSector;
  idStartUpState?: StartupState;

  constructor(inversor: Inversor) {
    this.id = inversor.id;
    this.name = inversor.name;
    this.email = inversor.email;
    this.idInvesterRange = inversor.idInvesterRange;
    this.idBusinessSector = inversor.idBusinessSector;
    this.idStartUpState = inversor.idStartUpState;
  }
}

export class EditInversorRequest extends CreateInversorRequest {
  override id: number;

  constructor(inversor: Inversor) {
    super(inversor);
    this.id = inversor.id;
  }
}
