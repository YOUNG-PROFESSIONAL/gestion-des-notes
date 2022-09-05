import { Notes } from './../notes/notes.model';
import { dataSource } from './../config/dbconnect';
import { Bulletin } from './bulletin.model';
import { Student } from './../person/student/student.model';
import { LEVEL } from './../person/student/level.enum';
import { Like } from 'typeorm';
import { Observation } from './observation.enum';
interface IBulletinService{
    getbulletin(id:string):Promise<Bulletin[]>;
}

class BulletinService implements IBulletinService{
    private repoStudent = dataSource.getRepository(Student);
    private repoNotes = dataSource.getRepository(Notes);

    async getbulletin(id:string): Promise<Bulletin[]> {
        let bulletin : Bulletin = {
            bNotes:[] as Notes[] 
         } as Bulletin;

        bulletin.bNotes = await this.repoNotes.find({
            where:{
                noteStudent:{personId:id} 
            },
            relations:{
                noteMatiere:true
            }
        })
         bulletin.bStudent =  await this.repoStudent.findOneBy({personId:id}) || null as any;
         
        return [bulletin];
    }
}
export default new BulletinService();