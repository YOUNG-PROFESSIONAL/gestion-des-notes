import { Notes } from '../notes/notes.model';
import { dataSource } from '../config/dbconnect';
import { Student } from '../person/student/student.model';
import { LEVEL } from '../person/student/level.enum';
import { Like } from 'typeorm';
import { Classement } from './classement.model';
interface IClassementService{
    getStudents(level:LEVEL): Promise<Student[]> ;
    getClassement(level:LEVEL):Promise<Classement[]>;
}

class ClassementService implements IClassementService{
    private repoStudent = dataSource.getRepository(Student);
    private repoNotes = dataSource.getRepository(Notes);

    async getStudents(level: any): Promise<Student[]> {
        
        const student = await this.repoStudent.find({
            where:{
                studentLevel: Like(level.studentLevel)
            }
        })
        return student as any;
    }

    async getClassement(level: any): Promise<Classement[]> {

        const classements: Classement[] = [];
        const classement : Classement = {
            bNotes:[] as Notes[]
         } as Classement;
        
        let notes: Notes[] ;

        notes = await this.repoNotes.find({
            where:{
                noteStudent:{studentLevel:level.level} 
            },
            relations:{
                noteStudent:true,
                noteMatiere:true
            }
        }) as any
         
         notes.forEach((note)=>{
            classement.bAverage = classement.bAverage + note.note*note.noteMatiere.matiereCoeff;
            classement.bCoefficient = classement.bCoefficient + note.noteMatiere.matiereCoeff;
         })
         console.log(notes)
         classement.bAverage = classement.bAverage/classement.bCoefficient;
        
        return classements.sort((student)=> student.bAverage) as any;
    }
}
export default new ClassementService();
