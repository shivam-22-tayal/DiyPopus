import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Advertisement } from './advertisement.model';
import { map } from 'rxjs/operators';
import {Observable} from 'rxjs';
import { EndUserService } from './end-user.service';


@Injectable({
  providedIn: 'root'
})
export class EndUserLinkService {

  constructor(private http: HttpClient) { }
  private apiUrl = 'http://localhost:8080/advertisements';
  

  getAllAdsByEid(eID: number): Observable<Advertisement[]>{



    return this.http.get<Advertisement[]>(`${this.apiUrl}/endUser/${eID}`)

    .pipe(map(response => response));

  }



  createEndUser(euser){

    return this.http.post(`${this.apiUrl}/endUser`,euser);

  }



  /*findUserProfile(eID:number){

    return this.http.get<EndUserService>(`${this.apiUrl}/endUser/${eID}/profile`);

  }*/



  /*deleteAds(vID:number){

    return this.http.delete(`${this.apiUrl}/${vID}`)

    .pipe(map(response => response));

  }



  updateAds(ad:AdsService){

    return this.http.put(`${this.apiUrl}`,ad).pipe(map(response => response));

  }*/

clickify(vID: number){

  return this.http.get(`${this.apiUrl}/${vID}/clicks`)
  .pipe(map(response=> response));
}

showEnduser(id: number){

  return this.http.get(`${this.apiUrl}/endUser/${id}/profile`).
  pipe(map(response => response));
}

creditFlow(eid: number, vid: number){
  return this.http.get(`${this.apiUrl}/endUser/${eid}/${vid}`).
  pipe(map(response => response));
}

showCredits(eid: number){
  return this.http.get(`${this.apiUrl}/endUser/${eid}/credits`).pipe(map(response=>response));
}

findId(euser){
  return this.http.get(`${this.apiUrl}/endUser/userprofileid`).pipe(map(response=>response));

}


}
