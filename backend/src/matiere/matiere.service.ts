import { randomUUID } from 'crypto';
import { dataSource } from '../config/dbconnect';
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

    async getListMatieres(): Promise<Matiere[]> {
        return await this.matiereRepo.find();
    }
    async addMatiere(matiere: Matiere):Promise<Matiere> {
        if(matiere.matiereId == null){
            matiere.matiereId = randomUUID();
            matiere.matiereCode = 'M00'+(await this.matiereRepo.find()).length
        }
        console.log(matiere);
        return await this.matiereRepo.save(matiere);
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
        try{
            await this.matiereRepo.delete({matiereId:id});
            return true;
        } catch{
            return false;
        }    
    }
}
export default new MatiereService();