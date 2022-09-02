import { Matiere } from './../../matiere/matiere.model';
import { LEVEL } from './level.enum';
import { randomUUID } from 'crypto';
import { Student } from "./student.model";
import { dataSource } from '../../config/dbconnect';
import { Like } from 'typeorm';
import notesService from '../../notes/notes.service';
import { Notes } from '../../notes/notes.model';

interface IStudentService{
    getListStudents(key:string,level :LEVEL): Promise<Student[]> ;
    getStudent(id:string):Promise<Student>;
    addStudent(student:Student):Promise<Student>;
    updateStudent(student:Student):Promise<Student>;
    deleteStudent(id:string):Promise<boolean>;

}

class StudentService implements IStudentService{
    private studentRepo = dataSource.getRepository(Student);
    private matiereRepo = dataSource.getRepository(Matiere);
    private notesRepo = dataSource.getRepository(Notes);

    async getListStudents(key:string,level :LEVEL): Promise<Student[]> {
        if (level != LEVEL.Tous)
            return await this.studentRepo.find({
                where:[{personName: Like(`%${key}%`),studentLevel: level},{personMatricule:Like(`%${key}%`),studentLevel: level}],
                order:{personMatricule:'ASC'},         
            });
        else
            return await this.studentRepo.find({
                where:[{personName: Like(`%${key}%`)},{personMatricule:Like(`%${key}%`)}],
                order:{personMatricule:'ASC'},         
            });
    }

    async addStudent(student: Student):Promise<Student> {
        const  listMatiere : Matiere[] = await this.matiereRepo.find({}) || null;
        let newStudent!:Student;

        if(student.personId == null || student.personId == ""){
            student.personId = randomUUID();
            student.personMatricule = 'E00'+(await this.studentRepo.find()).length
        }
        newStudent = await this.studentRepo.save(student);
        
        if(listMatiere != null)
            listMatiere.forEach( mat => {
                notesService.createNotes(newStudent,mat);
            })

    
        return newStudent;
    }

    async getStudent(id: string): Promise<Student> {
           return await this.studentRepo.findOneBy({
            personId: id
        }) as any;
    }

    async updateStudent(student: Student): Promise<Student> {
        return await this.studentRepo.save(student);
    }

    async deleteStudent(id: string): Promise<boolean> {
        let listNote = await this.notesRepo.find({where:[{noteStudent:{personId:id}},{noteMatiere:{matiereId:id}}]})
        try{
           if(listNote != null) (listNote).forEach(note => {
                notesService.suppNotes(note.noteId);
            })
            await this.studentRepo.delete(id);
            return  true;
        } catch{
            return false;
        }  
    }
     async delay(ms: number) {
        return new Promise( resolve => setTimeout(resolve, ms) );
    }
    
}
export default new StudentService();