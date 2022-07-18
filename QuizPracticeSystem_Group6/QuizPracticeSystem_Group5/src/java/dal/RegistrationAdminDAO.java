/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PricePackage;
import model.Registration;
import model.RegistrationAdmin;
import model.RegistrationManage;
import model.Subject;
import model.User;

/**
 *
 * @author lyLy
 */
public class RegistrationAdminDAO extends DBContext {

    public static final String SELECT = "SELECT [regId] ,[userId] ,[regTime] ,[packId] ,[cost] ,[validFrom] ,[validTo] ,[lastUpdatedBy] ,[note] ,[status] FROM [dbo].[Registration]";
    public static final String INSERT = "INSERT INTO [dbo].[Registration] ([userId] ,[regTime] ,[packId] ,[cost] ,[validFrom] ,[validTo] ,[lastUpdatedBy] ,[note] ,[status]) VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE = "UPDATE [dbo].[Registration] SET [userId] = ? ,[regTime] = ? ,[packId] = ? ,[cost] = ? ,[validFrom] = ? ,[validTo] = ? ,[lastUpdatedBy] = ? ,[note] = ? ,[status] = ? WHERE [RegId] = ?";
    public static final String DELETE = "UPDATE [dbo].[Registration] SET [status] = 0 WHERE [Id] = ?";

    public ArrayList<Registration> GetAll() {
        ArrayList<Registration> al = new ArrayList<>();
        try {
            String query = SELECT;
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                r.setCost(rs.getDouble("cost"));
                r.setLastUpdatedBy(rs.getInt("lastUpdatedBy"));
                r.setNote(rs.getString("note"));
                r.setPackId(rs.getInt("packId"));
                r.setRegId(rs.getInt("RegId"));
                r.setRegTime(rs.getDate("regTime"));
                r.setStatus(rs.getBoolean("status"));
                r.setUserId(rs.getInt("userId"));
                r.setValidFrom(rs.getDate("validFrom"));
                r.setValidTo(rs.getDate("validTo"));
                al.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return al;
    }

    public int Insert(Registration r) {
        int row_effects = 0;
        try {
            String query = INSERT;
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, r.getUserId());
            st.setDate(2, (Date) r.getRegTime());
            st.setInt(3, r.getPackId());
            st.setDouble(4, r.getCost());
            st.setDate(5, (Date) r.getValidFrom());
            st.setDate(6, (Date) r.getValidTo());
            st.setInt(7, r.getLastUpdatedBy());
            st.setString(8, r.getNote());
            st.setBoolean(9, r.isStatus());
            row_effects = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (row_effects > 0) {
            System.out.println("insert: " + r.toString());
        }
        return row_effects;
    }

    public ArrayList<Registration> GetById(int id) {
        ArrayList<Registration> al = new ArrayList<>();
        try {
            String query = SELECT + " WHERE [RegId] = ?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Registration r = new Registration();
                r.setCost(rs.getDouble("cost"));
                r.setLastUpdatedBy(rs.getInt("lastUpdatedBy"));
                r.setNote(rs.getString("note"));
                r.setPackId(rs.getInt("packId"));
                r.setRegId(rs.getInt("RegId"));
                r.setRegTime(rs.getDate("regTime"));
                r.setStatus(rs.getBoolean("status"));
                r.setUserId(rs.getInt("userId"));
                r.setValidFrom(rs.getDate("validFrom"));
                r.setValidTo(rs.getDate("validTo"));
                al.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return al;
    }

    public int Update(Registration r) {
        int row_effects = 0;
        try {
            String query = UPDATE;
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, r.getUserId());
            st.setDate(2, (Date) r.getRegTime());
            st.setInt(3, r.getPackId());
            st.setDouble(4, r.getCost());
            st.setDate(5, (Date) r.getValidFrom());
            st.setDate(6, (Date) r.getValidTo());
            st.setInt(7, r.getLastUpdatedBy());
            st.setString(8, r.getNote());
            st.setBoolean(9, r.isStatus());
            st.setInt(10, r.getRegId());
            row_effects = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (row_effects > 0) {
            System.out.println("update: " + r.toString());
        }
        return row_effects;
    }

    public ArrayList<RegistrationAdmin> getAllRegistration() {
        ArrayList<RegistrationAdmin> Registrations = new ArrayList<>();
        try {
            UserDAO uDAO= new UserDAO();
            SubjectDAO subDAO=new SubjectDAO();
            PricePackageDAO pacDAO= new PricePackageDAO();
            ArrayList<User> uList = uDAO.getAllUser();      
            ArrayList<Subject> subList = subDAO.getAllSubject();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Registration");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RegistrationAdmin a = new RegistrationAdmin();
                a.setRegId(rs.getInt(1));
                for(User i:uList){
                    if(i.getUserId()==rs.getInt(2)){
                        a.setUser(i);
                        break;
                    }
                }
                a.setPack(pacDAO.getAllPricePackagesById(rs.getInt("packId")));
                for(Subject i : subList){
                    if(i.getSubjectId()==pacDAO.getAllPricePackagesById(rs.getInt("packId")).getSubjectId()){
                        a.setSubject(i);
                        break;
                    }
                }
                a.setRegTime(rs.getDate("regTime"));
                
                a.setCost(rs.getDouble("cost"));
                a.setValidFrom(rs.getDate("validFrom"));
                a.setValidTo(rs.getDate("validTo"));
                
                for(User i: uList){
                    if( i.getUserId()==rs.getInt("lastUpdatedBy")){
                        a.setLastUpdatedBy(i);
                        break;
                    }
                }
                
                a.setStatus(rs.getBoolean("status"));
                a.setNote(rs.getString("note"));
                Registrations.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationAdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Registrations;
    }

}
