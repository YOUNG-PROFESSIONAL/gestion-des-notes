"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.parseQueryPagination = void 0;
// --------------------------------------------------
function finalizePagination(data, options) {
    if (typeof options.maxLimit !== 'undefined') {
        if (typeof data.limit === 'undefined' ||
            data.limit > options.maxLimit) {
            data.limit = options.maxLimit;
        }
    }
    if (typeof data.limit !== 'undefined' &&
        typeof data.offset === 'undefined') {
        data.offset = 0;
    }
    return data;
}
/**
 * Transform pagination data to an appreciate data format.
 *
 * @param data
 * @param options
 */
function parseQueryPagination(data, options) {
    options !== null && options !== void 0 ? options : (options = {});
    const pagination = {};
    const prototype = Object.prototype.toString.call(data);
    if (prototype !== '[object Object]') {
        return finalizePagination(pagination, options);
    }
    let { limit, offset } = data;
    if (typeof limit !== 'undefined') {
        limit = parseInt(limit, 10);
        if (!Number.isNaN(limit) && limit > 0) {
            pagination.limit = limit;
        }
    }
    if (typeof offset !== 'undefined') {
        offset = parseInt(offset, 10);
        if (!Number.isNaN(offset) && offset >= 0) {
            pagination.offset = offset;
        }
    }
    return finalizePagination(pagination, options);
}
exports.parseQueryPagination = parseQueryPagination;
//# sourceMappingURL=parse.js.map