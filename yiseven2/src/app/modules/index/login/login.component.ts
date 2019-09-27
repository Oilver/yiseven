import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {IndexService} from '../../../service/index.service';
import {environment} from '../../../../environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  validateForm: FormGroup;
  success: boolean = true;
  errorMessage = '';

  constructor(private fb: FormBuilder, private router: Router, private indexService: IndexService) {

  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      phone: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    if (this.validateForm.invalid) {
      return;
    }

    this.indexService.login(this.validateForm.value).subscribe(result => {
      if (result.status == 100) {
        this.success = true;
        sessionStorage.setItem(environment.current_user, result.data);
        this.router.navigate(['account/index'], {
          queryParams: {}
        });
      } else {
        this.success = false;
        this.errorMessage = result.msg;
      }
    });
  }

  registry() {
    this.router.navigateByUrl('/index/registry');
  }

}
