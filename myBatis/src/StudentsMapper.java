import java.util.List;

public interface StudentsMapper {
    Students getStudent(int id);
    List<Students> getStudentALL();
    void add(Students students);
    void update(Students students);
    void delete(int id);
}
