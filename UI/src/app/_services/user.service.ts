import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../_models/user';
import { AdsModel } from '../_models/adsModel';
import { Observable, config } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class UserService {
  config = { apiUrl: 'http://localhost:8080/api' };
  constructor(private http: HttpClient) { }

  getCurrentUserDetails() {
    return this.http.get<User>(`${this.config.apiUrl}/user/me`);
  }

  // getAll() {
  //     return this.http.get<User[]>(`${this.config.apiUrl}/users`);
  // }

  // getById(id: number) {
  //     return this.http.get(`${this.config.apiUrl}/users/${id}`);
  // }

  register(user) {
    console.log("running register");
    return this.http.post(`${this.config.apiUrl}/auth/signup`, user);
  }

  // update(user: User) {
  //     return this.http.put(`${this.config.apiUrl}/users/${user.id}`, user);
  // }

  // delete(id: number) {
  //     return this.http.delete(`${this.config.apiUrl}/users/${id}`);
  // }
  clickify(vID: number){

    return this.http.get(`${this.config.apiUrl}/advt/${vID}/clicks`)
    .pipe(map(response=> response));
  }
  
  showEnduser(id: number){
  
    return this.http.get(`${this.config.apiUrl}/endUser/${id}/profile`).
    pipe(map(response => response));
  }
  
  creditFlow(vid: number){
    return this.http.get(`${this.config.apiUrl}/my/${vid}`).
    pipe(map(response => response));
  }
  
  showCredits(){
    return this.http.get(`${this.config.apiUrl}/my/upcredits`).pipe(map(response=>response));
  }
  
  findId(euser){
    return this.http.get(`${this.config.apiUrl}/endUser/userprofileid`).pipe(map(response=>response));
  
  }
  
  getDownCredits(){

    return this.http.get(`${this.config.apiUrl}/my/downcredits`).pipe(map(response=>response));
  }
  
}
