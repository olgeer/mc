package com.ucsmy.mc.common.entity;

public class SystemLogExtend {
    private String sleId;

    private String className;

    private String classDescription;

    private String methodName;

    private String methodDescription;

    public String getSleId() {
        return sleId;
    }

    public void setSleId(String sleId) {
        this.sleId = sleId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }
}