package com.ucsmy.mc.common.entity;

public class Grade {
    private String gradId;

    private String gradGradeNo;

    private String gradGradeName;

    private Byte gradType;

    public String getGradId() {
        return gradId;
    }

    public void setGradId(String gradId) {
        this.gradId = gradId;
    }

    public String getGradGradeNo() {
        return gradGradeNo;
    }

    public void setGradGradeNo(String gradGradeNo) {
        this.gradGradeNo = gradGradeNo;
    }

    public String getGradGradeName() {
        return gradGradeName;
    }

    public void setGradGradeName(String gradGradeName) {
        this.gradGradeName = gradGradeName;
    }

    public Byte getGradType() {
        return gradType;
    }

    public void setGradType(Byte gradType) {
        this.gradType = gradType;
    }
}