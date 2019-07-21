import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfilCondidate } from 'app/shared/model/profil-condidate.model';
import { ProfilCondidateService } from './profil-condidate.service';

@Component({
    selector: 'jhi-profil-condidate-delete-dialog',
    templateUrl: './profil-condidate-delete-dialog.component.html'
})
export class ProfilCondidateDeleteDialogComponent {
    profilCondidate: IProfilCondidate;

    constructor(
        private profilCondidateService: ProfilCondidateService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.profilCondidateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'profilCondidateListModification',
                content: 'Deleted an profilCondidate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-profil-condidate-delete-popup',
    template: ''
})
export class ProfilCondidateDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilCondidate }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProfilCondidateDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.profilCondidate = profilCondidate;
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
