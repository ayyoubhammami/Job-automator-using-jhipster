import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfilCondidate } from 'app/shared/model/profil-condidate.model';

@Component({
    selector: 'jhi-profil-condidate-detail',
    templateUrl: './profil-condidate-detail.component.html'
})
export class ProfilCondidateDetailComponent implements OnInit {
    profilCondidate: IProfilCondidate;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilCondidate }) => {
            this.profilCondidate = profilCondidate;
        });
    }

    previousState() {
        window.history.back();
    }
}
