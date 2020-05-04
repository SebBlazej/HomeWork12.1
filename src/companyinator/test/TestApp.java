package companyinator.test;

import companyinator.app.FileParserImpl;
import companyinator.app.FileControllerImpl;
import companyinator.model.EmployeeFactory;
import java.io.IOException;


public class TestApp {
    public static void main(String[] args) {

        EmployeeFactory employeeFactory = new EmployeeFactory();
        FileParserImpl fileParserImpl = new FileParserImpl(employeeFactory, ';', true);

        try {
            FileControllerImpl fileControllerImpl = new FileControllerImpl(fileParserImpl);
            fileControllerImpl.readFile("inputFile.csv");
            fileControllerImpl.saveStatsToFile("statsFile.txt");

        } catch (IOException e) {
            System.out.println("Nie udalo sie wykonac operacji na plikach.");
            e.printStackTrace();
        }

    }


}
