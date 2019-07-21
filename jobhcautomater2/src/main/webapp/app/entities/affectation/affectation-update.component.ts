import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IAffectation } from 'app/shared/model/affectation.model';
import { AffectationService } from './affectation.service';
import { IJob } from 'app/shared/model/job.model';
import { JobService } from 'app/entities/job';
import { IFreelancerDetails } from 'app/shared/model/freelancer-details.model';
import { FreelancerDetailsService } from 'app/entities/freelancer-details';
import { IEmployeeDetails } from 'app/shared/model/employee-details.model';
import { EmployeeDetailsService } from 'app/entities/employee-details';

@Component({
    selector: 'jhi-affectation-update',
    templateUrl: './affectation-update.component.html'

           })    
export class AffectationUpdateComponent implements OnInit {
    private _affectation: IAffectation;
    isSaving: boolean;
    jobs: IJob[];
    freelancerdetails: IFreelancerDetails[];
    employeedetails: IEmployeeDetails[];
    date: string;

    
    constructor(
        private jhiAlertService: JhiAlertService,
        private affectationService: AffectationService,
        private jobService: JobService,
        private freelancerDetailsService: FreelancerDetailsService,
        private employeeDetailsService: EmployeeDetailsService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ affectation }) => {
            this.affectation = affectation;
        });
        this.jobService.query({ filter: 'affectation-is-null' }).subscribe(
            (res: HttpResponse<IJob[]>) => {
                if (!this.affectation.job || !this.affectation.job.id) {
                    this.jobs = res.body;
                } else {
                    this.jobService.find(this.affectation.job.id).subscribe(
                        (subRes: HttpResponse<IJob>) => {
                            this.jobs = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.freelancerDetailsService.query().subscribe(
            (res: HttpResponse<IFreelancerDetails[]>) => {
                this.freelancerdetails = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.employeeDetailsService.query().subscribe(
            (res: HttpResponse<IEmployeeDetails[]>) => {
                this.employeedetails = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.affectation.date = moment(this.date, DATE_TIME_FORMAT);
        if (this.affectation.id !== undefined) {
            this.subscribeToSaveResponse(this.affectationService.update(this.affectation));
        } else {
            this.subscribeToSaveResponse(this.affectationService.create(this.affectation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAffectation>>) {
        result.subscribe((res: HttpResponse<IAffectation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackJobById(index: number, item: IJob) {
        return item.id;
    }

    trackFreelancerDetailsById(index: number, item: IFreelancerDetails) {
        return item.id;
    }

    trackEmployeeDetailsById(index: number, item: IEmployeeDetails) {
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
    get affectation() {
        return this._affectation;
    }

    set affectation(affectation: IAffectation) {
        this._affectation = affectation;
        this.date = moment(affectation.date).format(DATE_TIME_FORMAT);
    }
}
