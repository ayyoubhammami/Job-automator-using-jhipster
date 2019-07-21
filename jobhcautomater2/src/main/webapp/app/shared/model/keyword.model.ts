export interface IKeyword {
    id?: number;
    libelle?: string;
    description?: string;
}

export class Keyword implements IKeyword {
    constructor(public id?: number, public libelle?: string, public description?: string) {}
}
