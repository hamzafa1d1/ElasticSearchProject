import { Component } from '@angular/core';
import {ImagesService} from "../../Services/ImagesService/images.service";

@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent {
  selectedFile: File | null = null;
  listOfImageUrls: any = [];

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  constructor(private imageUploadService: ImagesService) {

  }

  onSubmit() {
    if (this.selectedFile) {
      this.imageUploadService.uploadImage(this.selectedFile).subscribe((response) => console.log("Image Uploaded Successfully") );
    } else {
      alert('Please select a file before uploading.');
    }
  }
}
