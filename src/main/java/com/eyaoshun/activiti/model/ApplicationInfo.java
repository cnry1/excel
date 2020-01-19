package com.eyaoshun.activiti.model;

/**
 * @author zmm
 * @date 2020/1/19
 */
public class ApplicationInfo {
     private  String appNo;
    private  String total ;
    private  String applicantname;


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getApplicantname() {
        return applicantname;
    }

    public void setApplicantname(String applicantname) {
        this.applicantname = applicantname;
    }

    @Override
    public String toString() {
        return "ApplicationInfo{" +
                "appNo='" + appNo + '\'' +
                ", total='" + total + '\'' +
                ", applicantname='" + applicantname + '\'' +
                '}';
    }
}
