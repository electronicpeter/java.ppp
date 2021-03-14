import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PerfectPermutationResponseContent, PerfectPermutationService} from "../api";

@Component({
    selector: 'app-input',
    templateUrl: './input.component.html',
    styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

    inputForm: FormGroup;
    response: PerfectPermutationResponseContent;

    constructor(private formBuilder: FormBuilder,
                private perfectPermutationService: PerfectPermutationService) {
    }

    ngOnInit() {
        this.inputForm = this.formBuilder.group(
            {
                numberOfElements: ['16', [Validators.required]],
                fillAlgorithm: ['BEST']
            }
        );
    }

    getPermutation() {
        let numberOfElements = this.inputForm.getRawValue().numberOfElements;
        let fillAlgorithm = this.inputForm.getRawValue().fillAlgorithm;
        console.log("check it for ",numberOfElements," elements for algorithm ",fillAlgorithm );
        if (fillAlgorithm === "BEST") {
            this.perfectPermutationService.calculatePerfectPermutation(numberOfElements)
            .subscribe(response => {this.response = response;});
        } else {
            this.perfectPermutationService.calculatePermutation(numberOfElements, fillAlgorithm)
                .subscribe(response => {this.response = response;});
        }

    }
}
