import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute, Event } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ICompaign } from 'app/shared/model/compaign.model';
import { CompaignService } from './compaign.service';
import { ISocialMedia } from 'app/shared/model/social-media.model';
import { SocialMediaService } from 'app/entities/social-media';

@Component({
    selector: 'jhi-compaign-update',
    templateUrl: './compaign-update.component.html'
})
export class CompaignUpdateComponent implements OnInit {
    private _compaign: ICompaign;
    isSaving: boolean;

    socialmedias: ISocialMedia[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private compaignService: CompaignService,
        private socialMediaService: SocialMediaService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ compaign }) => {
            this.compaign = compaign;
        });
        this.socialMediaService.query().subscribe(
            (res: HttpResponse<ISocialMedia[]>) => {
                this.socialmedias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.compaign, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.compaign.id !== undefined) {
            this.subscribeToSaveResponse(this.compaignService.update(this.compaign));
        } else {
            this.subscribeToSaveResponse(this.compaignService.create(this.compaign));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICompaign>>) {
        result.subscribe((res: HttpResponse<ICompaign>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSocialMediaById(index: number, item: ISocialMedia) {
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

    get compaign() {
        return this._compaign;
    }

    set compaign(compaign: ICompaign) {
        this._compaign = compaign;
    }
}
