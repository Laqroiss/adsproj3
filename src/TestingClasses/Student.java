package TestingClasses;

public class Student {
    private String name;
    private int id;
    private char gender;

    public Student(String name, char gender) {
        this.name = name;
        this.gender= gender;
    }
    @Override
    public int hashCode(){
        int hash = 7;
        for (int i = 0; i < name.length(); i++) {
            hash = 31 * hash + name.charAt(i);
        }
        return hash;
    }

}
