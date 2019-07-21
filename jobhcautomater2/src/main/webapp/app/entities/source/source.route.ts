import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Source } from 'app/shared/model/source.model';
import { SourceService } from './source.service';
import { SourceComponent } from './source.component';
import { SourceDetailComponent } from './source-detail.component';
import { SourceUpdateComponent } from './source-update.component';
import { SourceDeletePopupComponent } from './source-delete-dialog.component';
import { ISource } from 'app/shared/model/source.model';

@Injectable({ providedIn: 'root' })
export class SourceResolve implements Resolve<ISource> {
    constructor(private service: SourceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((source: HttpResponse<Source>) => source.body);
        }
        return Observable.of(new Source());
    }
}

export const sourceRoute: Routes = [
    {
        path: 'source',
        component: SourceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.source.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'source/:id/view',
        component: SourceDetailComponent,
        resolve: {
            source: SourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.source.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'source/new',
        component: SourceUpdateComponent,
        resolve: {
            source: SourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.source.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'source/:id/edit',
        component: SourceUpdateComponent,
        resolve: {
            source: SourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.source.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sourcePopupRoute: Routes = [
    {
        path: 'source/:id/delete',
        component: SourceDeletePopupComponent,
        resolve: {
            source: SourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.source.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
