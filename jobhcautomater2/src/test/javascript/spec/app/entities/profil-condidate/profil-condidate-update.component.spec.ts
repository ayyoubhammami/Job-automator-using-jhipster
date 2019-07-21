/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { ProfilCondidateUpdateComponent } from 'app/entities/profil-condidate/profil-condidate-update.component';
import { ProfilCondidateService } from 'app/entities/profil-condidate/profil-condidate.service';
import { ProfilCondidate } from 'app/shared/model/profil-condidate.model';

describe('Component Tests', () => {
    describe('ProfilCondidate Management Update Component', () => {
        let comp: ProfilCondidateUpdateComponent;
        let fixture: ComponentFixture<ProfilCondidateUpdateComponent>;
        let service: ProfilCondidateService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [ProfilCondidateUpdateComponent]
            })
                .overrideTemplate(ProfilCondidateUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfilCondidateUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilCondidateService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProfilCondidate(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.profilCondidate = entity;
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
                    const entity = new ProfilCondidate();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.profilCondidate = entity;
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
