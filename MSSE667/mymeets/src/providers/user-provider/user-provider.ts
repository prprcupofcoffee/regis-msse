import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { User } from '../../models/user';

/*
  Class for the UserProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class UserProvider {
  private usersUrl = 'api/users';

  constructor(private http: HttpClient) { }

  login(user: User) {
    this.http.get<User>(`{this.usersURL}?name={user.email}`)
  }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  getById(id: number): Observable<User> {
    return this.http.get<User>(`{this.usersUrl}/{id}`)
  }
}
