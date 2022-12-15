import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StartupState } from '../model/startupState';
import { API_CONFIG } from '../shared/api.config';

@Injectable({
  providedIn: 'root',
})
export class StartupStateService {
  constructor(private http: HttpClient) {}

  public getStartupState(): Observable<StartupState[]> {
    const url = API_CONFIG.urlBaseStartUpState;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8'
      // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),

    });
    return this.http.get<StartupState[]>(url, { headers });
  }
}
