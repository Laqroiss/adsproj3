package Tests;

import MyHashTable.MyHashTable;
import TestingClasses.MyTestingClass;
import TestingClasses.Student;

import java.util.Random;

public class HashTableTest {
    static MyHashTable<MyTestingClass, Student> hashTable= new MyHashTable<MyTestingClass,Student>();

    public static void main(String[] args) {
        for(int i = 0; i < 10000; i++){
            hashTable.put(testingClassGenerator(i),studentGenerator(i));
        }

        int[] containers = hashTable.containersLen();
        int cnt = 0;
        for(int i : containers) if(i != 0){
            System.out.print(i + ", ");
            cnt++;
        }
        System.out.printf("\nFilled containers: %d", cnt);
        System.out.printf("\nFilled containers (in percentage): %.2f%%", (cnt * 100f / containers.length));
        System.out.printf("\nTotal  amount of containers: %d", containers.length);
    }






    private static final Random random = new Random();

    private static final String[] NAMES = {
            "Alice", "Bob", "Charlie", "David", "Eve",
            "Frank", "Grace", "Hannah", "Isaac", "Jane"
    };
    private static final char[] GENDERS = {'M', 'F'};

    public static MyTestingClass testingClassGenerator(int id) {
        String name = NAMES[random.nextInt(NAMES.length)];
        return new MyTestingClass(name, id);
    }

    public static Student studentGenerator(int id) {
        String name = NAMES[random.nextInt(NAMES.length)];
        char gender = GENDERS[random.nextInt(GENDERS.length)];
        return new Student(name, gender);
    }
}
