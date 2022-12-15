import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Buffer } from 'buffer';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Inversor } from '../model/inversor';
import { AnyPageFilter } from '../model/rest/filter';
import { CreateInversorRequest, EditInversorRequest } from '../model/rest/requestInversor';
import { DataSourceRESTResponse } from '../model/rest/response';
import { API_CONFIG } from '../shared/api.config';

@Injectable({
  providedIn: 'root'
})
export class InversorService {

  constructor(private http: HttpClient) { }



  public getInversores(pageFilter: AnyPageFilter): Observable<DataSourceRESTResponse<Inversor[]>> {
    const url = API_CONFIG.getInvestorsPage;
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
      // Authorization: 'Basic ' + btoa(`${environment.clientName}:${environment.clientSecret}`),
  //    Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });

    console.log("ESTOY EN GETINVERSORES=",this.http.post<DataSourceRESTResponse<Inversor[]>>(url, pageFilter, { headers }));

    return this.http.post<DataSourceRESTResponse<Inversor[]>>(url, pageFilter, { headers });
  }

  public getInversor(id: number): Observable<Inversor> {
    const url = API_CONFIG.getInvestor;
    const headers = new HttpHeaders({
      'Content-type': 'charset=utf-8',
//      Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });
    const params = new HttpParams().set('id', id.toString());
    return this.http.get<Inversor>(url, { params, headers });
  }

  public createInversor(inversor: Inversor): Observable<any> {
    const url = API_CONFIG.createInvestor;
    const body: CreateInversorRequest = new CreateInversorRequest(inversor);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8'
    });
    return this.http.post<Inversor>(url, body, { headers }).pipe(
      catchError(e =>{
        return throwError(()=>e);
      })
    );
  }

  public editInversor(inversor: Inversor): Observable<any> {
    const url = API_CONFIG.editInvestor;
    const body: EditInversorRequest = new EditInversorRequest(inversor);
    const headers = new HttpHeaders({
      'Content-type': 'application/json; charset=utf-8',
 //     Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });
    return this.http.post<any>(url, body, { headers }).pipe(
      catchError((e:HttpErrorResponse) =>{
        return throwError(()=>e);
      })
    );
  }

  public deleteInversor(id: number): Observable<any> {
    const url = API_CONFIG.deleteInvestor;
    const headers = new HttpHeaders({
      'Content-type': 'charset=utf-8',
   //   Authorization: 'Basic ' + Buffer.from(`${environment.clientName}:${environment.clientSecret}`, 'utf8').toString('base64'),
    });
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete<any>(url, { params, headers });
  }
}
