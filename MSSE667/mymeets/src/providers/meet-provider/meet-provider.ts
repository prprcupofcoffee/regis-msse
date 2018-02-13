import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { Meet } from '../../models/meet';

/*
  Class for the MeetProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class MeetProvider {
  private meetsUrl = 'api/meets';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Meet[]> {
    return this.http.get<Meet[]>(this.meetsUrl);
  }

  // getById(id: number): Meet {
  //   return this.http.get(`{this.meetsUrl}/{id}`)
  // }
}
