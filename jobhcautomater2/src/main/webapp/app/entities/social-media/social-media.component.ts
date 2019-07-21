import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISocialMedia } from 'app/shared/model/social-media.model';
import { Principal } from 'app/core';
import { SocialMediaService } from './social-media.service';

@Component({
    selector: 'jhi-social-media',
    templateUrl: './social-media.component.html'
})
export class SocialMediaComponent implements OnInit, OnDestroy {
    socialMedias: ISocialMedia[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private socialMediaService: SocialMediaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.socialMediaService.query().subscribe(
            (res: HttpResponse<ISocialMedia[]>) => {
                this.socialMedias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSocialMedias();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISocialMedia) {
        return item.id;
    }

    registerChangeInSocialMedias() {
        this.eventSubscriber = this.eventManager.subscribe('socialMediaListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
