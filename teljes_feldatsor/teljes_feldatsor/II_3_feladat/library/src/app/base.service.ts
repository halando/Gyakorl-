import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Kolcsonzesek } from 'src/Kolcsonzesek';
import { Kolcsonzok } from 'src/Kolcsonzok';

@Injectable({
  providedIn: 'root'
})
export class BaseService {

  private baseUrl = 'http://localhost:8000/';

  constructor(private http: HttpClient) { }

  getKolcsonzesek(id: number): Observable<Kolcsonzesek[]> {
    return this.http.get<Kolcsonzesek[]>(`${this.baseUrl}kolcsonzesek?kolcsonzoId=${id}`);
  }

  createKolcsonzes(kolcsonzes: Kolcsonzesek): Observable<Kolcsonzesek> {
    return this.http.post<Kolcsonzesek>(`${this.baseUrl}kolcsonzesek`, kolcsonzes);
  }

  getKolcsonzes(id: number): Observable<Kolcsonzesek> {
    return this.http.get<Kolcsonzesek>(`${this.baseUrl}kolcsonzesek/${id}`);
  }

  updateKolcsonzes(id: any, kolcsonzes: Kolcsonzesek): Observable<Kolcsonzesek> {
    return this.http.put<Kolcsonzesek>(`${this.baseUrl}kolcsonzesek/${id}`, kolcsonzes);
  }

  deleteKolcsonzes(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}kolcsonzesek/${id}`);
  }

  getKolcsonzok(): Observable<Kolcsonzok[]> {
    return this.http.get<Kolcsonzok[]>(`${this.baseUrl}kolcsonzok`);
  }

  getKolcsonzo(id: number): Observable<Kolcsonzok> {
    return this.http.get<Kolcsonzok>(`${this.baseUrl}kolcsonzok/${id}`);
  }
}
