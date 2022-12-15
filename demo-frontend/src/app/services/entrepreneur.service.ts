import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Entrepreneur } from '../model/entrepreneur';
import { API_CONFIG } from '../shared/api.config';

@Injectable({
  providedIn: 'root',
})
export class EntrepreneureService {
  constructor(private http: HttpClient) {}

  public getEntrepreneurs(): Observable<Entrepreneur[]> {
    const url = API_CONFIG.urlBaseEntrepreneur;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
   //   Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });
    return this.http.get<Entrepreneur[]>(url, { headers });
  }
}
