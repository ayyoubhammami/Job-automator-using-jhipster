import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Candidature } from 'app/shared/model/candidature.model';
import { CandidatureService } from './candidature.service';
import { CandidatureComponent } from './candidature.component';
import { CandidatureDetailComponent } from './candidature-detail.component';
import { CandidatureUpdateComponent } from './candidature-update.component';
import { CandidatureDeletePopupComponent } from './candidature-delete-dialog.component';
import { ICandidature } from 'app/shared/model/candidature.model';

@Injectable({ providedIn: 'root' })
export class CandidatureResolve implements Resolve<ICandidature> {
    constructor(private service: CandidatureService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((candidature: HttpResponse<Candidature>) => candidature.body);
        }
        return Observable.of(new Candidature());
    }
}

export const candidatureRoute: Routes = [
    {
        path: 'candidature',
        component: CandidatureComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.candidature.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'candidature/:id/view',
        component: CandidatureDetailComponent,
        resolve: {
            candidature: CandidatureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.candidature.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'candidature/new',
        component: CandidatureUpdateComponent,
        resolve: {
            candidature: CandidatureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.candidature.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'candidature/:id/edit',
        component: CandidatureUpdateComponent,
        resolve: {
            candidature: CandidatureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.candidature.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const candidaturePopupRoute: Routes = [
    {
        path: 'candidature/:id/delete',
        component: CandidatureDeletePopupComponent,
        resolve: {
            candidature: CandidatureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.candidature.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
