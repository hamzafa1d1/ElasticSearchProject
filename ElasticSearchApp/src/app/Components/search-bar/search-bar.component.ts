import {Component} from '@angular/core';
import {ImageUrl} from "../../Models/ImageUrl";
import {ImagesService} from "../../Services/ImagesService/images.service";

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent {
  searchTerm = '';
  listOfImageUrls: ImageUrl[] = [];
  isLoading: boolean = true;
  constructor(private imagesService: ImagesService) {
  }

  search() {
    this.imagesService.getImageUrls(this.searchTerm).subscribe((response) => {
      this.listOfImageUrls = response;
      this.isLoading = false;
      console.log(this.listOfImageUrls)
    })
    console.log(this.searchTerm);
  }
}
