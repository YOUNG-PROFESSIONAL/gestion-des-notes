import { randomUUID } from 'crypto';
import { Student } from "./student.model";
import { dataSource } from '../../config/dbconnect';

interface IStudentService{
    getListStudents(): Promise<Student[]> ;
    getStudent(id:string):Promise<Student>;
    addStudent(student:Student):Promise<Student>;
    updateStudent(student:Student):Promise<Student>;
    deleteStudent(id:string):Promise<boolean>;

}

class StudentService implements IStudentService{
    private studentRepo = dataSource.getRepository(Student);

    async getListStudents(): Promise<Student[]> {
        return await (await this.studentRepo.find({order:{studentLevel:'ASC'}}));
    }
    async addStudent(student: Student):Promise<Student> {
        if(student.personId == null){
            student.personId = randomUUID();
            student.personMatricule = 'E00'+(await this.studentRepo.find()).length
        }
        return await this.studentRepo.save(student);
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
        try{
            await this.studentRepo.delete({personId:id});
            return true;
        } catch{
            return false;
        }
         
    }
    
}
export default new StudentService();