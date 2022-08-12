"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.applyQueryParseOutput = void 0;
const query_1 = require("@trapi/query");
const parameter_1 = require("./parameter");
function applyQueryParseOutput(query, context) {
    const keys = Object.keys(context);
    for (let i = 0; i < keys.length; i++) {
        const key = keys[i];
        switch (key) {
            case query_1.Parameter.FIELDS:
                (0, parameter_1.applyQueryFieldsParseOutput)(query, context[key]);
                break;
            case query_1.Parameter.FILTERS:
                (0, parameter_1.applyQueryFiltersParseOutput)(query, context[key]);
                break;
            case query_1.Parameter.PAGINATION:
                (0, parameter_1.applyQueryPaginationParseOutput)(query, context[key]);
                break;
            case query_1.Parameter.RELATIONS:
                (0, parameter_1.applyQueryRelationsParseOutput)(query, context[key]);
                break;
            case query_1.Parameter.SORT:
                (0, parameter_1.applyQueryRelationsParseOutput)(query, context[key]);
                break;
        }
    }
    return context;
}
exports.applyQueryParseOutput = applyQueryParseOutput;
//# sourceMappingURL=utils.js.map