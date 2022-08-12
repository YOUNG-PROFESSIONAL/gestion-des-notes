"use strict";
/*
 * Copyright (c) 2022-2022.
 * Author Peter Placzek (tada5hi)
 * For the full copyright and license information,
 * view the LICENSE file that was distributed with this source code.
 */
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.loadJsonFileSync = exports.loadJsonFile = void 0;
const fs_1 = __importDefault(require("fs"));
function loadJsonFile(filePath) {
    return __awaiter(this, void 0, void 0, function* () {
        try {
            const file = yield fs_1.default.promises.readFile(filePath);
            return JSON.parse(file.toString('utf-8'));
        }
        catch (e) {
            /* istanbul ignore next */
            return undefined;
        }
    });
}
exports.loadJsonFile = loadJsonFile;
function loadJsonFileSync(filePath) {
    try {
        const file = fs_1.default.readFileSync(filePath);
        return JSON.parse(file.toString('utf-8'));
    }
    catch (e) {
        /* istanbul ignore next */
        return undefined;
    }
}
exports.loadJsonFileSync = loadJsonFileSync;
//# sourceMappingURL=json.js.map