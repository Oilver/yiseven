import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  queryUserList(json: any): Observable<any> {
    return this.http.post(environment.url + '/user/queryUserList', json);
  }

  updateUser(json: any): Observable<any> {
    return this.http.post(environment.url + '/user/update', json);
  }

  queryCurrentUser(json: any): Observable<any> {
    return this.http.post(environment.url + '/user/queryCurrentUser', json);
  }

  delete(id: any): Observable<any> {
    return this.http.post(environment.url + '/user/delete', id);
  }
}
