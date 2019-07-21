import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAffectation } from 'app/shared/model/affectation.model';

@Component({
    selector: 'jhi-affectation-detail',
    templateUrl: './affectation-detail.component.html'
})
export class AffectationDetailComponent implements OnInit {
    affectation: IAffectation;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ affectation }) => {
            this.affectation = affectation;
        });
    }

    previousState() {
        window.history.back();
    }
}
