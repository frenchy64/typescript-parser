(ns typescript-parser.core
  (:require [instaparse.core :as insta]))

(def grammar
  (insta/parser
    "

<declarations-file>      = ws | declaration-element
<ws>                     = <#'\\s+'>
declaration-element      = export-assignment
                         | exportedness interface-declaration (* TODO *)
export-assignment        = <'export'> ws* <'='> ws* identifier
identifier               = #'[a-zA-Z_$][a-zA-Z0-9_$]*'
<exportedness>           = <'export'> ws*
interface-declaration    = <'interface'> ws+ identifier ws+ type-params? interface-extends-clause? object-type
type-params              = <'<'> type-param (<','> type-param)*  <'>'>
type-param               = identifier | identifier 
extends-clause           = <'extends'> identifier
type-reference           = entity-name type-args
type-args                = <'<'> type-spec (<','> type-spec)*  <'>'>
interface-extends-clause = <'extends'> type-reference (<','> type-reference)*
entity-name              = identifier ('.' identifier)*
object-type              = <'{'> ws* (type-member <';'>?)* ws* <'}'>
type-member              = construct-signature 
                         | method-signature (* TODO *)
construct-signature      = <'new'> ws+ type-params type-annotation?
method-signature         = property-name ws optionality? ws call-signature
optionality              = <'?'>
call-signature           = type-params param-list type-annotation?
param-list               = <'('> 
                           ( required-parameter-list
                           | optional-parameter-list
                           | rest-parameter
                           | required-parameter-list ws* <','> ws* optional-parameter-list
                           | required-parameter-list ws* <','> ws* rest-parameter
                           | optional-parameter-list ws* <','> ws* rest-parameter
                           | required-parameter-list ws* <','> ws* optional-parameter-list ws* <','> ws* rest-parameter
                           )
                           <')'>
required-parameter-list  = required-parameter
                         | required-parameter-list ws* <','> ws* required-parameter
optional-parameter-list  = optional-parameter
                         | optional-parameter-list ws* <','> ws* optional-parameter
required-parameter       = public-or-private? ws identifier ws type-annotation?
                         | identifier ws* <':'> ws* string-literal
optional-parameter       = public-or-private? ws identifier <'?'> ws type-annotation?
                         | public-or-private? ws identifier (ws type-annotation)? ws initialiser
rest-parameter           = <'...'> ws identifier ws type-annotation
public-or-private        = 'public' | 'private'
type-annotation          = <':'> ws* type-spec
type-spec                = type-literal 
type-literal             = object-literal  
object-literal           = object-type
    "))


(grammar "       ")
(grammar "export = Client")

(grammar1 "export interface Foo<A>")
(grammar1 "export interface P { x: number; y: number; }")

(#'[a-zA-Z_$][a-zA-Z0-9_$]*'

(re-matches #"[a-zA-Z_$][a-zA-Z0-9_$]*"
            "P")

(def grammar1
  (insta/parser (slurp "typescript.ebnf")))

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
"interface PropertyDescriptor {
    configurable?: boolean;
    enumerable?: boolean;
    value?: any;
    writable?: boolean;
    get? (): any;
    set? (v: any): void;
}")


(grammar1
"interface PropertyDescriptorMap {
    [s: string]: PropertyDescriptor;
}")

(grammar1
"interface Object {
    toString(): string;
    toLocaleString(): string;
    valueOf(): Object;
    hasOwnProperty(v: string): boolean;
    isPrototypeOf(v: Object): boolean;
    propertyIsEnumerable(v: string): boolean;

    [s: string]: any;
}")

(grammar1
"declare var Object: {
    new (value?: any): Object;
    (): any;
    (value: any): any;

    prototype: Object;

    getPrototypeOf(o: any): any;
    getOwnPropertyDescriptor(o: any, p: string): PropertyDescriptor;
    getOwnPropertyNames(o: any): string[];
    create(o: any, properties?: PropertyDescriptorMap): any;
    defineProperty(o: any, p: string, attributes: PropertyDescriptor): any;
    defineProperties(o: any, properties: PropertyDescriptorMap): any;
    seal(o: any): any;
    freeze(o: any): any;
    preventExtensions(o: any): any;
    isSealed(o: any): boolean;
    isFrozen(o: any): boolean;
    isExtensible(o: any): boolean;
    keys(o: any): string[];
}")

(grammar1 "// asdfasdf
          ")

(grammar1
"interface Function {
    apply(thisArg: any, argArray?: any): any;
    call(thisArg: any, ...argArray: any[]): any;
    bind(thisArg: any, ...argArray: any[]): any;

    prototype: any;
    length: number;

    // Non-standard extensions
    arguments: any;
    caller: Function;
}")

(grammar1
  "declare var Array: {
    new <T>(arrayLength: number): T[];
    new <T>(...items: T[]): T[];
    <T>(arrayLength: number): T[];
    <T>(...items: T[]): T[];
    isArray(arg: any): boolean;
    prototype: Array<any>;
}")
