package parser;

import logic.DataBase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class NewDataBaseTest {

    private static NewDataBase ndb = new NewDataBase();

    @Test
    public void indexation_csv() throws IOException {
        DataBase db = ndb.inIndex(new File("src/resources/dogs.csv"));

        String result = "Table_Name=Nom de la table : Table_Name\n" +
                "Nom de la colonne : Age Type : String\n" +
                "id : 0 value : 5\n" +
                "id : 1 value : 10\n" +
                "id : 2 value : 5\n" +
                "id : 3 value : 5\n" +
                "id : 4 value : 7\n" +
                "id : 5 value : 12\n" +
                "id : 6 value : 3\n" +
                "id : 7 value : 14\n" +
                "id : 8 value : 17\n" +
                "id : 9 value : 1\n" +
                "\n" +
                "Nom de la colonne : Couleur Type : String\n" +
                "id : 0 value : Jaune\n" +
                "id : 1 value : Noir\n" +
                "id : 2 value : Noir\n" +
                "id : 3 value : Gris\n" +
                "id : 4 value : Blanc\n" +
                "id : 5 value : Marron\n" +
                "id : 6 value : Blanc\n" +
                "id : 7 value : Blanc\n" +
                "id : 8 value : Jaune\n" +
                "id : 9 value : Roux\n" +
                "\n" +
                "Nom de la colonne : Id Type : String\n" +
                "id : 0 value : 1\n" +
                "id : 1 value : 2\n" +
                "id : 2 value : 3\n" +
                "id : 3 value : 4\n" +
                "id : 4 value : 5\n" +
                "id : 5 value : 6\n" +
                "id : 6 value : 7\n" +
                "id : 7 value : 8\n" +
                "id : 8 value : 9\n" +
                "id : 9 value : 10\n" +
                "\n" +
                "Nom de la colonne : Prénom Type : String\n" +
                "id : 0 value : Titi\n" +
                "id : 1 value : Médor\n" +
                "id : 2 value : Pitié\n" +
                "id : 3 value : Juju\n" +
                "id : 4 value : Vanille\n" +
                "id : 5 value : Chocolat\n" +
                "id : 6 value : Milou\n" +
                "id : 7 value : Idefix\n" +
                "id : 8 value : Pluto\n" +
                "id : 9 value : Dingo\n\n";

        assertEquals(db.toString(), result);
    }

    @Test
    public void trimed_a_file_name() {
        String nameTrimed_1 = ndb.trimName("src/dogs.csv");
        String nameWeWant_1 = "dogs";

        String nameTrimed_2 = ndb.trimName("/src/main/java/quelque_chose.csv");
        String nameWeWant_2 = "quelque_chose";

        assertEquals(nameTrimed_1, nameWeWant_1);
        assertEquals(nameTrimed_2, nameWeWant_2);
    }

}
