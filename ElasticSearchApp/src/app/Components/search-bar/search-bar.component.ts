import {Component} from '@angular/core';
import {ImageUrl} from "../../Models/ImageUrl";
import {ImagesService} from "../../Services/ImagesService/images.service";
import {SearchText} from "../../Models/SearchText";

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
    let searchText: SearchText = {
      Text: this.searchTerm
    };
    this.imagesService.getImageUrls(searchText).subscribe((response) => {
      this.listOfImageUrls = response;
      this.isLoading = false;
    })
  }
}
