import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IFreelancerDetails } from 'app/shared/model/freelancer-details.model';

@Component({
    selector: 'jhi-freelancer-details-detail',
    templateUrl: './freelancer-details-detail.component.html'
})
export class FreelancerDetailsDetailComponent implements OnInit {
    freelancerDetails: IFreelancerDetails;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ freelancerDetails }) => {
            this.freelancerDetails = freelancerDetails;
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
