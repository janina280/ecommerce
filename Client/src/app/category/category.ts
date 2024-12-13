export class Category {
    id: number;
    name: string;
    description: string;

    public constructor(id: number, name: string, description: string) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
