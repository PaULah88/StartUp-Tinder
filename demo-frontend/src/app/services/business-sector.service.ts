import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BusinessSector } from '../model/businessSector';
import { API_CONFIG } from '../shared/api.config';

@Injectable({
  providedIn: 'root',
})
export class BusinessSectorService {
  constructor(private http: HttpClient) {}

  public getBusinessSectors(): Observable<BusinessSector[]> {
    const url = API_CONFIG.urlBaseBusinessSector;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
    });
    return this.http.get<BusinessSector[]>(url, { headers });
  }
}
