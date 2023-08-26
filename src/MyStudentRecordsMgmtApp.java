import model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class MyStudentRecordsMgmtApp {

    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(110001, "Dave", "11/12/1951"));
        studentList.add(new Student(110002, "Anna", "12/07/1990"));
        studentList.add(new Student(110003, "Erica", "01/31/1974"));
        studentList.add(new Student(110004, "Carlos", "08/22/2009"));
        studentList.add(new Student(110005, "Bob", "03/05/1990"));

        System.out.println("=== STUDENTS admissions ===");
        printListOfStudents(studentList);
        System.out.println("\n=== ALUMNI STUDENTS 30 years ===");
        getListOfPlatiniumAlumniStudents(studentList);
        System.out.println("=== Hello World numbers ===");
        printHelloWorld(new int[] {1,3,4,5,6,7,8,14,21,35});
        System.out.println("=== second biggest ===");
        findSecondBiggest(new int[] {1,3,4,5});
    }
    static void printListOfStudents(List<Student> studentList) {

        studentList.stream().sorted(Comparator.comparing(Student::getName)).forEach(student -> System.out.println(student.getDateOfAdmission()));
    }
    static void getListOfPlatiniumAlumniStudents(List<Student> studentList) {

        studentList.stream().filter(std -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            // Parse the given date string into a Date object
            Date givenDate = null;
            try {
                givenDate = dateFormat.parse(std.getDateOfAdmission()); // Replace with your date string
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar givenCalendar = Calendar.getInstance();
            givenCalendar.setTime(givenDate);

            Calendar currentDate = Calendar.getInstance();

            int yearDifference = currentDate.get(Calendar.YEAR) - givenCalendar.get(Calendar.YEAR);

            if (yearDifference >= 30) {
               return true;
            } else {
                return false;
            }
        }).sorted((std1, std2)-> {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date date1 = dateFormat.parse(std1.getDateOfAdmission());
                Date date2 = dateFormat.parse(std2.getDateOfAdmission());
                return  date1.compareTo(date2);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).forEach(student -> System.out.println(student.getName()));
    }
    static void printHelloWorld(int[] numbers) {
        for (int val : numbers) {
            if(val%5==0 && val%7==0) {
                System.out.println("HelloWorld");
            } else if (val%5==0) {
                System.out.println("Hello");
            } else if (val%7==0) {
                System.out.println("World");
            }
        }
    }
    static void findSecondBiggest(int[] numbers) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int val: numbers) {
            if(val  >= first) {
                second = first;
                first = val;
            }
        }

        System.out.println("second: "+ second);
    }

}