import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfilCandidate } from 'app/shared/model/profil-candidate.model';

@Component({
    selector: 'jhi-profil-candidate-detail',
    templateUrl: './profil-candidate-detail.component.html'
})
export class ProfilCandidateDetailComponent implements OnInit {
    profilCandidate: IProfilCandidate;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilCandidate }) => {
            this.profilCandidate = profilCandidate;
        });
    }

    previousState() {
        window.history.back();
    }
}
