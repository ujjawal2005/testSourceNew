import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import {of as observableOf,  Observable } from 'rxjs';


@Injectable()
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(username: string, password: string) {
      const params = new HttpParams()
        .set('username', username)
        .set('password', password)
        .set('grant_type', 'password');
/*
, {
          headers: {
            'Content-Type' : 'application/x-www-form-urlencoded',
            'Cache-Control': 'no-cache'
          },
          params: params
        }


        this.http
        .get(url, return new RequestOptions({
          headers: new Headers({
            Authorization: `Bearer ${authtoken}`
          }),
        }))
        .map(res => res.json());
 */
      // return this.http.post<any>(`/users/authenticate`, { username: username, password: password })
      // this,http.get().add()
      return this.http.get<any>(`http://localhost:9191/api/oauth/token`,
        {
          headers: {
            'Content-Type' : 'application/x-www-form-urlencoded',
            'Cache-Control': 'no-cache',
            'Authorization': `Basic  Y2hpbGRjYXJlYXBwOnRlbXBvcmFyeQ==`
          },
          params: params
          // 'Authorization': `Basic  Y2hpbGRjYXJlYXBwOnRlbXBvcmFyeQ==`
        }
          // 'user': 'childcareapp:temporary'
        // }
      )

            .pipe(map(user => {
                // login successful if there's a jwt token in the response
                if (user && user.access_token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }

                return user;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
  refreshToken() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    let token = currentUser.refreshToken;
    const params = new HttpParams()
      .set('grant_type', 'refresh_token')
      .set('refresh_token', token);
    return this.http.post<any>("http://localhost:9191/api/oauth/token", { 'token': token },
      {
        headers: {
          'Content-Type' : 'application/x-www-form-urlencoded',
          'Cache-Control': 'no-cache',
          'Authorization': `Basic  Y2hpbGRjYXJlYXBwOnRlbXBvcmFyeQ==`
        },
        params: params
      })
      .pipe(
        map(user => {
          if (user && user.access_token) {
            localStorage.setItem('currentUser', JSON.stringify(user));
          }
          return user;
        }));
  }
}
