import { PrimaryColumn, Column} from "typeorm";


export abstract class Person {
    
    // @PrimaryColumn()
    @PrimaryColumn("uuid")
    personId: string;
    @Column({type:'varchar',length:25})
    personName: string;                     //Nom
    @Column({type:'varchar',length:10})
    personMatricule: string;                // N°Etudiant

    constructor(id: string,name:string,matricule:string) {
        this.personId = id;
        this.personName = name;
        this.personMatricule = matricule;
    }
} 