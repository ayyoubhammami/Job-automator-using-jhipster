import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import {
    InternalFilterComponent,
    InternalFilterDetailComponent,
    InternalFilterUpdateComponent,
    InternalFilterDeletePopupComponent,
    InternalFilterDeleteDialogComponent,
    internalFilterRoute,
    internalFilterPopupRoute
} from './';
import { Ng5SliderModule } from 'ng5-slider';

const ENTITY_STATES = [...internalFilterRoute, ...internalFilterPopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, RouterModule.forChild(ENTITY_STATES), Ng5SliderModule],
    declarations: [
        InternalFilterComponent,
        InternalFilterDetailComponent,
        InternalFilterUpdateComponent,
        InternalFilterDeleteDialogComponent,
        InternalFilterDeletePopupComponent
    ],
    entryComponents: [
        InternalFilterComponent,
        InternalFilterUpdateComponent,
        InternalFilterDeleteDialogComponent,
        InternalFilterDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2InternalFilterModule {}
