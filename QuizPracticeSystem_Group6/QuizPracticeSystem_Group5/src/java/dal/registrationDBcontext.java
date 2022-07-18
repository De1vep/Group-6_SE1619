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
import model.PricePackage;
import model.RegistrationManage;
import model.Subject;

/**
 *
 * @author Admin
 */
public class registrationDBcontext extends DBContext {

    
    
    
    public int CountRegistrationByUserId(int userId) {
        ArrayList<RegistrationManage> Registrations = new ArrayList<>();
        try {
            String sql = "SELECT COUNT(r.regId) FROM Registration r\n"
                    + "INNER JOIN PricePackage p ON r.packId = p.packId\n"
                    + "INNER JOIN  [Subject] s ON s.subjectId = p.subjectId\n"
                    + "WHERE r.userId = ?\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public ArrayList<RegistrationManage> GetAllRegistrationByUserIdWithPage(int userId, int page, int PAGE_SIZE) {
        ArrayList<RegistrationManage> Registrations = new ArrayList<>();
        try {
            String sql = "SELECT r.regId,s.subjectName,r.regTime,p.packName,r.cost,r.validFrom,r.validTo,r.[status],r.note FROM Registration r\n"
                    + "INNER JOIN PricePackage p ON r.packId = p.packId\n"
                    + "INNER JOIN  [Subject] s ON s.subjectId = p.subjectId\n"
                    + "WHERE r.userId = ?\n"
                    + "order by r.regId offset (?-1)*? row fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, page);
            stm.setInt(3, PAGE_SIZE);
            stm.setInt(4, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RegistrationManage a = new RegistrationManage();
                a.setRegId(rs.getInt(1));
                a.setSubjectName(rs.getString(2));
                a.setRegTime(rs.getDate(3));
                a.setPackName(rs.getString(4));
                a.setCost(rs.getDouble(5));
                a.setValidFrom(rs.getDate(6));
                a.setValidTo(rs.getDate(7));
                a.setStatus(rs.getString(8));
                a.setNote(rs.getString(9));
                Registrations.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Registrations;

    }

    public int CountRegistrationByUserName(int subjectCateId, int userId) {
        ArrayList<RegistrationManage> Registrations = new ArrayList<>();
        try {
            String sql = "SELECT COUNT(r.regId) FROM Registration r\n"
                    + "INNER JOIN PricePackage p ON r.packId = p.packId\n"
                    + "INNER JOIN  [Subject] s ON s.subjectId = p.subjectId\n"
                    + "INNER JOIN CategorySubject c ON c.subjectId = s.subjectId\n"
                    + "INNER JOIN SubjectCategory sc ON c.cateId = sc.subjectCateId\n"
                    + "WHERE r.userId = ? and sc.subjectCateId = ?\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, subjectCateId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<RegistrationManage> GetAllRegistrationByUserNameWithPage(int subjectCateId, int userId, int page, int PAGE_SIZE) {
        ArrayList<RegistrationManage> Registrations = new ArrayList<>();
        try {
            String sql = "SELECT r.regId,s.subjectName,r.regTime,p.packName,r.cost,r.validFrom,r.validTo,r.[status],r.note FROM Registration r\n"
                    + "INNER JOIN PricePackage p ON r.packId = p.packId\n"
                    + "INNER JOIN  [Subject] s ON s.subjectId = p.subjectId\n"
                    + "INNER JOIN CategorySubject c ON c.subjectId = s.subjectId\n"
                    + "INNER JOIN SubjectCategory sc ON c.cateId = sc.subjectCateId\n"
                    + "WHERE r.userId = ? and sc.subjectCateId = ?\n"
                    + "order by r.regId offset (?-1)*? row fetch next ? rows only";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, subjectCateId);
            stm.setInt(3, page);
            stm.setInt(4, PAGE_SIZE);
            stm.setInt(5, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RegistrationManage a = new RegistrationManage();
                a.setRegId(rs.getInt(1));
                a.setSubjectName(rs.getString(2));
                a.setRegTime(rs.getDate(3));
                a.setPackName(rs.getString(4));
                a.setCost(rs.getDouble(5));
                a.setValidFrom(rs.getDate(6));
                a.setValidTo(rs.getDate(7));
                a.setStatus(rs.getString(8));
                a.setNote(rs.getString(9));
                Registrations.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Registrations;
    }

    public ArrayList<PricePackage> getAllPricePackage() {
        ArrayList<PricePackage> PricePackages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PricePackage p \n"
                    + "INNER JOIN [Subject] s ON p.subjectId = s.subjectId";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PricePackage a = new PricePackage();
                a.setPackId(rs.getInt(1));
                a.setPackName(rs.getString(2));
                a.setSubjectId(rs.getInt(3));
                a.setListPrice(rs.getFloat(5));
                a.setSalePrice(rs.getFloat(6));
                a.setSubjectName(rs.getString(9));

                PricePackages.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PricePackages;
    }

    public void upDateRegistration(int packId, int UserId, String note, int regId) {
        try {
            String sql = "UPDATE [Registration]\n"
                    + "   SET \n"
                    + "      [packId] = ?\n"
                    + "      ,[lastUpdatedBy] = ?\n"
                    + "      ,[note] = ?\n"
                    + "      \n"
                    + " WHERE regId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, packId);
            stm.setInt(2, UserId);
            stm.setString(3, note);
            stm.setInt(4, regId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteRegistration(int regId) {
        try {
            String sql = "DELETE FROM Registration WHERE regId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, regId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(registrationDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Subject> getRegistedSubject(int userId) throws Exception {
        SubjectDAO subjectDAO = new SubjectDAO();
        
        ArrayList<Subject> registedSubject = new ArrayList<>();
        String sql = "SELECT b.subjectId\n"
                + "  FROM [QuizSystem].[dbo].[Registration] as a "
                + "inner join [QuizSystem].[dbo].[PricePackage] as b "
                + "on a.packId = b.packId where a.userId = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                registedSubject.add(subjectDAO.getSubjectbyId(rs.getInt("subjectId")));
            }
        } catch (Exception ex) {
            throw ex;
        } 
        return registedSubject;
    }

    public static void main(String[] args) throws Exception {
        registrationDBcontext a = new registrationDBcontext();
        ArrayList<Subject> registedSubject = a.getRegistedSubject(1);
        for (Subject subject : registedSubject) {
            System.out.println(subject.getDimensions());
        }
    }

}
