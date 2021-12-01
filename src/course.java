
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class course {
    
    
    
    public void insertUpdateDeleteStudent(char operation, Integer id, String cname,Integer time) 
    {

        Connection con = SqlConnection.getConnection();
        PreparedStatement ps;
        //i for insert
        if(operation=='i'){
            try {
             ps= con.prepareStatement("INSERT INTO `course`(`Course Name`, `Course Time (Days)`) VALUES (?,?)");
             ps.setString(1, cname);
             ps.setInt(2, time);
             
             
             if(ps.executeUpdate() > 0){
                 JOptionPane.showMessageDialog(null, "New Course Added");
             }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(operation=='u')//u for update
        {
            try {
             ps= con.prepareStatement("UPDATE `course` SET `Course Name`=?,`Course Time (Days)`=? WHERE `Course ID`=?");
             ps.setString(1, cname);
             ps.setInt(2, time);
             ps.setInt(3, id);
             
             if(ps.executeUpdate() > 0){
                 JOptionPane.showMessageDialog(null, "Course Data Updated");
             }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(operation=='d')//d for delete
        {
            try {
             ps= con.prepareStatement("DELETE FROM `course` WHERE `Course ID`=?");
             
             ps.setInt(1, id);
             if(ps.executeUpdate() > 0){
                 JOptionPane.showMessageDialog(null, "Course Deleted");
             }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
        

    
    public boolean isCourseExit(String courseName){
        
        boolean isExist = false;
        Connection con = SqlConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `course` WHERE `Course Name`= ?");
            ps.setString(1, courseName);
            
            ResultSet rs = ps.executeQuery();
            
            
            
            
           if(rs.next()){
              isExist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }
    public void fillCourseJtable(JTable table)
    {
        Connection con = SqlConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `course`");
            
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[3];
                row[0]= rs.getInt(1);
                row[1]= rs.getString(2);
                row[2]= rs.getInt(3);
                
                
                model.addRow(row);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
