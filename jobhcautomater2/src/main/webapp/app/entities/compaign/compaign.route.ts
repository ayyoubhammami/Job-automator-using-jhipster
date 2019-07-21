import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Compaign } from 'app/shared/model/compaign.model';
import { CompaignService } from './compaign.service';
import { CompaignComponent } from './compaign.component';
import { CompaignDetailComponent } from './compaign-detail.component';
import { CompaignUpdateComponent } from './compaign-update.component';
import { CompaignDeletePopupComponent } from './compaign-delete-dialog.component';
import { ICompaign } from 'app/shared/model/compaign.model';

@Injectable({ providedIn: 'root' })
export class CompaignResolve implements Resolve<ICompaign> {
    constructor(private service: CompaignService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((compaign: HttpResponse<Compaign>) => compaign.body);
        }
        return Observable.of(new Compaign());
    }
}

export const compaignRoute: Routes = [
    {
        path: 'compaign',
        component: CompaignComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.compaign.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'compaign/:id/view',
        component: CompaignDetailComponent,
        resolve: {
            compaign: CompaignResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.compaign.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'compaign/new',
        component: CompaignUpdateComponent,
        resolve: {
            compaign: CompaignResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.compaign.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'compaign/:id/edit',
        component: CompaignUpdateComponent,
        resolve: {
            compaign: CompaignResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.compaign.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const compaignPopupRoute: Routes = [
    {
        path: 'compaign/:id/delete',
        component: CompaignDeletePopupComponent,
        resolve: {
            compaign: CompaignResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.compaign.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
