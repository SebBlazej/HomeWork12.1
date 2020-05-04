package companyinator.app;

import companyinator.model.Employee;
import companyinator.model.EmployeeFactory;
import parser.app.FileController;
import parser.app.FileParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileControllerImpl extends FileController<Employee, EmployeeFactory> {

    public FileControllerImpl(FileParser<Employee, EmployeeFactory> fileParser) throws IOException {
        super(fileParser);
    }

    public void saveStatsToFile(String fileName) throws IOException {
        StatsCreator statsCreator = new StatsCreator(super.parsedObjectsList);
        String[] statsArray = statsCreator.getStats();
        Path filePath = Path.of(fileName);

        if(Files.exists(filePath)) Files.delete(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));

        for(String statsLine: statsArray){
            bufferedWriter.write(statsLine);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        System.out.println("Statystyki zostały zapisane do pliku - " + fileName);
    }


}
