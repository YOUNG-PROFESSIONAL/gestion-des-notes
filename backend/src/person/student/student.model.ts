import { Entity, Column, OneToMany } from "typeorm";
import { Notes } from "../../notes/notes.model";
import { Person } from "../person.model";
import { LEVEL } from "./level.enum";
import { PARCOURS } from "./parcours.enum";

@Entity()
export class Student extends Person{
    @Column({
        type: 'enum'
        ,enum:LEVEL})
    studentLevel: LEVEL;

    @OneToMany(() => Notes,(notes) => notes.note)
    studentNotes: Notes[];
   
    constructor(num:string, name:string,matricule:string,level:LEVEL,notes:Notes[]){
        super(num, name,matricule);
        this.studentLevel = level;
        this.studentNotes = notes;
    }
}