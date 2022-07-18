/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lesson;
import model.LessonAdmin;
import model.LessonType;
import model.Subject;

/**
 *
 * @author Admin
 */
public class LessonDAO extends DBContext{
   
    public ArrayList<Lesson> getAllLessonDPV() {
        ArrayList<Lesson> listLesson = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Lesson WHERE status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson s = new Lesson();
                s.setLessonId(rs.getInt("lessonId"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setLessonName(rs.getString("lessonName"));
                s.setLessonOrder(rs.getInt("lessonOrder"));
                s.setLessonTypeId(rs.getInt("lessonTypeId"));
                s.setVideoLink(rs.getString("videoLink"));
                s.setContent(rs.getString("content"));
                s.setStatus(rs.getBoolean("status"));
                listLesson.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLesson;
    }
    
    public ArrayList<Lesson> getLessonByIdDPV(int subjectId) {
        ArrayList<Lesson> listLessonById = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Lesson WHERE status = 1 AND subjectId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson s = new Lesson();
                s.setLessonId(rs.getInt("lessonId"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setLessonName(rs.getString("lessonName"));
                s.setLessonOrder(rs.getInt("lessonOrder"));
                s.setLessonTypeId(rs.getInt("lessonTypeId"));
                s.setVideoLink(rs.getString("videoLink"));
                s.setContent(rs.getString("content"));
                s.setStatus(rs.getBoolean("status"));
                listLessonById.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLessonById;
    }

    public ArrayList<LessonType> getLessonTypeDPV() {
        ArrayList<LessonType> listLessonType = new ArrayList<>();
        try {

            String sql = "SELECT * FROM LessonType WHERE status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LessonType s = new LessonType();
                s.setLessonTypeId(rs.getInt("lessonTypeId"));
                s.setLessonTypeName(rs.getString("lessonTypeName"));
                s.setStatus(rs.getBoolean("status"));
                listLessonType.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLessonType;
    }


    public ArrayList<Lesson> getListLessonById(int subjectId) {
        ArrayList<Lesson> listLessonById = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Lesson WHERE status = 1 AND subjectId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson s = new Lesson();
                s.setLessonId(rs.getInt("lessonId"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setLessonName(rs.getString("lessonName"));
                s.setLessonOrder(rs.getInt("lessonOrder"));
                s.setLessonTypeId(rs.getInt("lessonTypeId"));
                s.setVideoLink(rs.getString("videoLink"));
                s.setContent(rs.getString("content"));
                s.setStatus(rs.getBoolean("status"));
                listLessonById.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLessonById;
    }
    
    public Lesson getLessonById(int lessonId) throws Exception {


        Lesson lessonById = null;
        String sql = "SELECT * FROM [Lesson] WHERE [lessonId] = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                String lessonName = rs.getString("lessonName");
                int lessonOrder = rs.getInt("lessonOrder");
                int lessonTypeId = rs.getInt("lessonTypeId");
                String videoLink = rs.getString("videoLink");
                String content = rs.getString("content");
                Boolean status = rs.getBoolean("status");

                lessonById = new Lesson(lessonId, subjectId, lessonName, lessonOrder, lessonTypeId, videoLink, content, status, null);
            }
        } catch (Exception ex) {
            throw ex;
        } 
        return lessonById;
    }
    
    public ArrayList<LessonAdmin> getAllLesson(){
        ArrayList<LessonAdmin> allLesson = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Lesson");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int lessonId = rs.getInt("lessonId");
                Subject subject = getSubject(rs.getInt("subjectId"));
                String lessonName = rs.getString("lessonName");
                int lessonOrder = rs.getInt("lessonOrder");
                LessonType lessonType = getType(rs.getInt("lessonTypeId"));                
                String videoLink = rs.getString("videoLink");
                String content = rs.getString("content");                
                boolean status = rs.getBoolean("status");
                allLesson.add(new LessonAdmin(lessonId, subject, lessonName, lessonOrder, lessonType,videoLink,content, status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allLesson;
    }
    
    public Subject getSubject(int subId){
        try {
            String sql = "SELECT * FROM [Subject] WHERE subjectId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectId(rs.getInt("subjectId"));
                s.setSubjectName(rs.getString("subjectName"));
                s.setDescription(rs.getString("description"));
                s.setThumbnail(rs.getString("thumbnail"));
                s.setFeaturedSubject(rs.getBoolean("featuredSubject"));              
                s.setStatus(rs.getBoolean("status"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public LessonType getType(int typeId){
        try {
            String sql = "SELECT * FROM [LessonType] WHERE lessonTypeId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, typeId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LessonType s = new LessonType();
                s.setLessonTypeId(rs.getInt("lessonTypeId"));
                s.setLessonTypeName(rs.getString("lessonTypeName"));                       
                s.setStatus(rs.getBoolean("status"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updateLess(int lesId, String lesSub,String lesName,int lesOrder ,String lesLink,String lesContent,int lesType,boolean lesStatus){
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE [dbo].[Lesson] SET [lessonName] = ?,[lessonOrder] = ?,[lessonTypeId] = ?,[videoLink] = ?, [content]=?,[status] = ? WHERE [lessonId] = ?");
            stm.setString(1, lesName);
            stm.setInt(2, lesOrder);
            stm.setInt(3, lesType);
            stm.setString(4, lesLink);
            stm.setString(5, lesContent);
            stm.setBoolean(6, lesStatus);   
            stm.setInt(7, lesId);

            int status = stm.executeUpdate();
            return 1;
        } catch (SQLException ex) {           
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public ArrayList<LessonType> getLessonType() {
        ArrayList<LessonType> listLessonType = new ArrayList<>();
        try {

            String sql = "SELECT * FROM LessonType WHERE status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                LessonType s = new LessonType();
                s.setLessonTypeId(rs.getInt("lessonTypeId"));
                s.setLessonTypeName(rs.getString("lessonTypeName"));
                s.setStatus(rs.getBoolean("status"));
                listLessonType.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLessonType;
    }
    
    public ArrayList<Lesson> getLessonBySubjectId(int subjectId) {
        ArrayList<Lesson> listLessonById = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Lesson WHERE status = 1 AND subjectId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lesson s = new Lesson();
                s.setLessonId(rs.getInt("lessonId"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setLessonName(rs.getString("lessonName"));
                s.setLessonOrder(rs.getInt("lessonOrder"));
                s.setLessonTypeId(rs.getInt("lessonTypeId"));
                s.setVideoLink(rs.getString("videoLink"));
                s.setContent(rs.getString("content"));
                s.setStatus(rs.getBoolean("status"));
                listLessonById.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLessonById;
    }

    
    
}
