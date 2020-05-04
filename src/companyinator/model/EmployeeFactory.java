package companyinator.model;

import parser.model.ParsableFactory;

public class EmployeeFactory extends ParsableFactory<Employee> {

    @Override
    public Employee createParsedObject(String[] valuesByColumnsOrder) {
        return new Employee(valuesByColumnsOrder);
    }
}
