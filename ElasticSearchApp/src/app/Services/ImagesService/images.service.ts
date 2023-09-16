import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ImageUrl} from "../../Models/ImageUrl";
import {Observable} from "rxjs";
import {endpoints} from "../../endpoints";

@Injectable({
  providedIn: 'root'
})
export class ImagesService {
  constructor(private http: HttpClient) { }
  getImageUrls(searchText: string): Observable<ImageUrl[]>{
    return this.http.post<ImageUrl[]>(endpoints.ServerUrl + endpoints.GetImages, searchText);
  }
}
