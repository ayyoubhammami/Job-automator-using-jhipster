import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ICandidature, Status } from 'app/shared/model/candidature.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { CandidatureService } from './candidature.service';

@Component({
    selector: 'jhi-candidature',
    templateUrl: './candidature.component.html',
    styleUrls: ['candidature.component.css']
})
export class CandidatureComponent implements OnInit, OnDestroy {
    isMoved: boolean;
    candidatures: ICandidature[];
    candidatures_total: ICandidature[]=[];
    candidatures_possible: ICandidature[]=[];
    candidatures_inDiscussion: ICandidature[]=[];
    candidatures_selected: ICandidature[]=[];
    candidatures_rejected: ICandidature[]=[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    queryCount: any;
    reverse: any;
    totalItems: number;

    constructor(
        private candidatureService: CandidatureService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private parseLinks: JhiParseLinks,
        private principal: Principal
    ) {
        this.candidatures = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
    }

    loadAll() {
        this.candidatureService
            .query({
                page: this.page,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ICandidature[]>) => this.paginateCandidatures(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.candidatureService
            .query()
            .subscribe(
                 (res: HttpResponse<ICandidature[]>) => {
                     this.candidatures_total=res.body;
                     this.candidatures_inDiscussion=this.candidatures_total.filter(x => x.status===Status.IN_DISCUSSION);
                     this.candidatures_selected=this.candidatures_total.filter(x => x.status===Status.Selected);
                     this.candidatures_rejected=this.candidatures_total.filter(x => x.status===Status.Rejected);
                     this.candidatures_possible=this.candidatures_total.filter(x => x.status===Status.POSSIBLE);
                },
                 (res: HttpErrorResponse) => this.onError(res.message)
            );
            console.log( "size****"+this.candidatures_total.length);

    }

    reset() {
        this.page = 0;
        this.candidatures = [];
        this.loadAll();
    }

    loadPage(page) {
        this.page = page;
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCandidatures();

    }
    getstatus(){
       this.candidatures
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICandidature) {
        return item.id;
    }

    registerChangeInCandidatures() {
        this.eventSubscriber = this.eventManager.subscribe('candidatureListModification', response => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateCandidatures(data: ICandidature[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        for (let i = 0; i < data.length; i++) {
            this.candidatures.push(data[i]);
        }
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    //drag&drop
    drop(ev) {
        // this.isMoved=true;
        ev.preventDefault();
        let data = ev.dataTransfer.getData('text');
        console.log("data: "+data);
        console.log("status: "+ev.target.id);
        let candidat:ICandidature;
        candidat=this.candidatures.find(x => x.id == data);
        candidat.status= ev.target.id;
        this.candidatureService.update(candidat)
                                .subscribe(
                                    (res: HttpResponse<ICandidature>) => console.log("ok"),
                                    (res: HttpErrorResponse) => this.onError(res.message)
                                );
        ev.target.appendChild(document.getElementById(data));
    }

    allowDrop(ev) {
        ev.preventDefault();

    }


    drag(ev) {
        ev.dataTransfer.setData('text', ev.target.id);

    }


}
