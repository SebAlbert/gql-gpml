(ns gpml.core
  (:require [instaparse.core :as insta]))

(def parse-gpml
  (insta/parser
   "STATEMENT = <ws*> MATCH [<ws+> WHERE] <ws*>
    MATCH = <'MATCH' ws+> (EDGE | PATH)
            { <ws* ',' ws*> (EDGE | PATH) }
    PATH = [VARIABLE <ws* '=' ws*>]
           NODE { <ws*> EDGE <ws*> NODE }
    NODE = <'('> [SPEC] <')'>
    <EDGE> = EDGE_LEFT | EDGE_UNDIR | EDGE_RIGHT |
             EDGE_LEFT_OR_UNDIR | EDGE_UNDIR_OR_RIGHT |
             EDGE_LEFT_OR_RIGHT | EDGE_ANY_DIRECTION
    EDGE_LEFT = <'<-'> [ <'['> SPEC <']-'> ]
    EDGE_UNDIR = <'~'> [ <'['> SPEC <']~'> ]
    EDGE_RIGHT = [ <'-['> SPEC <']'> ] <'->'>
    EDGE_LEFT_OR_UNDIR = <'<~'> [ <'['> SPEC <']~'> ]
    EDGE_UNDIR_OR_RIGHT = [ <'~['> SPEC <']'> ] <'~>'>
    EDGE_LEFT_OR_RIGHT = <'<-'> [ <'['> SPEC <']-'> ] <'>'>
    EDGE_ANY_DIRECTION = <'-'> [ <'['> SPEC <']-'> ]
    <SPEC> = <ws*> [VARIABLE] <ws*> [LABEL]
                  <ws*> [WHERE] <ws*>
    VARIABLE = IDENTIFIER
    LABEL = <':' ws*> LABEL_EXPR
    <LABEL_EXPR> = <ws*> (LABEL_LITERAL | LABEL_ANY | LABEL_CONJUNCTION
                   | LABEL_DISJUNCTION | LABEL_NEGATION |
                   <'('> LABEL_EXPR <')'>) <ws*>
    LABEL_LITERAL = IDENTIFIER
    LABEL_ANY = '%'
    LABEL_CONJUNCTION = LABEL_EXPR (<'&'> LABEL_EXPR)+
    LABEL_DISJUNCTION = LABEL_EXPR (<'|'> LABEL_EXPR)+
    LABEL_NEGATION = <'!'> LABEL_EXPR
    WHERE = <'WHERE' ws+> #'.'* (* TODO *)
    <IDENTIFIER> = #'[a-zA-Z][a-zA-Z0-9]*'
    ws = #'\\s'"))
