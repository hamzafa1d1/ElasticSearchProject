import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ImageUrl} from "../../Models/ImageUrl";
import {Observable} from "rxjs";
import {endpoints} from "../../endpoints";
import {SearchText} from "../../Models/SearchText";

@Injectable({
  providedIn: 'root'
})
export class ImagesService {
  constructor(private http: HttpClient) { }
  getImageUrls(searchText: SearchText): Observable<ImageUrl[]>{
    return this.http.post<ImageUrl[]>(endpoints.ServerUrl + endpoints.GetImages, searchText);
  }

  uploadImage(image: File) {
    const formData = new FormData();
    formData.append('imageFile', image, image.name);
    return this.http.post<ImageUrl[]>(endpoints.ServerUrl + endpoints.ImageUploadUrl, formData);
  }
}
