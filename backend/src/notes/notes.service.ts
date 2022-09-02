import { LEVEL } from './../person/student/level.enum';
import { randomUUID } from 'crypto';
import { Student } from './../person/student/student.model';
import { dataSource } from '../config/dbconnect';
import { Notes } from './notes.model';
import { Matiere } from '../matiere/matiere.model';


export interface INotesService{
    getListNotes(niveau: LEVEL,matiereId:string): Promise<Notes[]> ;
    getNotes(id:string):Promise<Notes>;
    createNotes(student : Student, matiere:Matiere):Promise<Notes>;
    updateNotes(note:Notes):Promise<Notes>;
    suppNotes(id:string):Promise<boolean>;

}

class NotesService implements INotesService{
    private notesRepo = dataSource.getRepository(Notes);
    private studentRepo = dataSource.getRepository(Student);
    private matiereRepo = dataSource.getRepository(Matiere);

    async getListNotes(niveau: LEVEL,matiereId:string): Promise<Notes[]> {
       //console.log( await this.notesRepo.createQueryBuilder('notes').where('note>5').getMany())
        return await this.notesRepo.find({
            where:{
                noteStudent: {studentLevel: niveau},
                noteMatiere: {matiereId: matiereId}
            },
            relations:{
                noteMatiere:true,
                noteStudent:true
            }
        }
            );
    }

    // Use when we create student or matiere
    async createNotes(student : Student, matiere:Matiere):Promise<Notes> {
       let note: Notes = new Notes;
        note.note = 0;
        
        note.noteId = randomUUID();
        note.noteMatiere = matiere;
        note.noteStudent = student;
            
        return await this.notesRepo.save(note);
    }

    async getNotes(id:string): Promise<Notes> {
        return null as any;
    }
    async updateNotes(note:Notes): Promise<Notes> {
     
        return await this.notesRepo.save(note);;
    }
    async suppNotes(id:string): Promise<boolean> {
        try{
            await this.notesRepo.delete({noteId:id});
            return true;
        } catch(e){
            return false;
        }    
    }
}
export default new NotesService();