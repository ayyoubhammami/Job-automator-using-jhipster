import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IEmployeeDetails } from 'app/shared/model/employee-details.model';

@Component({
    selector: 'jhi-employee-details-detail',
    templateUrl: './employee-details-detail.component.html'
})
export class EmployeeDetailsDetailComponent implements OnInit {
    employeeDetails: IEmployeeDetails;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ employeeDetails }) => {
            this.employeeDetails = employeeDetails;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
