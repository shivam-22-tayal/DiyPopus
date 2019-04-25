import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService } from '../_services/alert.service';
import { AuthenticationService } from '../_services/authentication.service';
import { User } from '../_models/user';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  user: User;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private userService: UserService
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) {
          this.router.navigate(['/home']);
    }
  }

  ngOnInit() {

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams[' returnUrl '] || '/';
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    console.log("Login Component");
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService
      .login(this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        data => {
          this.userService.getCurrentUserDetails()
          .subscribe(
            res => {
              if(res){
                if(res.type === 'business' || res.type === 'buser'){
                  this.router.navigate(['/owner-dashboard']);
                }
                else if(res.type === 'normal' || res.type === 'nuser'){
                  this.router.navigate(['/user-dashboard']);
                }
              }
            },
            err => console.log(err)
          );
          //this.router.navigate(['/home']);
        },
        error => {
          if (error === 'Wrong Credentials') {
            this.alertService.error('Invalid Username / Password');
          } else {
            this.alertService.error(error);
          }
          this.loading = false;
          console.log(error);
        }
      );
  }

}
