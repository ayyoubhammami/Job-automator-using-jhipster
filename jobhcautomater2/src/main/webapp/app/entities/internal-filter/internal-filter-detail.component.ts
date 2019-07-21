import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInternalFilter } from 'app/shared/model/internal-filter.model';

@Component({
    selector: 'jhi-internal-filter-detail',
    templateUrl: './internal-filter-detail.component.html'
})
export class InternalFilterDetailComponent implements OnInit {
    internalFilter: IInternalFilter;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ internalFilter }) => {
            this.internalFilter = internalFilter;
        });
    }

    previousState() {
        window.history.back();
    }
}
