// Student.java
public class Student {
    private String name;
    private long prn;
    private String dob;
    private double marks;

    public Student(String name, long prn, String dob, double marks) {
        this.name = name;
        this.prn = prn;
        this.dob = dob;
        this.marks = marks;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getPRN() { return prn; }
    public void setPRN(long prn) { this.prn = prn; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return "Name: " + name + ", PRN: " + prn + ", DoB: " + dob + ", Marks: " + marks;
    }
}
