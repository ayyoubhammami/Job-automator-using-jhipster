/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { ProfilCandidateUpdateComponent } from 'app/entities/profil-candidate/profil-candidate-update.component';
import { ProfilCandidateService } from 'app/entities/profil-candidate/profil-candidate.service';
import { ProfilCandidate } from 'app/shared/model/profil-candidate.model';

describe('Component Tests', () => {
    describe('ProfilCandidate Management Update Component', () => {
        let comp: ProfilCandidateUpdateComponent;
        let fixture: ComponentFixture<ProfilCandidateUpdateComponent>;
        let service: ProfilCandidateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [ProfilCandidateUpdateComponent]
            })
                .overrideTemplate(ProfilCandidateUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfilCandidateUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilCandidateService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProfilCandidate(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.profilCandidate = entity;
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
                    const entity = new ProfilCandidate();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.profilCandidate = entity;
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
