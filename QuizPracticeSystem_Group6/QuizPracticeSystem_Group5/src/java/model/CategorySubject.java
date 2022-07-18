/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Minh-PC
 */
public class CategorySubject {
    private int subjectId;
    private int cateId;

    public CategorySubject() {
    }

    public CategorySubject(int subjectId, int cateId) {
        this.subjectId = subjectId;
        this.cateId = cateId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    @Override
    public String toString() {
        return "CategorySubject{" + "subjectId=" + subjectId + ", cateId=" + cateId + '}';
    }
    
}
