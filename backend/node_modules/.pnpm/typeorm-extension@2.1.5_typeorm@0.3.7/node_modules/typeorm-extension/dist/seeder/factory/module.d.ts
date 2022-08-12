import { SaveOptions } from 'typeorm';
import { SeederFactoryContext } from './type';
export declare class SeederFactory<O extends Record<string, any>> {
    readonly context: SeederFactoryContext<O>;
    meta: unknown;
    constructor(context: SeederFactoryContext<O>);
    setMeta(value: unknown): this;
    make(params?: Partial<O>, save?: boolean): Promise<O>;
    save(params?: Partial<O>, options?: SaveOptions): Promise<O>;
    saveMany(amount: number, params?: Partial<O>, options?: SaveOptions): Promise<O[]>;
    private resolve;
}
