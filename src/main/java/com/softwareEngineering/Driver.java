package com.softwareEngineering;


import com.softwareEngineering.assignment1.*;
import org.joda.time.LocalDate;

import java.util.*;

/**
 * Created by Aidan Boyd on 19/09/17.
 */
public class Driver {

    public static void main(String[] args) {

        //Student One
        LocalDate dobOne = new LocalDate(1995, 1,1);
        Student s1 = new Student("Aidan", dobOne, 100000);

        //Student Two
        LocalDate dobTwo = new LocalDate(1996, 11, 2);
        Student s2 = new Student("Gavin", dobTwo, 100001);

        //Student Three
        LocalDate dobThree = new LocalDate(1996,1,14);
        Student s3 = new Student("Paddy", dobThree, 100002);

        //Student Four
        LocalDate dobFour = new LocalDate(1995, 5, 16);
        Student s4 = new Student("John", dobFour, 100003);

        //List of students for the different modules
        List<Student> studentListModuleOne = new ArrayList<Student>();
        List<Student> studentListModuleTwo = new ArrayList<Student>();
        List<Student> studentListModuleThree = new ArrayList<Student>();
        List<Student> studentListModuleFour = new ArrayList<Student>();

        //Create a few modules
        Module module1 = new Module("Digital Systems", "EE344", studentListModuleOne, "Dr. Fearghal Morgan");
        Module module2= new Module("Programming", "EE321", studentListModuleTwo, "Dr. Enda Barrett");
        Module module3 = new Module("Chemistry", "EE446", studentListModuleThree, "Dr. Martin Glavin");
        Module module4 = new Module("Analogue Systems Design", "EE345", studentListModuleFour, "Prof. Peter Corcoran");

        //Add students to module one
        module1.addStudent(s1);
        module1.addStudent(s2);
        module1.addStudent(s3);

        //Add students to module two
        module2.addStudent(s2);
        module2.addStudent(s3);
        module2.addStudent(s4);

        //Add students to module three
        module3.addStudent(s1);
        module3.addStudent(s3);
        module3.addStudent(s4);

        //Add students to module four
        module4.addStudent(s1);
        module4.addStudent(s2);
        module4.addStudent(s4);

        //Add a start date for the course
        LocalDate startDate = new LocalDate(2017, 9,1);

        //Add a finish date for the course
        LocalDate endDate = new LocalDate(2017, 11, 30);

        //Create a list of modules in the course
        List<Module> moduleList = new ArrayList<Module>();

        //Create a course
        Course ece = new Course("Electronic and Computer Engineering", "4BP1", moduleList, startDate, endDate);

        //Add modules to the course
        ece.addModule(module1);
        ece.addModule(module2);
        ece.addModule(module3);
        ece.addModule(module4);

        //Add a course list in case of expansion
        List<Course> courses = new ArrayList<Course>();

        //Add our course to that list
        courses.add(ece);

        //Get all students in course
        Set<Student> students = new HashSet<Student>();
        for(Course c : courses) { // For all courses
            for(Module m : c.getModules()) {
                for(Student s : m.getStudentList()) {
                    students.add(s);
                }
            }
            //Add them to a set so there are no duplicates
            HashMap<Student, ArrayList<Module>> associatedModules = new HashMap<Student, ArrayList<Module>>();

            //For all students
            for(Student s : students) {
                //Add the student to a HashMap along with an empty array
                associatedModules.put(s, new ArrayList<Module>());
                //Now add the appropriate modules to each student
                for(Module m : c.getModules()) {
                    //If the student is in that module add it to its list
                    if(m.getStudentList().contains(s)) {
                        associatedModules.get(s).add(m);
                    }
                }
            }

            System.out.println("Number of students in course " + c.getCourseName() +": " + students.size());
            //Again for all students
            for (Student s : students) {
                StringBuilder string = new StringBuilder("Modules for ");
                string.append(s.getName());
                string.append(": ");
                ArrayList<Module> modules = associatedModules.get(s);

                //For all associated modules
                for(Module m : modules){
                    string.append(m.getModuleName());
                    string.append(", ");
                }

                //Remove last , and space
                String preOutput = string.toString().substring(0,string.length()-2);

                //Add in details about that students course
                StringBuilder sb = new StringBuilder(preOutput);
                sb.append(". This student is enrolled in the ");
                sb.append(c.getCourseName());
                sb.append(" - ");
                sb.append(c.getCourseCode());
                sb.append(" Course.");

                String output = sb.toString();

                //Display output on console
                System.out.println(output);
            }


        }



    }
}
