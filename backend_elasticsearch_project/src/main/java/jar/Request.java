package main.java.jar;

public class Request {
    /*
    Format : JSon
    Type de methode : uptdate, create, delete, ...
    Dans quoi on cherche : where et condition
    Argument en plus : count, distinct, ...

    comment on fait ça : SELECT *
FROM `table`
WHERE `nom_colonne` = (
    SELECT `valeur`
    FROM `table2`
    LIMIT 1
  )
     */
}
