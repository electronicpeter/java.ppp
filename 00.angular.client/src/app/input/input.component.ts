import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PerfectPermutationResponseContent, PerfectPermutationService} from "../api";

@Component({
    selector: 'app-input',
    templateUrl: './input.component.html',
    styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

    firstSelectedElement = -1;
    secondSelectedElement = -1;
    matchSuperSet = -1;
    matchSet = -1;
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
        this.firstSelectedElement = -1;
        this.secondSelectedElement = -1;
        this.matchSuperSet = -1;
        this.matchSet = -1;

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

    isSelected(element: number): string {
        if (element === this.firstSelectedElement) {
            return "markFirst";
        }
        if (element === this.secondSelectedElement) {
            return "markSecond";
        }
        return "";
    }

    select(element: number) {
        if (element === undefined) {
            return;
        }
        if (this.firstSelectedElement === element) {
            this.firstSelectedElement = -1;
            this.matchSet = -1;
            this.matchSuperSet = -1;
            return;
        }
        if (this.secondSelectedElement === element) {
            this.secondSelectedElement = -1;
            this.matchSet = -1;
            this.matchSuperSet = -1;
            return;
        }
        if (this.firstSelectedElement === -1) {
            this.firstSelectedElement = element;
            this.checkMatch();
            return;
        }
        if (this.secondSelectedElement === -1) {
            this.secondSelectedElement = element;
            this.checkMatch();
            return;
        }
    }

    checkMatch() {
        if (this.firstSelectedElement != -1 && this.secondSelectedElement != -1) {
            let superSetCounter = 0;
            for (let ss of this.response.cycles!) {
                let setCounter = 0;
                for (let s of ss) {
                    let foundFirst = false;
                    let foundSecond = false;
                    for (let e of s) {
                        if (e == this.firstSelectedElement) {foundFirst = true;}
                        if (e == this.secondSelectedElement) {foundSecond = true;}
                    }
                    if (foundFirst && foundSecond) {
                        this.matchSuperSet = superSetCounter;
                        this.matchSet = superSetCounter * this.response.square!.dimension! + setCounter;
                        return;
                    }
                    setCounter++;
                }
                superSetCounter++;
            }
        }
    }

    matchSuperSetClass(ss: number) {
        if (ss === this.matchSuperSet) {
            return "markSuperSet";
        }
        return "";
    }

    matchSetClass(s: number) {
        if (s === this.matchSet) {
            return "markSet";
        }
        return "";
    }

}
