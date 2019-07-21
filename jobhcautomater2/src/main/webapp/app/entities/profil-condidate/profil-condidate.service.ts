import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProfilCondidate } from 'app/shared/model/profil-condidate.model';

type EntityResponseType = HttpResponse<IProfilCondidate>;
type EntityArrayResponseType = HttpResponse<IProfilCondidate[]>;

@Injectable({ providedIn: 'root' })
export class ProfilCondidateService {
    private resourceUrl = SERVER_API_URL + 'api/profil-condidates';

    constructor(private http: HttpClient) {}

    create(profilCondidate: IProfilCondidate): Observable<EntityResponseType> {
        return this.http.post<IProfilCondidate>(this.resourceUrl, profilCondidate, { observe: 'response' });
    }

    update(profilCondidate: IProfilCondidate): Observable<EntityResponseType> {
        return this.http.put<IProfilCondidate>(this.resourceUrl, profilCondidate, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProfilCondidate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProfilCondidate[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
