import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ProfilCondidate } from 'app/shared/model/profil-condidate.model';
import { ProfilCondidateService } from './profil-condidate.service';
import { ProfilCondidateComponent } from './profil-condidate.component';
import { ProfilCondidateDetailComponent } from './profil-condidate-detail.component';
import { ProfilCondidateUpdateComponent } from './profil-condidate-update.component';
import { ProfilCondidateDeletePopupComponent } from './profil-condidate-delete-dialog.component';
import { IProfilCondidate } from 'app/shared/model/profil-condidate.model';

@Injectable({ providedIn: 'root' })
export class ProfilCondidateResolve implements Resolve<IProfilCondidate> {
    constructor(private service: ProfilCondidateService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((profilCondidate: HttpResponse<ProfilCondidate>) => profilCondidate.body);
        }
        return Observable.of(new ProfilCondidate());
    }
}

export const profilCondidateRoute: Routes = [
    {
        path: 'profil-condidate',
        component: ProfilCondidateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCondidate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-condidate/:id/view',
        component: ProfilCondidateDetailComponent,
        resolve: {
            profilCondidate: ProfilCondidateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCondidate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-condidate/new',
        component: ProfilCondidateUpdateComponent,
        resolve: {
            profilCondidate: ProfilCondidateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCondidate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-condidate/:id/edit',
        component: ProfilCondidateUpdateComponent,
        resolve: {
            profilCondidate: ProfilCondidateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCondidate.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const profilCondidatePopupRoute: Routes = [
    {
        path: 'profil-condidate/:id/delete',
        component: ProfilCondidateDeletePopupComponent,
        resolve: {
            profilCondidate: ProfilCondidateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCondidate.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
