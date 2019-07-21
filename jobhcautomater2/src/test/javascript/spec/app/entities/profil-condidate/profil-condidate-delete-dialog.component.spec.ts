/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { ProfilCondidateDeleteDialogComponent } from 'app/entities/profil-condidate/profil-condidate-delete-dialog.component';
import { ProfilCondidateService } from 'app/entities/profil-condidate/profil-condidate.service';

describe('Component Tests', () => {
    describe('ProfilCondidate Management Delete Component', () => {
        let comp: ProfilCondidateDeleteDialogComponent;
        let fixture: ComponentFixture<ProfilCondidateDeleteDialogComponent>;
        let service: ProfilCondidateService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [ProfilCondidateDeleteDialogComponent]
            })
                .overrideTemplate(ProfilCondidateDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfilCondidateDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilCondidateService);
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
