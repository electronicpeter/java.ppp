import {Component, NgModule, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PerfectPermutationResponseContent, PerfectPermutationService} from "../api";
import {NullException} from "../common/NullException";
import {Observable} from "rxjs";

@Component({
    selector: 'app-text',
    templateUrl: './text.component.html',
    styleUrls: ['./text.component.css']
})
export class TextComponent {
    matchSuperSet = -1;
    matchSet = -1;
    firstSelectedElement = -1;
    secondSelectedElement = -1;
    inputForm: FormGroup;
    response$: Observable<PerfectPermutationResponseContent> = new Observable<PerfectPermutationResponseContent>();
    memberArray: string[] = [];


    constructor(private formBuilder: FormBuilder,
                private perfectPermutationService: PerfectPermutationService) {
        this.inputForm = this.formBuilder.group(
            {
                membersField: ['peter\nmarissa\nthilo\nfrancis\nmario\nbastian\nyannick\nfriederike\numa', [Validators.required]]
            }
        );
    }

    getPermutation() {
        this.firstSelectedElement = -1;
        this.secondSelectedElement = -1;
        this.matchSuperSet = -1;
        this.matchSet = -1;

        let members = this.inputForm.getRawValue().membersField;
        console.log("member", members);
        this.memberArray = members.split(/\r?\n/);
        let numberOfElements = this.memberArray.length;
        this.response$ = this.perfectPermutationService.calculatePermutation(numberOfElements, "BEST", true);
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

    select(response: PerfectPermutationResponseContent, element: number) {
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
            this.checkMatch(response);
            return;
        }
        if (this.secondSelectedElement === -1) {
            this.secondSelectedElement = element;
            this.checkMatch(response);
            return;
        }
        this.firstSelectedElement = element;
        this.secondSelectedElement = -1;
        this.checkMatch(response);
    }

    checkMatch(response: PerfectPermutationResponseContent) {
        if (this.firstSelectedElement != -1 && this.secondSelectedElement != -1) {
            let superSetCounter = 0;
            for (let ss of response.cycles!) {
                let setCounter = 0;
                for (let s of ss) {
                    let foundFirst = false;
                    let foundSecond = false;
                    for (let e of s) {
                        if (e == this.firstSelectedElement) {
                            foundFirst = true;
                        }
                        if (e == this.secondSelectedElement) {
                            foundSecond = true;
                        }
                    }
                    if (foundFirst && foundSecond) {
                        this.matchSuperSet = superSetCounter;
                        this.matchSet = superSetCounter * response.square!.dimension! + setCounter;
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
