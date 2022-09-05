import { dataSource } from './../config/dbconnect';
import { Notes } from '../notes/notes.model';
import { Student } from '../person/student/student.model';
import { LEVEL } from '../person/student/level.enum';
import { createQueryBuilder, Like } from 'typeorm';
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

        const classements : Classement[] = [];

        const students:Student[] = await this.repoStudent.find({
            where:{
                studentLevel: level
            }
        })
        for(var student of students){
            const classement : Classement = {} as Classement;
            classement.bCoefficient = 0;
            classement.bAverage = 0;
            const notes : Notes[] = await this.repoNotes.find({
                where:{noteStudent:{personId:student.personId}},
                relations:{noteMatiere:true}
            })
           classement.bStudent = student;
           classement.bNotes = notes;
           notes.forEach(note => {
            classement.bCoefficient = classement.bCoefficient + note.noteMatiere.matiereCoeff;
            classement.bAverage = classement.bAverage + note.note * note.noteMatiere.matiereCoeff;
           })
           classement.bAverage = classement.bAverage/classement.bCoefficient

           classements.push(classement);
            console.log(classement);
        }
        console.log(classements);
        
        return classements.sort((a,b)=>(a.bAverage>b.bAverage?-1:1));
    }
}
export default new ClassementService();
