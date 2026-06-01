package complexity.opgave1.util;

import complexity.opgave1.entities.Student;

import java.io.*;
import java.util.*;


public class Factory {

    private static List<String> studentNames = new ArrayList<>();
    private static String studentPath = "data/Students.txt";

    static{
        readStudentNames();
    }

    private static void readStudentNames(){
        File file = new File(studentPath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(studentPath));
            while(reader.ready()){
                studentNames.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file " + studentPath + " cannot be found");
        } catch (IOException e) {
            System.out.println("Læsning fra filen fejlede");
        }
    }

    public static void fillWithStudents(List<Student> collection, int amount){
        for(int i = 0; i < amount; i++){
            Random random = new Random();
            collection.add(new Student(studentNames.get(random.nextInt(studentNames.size())), i));
        }
    }

}
