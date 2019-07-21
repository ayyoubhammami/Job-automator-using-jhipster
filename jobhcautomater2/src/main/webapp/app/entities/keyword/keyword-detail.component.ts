import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKeyword } from 'app/shared/model/keyword.model';

@Component({
    selector: 'jhi-keyword-detail',
    templateUrl: './keyword-detail.component.html'
})
export class KeywordDetailComponent implements OnInit {
    keyword: IKeyword;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ keyword }) => {
            this.keyword = keyword;
        });
    }

    previousState() {
        window.history.back();
    }
}
