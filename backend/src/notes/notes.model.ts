import { Matiere } from './../matiere/matiere.model';
import { Student } from "../person/student/student.model";
import { Column, Entity, JoinColumn, ManyToOne, OneToOne, PrimaryColumn, Unique } from 'typeorm';

@Entity()
@Unique(['noteStudent','noteMatiere'])
export class Notes{
    @PrimaryColumn()
    noteId!: string;
    @Column({ type: 'double'})
    note!: number;
    @ManyToOne(() => Student, (student) => student.studentNotes,{cascade:['insert']})
    noteStudent!: Student;
    @ManyToOne(() => Matiere, (matiere) => matiere.matiereNote,{cascade:['insert']})
    noteMatiere!: Matiere;
}
