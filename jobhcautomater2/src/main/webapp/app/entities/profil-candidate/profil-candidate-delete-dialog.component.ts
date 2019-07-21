import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfilCandidate } from 'app/shared/model/profil-candidate.model';
import { ProfilCandidateService } from './profil-candidate.service';

@Component({
    selector: 'jhi-profil-candidate-delete-dialog',
    templateUrl: './profil-candidate-delete-dialog.component.html'
})
export class ProfilCandidateDeleteDialogComponent {
    profilCandidate: IProfilCandidate;

    constructor(
        private profilCandidateService: ProfilCandidateService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.profilCandidateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'profilCandidateListModification',
                content: 'Deleted an profilCandidate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-profil-candidate-delete-popup',
    template: ''
})
export class ProfilCandidateDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilCandidate }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProfilCandidateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.profilCandidate = profilCandidate;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
