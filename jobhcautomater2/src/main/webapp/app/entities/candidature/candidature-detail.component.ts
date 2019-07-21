import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICandidature } from 'app/shared/model/candidature.model';

@Component({
    selector: 'jhi-candidature-detail',
    templateUrl: './candidature-detail.component.html'
})
export class CandidatureDetailComponent implements OnInit {
    candidature: ICandidature;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ candidature }) => {
            this.candidature = candidature;
        });
    }

    previousState() {
        window.history.back();
    }
}
