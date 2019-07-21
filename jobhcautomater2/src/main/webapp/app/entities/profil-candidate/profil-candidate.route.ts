import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ProfilCandidate } from 'app/shared/model/profil-candidate.model';
import { ProfilCandidateService } from './profil-candidate.service';
import { ProfilCandidateComponent } from './profil-candidate.component';
import { ProfilCandidateDetailComponent } from './profil-candidate-detail.component';
import { ProfilCandidateUpdateComponent } from './profil-candidate-update.component';
import { ProfilCandidateDeletePopupComponent } from './profil-candidate-delete-dialog.component';
import { IProfilCandidate } from 'app/shared/model/profil-candidate.model';

@Injectable({ providedIn: 'root' })
export class ProfilCandidateResolve implements Resolve<IProfilCandidate> {
    constructor(private service: ProfilCandidateService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((profilCandidate: HttpResponse<ProfilCandidate>) => profilCandidate.body);
        }
        return Observable.of(new ProfilCandidate());
    }
}

export const profilCandidateRoute: Routes = [
    {
        path: 'profil-candidate',
        component: ProfilCandidateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCandidate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-candidate/:id/view',
        component: ProfilCandidateDetailComponent,
        resolve: {
            profilCandidate: ProfilCandidateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCandidate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-candidate/new',
        component: ProfilCandidateUpdateComponent,
        resolve: {
            profilCandidate: ProfilCandidateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCandidate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-candidate/:id/edit',
        component: ProfilCandidateUpdateComponent,
        resolve: {
            profilCandidate: ProfilCandidateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCandidate.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const profilCandidatePopupRoute: Routes = [
    {
        path: 'profil-candidate/:id/delete',
        component: ProfilCandidateDeletePopupComponent,
        resolve: {
            profilCandidate: ProfilCandidateResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.profilCandidate.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
