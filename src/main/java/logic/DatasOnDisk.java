package logic;

import org.apache.commons.lang3.SerializationUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;
import java.util.ArrayList;

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
     * Write the line in the file 'datas'
     * @param line
     * @param columns
     * @throws IOException
     */
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

        writePositions(new long[]{startPos, lineSize});
        datas.seek(startPos);

        countLines++;
    }

    /**
     * Write the position of a line in the file 'positions'
     * @param positions
     * @throws IOException
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
     * @param noLine
     * @param cols
     * @return a line split by its components
     * @throws IOException
     */
    public Object[] readLine(int noLine, ArrayList<Column> cols) throws IOException {
        long[] linePos = readPositions(noLine);
        datas.seek(linePos[0]);

        Object[] line = new Object[cols.size()];
        for (int i = 0; i < cols.size(); i++) {
            line[i] = cols.get(i).readFromFile(datas);
        }

        return line;
    }

    /**
     * Read the position of a line based on its number
     * @param noLine
     * @return the position of the line and its size
     * @throws IOException
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