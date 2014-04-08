(ns typescript-parser.core
  (:require [instaparse.core :as insta]))

(def grammar1
  (insta/parser (slurp "typescript.ebnf")))
(grammar1 "/////////////////////////////////////
          ////////////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ")
(comment

(grammar1 "declare var NaN: number;")
(grammar1 "declare var Infinity: number;")
(grammar1 "declare function eval(x: string): any;")
(grammar1 "declare function parseInt(s: string, radix?: number): number;")

(grammar1 "declare function parseFloat(string: string): number;")

(grammar1 "declare function eval(x: string): any;
declare function parseInt(s: string, radix?: number): number;
declare function parseFloat(string: string): number;
declare function isNaN(number: number): boolean;
declare function isFinite(number: number): boolean;
declare function decodeURI(encodedURI: string): string;
declare function decodeURIComponent(encodedURIComponent: string): string;
declare function encodeURI(uri: string): string;
declare function encodeURIComponent(uriComponent: string): string;")
(grammar1
"
interface ArrayBuffer {
    byteLength: number;
}
"
)

(grammar1
  "// single lline
  ")

(grammar1
  "// single lline
  // multi line
  ")

(grammar1
  "// single lline
  // multi line
declare var NaN: number;
  ")

(grammar1
"/* *****************************************************************************
Copyright (c) Microsoft Corporation. All rights reserved. 
Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use
this file except in compliance with the License. You may obtain a copy of the
License at http://www.apache.org/licenses/LICENSE-2.0  
 
THIS CODE IS PROVIDED ON AN *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION ANY IMPLIED
WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A PARTICULAR PURPOSE, 
MERCHANTABLITY OR NON-INFRINGEMENT. 
 
See the Apache Version 2.0 License for specific language governing permissions
and limitations under the License.
***************************************************************************** */

/////////////////////////////
/// ECMAScript APIs
/////////////////////////////

declare var NaN: number;
                                   ")
)

(def comment-grammar
  (insta/parser
    "
S ::= Whitespace*
Whitespace ::= (#'\\s+' | SingleLineComment )
SingleLineComment ::= '//' #'[^\n]*'
    "))
(comment-grammar
  "/* asd asdf asdf 
  
  */
  //
  //
  ")

(comment-grammar
"/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////

                                   "
  )
(comment-grammar "/////////////////////////////////////
          ////////////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ///////////////////////////////////
          ")

#_(grammar1
"interface PropertyDescriptor {
    configurable?: boolean;
    enumerable?: boolean;
    value?: any;
    writable?: boolean;
    get? (): any;
    set? (v: any): void;
}")
;
;
;(grammar1
;"interface PropertyDescriptorMap {
;    [s: string]: PropertyDescriptor;
;}")
;
;(grammar1
;"interface Object {
;    toString(): string;
;    toLocaleString(): string;
;    valueOf(): Object;
;    hasOwnProperty(v: string): boolean;
;    isPrototypeOf(v: Object): boolean;
;    propertyIsEnumerable(v: string): boolean;
;
;    [s: string]: any;
;}")
;
;(grammar1
;"declare var Object: {
;    new (value?: any): Object;
;    (): any;
;    (value: any): any;
;
;    prototype: Object;
;
;    getPrototypeOf(o: any): any;
;    getOwnPropertyDescriptor(o: any, p: string): PropertyDescriptor;
;    getOwnPropertyNames(o: any): string[];
;    create(o: any, properties?: PropertyDescriptorMap): any;
;    defineProperty(o: any, p: string, attributes: PropertyDescriptor): any;
;    defineProperties(o: any, properties: PropertyDescriptorMap): any;
;    seal(o: any): any;
;    freeze(o: any): any;
;    preventExtensions(o: any): any;
;    isSealed(o: any): boolean;
;    isFrozen(o: any): boolean;
;    isExtensible(o: any): boolean;
;    keys(o: any): string[];
;}")
;
;(grammar1 "// asdfasdf
;          ")
;
;(grammar1
;"interface Function {
;    apply(thisArg: any, argArray?: any): any;
;    call(thisArg: any, ...argArray: any[]): any;
;    bind(thisArg: any, ...argArray: any[]): any;
;
;    prototype: any;
;    length: number;
;
;    // Non-standard extensions
;    arguments: any;
;    caller: Function;
;}")
;
;(grammar1
;  "declare var Array: {
;    new <T>(arrayLength: number): T[];
;    new <T>(...items: T[]): T[];
;    <T>(arrayLength: number): T[];
;    <T>(...items: T[]): T[];
;    isArray(arg: any): boolean;
;    prototype: Array<any>;
;}")
;
;(grammar1 "
;
;/////////////////////////////
;/// IE10 ECMAScript Extensions
;/////////////////////////////
;
;interface ArrayBuffer {
;    byteLength: number;
;}
;declare var ArrayBuffer: {
;    prototype: ArrayBuffer;
;    new (byteLength: number): ArrayBuffer;
;}")
;
;(grammar1
;"
;interface Math {
;    E: number;
;    LN10: number;
;    LN2: number;
;    LOG2E: number;
;    LOG10E: number;
;    PI: number;
;    SQRT1_2: number;
;    SQRT2: number;
;    abs(x: number): number;
;    acos(x: number): number;
;    asin(x: number): number;
;    atan(x: number): number;
;    atan2(y: number, x: number): number;
;    ceil(x: number): number;
;    cos(x: number): number;
;    exp(x: number): number;
;    floor(x: number): number;
;    log(x: number): number;
;    max(...values: number[]): number;
;    min(...values: number[]): number;
;    pow(x: number, y: number): number;
;    random(): number;
;    round(x: number): number;
;    sin(x: number): number;
;    sqrt(x: number): number;
;    tan(x: number): number;
;}")
;
;(grammar1
;  (str
;"interface RegExp {
;}
;"))

#_(grammar1
"
declare module Intl {
    interface CollatorOptions {
        usage?: string;
        localeMatcher?: string;
        numeric?: boolean;
        caseFirst?: string;
        sensitivity?: string;
        ignorePunctuation?: boolean;
    }
}
")
insta/parses

#_(map prn (grammar1 (slurp "lib.copy")))
#_(last (grammar1 (slurp "lib.d.ts")))
