package parser.app;

import parser.model.Parsable;
import parser.model.ParsableFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class FileParser<P extends Parsable, F extends ParsableFactory<P>> {

    private final F factory;
    private final char delimiter;
    private final boolean haveHeader;

    public FileParser(F parsedObjectFactoryImpl, char delimiter, boolean haveHeader) {
        this.factory = parsedObjectFactoryImpl;
        this.delimiter = delimiter;
        this.haveHeader = haveHeader;

    }

    List<P> parseLines(List<String> lines) {
        if(haveHeader) lines = lines.subList(1, lines.size());
        List<P> listOfFieldsArray = new ArrayList<>(lines.size());
        for (String line : lines) {
            String[] fieldsValues = splitLineByColumnsOrder(line);
            P parsedObject =  factory.createParsedObject(fieldsValues);
            listOfFieldsArray.add(parsedObject);
        }
        return listOfFieldsArray;
    }

    private String[] splitLineByColumnsOrder(String line) {
        int numberOfColumns = countColumns(line);
        String[] fields = new String[numberOfColumns];
        for (int index = 0; index < numberOfColumns-1; index++) {
                fields[index] = line.substring(0, line.indexOf(delimiter));
                line = line.substring(line.indexOf(delimiter) + 1);
        }
        fields[numberOfColumns-1] = line;
        return fields;
    }

    private int countColumns(String line) {
        return (int) (line.chars().filter(ch -> ch == delimiter).count() + 1);
    }

    char getDelimiter() {
        return delimiter;
    }
}
