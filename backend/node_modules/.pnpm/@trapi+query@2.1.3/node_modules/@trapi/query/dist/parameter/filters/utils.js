"use strict";
/*
 * Copyright (c) 2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.isFilterOperatorConfig = exports.determineFilterOperatorLabelsByValue = void 0;
const utils_1 = require("../../utils");
const constants_1 = require("./constants");
const config = [];
const operatorKeys = Object.keys(constants_1.FilterOperator);
for (let i = 0; i < operatorKeys.length; i++) {
    config.push({
        sign: constants_1.FilterOperator[operatorKeys[i]],
        label: constants_1.FilterOperatorLabel[operatorKeys[i]],
    });
}
function determineFilterOperatorLabelsByValue(input) {
    let value = input;
    const operators = [];
    for (let i = 0; i < config.length; i++) {
        if (typeof value !== 'string') {
            continue;
        }
        switch (config[i].sign) {
            case constants_1.FilterOperator.IN:
                if (value.includes(config[i].sign)) {
                    operators.push(config[i].label);
                    value = value.split(config[i].sign);
                }
                break;
            default:
                if (value.slice(0, config[i].sign.length) === config[i].sign) {
                    operators.push(config[i].label);
                    value = value.slice(config[i].sign.length);
                    if (value.toLowerCase() === 'null') {
                        value = null;
                    }
                }
                break;
        }
    }
    return {
        operators,
        value,
    };
}
exports.determineFilterOperatorLabelsByValue = determineFilterOperatorLabelsByValue;
function isFilterOperatorConfig(data) {
    if (typeof data !== 'object') {
        return false;
    }
    if ((0, utils_1.hasOwnProperty)(data, 'operator')) {
        const operators = Object.values(constants_1.FilterOperator);
        if (typeof data.operator === 'string') {
            if (operators.indexOf(data.operator) === -1) {
                return false;
            }
        }
        else if (Array.isArray(data.operator)) {
            for (let i = 0; i < data.operator.length; i++) {
                if (typeof data.operator[i] !== 'string') {
                    return false;
                }
                if (operators.indexOf(data.operator[i]) === -1) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }
    else {
        return false;
    }
    if ((0, utils_1.hasOwnProperty)(data, 'value')) {
        if (!(0, utils_1.isSimpleValue)(data.value, {
            withNull: true,
            withUndefined: true,
        })) {
            if (Array.isArray(data.value)) {
                for (let i = 0; i < data.value.length; i++) {
                    if (!(0, utils_1.isSimpleValue)(data.value[i])) {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }
    }
    else {
        return false;
    }
    return true;
}
exports.isFilterOperatorConfig = isFilterOperatorConfig;
//# sourceMappingURL=utils.js.map