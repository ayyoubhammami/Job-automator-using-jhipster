import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICompaign } from 'app/shared/model/compaign.model';
import { SocialMediaType } from 'app/shared/model/social-media.model';
import { CompaignService } from './compaign.service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'jhi-compaign-detail',
    templateUrl: './compaign-detail.component.html'
})
export class CompaignDetailComponent implements OnInit {
    compaign: ICompaign;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute, private compaignService: CompaignService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ compaign }) => {
            this.compaign = compaign;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    previousState() {
        window.history.back();
    }

    run_now(){
        this.compaignService.publish(this.compaign).subscribe((res: HttpResponse<ICompaign>) =>{alert("published") ;console.log("published")}, (res: HttpErrorResponse) => {alert("NOT published");console.log("NOT published");});

    }


}
