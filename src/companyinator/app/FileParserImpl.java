package companyinator.app;

import companyinator.model.Employee;
import companyinator.model.EmployeeFactory;
import parser.app.FileParser;

public class FileParserImpl extends FileParser<Employee, EmployeeFactory> {

    public FileParserImpl(EmployeeFactory parsedObjectFactoryImpl, char delimiter, boolean haveHeader) {
        super(parsedObjectFactoryImpl, delimiter, haveHeader);
    }

}
