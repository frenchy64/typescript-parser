(ns typescript-parser.core
  (:require [instaparse.core :as insta]))

(def grammar
  (insta/parser (slurp "typescript.ebnf")
                :output-format :enlive
                :optimize :memory))

(defn parse-tree [s]
  (grammar s))

(defn output-ts []
  (grammar (slurp "lib.d.ts")))

(defn transform [tree]
  (insta/transform
    {:Identifier
     (fn id [id] id)
     :QualifiedIdentifier
     (fn qid
       ([id] id)
       ([module id] (str module "." id)))
     :ModuleName
     (fn mn [nme & nms]
       (apply str (interpose "." (concat nme nms))))
     :TypeReference
     (fn tr
       ([id]
        {:op :type-reference
         :id id})
       ([id targs]
        {:op :type-reference
         :id id
         :targs targs}))
     :AmbientVariableDeclaration
     (fn avd
       ([id]
        {:op :ambient-var-declaration
         :id id})
       ([id type]
        {:op :ambient-var-declaration
         :id id
         :type type}))
     :AmbientFunctionDeclaration
     (fn afd
       [id sig]
       {:op :ambient-fn-declaration
        :id id
        :sig sig})
;     :CallSignature
;     (fn [& args]
;       (merge {:op :call-signature}
}

    tree))

#_(transform (first (output-ts)))

(-> 
"declare function eval(x: string): any;"
    parse-tree
    transform)
