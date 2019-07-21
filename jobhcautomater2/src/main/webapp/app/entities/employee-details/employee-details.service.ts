import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEmployeeDetails } from 'app/shared/model/employee-details.model';

type EntityResponseType = HttpResponse<IEmployeeDetails>;
type EntityArrayResponseType = HttpResponse<IEmployeeDetails[]>;

@Injectable({ providedIn: 'root' })
export class EmployeeDetailsService {
    private resourceUrl = SERVER_API_URL + 'api/employee-details';
    private resourceUrl2 = SERVER_API_URL + 'api/employee-details-user';

    constructor(private http: HttpClient) {}

    create(employeeDetails: IEmployeeDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(employeeDetails);
        return this.http
            .post<IEmployeeDetails>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(employeeDetails: IEmployeeDetails): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(employeeDetails);
        return this.http
            .put<IEmployeeDetails>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEmployeeDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEmployeeDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(employeeDetails: IEmployeeDetails): IEmployeeDetails {
        const copy: IEmployeeDetails = Object.assign({}, employeeDetails, {
            delevredDate:
                employeeDetails.delevredDate != null && employeeDetails.delevredDate.isValid()
                    ? employeeDetails.delevredDate.toJSON()
                    : null,
            hiringDate:
                employeeDetails.hiringDate != null && employeeDetails.hiringDate.isValid() ? employeeDetails.hiringDate.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.delevredDate = res.body.delevredDate != null ? moment(res.body.delevredDate) : null;
        res.body.hiringDate = res.body.hiringDate != null ? moment(res.body.hiringDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((employeeDetails: IEmployeeDetails) => {
            employeeDetails.delevredDate = employeeDetails.delevredDate != null ? moment(employeeDetails.delevredDate) : null;
            employeeDetails.hiringDate = employeeDetails.hiringDate != null ? moment(employeeDetails.hiringDate) : null;
        });
        return res;
    }
    getEmployeeByUser(id: any) {
        return this.http.get<IEmployeeDetails>(`${this.resourceUrl2}/${id}`);
    }
}
