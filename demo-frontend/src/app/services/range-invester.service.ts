import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RangeInvester } from '../model/rangeInvester';
import { API_CONFIG } from '../shared/api.config';


@Injectable({
  providedIn: 'root',
})
export class RangeInvesterService {
  constructor(private http: HttpClient) {}

  public getRangeInvesters(): Observable<RangeInvester[]> {
    const url = API_CONFIG.urlBaseRange;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
    });
    return this.http.get<RangeInvester[]>(url, { headers });
  }
}
