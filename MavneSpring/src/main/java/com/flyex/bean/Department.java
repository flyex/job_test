package com.flyex.bean;

public class Department {
    private Long deptId;
    private String deptNo;
    private String deptName;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
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

    public Department(Long deptId,String deptNo,String deptName){
        this.deptId=deptId;
        this.deptName=deptName;
        this.deptNo=deptNo;
    }
}
