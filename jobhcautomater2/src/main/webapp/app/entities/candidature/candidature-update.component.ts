import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICandidature } from 'app/shared/model/candidature.model';
import { CandidatureService } from './candidature.service';
import { IProfilCandidate } from 'app/shared/model/profil-candidate.model';
import { ProfilCandidateService } from 'app/entities/profil-candidate';
import { IPost } from 'app/shared/model/post.model';
import { PostService } from 'app/entities/post';

@Component({
    selector: 'jhi-candidature-update',
    templateUrl: './candidature-update.component.html'
})
export class CandidatureUpdateComponent implements OnInit {
    private _candidature: ICandidature;
    isSaving: boolean;

    profilcandidates: IProfilCandidate[];

    posts: IPost[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private candidatureService: CandidatureService,
        private profilCandidateService: ProfilCandidateService,
        private postService: PostService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ candidature }) => {
            this.candidature = candidature;
        });
        this.profilCandidateService.query().subscribe(
            (res: HttpResponse<IProfilCandidate[]>) => {
                this.profilcandidates = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.postService.query().subscribe(
            (res: HttpResponse<IPost[]>) => {
                this.posts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.candidature.id !== undefined) {
            this.subscribeToSaveResponse(this.candidatureService.update(this.candidature));
        } else {
            this.subscribeToSaveResponse(this.candidatureService.create(this.candidature));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICandidature>>) {
        result.subscribe((res: HttpResponse<ICandidature>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProfilCandidateById(index: number, item: IProfilCandidate) {
        return item.id;
    }

    trackPostById(index: number, item: IPost) {
        return item.id;
    }
    get candidature() {
        return this._candidature;
    }

    set candidature(candidature: ICandidature) {
        this._candidature = candidature;
    }
}
