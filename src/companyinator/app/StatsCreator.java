package companyinator.app;

import companyinator.model.Employee;

import java.util.*;

class StatsCreator {

    private static final int CONST_STATS_NUMBER = 4;
    private List<Employee> employeeList;
    private HashMap<String, Integer> employeesNumberBySection;

    StatsCreator(List<Employee> employeeList) {
        this.employeeList = employeeList;
        setEmployeesNumberBySection(employeesNumberBySection());
    }

    String[] getStats() {
        int arraySize = CONST_STATS_NUMBER + this.employeesNumberBySection.size();
        String[] statsArray = new String[arraySize];
        
        addStats(arraySize, statsArray);
        
        return statsArray;
    }

    private void addStats(int arraySize, String[] statsArray) {
        addConstStats(statsArray);
        addSectionsStats(arraySize, statsArray);
    }

    private void addConstStats(String[] statsArray) {
        statsArray[0] = getAverageSalaryStatAsString();
        statsArray[1] = getMinSalaryStatAsString();
        statsArray[2] = getMaxSalaryStatAsString();
        statsArray[3] = getEmployeeCountStatAsString();
    }

    private void addSectionsStats(int arraySize, String[] statsArray) {
        String[] uniqueSectionsStrings = getUniqueSections();
        for(int index = CONST_STATS_NUMBER; index < arraySize; index++){
            statsArray[index] = uniqueSectionsStrings[index - CONST_STATS_NUMBER];
        }
    }

    private String[] getUniqueSections() {
        Object[] uniqueSections = employeesNumberBySection.entrySet().toArray();
        String[] sectionsArray = new String[uniqueSections.length];

        for (int index = 0; index < sectionsArray.length; index++) {
            sectionsArray[index] = "Liczba pracowników w dziale " + uniqueSections[index].toString();
        }
        
        return sectionsArray;
    }
    
    private String getMinSalaryStatAsString() {
        return "Najmniejsza wypłata: " + getMinSalary();
    }

    private String getMaxSalaryStatAsString() {
        return "Największa wypłata: " + getMaxSalary();
    }

    private String getAverageSalaryStatAsString() {
        return "Średnia wypłata: " + getAverageSalary();
    }

    private String getEmployeeCountStatAsString() {
        return "Liczba pracowników: " + employeeList.size();
    }


    private int getAverageSalary() {
        List<Integer> salaryList = getSalaries();

        int salarySum = 0;
        for (int salary : salaryList) {
            salarySum += salary;
        }

        return salarySum / salaryList.size();
    }


    private int getMinSalary() {
        List<Integer> salaryList = getSalaries();
        return Collections.min(salaryList);
    }

    private int getMaxSalary() {
        List<Integer> salaryList = getSalaries();
        return Collections.max(salaryList);
    }

    private List<Integer> getSalaries() {
        List<Integer> salaryList = new ArrayList<>();

        for (Employee employee : this.employeeList) {
            salaryList.add(employee.getSalary());
        }

        return salaryList;
    }

    private HashMap<String, Integer> employeesNumberBySection() {
        HashMap<String, Integer> sectionsMap = new HashMap<>();

        for (Employee employee : this.employeeList) {
            String section = employee.getSection().toLowerCase();
            if (sectionExists(sectionsMap, section)) {
                increaseEmployeesInSection(sectionsMap, section);
            } else sectionsMap.put(section, 1);
        }

        return sectionsMap;
    }

    private void increaseEmployeesInSection(HashMap<String, Integer> sectionsMap, String section) {
        int counter = sectionsMap.get(section);
        sectionsMap.put(section, counter + 1);
    }

    private boolean sectionExists(HashMap sectionsMap, String section) {
        return sectionsMap.containsKey(section);
    }

    private void setEmployeesNumberBySection(HashMap<String, Integer> employeesNumberBySection) {
        this.employeesNumberBySection = employeesNumberBySection;
    }
}
