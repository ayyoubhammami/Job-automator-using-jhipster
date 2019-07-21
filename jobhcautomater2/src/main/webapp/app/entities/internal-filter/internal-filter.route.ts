import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { InternalFilter } from 'app/shared/model/internal-filter.model';
import { InternalFilterService } from './internal-filter.service';
import { InternalFilterComponent } from './internal-filter.component';
import { InternalFilterDetailComponent } from './internal-filter-detail.component';
import { InternalFilterUpdateComponent } from './internal-filter-update.component';
import { InternalFilterDeletePopupComponent } from './internal-filter-delete-dialog.component';
import { IInternalFilter } from 'app/shared/model/internal-filter.model';

@Injectable({ providedIn: 'root' })
export class InternalFilterResolve implements Resolve<IInternalFilter> {
    constructor(private service: InternalFilterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((internalFilter: HttpResponse<InternalFilter>) => internalFilter.body);
        }
        return Observable.of(new InternalFilter());
    }
}

export const internalFilterRoute: Routes = [
    {
        path: 'internal-filter',
        component: InternalFilterComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jobHcAutomater2App.internalFilter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'internal-filter/:id/view',
        component: InternalFilterDetailComponent,
        resolve: {
            internalFilter: InternalFilterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.internalFilter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'internal-filter/new',
        component: InternalFilterUpdateComponent,
        resolve: {
            internalFilter: InternalFilterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.internalFilter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'internal-filter/:id/edit',
        component: InternalFilterUpdateComponent,
        resolve: {
            internalFilter: InternalFilterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.internalFilter.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const internalFilterPopupRoute: Routes = [
    {
        path: 'internal-filter/:id/delete',
        component: InternalFilterDeletePopupComponent,
        resolve: {
            internalFilter: InternalFilterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.internalFilter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
