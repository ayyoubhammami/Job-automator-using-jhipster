import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { FreelancerDetails } from 'app/shared/model/freelancer-details.model';
import { FreelancerDetailsService } from './freelancer-details.service';
import { FreelancerDetailsComponent } from './freelancer-details.component';
import { FreelancerDetailsDetailComponent } from './freelancer-details-detail.component';
import { FreelancerDetailsUpdateComponent } from './freelancer-details-update.component';
import { FreelancerDetailsDeletePopupComponent } from './freelancer-details-delete-dialog.component';
import { IFreelancerDetails } from 'app/shared/model/freelancer-details.model';

@Injectable({ providedIn: 'root' })
export class FreelancerDetailsResolve implements Resolve<IFreelancerDetails> {
    constructor(private service: FreelancerDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((freelancerDetails: HttpResponse<FreelancerDetails>) => freelancerDetails.body);
        }
        return Observable.of(new FreelancerDetails());
    }
}

export const freelancerDetailsRoute: Routes = [
    {
        path: 'freelancer-details',
        component: FreelancerDetailsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.freelancerDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'freelancer-details/:id/view',
        component: FreelancerDetailsDetailComponent,
        resolve: {
            freelancerDetails: FreelancerDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.freelancerDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'freelancer-details/new',
        component: FreelancerDetailsUpdateComponent,
        resolve: {
            freelancerDetails: FreelancerDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.freelancerDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'freelancer-details/:id/edit',
        component: FreelancerDetailsUpdateComponent,
        resolve: {
            freelancerDetails: FreelancerDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.freelancerDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const freelancerDetailsPopupRoute: Routes = [
    {
        path: 'freelancer-details/:id/delete',
        component: FreelancerDetailsDeletePopupComponent,
        resolve: {
            freelancerDetails: FreelancerDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.freelancerDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
