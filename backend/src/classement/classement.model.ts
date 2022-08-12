import { Notes } from '../notes/notes.model';
import { Student } from '../person/student/student.model';
export interface Classement {
    bStudent: Student;
    bNotes: Notes[];
    bAverage: number;
    bCoefficient: number;
}