/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { ProfilCandidateDeleteDialogComponent } from 'app/entities/profil-candidate/profil-candidate-delete-dialog.component';
import { ProfilCandidateService } from 'app/entities/profil-candidate/profil-candidate.service';

describe('Component Tests', () => {
    describe('ProfilCandidate Management Delete Component', () => {
        let comp: ProfilCandidateDeleteDialogComponent;
        let fixture: ComponentFixture<ProfilCandidateDeleteDialogComponent>;
        let service: ProfilCandidateService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [ProfilCandidateDeleteDialogComponent]
            })
                .overrideTemplate(ProfilCandidateDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfilCandidateDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilCandidateService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
