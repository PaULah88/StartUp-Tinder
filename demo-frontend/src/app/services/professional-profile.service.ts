import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProfessionalProfile } from '../model/ProfessionalProfile';
import { API_CONFIG } from '../shared/api.config';

@Injectable({
  providedIn: 'root',
})
export class ProfessionalProfileService {
  constructor(private http: HttpClient) {}

  public getProfessionalProfile(): Observable<ProfessionalProfile[]> {
    return this.http.get<ProfessionalProfile[]>(
      `${API_CONFIG.urlProfessionalProfile}`
    );
  }

  getPage(request): Observable<any> {
    const params = request;
    return this.http.get(`${API_CONFIG.getPPage}`, { params });
  }
}
