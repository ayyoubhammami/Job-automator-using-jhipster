import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFreelancerDetails } from 'app/shared/model/freelancer-details.model';

type EntityResponseType = HttpResponse<IFreelancerDetails>;
type EntityArrayResponseType = HttpResponse<IFreelancerDetails[]>;

@Injectable({ providedIn: 'root' })
export class FreelancerDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/freelancer-details';
    private resourceUrl2 = SERVER_API_URL + 'api/freelancer-details-user';

    constructor(private http: HttpClient) {}

    create(freelancerDetails: IFreelancerDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(freelancerDetails);
        return this.http
            .post<IFreelancerDetails>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(freelancerDetails: IFreelancerDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(freelancerDetails);
        return this.http
            .put<IFreelancerDetails>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFreelancerDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFreelancerDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(freelancerDetails: IFreelancerDetails): IFreelancerDetails {
        const copy: IFreelancerDetails = Object.assign({}, freelancerDetails, {
            delevredDate:
                freelancerDetails.delevredDate != null && freelancerDetails.delevredDate.isValid()
                    ? freelancerDetails.delevredDate.toJSON()
                    : null,
            hiringDate:
                freelancerDetails.hiringDate != null && freelancerDetails.hiringDate.isValid()
                    ? freelancerDetails.hiringDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.delevredDate = res.body.delevredDate != null ? moment(res.body.delevredDate) : null;
        res.body.hiringDate = res.body.hiringDate != null ? moment(res.body.hiringDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((freelancerDetails: IFreelancerDetails) => {
            freelancerDetails.delevredDate = freelancerDetails.delevredDate != null ? moment(freelancerDetails.delevredDate) : null;
            freelancerDetails.hiringDate = freelancerDetails.hiringDate != null ? moment(freelancerDetails.hiringDate) : null;
        });
        return res;
    }
    getFreelancerByUser(id: any) {
        return this.http.get<IFreelancerDetails>(`${this.resourceUrl2}/${id}`);
    }
}
