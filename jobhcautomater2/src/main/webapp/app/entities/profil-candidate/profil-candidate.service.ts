import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProfilCandidate } from 'app/shared/model/profil-candidate.model';

type EntityResponseType = HttpResponse<IProfilCandidate>;
type EntityArrayResponseType = HttpResponse<IProfilCandidate[]>;

@Injectable({ providedIn: 'root' })
export class ProfilCandidateService {
    private resourceUrl = SERVER_API_URL + 'api/profil-candidates';

    constructor(private http: HttpClient) {}

    create(profilCandidate: IProfilCandidate): Observable<EntityResponseType> {
        return this.http.post<IProfilCandidate>(this.resourceUrl, profilCandidate, { observe: 'response' });
    }

    update(profilCandidate: IProfilCandidate): Observable<EntityResponseType> {
        return this.http.put<IProfilCandidate>(this.resourceUrl, profilCandidate, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProfilCandidate>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProfilCandidate[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
