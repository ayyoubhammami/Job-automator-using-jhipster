/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { FreelancerDetailsDetailComponent } from 'app/entities/freelancer-details/freelancer-details-detail.component';
import { FreelancerDetails } from 'app/shared/model/freelancer-details.model';

describe('Component Tests', () => {
    describe('FreelancerDetails Management Detail Component', () => {
        let comp: FreelancerDetailsDetailComponent;
        let fixture: ComponentFixture<FreelancerDetailsDetailComponent>;
        const route = ({ data: of({ freelancerDetails: new FreelancerDetails(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [FreelancerDetailsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FreelancerDetailsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FreelancerDetailsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.freelancerDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
