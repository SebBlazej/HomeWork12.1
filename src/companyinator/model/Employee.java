package companyinator.model;

import parser.model.Parsable;

public class Employee implements Parsable {
    private String name;
    private String surname;
    private long pesel;
    private String section;
    private int salary;


    Employee(String[] valuesByColumnsOrder) {
        this.name = valuesByColumnsOrder[0];
        this.surname = valuesByColumnsOrder[1];
        this.pesel = Long.parseLong(valuesByColumnsOrder[2]);
        this.section = valuesByColumnsOrder[3];
        this.salary = Integer.parseInt(valuesByColumnsOrder[4]);
    }


    public String getSection() {
        return section;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String[] getFields() {
        String[] fields = new String[5];
        fields[0] = this.name;
        fields[1] = this.surname;
        fields[2] = String.valueOf(this.pesel);
        fields[3] = this.section;
        fields[4] = String.valueOf(this.salary);
        return fields;
    }
}
