import { randomUUID } from 'crypto';
import { dataSource } from '../config/dbconnect';
import { Notes } from '../notes/notes.model';
import notesService from '../notes/notes.service';
import { Student } from '../person/student/student.model';
import { Matiere } from './matiere.model';
interface IMatiereService{
    getListMatieres(): Promise<Matiere[]> ;
    getMatiere(id:string):Promise<Matiere>;
    addMatiere(matiere:Matiere):Promise<Matiere>;
    updateMatiere(matiere:Matiere):Promise<Matiere>;
    suppMatiere(id:string):Promise<boolean>;

}

class MatiereService implements IMatiereService{
    private matiereRepo = dataSource.getRepository(Matiere);
    private studentRepo = dataSource.getRepository(Student);
    private notesRepo = dataSource.getRepository(Notes);

    async getListMatieres(): Promise<Matiere[]> {
        return await this.matiereRepo.find();
    }
    async addMatiere(matiere: Matiere):Promise<Matiere> {
    
        const  listStudent: Student[] = await this.studentRepo.find({}) || null;
       
        if( matiere.matiereId == '' || matiere.matiereId == undefined || matiere.matiereId == null ){
            matiere.matiereId = randomUUID();
        }
        const newMatiere = await this.matiereRepo.save(matiere);

        if(listStudent != null)
            listStudent.forEach(student => {
                notesService.createNotes(student,matiere);
            })

        return newMatiere;
    }
    async getMatiere(id: string): Promise<Matiere> {
           return await this.matiereRepo.findOneBy({
            matiereId: id
        }) as any;
    }
    async updateMatiere(matiere: Matiere): Promise<Matiere> {
        return await this.matiereRepo.save(matiere);
    }
    async suppMatiere(id: string): Promise<boolean> {
    
        let listNote = await this.notesRepo.find({where:[{noteStudent:{personId:id}},{noteMatiere:{matiereId:id}}]});
        try{
            (listNote).forEach(note => {
                notesService.suppNotes(note.noteId);
            })
            await this.matiereRepo.delete({matiereId:id});
            return true;
        } catch (e){
            return false;
        }    
    }
}
export default new MatiereService();