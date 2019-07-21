import { ICandidature } from 'app/shared/model//candidature.model';

export interface IProfilCandidate {
    id?: number;
    firstName?: string;
    lastName?: string;
    phoneNumber?: string;
    email?: string;
    facebook?: string;
    linkedIn?: string;
    twiter?: string;
    profilCandidates?: ICandidature[];
}

export class ProfilCandidate implements IProfilCandidate {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public phoneNumber?: string,
        public email?: string,
        public facebook?: string,
        public linkedIn?: string,
        public twiter?: string,
        public profilCandidates?: ICandidature[]
    ) {}
}
