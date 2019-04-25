import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../_models/user';
import { AdsModel } from '../_models/adsModel';
import { Observable, config } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AdsService {

  config = {apiUrl: 'http://localhost:8080/api/post'};
    constructor(private http: HttpClient) { }

    postAds(adsInfo) {
      return this.http.post(`${this.config.apiUrl}`, adsInfo);
    }

    getAdsByOthers(email): Observable<AdsModel[]> {
      return this.http
      .get<AdsModel[]>(`${this.config.apiUrl}/others`)
      .pipe(map((response) => response));
    }

    getMyAds(): Observable<AdsModel[]> {
      return this.http
      .get<AdsModel[]>(`${this.config.apiUrl}/my`)
      .pipe(map(response => response));
    }
    // getMyPosts(): Observable<PostModel[]> {
    //   return this.http
    //   .get<PostModel[]>(`${this.config.apiUrl}`)
    //   .pipe(map(response => response));
    // }
}
