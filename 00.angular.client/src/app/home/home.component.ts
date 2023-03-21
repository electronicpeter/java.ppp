import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {RoutingPath} from "../common/routing-path";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  cssClass = "card-list";

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToInput() {
    this.cssClass = "card-list card-list-out";
    this.router.navigate([RoutingPath.INPUT]);
  }
  goToText() {
    this.cssClass = "card-list card-list-out";
    this.router.navigate([RoutingPath.TEXT]);
  }
  goToPdf() {
    this.cssClass = "card-list card-list-out";
    this.router.navigate([RoutingPath.PDF]);
  }

  magnify() {
    this.cssClass = "card-list";

    // this.router.navigate([RoutingPath.ROOT]);
  }
}
