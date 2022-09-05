import { Matiere } from './../matiere/matiere.model';
import { Student } from "../person/student/student.model";
import { Column, Entity, JoinColumn, ManyToOne, OneToOne, PrimaryColumn, Unique } from 'typeorm';

@Entity()
@Unique(['noteStudent','noteMatiere'])
export class Notes{
    @PrimaryColumn("uuid")
    noteId!: string;
    @Column({ type: 'double'})
    note!: number;

    @ManyToOne(() => Student, (student) => student.studentNotes)
    noteStudent!: Student;
    @ManyToOne(() => Matiere, (matiere) => matiere.matiereNote)
    noteMatiere!: Matiere;

   /* constructor(id: string,notes:number,student:Student,matiere:Matiere) {
        this.noteId = id;
        this.note = notes;
        this.noteStudent = student;
        this.noteMatiere = matiere;
    }*/
}
