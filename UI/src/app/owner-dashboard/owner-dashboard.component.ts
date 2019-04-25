import { Component, OnInit } from '@angular/core';
import { AdvertisementService } from '../advertisement.service';
import { Advertisement } from '../advertisement.model';
import { Router } from '@angular/router';
import * as jwt_decode from 'jwt-decode';

import { AdsModel } from '../_models/adsModel';
import { AlertService } from '../_services/alert.service';
import { AdsService } from '../_services/ads.service';
import { AuthenticationService } from '../_services/authentication.service';
import { UserService } from '../_services/user.service';
import { User } from '../_models/user';

@Component({
  selector: 'app-owner-dashboard',
  templateUrl: './owner-dashboard.component.html',
  styleUrls: ['./owner-dashboard.component.css']
})
export class OwnerDashboardComponent implements OnInit {

  myPosts: AdsModel[];
  myAds: Advertisement[];
  advtInfo: Advertisement;
  idA: any;
  category;
  product;
  dop;
  desc;
  aid;
 downcredits;
  currentUser: User;
  constructor(private service: AdvertisementService,
    private router: Router, private alertService: AlertService,
    private postService: AdsService,
    private authenticationService: AuthenticationService,
    private userService: UserService) {

    this.service.getAllAdvt().subscribe(
      data => {
        this.myAds = data;
      },
      error => {
        console.log(error);
      }
    );
  }


  ngOnInit() {
    // this.getAllAdvt();
    this.getCurrentUserDetails();
    this.getMyAds();
    this.showcredits();
  }

  getCurrentUserDetails() {

    this.userService.getCurrentUserDetails()
      .subscribe(
        data => {
          console.log(data);
          this.currentUser = data;
        },
        error => console.log(error)
      );
  }

  getAllAdvt() {
    this.service.getAllAdvt().subscribe(
      data => {
        this.myAds = data;
      },
      error => {
        console.log(error);
      }
    );
  }

  submitAdvt() {


    if (this.category !== undefined && this.product !== undefined && this.dop !== undefined && this.desc !== undefined) {
      console.log('running');
      // this.advtInfo = new Advertisement(
      //   this.category,
      //   this.product,
      //   this.dop,
      //   this.desc);
      let advtObj = {
        categoryadd: this.category,
        product: this.product,
        dop: this.dop,
        description: this.desc
      };

      this.service.writeAdvt(advtObj)
        .subscribe(
          response => {
            alert('Advertisement has been posted...!!');
            this.router.navigateByUrl('/showAdd');

          },
          error => {
            console.log(error);

          });
      // this.service.getAdId(this.advtInfo).subscribe(
      //   response => {
      //     this.idA = response;

      //   },
      //   error => {
      //     console.log(error);

      //   });

      // this.advtInfo.id = this.idA;


      this.category = '';
      this.product = '';
      this.dop = '';
      this.desc = '';
    }
  }


  // clickCount(id: number) {

  //   this.service.clickify(id).subscribe(data => { console.log(data); },

  //     err => { console.log(err); });
  // }
  getMyAds() {
    this.service.getMyAds()
      .subscribe(
        data => {
          this.myAds = data;
        },
        error => {
          console.log(error);
        }
      );
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  showcredits(){

    this.userService.getDownCredits().subscribe(data=>{this.downcredits=data;},
      err=>{console.log(err);});
  }

}


