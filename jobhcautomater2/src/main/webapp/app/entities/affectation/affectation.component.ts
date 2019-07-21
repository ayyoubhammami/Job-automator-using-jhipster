import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAffectation } from 'app/shared/model/affectation.model';
import { Principal } from 'app/core';
import { AffectationService } from './affectation.service';

@Component({
    selector: 'jhi-affectation',
    templateUrl: './affectation.component.html'
})
export class AffectationComponent implements OnInit, OnDestroy {
    affectations: IAffectation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private affectationService: AffectationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.affectationService.query().subscribe(
            (res: HttpResponse<IAffectation[]>) => {
                this.affectations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAffectations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAffectation) {
        return item.id;
    }

    registerChangeInAffectations() {
        this.eventSubscriber = this.eventManager.subscribe('affectationListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
