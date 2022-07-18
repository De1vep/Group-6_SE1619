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
import model.Slider;

/**
 *
 * @author DPV
 */
public class SliderDAO extends DBContext {

    public ArrayList<Slider> getAllSlider() {
        ArrayList<Slider> allSlider = new ArrayList();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM [Slider]");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int sliderId = rs.getInt("sliderId");
                String sliderTitle = rs.getString("sliderTitle");
                String image = rs.getString("image");
                String link = rs.getString("link");
                String note = rs.getString("note");
                boolean status = rs.getBoolean("status");
                allSlider.add(new Slider(sliderId, sliderTitle, image, link, note, status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allSlider;
    }

    public int UpdateSlider(Slider s) {
        try {
            System.out.println(s.toString());
            PreparedStatement st = connection.prepareStatement("UPDATE [dbo].[Slider] SET [sliderTitle] = ? ,[image] = ? ,[link] = ? ,[note] = ? ,[status] = ? WHERE [sliderId] = ?");
            st.setString(1, s.getSliderTitle());
            st.setString(2, s.getImage());
            st.setString(3, s.getLink());
            st.setString(4, s.getNote());
            st.setBoolean(5, s.isStatus());
            st.setInt(6, s.getSliderId());
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public static void main(String[] args) {
        SliderDAO dao = new SliderDAO();
        System.out.println(dao.getAllSlider());
    }
}
