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
    filterNulls = false;

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
        console.log("check it for ", numberOfElements, " elements for algorithm ", fillAlgorithm, " filterNulls ", this.filterNulls);
        this.perfectPermutationService.calculatePermutation(numberOfElements, fillAlgorithm, this.filterNulls)
            .subscribe(response => {
                this.response = response;
            });
    }

    updateFilter() {
        this.filterNulls = !this.filterNulls;
        console.log("filter nulls: ", this.filterNulls);
    }
}
