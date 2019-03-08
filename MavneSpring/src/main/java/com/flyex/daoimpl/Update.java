package com.flyex.daoimpl;

import com.flyex.bean.Department;
import com.flyex.dao.DepartmentDAO;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Update implements DepartmentDAO {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Department> queryDepartment() throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql2 = "insert into Department (DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION)\n" +
                "values (14, '666', 'ttt', '234')";
        PreparedStatement statement = connection.prepareStatement(sql2);
        statement.executeUpdate();

        String sql ="select dept_id, dept_no,dept_name from department ";

        Statement smt = connection.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        List<Department> list = new ArrayList<Department>();
        while(rs.next()){
            Long deptId = rs.getLong("dept_id");
            String deptNo = rs.getString("dept_no");
            String deptName = rs.getString("dept_name");
            Department dept = new Department(deptId,deptNo,deptName);
            list.add(dept);
        }
        return list;

    }
}
