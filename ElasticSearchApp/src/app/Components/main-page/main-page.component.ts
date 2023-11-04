import { Component } from '@angular/core';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent {
  selectedSearch: string = ''; // Initially, no option is selected

  chooseSearch(option: string) {
    this.selectedSearch = option;
  }

}
