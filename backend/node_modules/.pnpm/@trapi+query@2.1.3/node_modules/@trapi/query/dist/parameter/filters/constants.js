"use strict";
/*
 * Copyright (c) 2022-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.FilterOperator = exports.FilterOperatorLabel = void 0;
var FilterOperatorLabel;
(function (FilterOperatorLabel) {
    FilterOperatorLabel["NEGATION"] = "negation";
    FilterOperatorLabel["LIKE"] = "like";
    FilterOperatorLabel["LESS_THAN_EQUAL"] = "lessThanEqual";
    FilterOperatorLabel["LESS_THAN"] = "lessThan";
    FilterOperatorLabel["MORE_THAN_EQUAL"] = "moreThanEqual";
    FilterOperatorLabel["MORE_THAN"] = "moreThan";
    FilterOperatorLabel["IN"] = "in";
})(FilterOperatorLabel = exports.FilterOperatorLabel || (exports.FilterOperatorLabel = {}));
var FilterOperator;
(function (FilterOperator) {
    FilterOperator["NEGATION"] = "!";
    FilterOperator["LIKE"] = "~";
    FilterOperator["LESS_THAN_EQUAL"] = "<=";
    FilterOperator["LESS_THAN"] = "<";
    FilterOperator["MORE_THAN_EQUAL"] = ">=";
    FilterOperator["MORE_THAN"] = ">";
    FilterOperator["IN"] = ",";
})(FilterOperator = exports.FilterOperator || (exports.FilterOperator = {}));
//# sourceMappingURL=constants.js.map