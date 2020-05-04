package parser.app;

import parser.model.Parsable;
import parser.model.ParsableFactory;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FileController<P extends Parsable, F extends ParsableFactory<P>> {

    protected FileParser<P, F> fileParser;
    private List<String> listOfLines;
    protected List<P> parsedObjectsList;


    public FileController(FileParser<P, F> fileParser) throws IOException {
        this.fileParser = fileParser;
    }

    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Parsable parsable : parsedObjectsList) {
                String[] values = parsable.getFields();
                for (int index = 0; index < values.length - 1; index++) {
                    writer.write(values[index] + fileParser.getDelimiter());
                }
                writer.write(values[values.length -1]);
                writer.newLine();
            }
        }
    }

    public void readFile(String fileName) throws IOException {
        List<String> listOfLines;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            listOfLines = reader.lines().collect(Collectors.toList());
            this.listOfLines = listOfLines;
        }
        parseFile();
    }

    private void parseFile() {
        this.parsedObjectsList = fileParser.parseLines(this.listOfLines);
    }
}



