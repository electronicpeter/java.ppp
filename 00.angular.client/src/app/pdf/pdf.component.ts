import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-pdf',
  templateUrl: './pdf.component.html',
  styleUrls: ['./pdf.component.css']
})
export class PdfComponent implements OnInit{

  ngOnInit() {
    let parent = document.getElementById("viewer")?.parentElement;
    if (parent != null) {
      document.getElementById("viewer")?.remove();
      let iframe = document.createElement("iframe");
      iframe.setAttribute("src", "https://electronicpeter.de/ppp.pdf");
      iframe.setAttribute("width", "100%");
      iframe.setAttribute("height", "100%");
      parent.append(iframe);
    }
  }

}
