"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseQueryFields = exports.buildDomainFields = void 0;
const utils_1 = require("../../utils");
const type_1 = require("./type");
// --------------------------------------------------
// --------------------------------------------------
function buildDomainFields(data, options) {
    options = options !== null && options !== void 0 ? options : { defaultAlias: type_1.DEFAULT_ALIAS_ID };
    let domainFields = {};
    if (Array.isArray(data)) {
        domainFields[options.defaultAlias] = data;
    }
    else {
        domainFields = data;
    }
    return domainFields;
}
exports.buildDomainFields = buildDomainFields;
function parseQueryFields(data, options) {
    var _a, _b, _c;
    options !== null && options !== void 0 ? options : (options = {});
    // If it is an empty array nothing is allowed
    if (typeof options.allowed !== 'undefined' &&
        Object.keys(options.allowed).length === 0) {
        return [];
    }
    (_a = options.aliasMapping) !== null && _a !== void 0 ? _a : (options.aliasMapping = {});
    (_b = options.relations) !== null && _b !== void 0 ? _b : (options.relations = []);
    (_c = options.defaultAlias) !== null && _c !== void 0 ? _c : (options.defaultAlias = type_1.DEFAULT_ALIAS_ID);
    let allowedDomainFields;
    if (options.allowed) {
        allowedDomainFields = buildDomainFields(options.allowed, options);
    }
    const prototype = Object.prototype.toString.call(data);
    if (prototype !== '[object Object]' &&
        prototype !== '[object Array]' &&
        prototype !== '[object String]') {
        return [];
    }
    if (prototype === '[object String]') {
        data = { [options.defaultAlias]: data };
    }
    if (prototype === '[object Array]') {
        data = { [options.defaultAlias]: data };
    }
    let transformed = [];
    const keys = Object.keys(data);
    for (let i = 0; i < keys.length; i++) {
        if (!(0, utils_1.hasOwnProperty)(data, keys[i]) ||
            typeof keys[i] !== 'string') {
            continue;
        }
        const fieldsArr = buildArrayFieldsRepresentation(data[keys[i]]);
        if (fieldsArr.length === 0) {
            continue;
        }
        let fields = [];
        for (let j = 0; j < fieldsArr.length; j++) {
            let operator;
            switch (true) {
                case fieldsArr[j].substring(0, 1) === type_1.FieldOperator.INCLUDE:
                    operator = type_1.FieldOperator.INCLUDE;
                    break;
                case fieldsArr[j].substring(0, 1) === type_1.FieldOperator.EXCLUDE:
                    operator = type_1.FieldOperator.EXCLUDE;
                    break;
            }
            if (operator)
                fieldsArr[j] = fieldsArr[j].substring(1);
            fields.push(Object.assign({ key: fieldsArr[j] }, (operator ? { value: operator } : {})));
        }
        const allowedDomains = typeof allowedDomainFields !== 'undefined' ?
            Object.keys(allowedDomainFields) :
            [];
        const targetKey = allowedDomains.length === 1 ?
            allowedDomains[0] :
            keys[i];
        // is not default domain && includes are defined?
        if (keys[i] !== type_1.DEFAULT_ALIAS_ID &&
            keys[i] !== options.defaultAlias &&
            typeof options.relations !== 'undefined') {
            let index = -1;
            for (let j = 0; j < options.relations.length; j++) {
                if (options.relations[j].value === keys[i]) {
                    index = j;
                    break;
                }
            }
            if (index === -1) {
                continue;
            }
        }
        for (let j = 0; j < fields.length; j++) {
            const fullKey = `${keys[i]}.${fields[j].key}`;
            fields[j] = Object.assign(Object.assign(Object.assign({}, (targetKey && targetKey !== type_1.DEFAULT_ALIAS_ID ? { alias: targetKey } : {})), fields[j]), { key: (0, utils_1.hasOwnProperty)(options.aliasMapping, fullKey) ?
                    options.aliasMapping[fullKey].split('.').pop() :
                    fields[j].key });
        }
        fields = fields
            .filter((field) => {
            if (typeof allowedDomainFields === 'undefined') {
                return true;
            }
            return (0, utils_1.hasOwnProperty)(allowedDomainFields, targetKey) &&
                allowedDomainFields[targetKey].indexOf(field.key) !== -1;
        });
        if (fields.length > 0) {
            transformed = [...transformed, ...fields];
        }
    }
    return transformed;
}
exports.parseQueryFields = parseQueryFields;
function buildArrayFieldsRepresentation(data) {
    const valuePrototype = Object.prototype.toString.call(data);
    if (valuePrototype !== '[object Array]' &&
        valuePrototype !== '[object String]') {
        return [];
    }
    let fieldsArr = [];
    /* istanbul ignore next */
    if (valuePrototype === '[object String]') {
        fieldsArr = data.split(',');
    }
    /* istanbul ignore next */
    if (valuePrototype === '[object Array]') {
        fieldsArr = data
            .filter((val) => typeof val === 'string');
    }
    return fieldsArr;
}
//# sourceMappingURL=parse.js.map