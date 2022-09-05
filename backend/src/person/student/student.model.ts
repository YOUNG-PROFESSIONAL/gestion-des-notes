import { Entity, Column, OneToMany, JoinColumn } from "typeorm";
import { Notes } from "../../notes/notes.model";
import { Person } from "../person.model";
import { LEVEL } from "./level.enum";

@Entity()
export class Student extends Person{
    @Column({
        type: 'int'
        })
    studentLevel!: number;
    @OneToMany(() => Notes,(notes) => notes.note,{cascade:['insert','remove','update']})
    studentNotes!: Notes[];
   
}