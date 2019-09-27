import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecordService {

  constructor(private http: HttpClient) {
  }

  queryList(json: any): Observable<any> {
    return this.http.post(environment.url + '/record/queryList', json);
  }

  add(json: any): Observable<any> {
    return this.http.post(environment.url + '/record/add', json);
  }
}
