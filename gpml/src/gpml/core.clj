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
    WHERE = <'WHERE' ws+> EXPR
    <EXPR> = BOOL1 | OR
    OR = BOOL1 (<ws+ 'OR' ws+> BOOL1)+
    <BOOL1> = BOOL2 | AND
    AND = BOOL2 (<ws+ 'AND' ws+> BOOL2)+
    <BOOL2> = BOOL3 | NOT
    NOT = 'NOT' ws+ BOOL3
    <BOOL3> = COMP1 | EDGE_TEST
    <EDGE_TEST> = is_directed | source_of | destination_of
    is_directed = IDENTIFIER <ws+ 'IS DIRECTED'>
    source_of = IDENTIFIER <ws+ 'IS SOURCE OF' ws+> IDENTIFIER
    destination_of = IDENTIFIER <ws+ 'IS DESTINATION OF' ws+> IDENTIFIER
    <COMP1> = COMP2 | eq | ne
    eq = COMP2 <ws* '=' ws*> COMP2
    ne = COMP2 <ws* ('!=' | '<>') ws*> COMP2
    <COMP2> = ARITH1 | lt | leq | gt | geq
    lt = ARITH1 (<ws* '<' ws*> ARITH1)+
    leq = ARITH1 (<ws* '<=' ws*> ARITH1)+
    gt = ARITH1 (<ws* '>' ws*> ARITH1)+
    geq = ARITH1 (<ws* '>=' ws*> ARITH1)+
    <ARITH1> = ARITH2 | add | sub | neg
    add = ARITH2 (<ws* '+' ws*> ARITH2)+
    sub = ARITH2 (<ws* '-' ws*> ARITH2)+
    neg = <'-' ws*> ARITH2
    <ARITH2> = ARITH3 | mul | div | mod
    mul = ARITH3 (<ws* '*' ws*> ARITH3)+
    div = ARITH3 (<ws* '/' ws*> ARITH3)+
    mod = ARITH3 (<ws* '%' ws*> ARITH3)+
    <ARITH3> = VALUE | power
    power = VALUE (<ws* ('**' | '^') ws*> VALUE)
    <VALUE> = LITERAL | REFERENCE | <'(' ws*> EXPR <ws* ')'>
    <LITERAL> = int | str | bool
    int = ['-'] #'[1-9][0-9]*' | '0'
    str = <'\\''> #'[^\\']*' <'\\''>
    bool = 'TRUE' | 'FALSE'
    REFERENCE = IDENTIFIER <'.'> IDENTIFIER
    <IDENTIFIER> = #'[a-zA-Z][a-zA-Z0-9]*'
    ws = #'\\s'"
   :string-ci true))
