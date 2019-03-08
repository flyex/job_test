import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HelloWord {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentsMapper studentsMapper = session.getMapper(StudentsMapper.class);
            //getstudentbyId
            Students students = studentsMapper.getStudent(4);
            System.out.println(students);
            //getstudentAll
            List<Students> list = studentsMapper.getStudentALL();
            System.out.println(list);
            //addstudent
            Students students1 = new Students();
            students1.setId(1);
            students1.setName('哈');
            String dateString = "2009-01-01";
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            students1.setBirth(date);
            studentsMapper.add(students1);
            session.commit();
            //updateStudent
            Students students2 = studentsMapper.getStudent(2);
            students2.setName('j');
            studentsMapper.update(students2);
            session.commit();
            //deleteStudent
            studentsMapper.delete(1);
            session.commit();

            }catch (Exception e) {
                e.printStackTrace();
            }
            session.close();
        }
    }

