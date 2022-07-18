/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PricePackage;

/**
 *
 * @author DPV
 */
public class PricePackageDAO extends DBContext {

    public ArrayList<PricePackage> getPricePackageBySubjectIdDPV(int subjectId) {
        ArrayList<PricePackage> listPricePackage = new ArrayList<>();
        try {

            String sql = "SELECT * FROM PricePackage WHERE status = 1 AND subjectId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PricePackage s = new PricePackage();
                s.setPackId(rs.getInt("packId"));
                s.setPackName(rs.getString("packName"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setDuration(rs.getInt("duration"));
                s.setListPrice(rs.getFloat("listPrice"));
                s.setSalePrice(rs.getFloat("salePrice"));
                s.setStatus(rs.getBoolean("status"));
                listPricePackage.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPricePackage;
    }

    //minh
    public ArrayList<PricePackage> getListSubjectByKeyword(int page, int page_size, ArrayList<PricePackage> ListSubjectByKeyword) {
        int start, end;
        start = (page - 1) * page_size;
        if (page * page_size > ListSubjectByKeyword.size()) {
            end = ListSubjectByKeyword.size();
        } else {
            end = page * page_size;
        }
        ArrayList<PricePackage> listBlogPaging = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listBlogPaging.add(ListSubjectByKeyword.get(i));
        }
        return listBlogPaging;
    }
    
    public int addPricePackage(PricePackage updatePricePackage) throws Exception {
        String sql = "INSERT INTO [dbo].[PricePackage]\n"
                + "           ([packName]\n"
                + "           ,[subjectId]\n"
                + "           ,[duration]\n"
                + "           ,[listPrice]\n"
                + "           ,[salePrice]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, updatePricePackage.getPackName());
            stm.setInt(2, updatePricePackage.getSubjectId());
            stm.setInt(3, updatePricePackage.getDuration());
            stm.setFloat(4, updatePricePackage.getListPrice());
            stm.setFloat(5, updatePricePackage.getSalePrice());
            stm.setBoolean(6, updatePricePackage.isStatus());
            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }

    public int deletePricePackage(int packId) throws Exception {
        String sql = "DELETE FROM [dbo].[PricePackage]\n"
                + "      WHERE packId = ?";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, packId);

            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }

    public int editPricePackage(int packId, PricePackage updatePricePackage) throws Exception {
        String sql = "UPDATE [dbo].[PricePackage]\n"
                + "   SET [packName] = ?\n"
                + "      ,[subjectId] = ?\n"
                + "      ,[duration] = ?\n"
                + "      ,[listPrice] = ?\n"
                + "      ,[salePrice] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE packId = ?";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, updatePricePackage.getPackName());
            stm.setInt(2, updatePricePackage.getSubjectId());
            stm.setInt(3, updatePricePackage.getDuration());
            stm.setFloat(4, updatePricePackage.getListPrice());
            stm.setFloat(5, updatePricePackage.getSalePrice());
            stm.setBoolean(6, updatePricePackage.isStatus());
            stm.setInt(7, packId);
            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }
    
    public int deletePricePackageBySubId(int subjectId) throws Exception {
        String sql = "DELETE FROM [dbo].[PricePackage]\n"
                + "      WHERE subjectId = ?";
        int check = 0;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectId);

            check = stm.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return check;
    }
    
    public PricePackage getAllPricePackagesById(int pacID) throws Exception {
        try {
            String sql = "SELECT * from PricePackage where packId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pacID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PricePackage s = new PricePackage();
                s.setPackId(rs.getInt("packId"));
                s.setPackName(rs.getString("packName"));
                s.setSubjectId(rs.getInt("subjectId"));
                s.setDuration(rs.getInt("duration"));
                s.setListPrice(rs.getFloat("listPrice"));
                s.setSalePrice(rs.getFloat("salePrice"));
                s.setStatus(rs.getBoolean("status"));
                return s;
            }
        } catch (Exception ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    public static void main(String[] args) throws Exception {
        PricePackageDAO dao =  new PricePackageDAO();
        SubjectDAO s = new SubjectDAO();
        System.out.println(dao.getListSubjectByKeyword(1, 2, s.getSubjectbyId(1).getPricePackage()));
    }

}
