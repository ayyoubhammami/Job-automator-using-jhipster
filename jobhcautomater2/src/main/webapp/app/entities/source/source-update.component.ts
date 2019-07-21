import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISource } from 'app/shared/model/source.model';
import { SourceService } from './source.service';

@Component({
    selector: 'jhi-source-update',
    templateUrl: './source-update.component.html'
})
export class SourceUpdateComponent implements OnInit {
    private _source: ISource;
    isSaving: boolean;

    constructor(private sourceService: SourceService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ source }) => {
            this.source = source;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.source.id !== undefined) {
            this.subscribeToSaveResponse(this.sourceService.update(this.source));
        } else {
            this.subscribeToSaveResponse(this.sourceService.create(this.source));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISource>>) {
        result.subscribe((res: HttpResponse<ISource>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get source() {
        return this._source;
    }

    set source(source: ISource) {
        this._source = source;
    }
}
