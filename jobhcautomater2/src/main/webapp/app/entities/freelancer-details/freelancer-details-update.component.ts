import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IFreelancerDetails } from 'app/shared/model/freelancer-details.model';
import { FreelancerDetailsService } from './freelancer-details.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-freelancer-details-update',
    templateUrl: './freelancer-details-update.component.html'
})
export class FreelancerDetailsUpdateComponent implements OnInit {
    private _freelancerDetails: IFreelancerDetails;
    isSaving: boolean;

    users: IUser[];
    delevredDate: string;
    hiringDate: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private freelancerDetailsService: FreelancerDetailsService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ freelancerDetails }) => {
            this.freelancerDetails = freelancerDetails;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
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

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.freelancerDetails.delevredDate = moment(this.delevredDate, DATE_TIME_FORMAT);
        this.freelancerDetails.hiringDate = moment(this.hiringDate, DATE_TIME_FORMAT);
        if (this.freelancerDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.freelancerDetailsService.update(this.freelancerDetails));
        } else {
            this.subscribeToSaveResponse(this.freelancerDetailsService.create(this.freelancerDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFreelancerDetails>>) {
        result.subscribe((res: HttpResponse<IFreelancerDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
    get freelancerDetails() {
        return this._freelancerDetails;
    }

    set freelancerDetails(freelancerDetails: IFreelancerDetails) {
        this._freelancerDetails = freelancerDetails;
        this.delevredDate = moment(freelancerDetails.delevredDate).format(DATE_TIME_FORMAT);
        this.hiringDate = moment(freelancerDetails.hiringDate).format(DATE_TIME_FORMAT);
    }
}
