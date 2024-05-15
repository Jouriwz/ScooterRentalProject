export class Pokemon {
    name: string;
    health: number;
    type: string;
    rarity: string;
    defense: number;
    speed: number;
    attack: number;

    constructor(json:any) {
        this.name = json.name;
        this.health = json.health;
        this.type = json.type;
        this.rarity = json.rarity;
        this.defense = json.defense;
        this.speed = json.speed;
        this.attack = json.attack;
    }
}
