import { Notes } from './../notes/notes.model';
import { Column, Entity, OneToMany, PrimaryColumn } from "typeorm";

@Entity()
export class Matiere {
    @PrimaryColumn({type:'uuid',primary:true})
    matiereId!: string;
    @Column({type:'varchar',length:4})
    matiereCode!: string;
    @Column({type:'varchar',length:20})
    matiereLibelle!: string;
    @Column({type:'tinyint',unsigned:true,default:0})
    matiereCoeff!: number;
    @OneToMany(() => Notes, (notes) => notes.noteMatiere,{cascade:['insert','remove','update']})
    matiereNote!: Notes[];
   
} 