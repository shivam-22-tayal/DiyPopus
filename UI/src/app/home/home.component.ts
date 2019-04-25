import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { first } from 'rxjs/operators';
import { ReactiveFormsModule, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

import { User } from '../_models/user';
import { UserService } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';
import { AlertService } from '../_services/alert.service';
import { AdsModel } from '../_models/adsModel';
import { AdsService } from '../_services/ads.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  currentUser: User;
  currentUserSubscription: Subscription;
  users: User[] = [];
  user = { firstName: '', lastName: '', email: '' };
  categoryadd = new FormControl('');
  product = new FormControl('');
  dop = new FormControl('');
  description = new FormControl('');
  adsByOthers: AdsModel[];

  myAds: AdsModel[];

  constructor(
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private router: Router,
    private alertService: AlertService,
    private adsService: AdsService
  ) {
    this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
  }

  ngOnInit() {
    //this.loadUser();
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.currentUserSubscription.unsubscribe();
  }

  private loadUser() {
    this.userService.getCurrentUserDetails()
      .subscribe(
        data => {
          console.log(data);
        },
        error => {
          console.log(error);
        }
      );
  }
  // ads() {
  //   if (this.categoryadd.value !== '' && this.product.value !== '' && this.dop.value !== '' 
  //   && this.description.value !== '') {

  //     const adsInfo = {
  //       categoryadd: this.categoryadd.value,
  //       product: this.product.value,
  //       dop: this.dop.value,
  //       description: this.description.value
  //     };

  //     this.adsService.postAds(adsInfo)
  //       .pipe(first())
  //       .subscribe(
  //         data => {
  //           this.alertService.success('Your Ad is Added', true);
  //           this.router.navigate(['/owner-dashboard']);
  //         },
  //         error => {
  //           this.alertService.error(error);
  //         });
  //   }
  // }
  // getAdsByOthers() {
  //   this.adsService.getAdsByOthers(this.user.email)
  //     .subscribe(
  //       data => {
  //         this.adsByOthers = data;
  //       },
  //       error => {
  //         this.alertService.error(error);
  //       });
  // }
}
