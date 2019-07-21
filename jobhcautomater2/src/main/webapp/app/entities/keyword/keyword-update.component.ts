import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IKeyword } from 'app/shared/model/keyword.model';
import { KeywordService } from './keyword.service';

@Component({
    selector: 'jhi-keyword-update',
    templateUrl: './keyword-update.component.html'
})
export class KeywordUpdateComponent implements OnInit {
    private _keyword: IKeyword;
    isSaving: boolean;

    constructor(private keywordService: KeywordService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ keyword }) => {
            this.keyword = keyword;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.keyword.id !== undefined) {
            this.subscribeToSaveResponse(this.keywordService.update(this.keyword));
        } else {
            this.subscribeToSaveResponse(this.keywordService.create(this.keyword));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IKeyword>>) {
        result.subscribe((res: HttpResponse<IKeyword>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get keyword() {
        return this._keyword;
    }

    set keyword(keyword: IKeyword) {
        this._keyword = keyword;
    }
}
