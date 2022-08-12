import { Notes } from './../notes/notes.model';
import { dataSource } from './../config/dbconnect';
import { Bulletin } from './bulletin.model';
import { Student } from './../person/student/student.model';
import { LEVEL } from './../person/student/level.enum';
import { Like } from 'typeorm';
import { Observation } from './observation.enum';
interface IBulletinService{
    getStudents(level:LEVEL): Promise<Student[]> ;
    getbulletin(student:Student):Promise<Bulletin>;
}

class BulletinService implements IBulletinService{
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
    async getbulletin(student: Student): Promise<Bulletin> {
        const bulletin : Bulletin = {
            bAverage:0,
            bCoefficient:0,
            bNotes:[] as Notes[] 
         } as Bulletin;
        let notes: Notes[] ;

        notes = await this.repoNotes.find({
            where:{
                noteStudent:{personId:student.personId} 
            },
            relations:{
                noteMatiere:true
            }
        }) as any
         
         notes.forEach((note)=>{
            bulletin.bAverage = bulletin.bAverage + note.note*note.noteMatiere.matiereCoeff;
            bulletin.bCoefficient = bulletin.bCoefficient + note.noteMatiere.matiereCoeff;
            bulletin.bNotes.push(note)
         })
         console.log()
         bulletin.bStudent = student;
         bulletin.bAverage = bulletin.bAverage/bulletin.bCoefficient;
         if(bulletin.bAverage<10) bulletin.bObservation = Observation.REDOUBLE;
         bulletin.bObservation = Observation.ADMIS;
        return bulletin as any;
    }
}
export default new BulletinService();