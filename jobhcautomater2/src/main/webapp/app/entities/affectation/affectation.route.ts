import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Affectation } from 'app/shared/model/affectation.model';
import { AffectationService } from './affectation.service';
import { AffectationComponent } from './affectation.component';
import { AffectationDetailComponent } from './affectation-detail.component';
import { AffectationUpdateComponent } from './affectation-update.component';
import { AffectationDeletePopupComponent } from './affectation-delete-dialog.component';
import { IAffectation } from 'app/shared/model/affectation.model';

@Injectable({ providedIn: 'root' })
export class AffectationResolve implements Resolve<IAffectation> {
    constructor(private service: AffectationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((affectation: HttpResponse<Affectation>) => affectation.body);
        }
        return Observable.of(new Affectation());
    }
}

export const affectationRoute: Routes = [
    {
        path: 'affectation',
        component: AffectationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.affectation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'affectation/:id/view',
        component: AffectationDetailComponent,
        resolve: {
            affectation: AffectationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.affectation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'affectation/new',
        component: AffectationUpdateComponent,
        resolve: {
            affectation: AffectationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.affectation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'affectation/:id/edit',
        component: AffectationUpdateComponent,
        resolve: {
            affectation: AffectationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.affectation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const affectationPopupRoute: Routes = [
    {
        path: 'affectation/:id/delete',
        component: AffectationDeletePopupComponent,
        resolve: {
            affectation: AffectationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.affectation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
