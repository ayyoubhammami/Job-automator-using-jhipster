import { Moment } from 'moment';
import { IProfilCandidate } from 'app/shared/model//profil-candidate.model';
import { IPost } from 'app/shared/model//post.model';

export const enum Status {
    POSSIBLE = 'POSSIBLE',
    IN_DISCUSSION = 'IN_DISCUSSION',
    Selected = 'Selected',
    Rejected = 'Rejected'
}

export interface ICandidature {
    id?: number;
    status?: Status;
    date?: Moment;
    profilCandidate?: IProfilCandidate;
    post?: IPost;
}

export class Candidature implements ICandidature {
    constructor(
        public id?: number,
        public status?: Status,
        public date?: Moment,
        public profilCandidate?: IProfilCandidate,
        public post?: IPost
    ) {}
}
