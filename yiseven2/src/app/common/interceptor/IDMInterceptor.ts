import {HttpHandler, HttpInterceptor, HttpErrorResponse, HttpResponse, HttpHeaderResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {HttpRequest, HttpEvent} from '@angular/common/http';
import {Observable, of, throwError} from 'rxjs';
import {catchError, mergeMap, tap} from 'rxjs/operators';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';
import {NzMessageService} from 'ng-zorro-antd';

@Injectable({providedIn: 'root'})
export class IDMInterceptor implements HttpInterceptor {

  autoDisplayErrorCode: number[] = [500, 404, 20001, 20002, 99999, 404, 11, 601, 602, 603, 604];

  constructor(private router: Router, private nzMessageService: NzMessageService,) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let phone = sessionStorage.getItem(environment.current_user);
    if (phone == null && req.url != 'http://localhost:8888//login') {
      this.router.navigate(['index/login']);
      this.nzMessageService.error('请先登录', {nzDuration: 5000});
      return of();
    } else if (phone != null) {
      req = req.clone({
        withCredentials: true,
        setHeaders: {
          phone: phone
        }
      });
    }
    return next.handle(req).pipe(
      tap(response => {
          if (response instanceof HttpResponse) {
            if (response.status === 200 && response.body && response.body.status != 100) {
              const message = response.body.msg || '未知错误';
              if (response.body.status === 401 || response.body.status === 11) {
                //重新登录
                this.router.navigate(['index/login']);
              }
              //自动报错
              if (this.autoDisplayErrorCode.includes(response.body.status)) {
                this.nzMessageService.error(response.body.msg, {nzDuration: 5000});
              }
            }
          }
        },
        error => {
          if (error instanceof HttpErrorResponse) {
            if (error.status >= 500) {
              this.nzMessageService.error(
                `服务器内部错误 ${error.status}`
              );
            } else if (error.status >= 400 && error.status < 500) {
              this.nzMessageService.error(
                `客户端参数错误 ${error.status}`
              );
            }
          }
        }
      )
    );
  }
}
