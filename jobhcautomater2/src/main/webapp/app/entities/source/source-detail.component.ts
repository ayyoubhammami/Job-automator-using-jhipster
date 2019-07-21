import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISource } from 'app/shared/model/source.model';

@Component({
    selector: 'jhi-source-detail',
    templateUrl: './source-detail.component.html'
})
export class SourceDetailComponent implements OnInit {
    source: ISource;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ source }) => {
            this.source = source;
        });
    }

    previousState() {
        window.history.back();
    }
}
