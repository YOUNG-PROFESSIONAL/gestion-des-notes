import { randomUUID } from 'crypto';
import { Student } from './../person/student/student.model';
import { LEVEL } from './../person/student/level.enum';
import { PARCOURS } from './../person/student/parcours.enum';
import { dataSource } from '../config/dbconnect';
import { Notes } from './notes.model';
import { Matiere } from '../matiere/matiere.model';
import { Like } from 'typeorm';

interface INotesService{
    getListNotes(data : any): Promise<Notes[]> ;
    getNotes(id:string):Promise<Notes>;
    addNotes(notes:Notes):Promise<Notes[]>;
    updateNotes(notes:Notes[]):Promise<Notes[]>;
    suppNotes(id:string):Promise<boolean>;

}

class NotesService implements INotesService{
    private notesRepo = dataSource.getRepository(Notes);
    private matiereRepo = dataSource.getRepository(Matiere);
    private studentRepo = dataSource.getRepository(Student);

    async getListNotes(data : any): Promise<Notes[]> {
       //console.log( await this.notesRepo.createQueryBuilder('notes').where('note>5').getMany())
        return await this.notesRepo.find({
            where:{
                noteMatiere:data.matiere,
                noteStudent: {studentLevel: Like(data.level)}
            },
            relations:{
                noteMatiere:true,
                noteStudent:true
            }
        }
            );
    }
    async addNotes(data : any):Promise<Notes[]> {
        const note:Notes[] = [];
        data.forEach(async (el:any) => {
            const matiere: Matiere = await this.matiereRepo.findOneBy({matiereId:el.matiere.matiereId}) as any;
            console.log(matiere)
            const student: Student = await this.studentRepo.findOneBy({personId:el.student.personId}) as any
            const notes:Notes = new Notes();
            notes.noteMatiere = matiere;
            notes.noteStudent = student;
            notes.note = el.note;
            if(notes.noteId == null) notes.noteId = randomUUID();
            note.push(await this.notesRepo.save(notes));
            
        });
        return note as any;
    }
    async getNotes(id: string): Promise<Notes> {
        return null as any;
    }
    async updateNotes(data: Notes[]): Promise<Notes[]> {
        const note:Notes[] = [];
        data.forEach(async (note)=>{
            await this.notesRepo.save(note);
        })
        return note;
    }
    async suppNotes(id: string): Promise<boolean> {
        try{
            await this.notesRepo.delete({noteId:id});
            return true;
        } catch{
            return false;
        }    
    }
}
export default new NotesService();