import { Component, OnInit } from '@angular/core';
import { AdvertisementService } from '../advertisement.service';
import { Advertisement } from '../advertisement.model';
import { Router } from '@angular/router';
import { EndUserLinkService } from '../end-user-link.service';
import { StoreUidService } from '../store-uid.service';
import { AuthenticationService } from '../_services/authentication.service';
import { UserService } from '../_services/user.service';
import { User } from '../_models/user';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  advts: Advertisement[];
  credits;
  ide;
  currentUser: User;
  constructor(
    private service: AdvertisementService,
    private router: Router,
    private eul: EndUserLinkService,
    private euid: StoreUidService,
    private authenticationService: AuthenticationService,
    private userService: UserService
  ) { }
  openNav() {
    document.getElementById('mySidenav').style.width = '250px';
  }
  closeNav() {
    document.getElementById('mySidenav').style.width = '0';

  }

  ngOnInit() {
    // this.ide = this.euid.uId;
    // this.getAllAdsById(this.ide);
    this.getCurrentUserDetails();
    this.getAllAds();
  }

  getAllAds() {
    this.service.getAllAdvt()
      .subscribe(
        data => {
          this.advts = data;
        },
        error => {
          console.log(error);
        }
      );
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

  getAllAdsById(id: number) {

    this.eul.getAllAdsByEid(id).subscribe(
      data => {
        this.advts = data;
      },
      error => {
        console.log(error);
      }
    );
  }



  clickCount(id: number) {

    this.userService.clickify(id).subscribe(data => { console.log(data); },

      err => { console.log(err); });
  }

  creditCount(vid: number) {
    //this.credits=0;
    console.log(vid);

    this.clickCount(vid);
    console.log("clicked");
    this.userService.creditFlow(vid).subscribe(data => this.credits = data,
      err => { console.log(err); });
    this.creditShow();
    console.log(this.credits);

  }

  creditShow() {
    this.userService.showCredits().subscribe(data => { this.credits = data; console.log(data); },
      err => { console.log(err); });

    // console.log(this.credits)
    // return this.credits;
  }
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
