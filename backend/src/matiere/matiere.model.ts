import { Notes } from './../notes/notes.model';
import { Column, Entity, OneToMany, PrimaryColumn } from "typeorm";

@Entity()
export class Matiere {
    @PrimaryColumn()
    matiereId: string;
    @Column({type:'varchar',length:4})
    matiereCode: string;
    @Column({type:'varchar',length:20})
    matiereLibelle: string;
    @Column({type:'tinyint',unsigned:true})
    matiereCoeff: number;
    @OneToMany(() => Notes, (notes) => notes.noteMatiere)
    matiereNote: Notes[];
    constructor(id: string,code:string,libelle:string,coeff:number,note:Notes[]) {
        this.matiereId = id;
        this.matiereCode = code;
        this.matiereLibelle = libelle;
        this.matiereCoeff = coeff;
        this.matiereNote = note;
    }
} 