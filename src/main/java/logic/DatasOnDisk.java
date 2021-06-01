package logic;

import org.apache.commons.lang3.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class DatasOnDisk {

    private final int sizeByte = 43;
    private final RandomAccessFile datas;
    private final RandomAccessFile positions;
    private long countLines;

    public DatasOnDisk() throws FileNotFoundException {
        datas = new RandomAccessFile(Paths.get("src", "resources", "datas").toString(), "rw");
        positions = new RandomAccessFile(Paths.get("src", "resources", "positions").toString(), "rw");
    }

    /**
     * Write the line in the file 'datas' according to the columns contained in the table
     * @param line line
     * @param table table
     * @throws IOException e
     */
    public void writeLine(String[] line, Table table) throws IOException {
        long startPos = datas.length();
        if (startPos > 0) {
            startPos++;
        }
        long lineSize = 0;

        datas.seek(startPos);
        ArrayList<Column> columns = table.getColumnsList();
        HashMap<String, String > list = new HashMap<>();
        for (int i = 0; i < line.length; i++) {
            lineSize += columns.get(i).writeToFile(datas, line[i]);
            list.put(columns.get(i).getName(), line[i]);
        }
        table.addLineIndex(list);

        writePositions(new long[]{startPos, lineSize});
        datas.seek(startPos);

        countLines++;
    }

    /**
     * Write the position of a line in the file 'positions'
     * @param positions positions
     * @throws IOException e
     */
    private void writePositions(long[] positions) throws IOException {
        long startPosition = sizeByte * countLines;
        if (countLines > 1) {
            startPosition++;
        }

        this.positions.seek(startPosition);

        byte[] positionArrayBytes = SerializationUtils.serialize(positions);

        this.positions.write(positionArrayBytes);
    }

    /**
     * Read a line from its number and columns
     * @param noLine noLine
     * @param cols cols
     * @param selectedCols selectedCols
     * @return a line split by its components - only the data corresponding to the selected columns
     * @throws IOException ex
     */
    public Object[] readLine(int noLine, ArrayList<Column> cols, ArrayList<Column> selectedCols) throws IOException {
        long[] linePos = readPositions(noLine);
        datas.seek(linePos[0]);

        Object[] line = new Object[cols.size()];
        Object[] lineTrimed = new Object[selectedCols.size()];
        for (int i = 0; i < cols.size(); i++) {
            line[i] = cols.get(i).readFromFile(datas);
        }
        for (int i = 0; i < selectedCols.size(); i++) {
            lineTrimed[i] = line[cols.indexOf(selectedCols.get(i))];
        }
        return lineTrimed;
    }

    /**
     * Read the position of a line based on its number
     * @param noLine noLine
     * @return the position of the line and its size
     * @throws IOException e
     */
    private long[] readPositions(int noLine) throws IOException {
        long startPosition = (long) sizeByte * noLine;
        if (noLine > 1) {
            startPosition++;
        }
        positions.seek(startPosition);

        byte[] positionArrayBytes = new byte[sizeByte];
        positions.read(positionArrayBytes, 0, sizeByte);

        return SerializationUtils.deserialize(positionArrayBytes);
    }

}