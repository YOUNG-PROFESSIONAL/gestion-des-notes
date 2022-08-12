import { Parameter, URLParameter } from '../constants';
import { ParseParameterOptions, ParseParameterOutput } from './parameter';
export declare type ParseInput = {
    [K in `${Parameter}` | `${URLParameter}`]?: any;
};
export declare type ParseOptions = {
    [K in `${Parameter}`]?: ParseParameterOptions<K> | boolean;
};
export declare type ParseOutput = {
    [K in `${Parameter}`]?: ParseParameterOutput<K>;
};
//# sourceMappingURL=type.d.ts.map