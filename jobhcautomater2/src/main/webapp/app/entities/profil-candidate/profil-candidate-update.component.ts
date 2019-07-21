import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IProfilCandidate } from 'app/shared/model/profil-candidate.model';
import { ProfilCandidateService } from './profil-candidate.service';

@Component({
    selector: 'jhi-profil-candidate-update',
    templateUrl: './profil-candidate-update.component.html'
})
export class ProfilCandidateUpdateComponent implements OnInit {
    private _profilCandidate: IProfilCandidate;
    isSaving: boolean;

    constructor(private profilCandidateService: ProfilCandidateService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profilCandidate }) => {
            this.profilCandidate = profilCandidate;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.profilCandidate.id !== undefined) {
            this.subscribeToSaveResponse(this.profilCandidateService.update(this.profilCandidate));
        } else {
            this.subscribeToSaveResponse(this.profilCandidateService.create(this.profilCandidate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProfilCandidate>>) {
        result.subscribe((res: HttpResponse<IProfilCandidate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get profilCandidate() {
        return this._profilCandidate;
    }

    set profilCandidate(profilCandidate: IProfilCandidate) {
        this._profilCandidate = profilCandidate;
    }
}
