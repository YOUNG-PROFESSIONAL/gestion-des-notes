"use strict";
var __rest = (this && this.__rest) || function (s, e) {
    var t = {};
    for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
        t[p] = s[p];
    if (s != null && typeof Object.getOwnPropertySymbols === "function")
        for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
            if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i]))
                t[p[i]] = s[p[i]];
        }
    return t;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.applyFilters = exports.applyQueryFilters = exports.applyQueryFiltersParseOutput = exports.applyFiltersTransformed = exports.transformParsedFilters = void 0;
const query_1 = require("@trapi/query");
const typeorm_1 = require("typeorm");
// --------------------------------------------------
function transformParsedFilters(data, options) {
    var _a;
    options !== null && options !== void 0 ? options : (options = {});
    const items = [];
    for (let i = 0; i < data.length; i++) {
        const fullKey = (data[i].alias ? `${data[i].alias}.` : '') + data[i].key;
        const filter = data[i];
        (_a = filter.operator) !== null && _a !== void 0 ? _a : (filter.operator = {});
        const statement = [
            fullKey,
        ];
        if (typeof filter.value === 'undefined' ||
            filter.value === null ||
            `${filter.value}`.toLowerCase() === 'null') {
            statement.push('IS');
            if (filter.operator.negation) {
                statement.push('NOT');
            }
            statement.push('NULL');
            items.push({
                statement: statement.join(' '),
                binding: {},
            });
        }
        else if (typeof filter.value === 'string' ||
            typeof filter.value === 'number' ||
            typeof filter.value === 'boolean' ||
            Array.isArray(filter.value)) {
            if ((typeof filter.value === 'string' ||
                typeof filter.value === 'number') &&
                filter.operator.like) {
                filter.value += '%';
            }
            if (filter.operator.in || filter.operator.like) {
                if (filter.operator.negation) {
                    statement.push('NOT');
                }
                if (filter.operator.like) {
                    statement.push('LIKE');
                }
                else if (filter.operator.in) {
                    statement.push('IN');
                }
            }
            else if (filter.operator.negation) {
                statement.push('!=');
            }
            else if (filter.operator.lessThan) {
                statement.push('<');
            }
            else if (filter.operator.lessThanEqual) {
                statement.push('<=');
            }
            else if (filter.operator.moreThan) {
                statement.push('>');
            }
            else if (filter.operator.moreThanEqual) {
                statement.push('>=');
            }
            else {
                statement.push('=');
            }
            let bindingKey = typeof options.bindingKeyFn === 'function' ?
                options.bindingKeyFn(fullKey) :
                undefined;
            if (typeof bindingKey === 'undefined') {
                bindingKey = `filter_${fullKey.replace('.', '_')}`;
            }
            if (filter.operator.in) {
                statement.push(`(:...${bindingKey})`);
            }
            else {
                statement.push(`:${bindingKey}`);
            }
            items.push({
                statement: statement.join(' '),
                binding: { [bindingKey]: filter.value },
            });
        }
    }
    return items;
}
exports.transformParsedFilters = transformParsedFilters;
/**
 * Apply transformed filter[s] parameter data on the db query.
 *
 * @param query
 * @param data
 */
function applyFiltersTransformed(query, data) {
    if (data.length === 0) {
        return data;
    }
    /* istanbul ignore next */
    query.andWhere(new typeorm_1.Brackets((qb) => {
        for (let i = 0; i < data.length; i++) {
            if (i === 0) {
                qb.where(data[i].statement, data[i].binding);
            }
            else {
                qb.andWhere(data[i].statement, data[i].binding);
            }
        }
    }));
    return data;
}
exports.applyFiltersTransformed = applyFiltersTransformed;
/**
 * Apply parsed filter[s] parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
function applyQueryFiltersParseOutput(query, data, options) {
    applyFiltersTransformed(query, transformParsedFilters(data, options));
    return data;
}
exports.applyQueryFiltersParseOutput = applyQueryFiltersParseOutput;
// --------------------------------------------------
/**
 * Apply raw filter[s] parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
function applyQueryFilters(query, data, options) {
    options !== null && options !== void 0 ? options : (options = {});
    const { transform: transformOptions } = options, parseOptions = __rest(options, ["transform"]);
    return applyQueryFiltersParseOutput(query, (0, query_1.parseQueryFilters)(data, parseOptions), transformOptions);
}
exports.applyQueryFilters = applyQueryFilters;
/**
 * Apply raw filter[s] parameter data on the db query.
 *
 * @param query
 * @param data
 * @param options
 */
function applyFilters(query, data, options) {
    return applyQueryFilters(query, data, options);
}
exports.applyFilters = applyFilters;
//# sourceMappingURL=module.js.map