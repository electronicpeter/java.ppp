import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {RoutingPath} from "../common/routing-path";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  gotToInput() {
    this.router.navigate([RoutingPath.INPUT]);
  }
  gotToText() {
    this.router.navigate([RoutingPath.TEXT]);
  }
}
