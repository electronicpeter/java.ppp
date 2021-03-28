import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PerfectPermutationResponseContent, PerfectPermutationService} from "../api";

@Component({
    selector: 'app-text',
    templateUrl: './text.component.html',
    styleUrls: ['./text.component.css']
})
export class TextComponent implements OnInit {

    constructor(private formBuilder: FormBuilder,
                private perfectPermutationService: PerfectPermutationService) {
    }

    firstSelectedElement = -1;
    secondSelectedElement = -1;
    inputForm: FormGroup;
    response: PerfectPermutationResponseContent;
    memberArray : string[] = [];

    ngOnInit() {
        this.inputForm = this.formBuilder.group(
            {
                membersField: ['peter\nmarissa\nthilo\nfrancis\nmario\nbastian\nyannick\nfriederike\numa', [Validators.required]]
            }
        );
    }

    getPermutation() {
        let members = this.inputForm.getRawValue().membersField;
        console.log("member", members);
        this.memberArray = members.split(/\r?\n/);
        let numberOfElements = this.memberArray.length;
        this.perfectPermutationService.calculatePermutation(numberOfElements, "BEST", true)
            .subscribe(response => {
                this.response = response;
            });
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
        if (this.firstSelectedElement === element) {
            this.firstSelectedElement = -1;
            return;
        }
        if (this.secondSelectedElement === element) {
            this.secondSelectedElement = -1;
            return;
        }
        if (this.firstSelectedElement === -1) {
            this.firstSelectedElement = element;
            return;
        }
        if (this.secondSelectedElement === -1) {
            this.secondSelectedElement = element;
            return;
        }
    }
}
