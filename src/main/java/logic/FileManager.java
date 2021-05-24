package logic;

import org.apache.commons.lang3.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {

    private final int sizeByte = 43;
    private final RandomAccessFile datas;
    private final RandomAccessFile positions;
    private long countLines;

    public FileManager() throws FileNotFoundException {
        datas = new RandomAccessFile(Paths.get("src", "main", "resources", "datas").toString(), "rw");
        positions = new RandomAccessFile(Paths.get("src", "main", "resources", "positions").toString(), "rw");
    }

    public void writeLine(String[] line, ArrayList<Column> columns) throws IOException {
        long startPos = datas.length();
        if (startPos > 0) {
            startPos++;
        }
        long lineSize = 0;

        datas.seek(startPos);

        for (int i = 0; i < line.length; i++) {
            lineSize += columns.get(i).writeToFile(datas, line[i]);
        }

        savePositionsToFile(new long[]{startPos, lineSize});
        datas.seek(startPos);

        countLines++;
    }

    private void savePositionsToFile(long[] positions) throws IOException {
        long startPosition = sizeByte * countLines;
        if (countLines > 1) {
            startPosition++;
        }

        this.positions.seek(startPosition);

        byte[] positionArrayBytes = SerializationUtils.serialize(positions);

        this.positions.write(positionArrayBytes);
    }

    public Object[] readLine(int noLine, ArrayList<Column> cols) throws IOException {
        long[] linePos = readPositionOfLine(noLine);
        datas.seek(linePos[0]);

        Object[] line = new Object[cols.size()];
        for (int i = 0; i < cols.size(); i++) {
            line[i] = cols.get(i).readFromFile(datas);
        }

        return line;
    }

    private long[] readPositionOfLine(int noLine) throws IOException {
        long startPosition = (long) sizeByte * noLine;
        if (noLine > 1) {
            startPosition++;
        }
        positions.seek(startPosition);

        byte[] positionArrayBytes = new byte[sizeByte];
        positions.read(positionArrayBytes, 0, sizeByte);

        return SerializationUtils.deserialize(positionArrayBytes);
    }

    // PENSEZ A SUPPRIMER LES FICHIERS APRES CHAQUE ESSAI
    public static void main (String[] args) throws IOException {

        Column id = new Column("Id", "int");
        Column prenom = new Column("Prenom", "String");
        Column couleur = new Column("couleur", "String");
        Column age = new Column("age", "int");

        ArrayList<Column> columns = new ArrayList<>();
        columns.add(id);
        columns.add(prenom);
        columns.add(couleur);
        columns.add(age);

        String[] line1 = {"1", "Titi", "Jaune", "5"};
        String[] line2 = {"2", "Medor", "Noir", "10"};
        String[] line3 = {"3", "Pitie", "Noir", "5"};
        String[] line4 = {"4", "Juju", "Gris", "5"};
        String[] line5 = {"5", "Vanille", "Blanc", "7"};
        String[] line6 = {"6", "Chocolat", "Marron", "12"};
        String[] line7 = {"7", "Milou", "Blanc", "3"};
        String[] line8 = {"8", "Idefix", "Blanc", "14"};
        String[] line9 = {"9", "Pluto", "Jaune", "17"};
        String[] line10 = {"10", "Dingo", "Roux", "1"};

        FileManager fm = new FileManager();
        fm.writeLine(line1, columns);
        fm.writeLine(line2, columns);
        fm.writeLine(line3, columns);
        fm.writeLine(line4, columns);
        fm.writeLine(line5, columns);
        fm.writeLine(line6, columns);
        fm.writeLine(line7, columns);
        fm.writeLine(line8, columns);
        fm.writeLine(line9, columns);
        fm.writeLine(line10, columns);

        Object[] data;
        for (int i = 0; i < 10; i++) {
            data = fm.readLine(i, columns);
            for (Object datum : data) {
                System.out.print(datum + ", ");
            }
            System.out.print("\n");
        }

    }

}