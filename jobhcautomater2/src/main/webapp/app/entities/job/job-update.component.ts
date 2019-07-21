import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IJob } from 'app/shared/model/job.model';
import { JobService } from './job.service';
import { Options, LabelType } from 'ng5-slider';
@Component({
    selector: 'jhi-job-update',
    templateUrl: './job-update.component.html'
})
export class JobUpdateComponent implements OnInit {
    private _job: IJob;
    isSaving: boolean;
       datePostedDp: number= Date.now();
   
    
  minBudget: number = 30;
  maxBudget: number = 60;
  options: Options = {
    floor: 0,
    ceil: 100000,
    translate: (value: number, label: LabelType): string => {
      switch (label) {
        case LabelType.Low:
          return   '<b> Min budget: </b>' + value  ;
        case LabelType.High:
          return '<b> Max budget: </b>' + value  ;
        default:
          return value + '';
      }
    }
  };
    

  minHoursPerWeek: number = 70;
  maxHoursPerWeek: number = 100;
  options1: Options = {
    floor: 0,
    ceil: 168,
    translate: (value: number, label: LabelType): string => {
      switch (label) {
        case LabelType.Low:
          return   '<b> Min hours: </b>' + value + 'hours' ;
        case LabelType.High:
          return '<b> Max hours: </b>' + value +  'hours' ;
        default:
          return value + 'hours';
      }
    }
  };


  minProjectLenghtWithMonthUnit: number = 5;
  maxProjectLenghtWithMonthUnit: number = 20;
  options2: Options = {
    floor: 1,
    ceil: 48,
    translate: (value: number, label: LabelType): string => {
      switch (label) {
        case LabelType.Low:
          return   '<b> Min Month: </b>' + value + 'Month' ;
        case LabelType.High:
          return '<b> Max Month: </b>' + value +  'Month' ;
        default:
          return value + 'Month';
      }
    }
  };

		clientHiresNumber: number = 50;
 		 options3: Options = {
    	floor: 0,
    	ceil: 100
 		 };


    constructor(private jobService: JobService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ job }) => {
            
            this.job = job;
            
        });
        
                if (this.job.id == undefined) {
                	this.job.minBudget=30;
                	this.job.maxBudget=60;	 
           		
               		this.job.minHoursPerWeek=70;
               		this.job.maxHoursPerWeek=130;
               		this.job.minProjectLenghtWithMonthUnit=5;
               		this.job.maxProjectLenghtWithMonthUnit=20;
               		this.job.clientHiresNumber=50;
               
                }
    
        
}

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.job.id !== undefined) {
            this.subscribeToSaveResponse(this.jobService.update(this.job));
        } else {
            this.subscribeToSaveResponse(this.jobService.create(this.job));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IJob>>) {
        result.subscribe((res: HttpResponse<IJob>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get job() {
        return this._job;
    }

    set job(job: IJob) {
        this._job = job;
    }
}
