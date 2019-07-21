import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISocialMedia } from 'app/shared/model/social-media.model';
import { SocialMediaService } from './social-media.service';
import { ICompaign } from 'app/shared/model/compaign.model';
import { CompaignService } from 'app/entities/compaign';

@Component({
    selector: 'jhi-social-media-update',
    templateUrl: './social-media-update.component.html'
})
export class SocialMediaUpdateComponent implements OnInit {
    private _socialMedia: ISocialMedia;
    isSaving: boolean;

    compaigns: ICompaign[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private socialMediaService: SocialMediaService,
        private compaignService: CompaignService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ socialMedia }) => {
            this.socialMedia = socialMedia;
        });
        this.compaignService.query().subscribe(
            (res: HttpResponse<ICompaign[]>) => {
                this.compaigns = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.socialMedia.id !== undefined) {
            this.subscribeToSaveResponse(this.socialMediaService.update(this.socialMedia));
        } else {
            this.subscribeToSaveResponse(this.socialMediaService.create(this.socialMedia));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISocialMedia>>) {
        result.subscribe((res: HttpResponse<ISocialMedia>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCompaignById(index: number, item: ICompaign) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get socialMedia() {
        return this._socialMedia;
    }

    set socialMedia(socialMedia: ISocialMedia) {
        this._socialMedia = socialMedia;
    }
}
