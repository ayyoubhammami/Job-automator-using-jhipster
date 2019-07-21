import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { EmployeeDetails } from 'app/shared/model/employee-details.model';
import { EmployeeDetailsService } from './employee-details.service';
import { EmployeeDetailsComponent } from './employee-details.component';
import { EmployeeDetailsDetailComponent } from './employee-details-detail.component';
import { EmployeeDetailsUpdateComponent } from './employee-details-update.component';
import { EmployeeDetailsDeletePopupComponent } from './employee-details-delete-dialog.component';
import { IEmployeeDetails } from 'app/shared/model/employee-details.model';

@Injectable({ providedIn: 'root' })
export class EmployeeDetailsResolve implements Resolve<IEmployeeDetails> {
    constructor(private service: EmployeeDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((employeeDetails: HttpResponse<EmployeeDetails>) => employeeDetails.body);
        }
        return Observable.of(new EmployeeDetails());
    }
}

export const employeeDetailsRoute: Routes = [
    {
        path: 'employee-details',
        component: EmployeeDetailsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.employeeDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-details/:id/view',
        component: EmployeeDetailsDetailComponent,
        resolve: {
            employeeDetails: EmployeeDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.employeeDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-details/new',
        component: EmployeeDetailsUpdateComponent,
        resolve: {
            employeeDetails: EmployeeDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.employeeDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-details/:id/edit',
        component: EmployeeDetailsUpdateComponent,
        resolve: {
            employeeDetails: EmployeeDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.employeeDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const employeeDetailsPopupRoute: Routes = [
    {
        path: 'employee-details/:id/delete',
        component: EmployeeDetailsDeletePopupComponent,
        resolve: {
            employeeDetails: EmployeeDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jobHcAutomater2App.employeeDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
