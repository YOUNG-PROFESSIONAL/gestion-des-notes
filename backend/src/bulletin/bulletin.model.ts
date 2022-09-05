import { Observation } from './observation.enum';
import { Notes } from './../notes/notes.model';
import { Student } from './../person/student/student.model';
export interface Bulletin {
    bStudent: Student;
    bNotes: Notes[];
    bNotePonderee: number;
    bAverage: number;
    bCoefficient: number;
    bObservation: Observation;
    
    getBulletin(num:string): Bulletin;

}