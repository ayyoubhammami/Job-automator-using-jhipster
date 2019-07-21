import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JobHcAutomater2SharedModule } from 'app/shared';
import {
    SocialMediaComponent,
    SocialMediaDetailComponent,
    SocialMediaUpdateComponent,
    SocialMediaDeletePopupComponent,
    SocialMediaDeleteDialogComponent,
    socialMediaRoute,
    socialMediaPopupRoute
} from './';

const ENTITY_STATES = [...socialMediaRoute, ...socialMediaPopupRoute];

@NgModule({
    imports: [JobHcAutomater2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SocialMediaComponent,
        SocialMediaDetailComponent,
        SocialMediaUpdateComponent,
        SocialMediaDeleteDialogComponent,
        SocialMediaDeletePopupComponent
    ],
    entryComponents: [SocialMediaComponent, SocialMediaUpdateComponent, SocialMediaDeleteDialogComponent, SocialMediaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2SocialMediaModule {}
