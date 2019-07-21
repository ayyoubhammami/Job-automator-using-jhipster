import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { IPost } from 'app/shared/model/post.model';
import { PostService } from './post.service';

@Component({
    selector: 'jhi-post-update',
    templateUrl: './post-update.component.html'
})
export class PostUpdateComponent implements OnInit {
    private _post: IPost;
    isSaving: boolean;

    constructor(
        private dataUtils: JhiDataUtils,
        private postService: PostService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ post }) => {
            this.post = post;
        });
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

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.post, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.post.id !== undefined) {
            this.subscribeToSaveResponse(this.postService.update(this.post));
        } else {
            this.subscribeToSaveResponse(this.postService.create(this.post));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPost>>) {
        result.subscribe((res: HttpResponse<IPost>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get post() {
        return this._post;
    }

    set post(post: IPost) {
        this._post = post;
    }
}
