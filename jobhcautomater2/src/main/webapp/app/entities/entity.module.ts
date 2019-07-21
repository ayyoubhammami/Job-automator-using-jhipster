import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JobHcAutomater2JobModule } from './job/job.module';

import { JobHcAutomater2EmployeeDetailsModule } from './employee-details/employee-details.module';
import { JobHcAutomater2FreelancerDetailsModule } from './freelancer-details/freelancer-details.module';

import { JobHcAutomater2ProfilCondidateModule } from './profil-condidate/profil-condidate.module';

import { JobHcAutomater2ProfilCandidateModule } from './profil-candidate/profil-candidate.module';
import { JobHcAutomater2CandidatureModule } from './candidature/candidature.module';
import { JobHcAutomater2PostModule } from './post/post.module';





import { JobHcAutomater2AffectationModule } from './affectation/affectation.module';

import { JobHcAutomater2CompaignModule } from './compaign/compaign.module';
import { JobHcAutomater2SocialMediaModule } from './social-media/social-media.module';


import { JobHcAutomater2SourceModule } from './source/source.module';

import { JobHcAutomater2KeywordModule } from './keyword/keyword.module';

import { JobHcAutomater2EmailModule } from './email/email.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */


import { JobHcAutomater2InternalFilterModule } from './internal-filter/internal-filter.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [

        JobHcAutomater2JobModule,

        JobHcAutomater2EmployeeDetailsModule,
        JobHcAutomater2FreelancerDetailsModule,


        JobHcAutomater2ProfilCondidateModule,
        JobHcAutomater2JobModule,

        JobHcAutomater2ProfilCandidateModule,
        JobHcAutomater2CandidatureModule,
        JobHcAutomater2PostModule,

        JobHcAutomater2AffectationModule,


        JobHcAutomater2CompaignModule,
        JobHcAutomater2SocialMediaModule,


        JobHcAutomater2SourceModule,
        JobHcAutomater2KeywordModule,

        JobHcAutomater2EmailModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */




        JobHcAutomater2InternalFilterModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */


    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JobHcAutomater2EntityModule {}
