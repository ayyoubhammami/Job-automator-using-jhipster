import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEmployeeDetails } from 'app/shared/model/employee-details.model';
import { EmployeeDetailsService } from './employee-details.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-employee-details-update',
    templateUrl: './employee-details-update.component.html'
})
export class EmployeeDetailsUpdateComponent implements OnInit {
    private _employeeDetails: IEmployeeDetails;
    isSaving: boolean;

    users: IUser[];
    delevredDate: string;
    hiringDate: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private employeeDetailsService: EmployeeDetailsService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ employeeDetails }) => {
            this.employeeDetails = employeeDetails;
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
        this.employeeDetails.delevredDate = moment(this.delevredDate, DATE_TIME_FORMAT);
        this.employeeDetails.hiringDate = moment(this.hiringDate, DATE_TIME_FORMAT);
        if (this.employeeDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.employeeDetailsService.update(this.employeeDetails));
        } else {
            this.subscribeToSaveResponse(this.employeeDetailsService.create(this.employeeDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEmployeeDetails>>) {
        result.subscribe((res: HttpResponse<IEmployeeDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get employeeDetails() {
        return this._employeeDetails;
    }

    set employeeDetails(employeeDetails: IEmployeeDetails) {
        this._employeeDetails = employeeDetails;
        this.delevredDate = moment(employeeDetails.delevredDate).format(DATE_TIME_FORMAT);
        this.hiringDate = moment(employeeDetails.hiringDate).format(DATE_TIME_FORMAT);
    }
}
