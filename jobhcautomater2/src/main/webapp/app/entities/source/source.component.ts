import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISource } from 'app/shared/model/source.model';
import { Principal } from 'app/core';
import { SourceService } from './source.service';

@Component({
    selector: 'jhi-source',
    templateUrl: './source.component.html'
})
export class SourceComponent implements OnInit, OnDestroy {
    sources: ISource[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private sourceService: SourceService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.sourceService.query().subscribe(
            (res: HttpResponse<ISource[]>) => {
                this.sources = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSources();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISource) {
        return item.id;
    }

    registerChangeInSources() {
        this.eventSubscriber = this.eventManager.subscribe('sourceListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
