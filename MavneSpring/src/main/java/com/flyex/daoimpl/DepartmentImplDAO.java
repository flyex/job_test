package com.flyex.daoimpl;

import com.flyex.bean.Department;
import com.flyex.dao.DepartmentDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentImplDAO implements DepartmentDAO {
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
    }

    public List<Department> queryDepartment() throws SQLException{
        Connection conn = dataSource.getConnection();
        /**String sql2 = "insert into Department (DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION)\n" +
                "values (14, '666', 'ttt', '234')";*/
        String sql ="select dept_id, dept_no,dept_name from department ";
        Statement smt = conn.createStatement();

        ResultSet rs = smt.executeQuery(sql);
        List<Department> list = new ArrayList<Department>();
        while(rs.next()){
            int deptId = rs.getInt("dept_id");
            String deptNo = rs.getString("dept_no");
            String deptName = rs.getString("dept_name");
            Department dept = new Department(deptId,deptNo,deptName);
            list.add(dept);
        }
        return list;

    }
}
