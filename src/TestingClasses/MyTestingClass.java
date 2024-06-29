package TestingClasses;

public class MyTestingClass {
    private int id;
    private String name;


    public MyTestingClass(String name){
        this(name, 0);
    }
    public MyTestingClass(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
