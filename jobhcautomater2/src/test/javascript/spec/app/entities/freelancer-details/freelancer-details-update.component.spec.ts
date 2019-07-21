/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { FreelancerDetailsUpdateComponent } from 'app/entities/freelancer-details/freelancer-details-update.component';
import { FreelancerDetailsService } from 'app/entities/freelancer-details/freelancer-details.service';
import { FreelancerDetails } from 'app/shared/model/freelancer-details.model';

describe('Component Tests', () => {
    describe('FreelancerDetails Management Update Component', () => {
        let comp: FreelancerDetailsUpdateComponent;
        let fixture: ComponentFixture<FreelancerDetailsUpdateComponent>;
        let service: FreelancerDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [FreelancerDetailsUpdateComponent]
            })
                .overrideTemplate(FreelancerDetailsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FreelancerDetailsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FreelancerDetailsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FreelancerDetails(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.freelancerDetails = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FreelancerDetails();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.freelancerDetails = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
