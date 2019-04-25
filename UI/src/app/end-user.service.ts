import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EndUserService {
id: number;
firstname: string;
lastname: string;
username: string;
email: string;
password: string;
phone: number;
gender: string;
type: string;

  /*constructor(firstname:string,lastname:string,email:string,password:string,phone:number,gender:string,role:string) {

//this.id=id;
this.firstname=firstname;
this.lastname=lastname;
this.email=email;
this.password=password;
this.phone=phone;
this.gender=gender;
this.role=role;

   }*/
}
