// StudentOperations.java
import java.sql.*;
import java.util.*;

public class StudentOperations {

    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, prn, dob, marks) VALUES (?, ?, ?, ?)";
        try {
                System.out.println("inside addStudent");
                Connection conn = DBConnection.getConnection();
                PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, student.getName());
            pst.setLong(2, student.getPRN());
            pst.setString(3, student.getDob());
            pst.setDouble(4, student.getMarks());
            pst.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void displayStudents() {
        String query = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)) {
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("Name: " + rs.getString("name") + ", PRN: " + rs.getLong("prn") + ", DoB: " + rs.getString("dob") + ", Marks: " + rs.getDouble("marks"));
            }
            if (!found) System.out.println("No students found.");
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
    }

    public Student searchByPRN(long prn) {
        String query = "SELECT * FROM students WHERE prn = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setLong(1, prn);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Student(rs.getString("name"), rs.getLong("prn"), rs.getString("dob"), rs.getDouble("marks"));
            }
        } catch (SQLException e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
        return null;
    }

    public Student searchByName(String name) {
        String query = "SELECT * FROM students WHERE name = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Student(rs.getString("name"), rs.getLong("prn"), rs.getString("dob"), rs.getDouble("marks"));
            }
        } catch (SQLException e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
        return null;
    }

    public Student searchByPosition(int index) {
        String query = "SELECT * FROM students LIMIT ?, 1";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, index);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Student(rs.getString("name"), rs.getLong("prn"), rs.getString("dob"), rs.getDouble("marks"));
            }
        } catch (SQLException e) {
            System.out.println("Error searching by position: " + e.getMessage());
        }
        return null;
    }

    public boolean updateStudent(long prn, String newName, String newDob, double newMarks) {
        String query = "UPDATE students SET name = ?, dob = ?, marks = ? WHERE prn = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, newName);
            pst.setString(2, newDob);
            pst.setDouble(3, newMarks);
            pst.setLong(4, prn);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteStudent(long prn) {
        String query = "DELETE FROM students WHERE prn = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setLong(1, prn);
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
        return false;
    }
}
