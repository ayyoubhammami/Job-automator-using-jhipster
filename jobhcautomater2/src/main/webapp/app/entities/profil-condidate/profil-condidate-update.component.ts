import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IProfilCondidate } from 'app/shared/model/profil-condidate.model';
import { ProfilCondidateService } from './profil-condidate.service';

@Component({
    selector: 'jhi-profil-condidate-update',
    templateUrl: './profil-condidate-update.component.html'
})
export class ProfilCondidateUpdateComponent implements OnInit {
    private _profilCondidate: IProfilCondidate;
    isSaving: boolean;

    constructor(private profilCondidateService: ProfilCondidateService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profilCondidate }) => {
            this.profilCondidate = profilCondidate;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.profilCondidate.id !== undefined) {
            this.subscribeToSaveResponse(this.profilCondidateService.update(this.profilCondidate));
        } else {
            this.subscribeToSaveResponse(this.profilCondidateService.create(this.profilCondidate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProfilCondidate>>) {
        result.subscribe((res: HttpResponse<IProfilCondidate>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get profilCondidate() {
        return this._profilCondidate;
    }

    set profilCondidate(profilCondidate: IProfilCondidate) {
        this._profilCondidate = profilCondidate;
    }
}
