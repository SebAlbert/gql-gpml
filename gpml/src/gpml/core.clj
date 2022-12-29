(ns gpml.core
  (:require [instaparse.core :as insta]))

(def parse-gpml
  (insta/parser
   "STATEMENT = MATCH [<WHITESPACE+> WHERE]
    MATCH = <'MATCH' WHITESPACE+> {NODE}
    NODE = <'('> <WHITESPACE*> [IDENTIFIER] <WHITESPACE*> [LABEL]
           <WHITESPACE*> [WHERE] <WHITESPACE*> <')'>
    IDENTIFIER = #'[a-zA-Z][a-zA-Z0-9]*'
    NODE_VAR = IDENTIFIER
    LABEL = <':' WHITESPACE*> LABEL_EXPR
    <LABEL_EXPR> = <WHITESPACE*> (IDENTIFIER | LABEL_ANY | LABEL_CONJUNCTION |
                   LABEL_DISJUNCTION | LABEL_NEGATION | <'('> LABEL_EXPR <')'>)
                   <WHITESPACE*>
    LABEL_ANY = '%'
    LABEL_CONJUNCTION = LABEL_EXPR (<'&'> LABEL_EXPR)+
    LABEL_DISJUNCTION = LABEL_EXPR (<'|'> LABEL_EXPR)+
    LABEL_NEGATION = <'!'> LABEL_EXPR
    WHERE = <'WHERE' WHITESPACE+> #'.'* (* TODO *)
    WHITESPACE = #'\\s'"))
