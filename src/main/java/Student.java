import java.util.Objects;

public class Student {
    private int studentId;
    private String name;
    private Gender gender;

    public Student(int studentId, String name, Gender gender) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId &&
                Objects.equals(name, student.name) &&
                gender == student.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, gender);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
