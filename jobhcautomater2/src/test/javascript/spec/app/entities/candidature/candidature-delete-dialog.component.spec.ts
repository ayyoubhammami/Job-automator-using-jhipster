/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JobHcAutomater2TestModule } from '../../../test.module';
import { CandidatureDeleteDialogComponent } from 'app/entities/candidature/candidature-delete-dialog.component';
import { CandidatureService } from 'app/entities/candidature/candidature.service';

describe('Component Tests', () => {
    describe('Candidature Management Delete Component', () => {
        let comp: CandidatureDeleteDialogComponent;
        let fixture: ComponentFixture<CandidatureDeleteDialogComponent>;
        let service: CandidatureService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JobHcAutomater2TestModule],
                declarations: [CandidatureDeleteDialogComponent]
            })
                .overrideTemplate(CandidatureDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CandidatureDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CandidatureService);
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
