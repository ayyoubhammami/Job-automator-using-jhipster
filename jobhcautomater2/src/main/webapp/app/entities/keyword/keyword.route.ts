import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Keyword } from 'app/shared/model/keyword.model';
import { KeywordService } from './keyword.service';
import { KeywordComponent } from './keyword.component';
import { KeywordDetailComponent } from './keyword-detail.component';
import { KeywordUpdateComponent } from './keyword-update.component';
import { KeywordDeletePopupComponent } from './keyword-delete-dialog.component';
import { IKeyword } from 'app/shared/model/keyword.model';

@Injectable({ providedIn: 'root' })
export class KeywordResolve implements Resolve<IKeyword> {
    constructor(private service: KeywordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((keyword: HttpResponse<Keyword>) => keyword.body);
        }
        return Observable.of(new Keyword());
    }
}

export const keywordRoute: Routes = [
    {
        path: 'keyword',
        component: KeywordComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jobHcAutomater2App.keyword.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'keyword/:id/view',
        component: KeywordDetailComponent,
        resolve: {
            keyword: KeywordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.keyword.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'keyword/new',
        component: KeywordUpdateComponent,
        resolve: {
            keyword: KeywordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.keyword.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'keyword/:id/edit',
        component: KeywordUpdateComponent,
        resolve: {
            keyword: KeywordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.keyword.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const keywordPopupRoute: Routes = [
    {
        path: 'keyword/:id/delete',
        component: KeywordDeletePopupComponent,
        resolve: {
            keyword: KeywordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.keyword.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
