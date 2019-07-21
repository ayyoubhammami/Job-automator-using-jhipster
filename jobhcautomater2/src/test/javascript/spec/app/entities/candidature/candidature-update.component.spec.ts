/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { CandidatureUpdateComponent } from 'app/entities/candidature/candidature-update.component';
import { CandidatureService } from 'app/entities/candidature/candidature.service';
import { Candidature } from 'app/shared/model/candidature.model';

describe('Component Tests', () => {
    describe('Candidature Management Update Component', () => {
        let comp: CandidatureUpdateComponent;
        let fixture: ComponentFixture<CandidatureUpdateComponent>;
        let service: CandidatureService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [CandidatureUpdateComponent]
            })
                .overrideTemplate(CandidatureUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CandidatureUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidatureService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Candidature(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.candidature = entity;
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
                    const entity = new Candidature();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.candidature = entity;
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
