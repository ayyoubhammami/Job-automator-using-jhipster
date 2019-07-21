import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import { JobHcAutomater2AdminModule } from 'app/admin/admin.module';
import {
    EmployeeDetailsComponent,
    EmployeeDetailsDetailComponent,
    EmployeeDetailsUpdateComponent,
    EmployeeDetailsDeletePopupComponent,
    EmployeeDetailsDeleteDialogComponent,
    employeeDetailsRoute,
    employeeDetailsPopupRoute
} from './';

const ENTITY_STATES = [...employeeDetailsRoute, ...employeeDetailsPopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, JobHcAutomater2AdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EmployeeDetailsComponent,
        EmployeeDetailsDetailComponent,
        EmployeeDetailsUpdateComponent,
        EmployeeDetailsDeleteDialogComponent,
        EmployeeDetailsDeletePopupComponent
    ],
    entryComponents: [
        EmployeeDetailsComponent,
        EmployeeDetailsUpdateComponent,
        EmployeeDetailsDeleteDialogComponent,
        EmployeeDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2EmployeeDetailsModule {}
