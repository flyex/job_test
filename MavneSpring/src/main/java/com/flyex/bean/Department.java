package com.flyex.bean;

public class Department {
    private int deptId;
    private String deptNo;
    private String deptName;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Department(){}

    public Department(int deptId,String deptNo,String deptName){
        this.deptId=deptId;
        this.deptName=deptName;
        this.deptNo=deptNo;
    }
}
