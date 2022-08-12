"use strict";
/*
 * Copyright (c) 2021-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.buildFieldWithAlias = exports.isFieldAllowedByRelations = void 0;
const field_1 = require("./field");
function isFieldAllowedByRelations(field, includes, options) {
    if (typeof includes === 'undefined') {
        return true;
    }
    options = options !== null && options !== void 0 ? options : {};
    const details = typeof field === 'string' ? (0, field_1.getFieldDetails)(field) : field;
    // check if field is associated to the default domain.
    if (typeof details.path === 'undefined' &&
        typeof details.alias === 'undefined') {
        return true;
    }
    // check if field is associated to the default domain.
    if (details.path === options.defaultAlias ||
        details.alias === options.defaultAlias) {
        return true;
    }
    return includes.filter((include) => include.value === details.path || include.value === details.alias).length > 0;
}
exports.isFieldAllowedByRelations = isFieldAllowedByRelations;
function buildFieldWithAlias(field, defaultAlias) {
    const details = typeof field === 'string' ? (0, field_1.getFieldDetails)(field) : field;
    if (typeof details.path === 'undefined' &&
        typeof details.alias === 'undefined') {
        // try to use query alias
        return defaultAlias ? `${defaultAlias}.${details.name}` : details.name;
    }
    return `${details.alias}.${details.name}`;
}
exports.buildFieldWithAlias = buildFieldWithAlias;
//# sourceMappingURL=relation.js.map