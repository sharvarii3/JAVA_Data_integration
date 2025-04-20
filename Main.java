// Name : Pooja Ramdas
// PRN No. : 23070126091
// Batch : AIML B-1 (2023-2027)

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        StudentOperations operations = new StudentOperations();

        while (true) {
            System.out.println("\n1. Add Student\n2. Display Students\n3. Search by PRN\n4. Search by Name\n5. Search by Position\n6. Update Student\n7. Delete Student\n8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("PRN: ");
                    long prn = sc.nextLong(); sc.nextLine();
                    System.out.print("DoB: ");
                    String dob = sc.nextLine();
                    System.out.print("Marks: ");
                    double marks = sc.nextDouble();
                    operations.addStudent(new Student(name, prn, dob, marks));
                }
                case 2 -> operations.displayStudents();
                case 3 -> {
                    System.out.print("PRN: ");
                    Student s = operations.searchByPRN(sc.nextLong());
                    System.out.println(s != null ? s : "Not found");
                }
                case 4 -> {
                    System.out.print("Name: ");
                    Student s = operations.searchByName(sc.nextLine());
                    System.out.println(s != null ? s : "Not found");
                }
                case 5 -> {
                    System.out.print("Position: ");
                    Student s = operations.searchByPosition(sc.nextInt());
                    System.out.println(s != null ? s : "Invalid position");
                }
                case 6 -> {
                    System.out.print("PRN: ");
                    long prn = sc.nextLong(); sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New DoB: ");
                    String newDob = sc.nextLine();
                    System.out.print("New Marks: ");
                    double newMarks = sc.nextDouble();
                    if (!operations.updateStudent(prn, newName, newDob, newMarks))
                        System.out.println("Not found");
                }
                case 7 -> {
                    System.out.print("PRN: ");
                    if (!operations.deleteStudent(sc.nextLong()))
                        System.out.println("Not found");
                }
                case 8 -> {
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
