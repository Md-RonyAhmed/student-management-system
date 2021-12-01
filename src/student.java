
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class student {

    public void insertUpdateDeleteStudent(char operation, Integer id, String fname, String lname, String department,String sex, String bdate, String phone, String email) 
    {

        Connection con = SqlConnection.getConnection();
        PreparedStatement ps;
        //i for insert
        if(operation=='i'){
            try {
             ps= con.prepareStatement("INSERT INTO student(First_name, Last_name, Department, Sex, Birth_date, Phone, Email) VALUES (?, ?, ?, ?, ?, ?, ?)");
             ps.setString(1, fname);
             ps.setString(2, lname);
             ps.setString(3, department);
             ps.setString(4, sex);
             ps.setString(5, bdate);
             ps.setString(6, phone);
             ps.setString(7, email);
             
             if(ps.executeUpdate() > 0){
                 JOptionPane.showMessageDialog(null, "New Student Added");
             }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(operation=='u')//u for update
        {
            try {
             ps= con.prepareStatement("UPDATE `student` SET `First_name`= ?,`Last_name`= ?,`Department`= ?,`Sex`= ?,`Birth_date`= ?,`Phone`= ?,`Email`= ? WHERE `Student ID`= ? ");
             ps.setString(1, fname);
             ps.setString(2, lname);
             ps.setString(3, department);
             ps.setString(4, sex);
             ps.setString(5, bdate);
             ps.setString(6, phone);
             ps.setString(7, email);
             ps.setInt(8, id);
             if(ps.executeUpdate() > 0){
                 JOptionPane.showMessageDialog(null, "Student Data Updated");
             }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(operation=='d')//d for delete
        {
            try {
             ps= con.prepareStatement("DELETE FROM `student` WHERE `Student ID`=?");
             
             ps.setInt(1, id);
             if(ps.executeUpdate() > 0){
                 JOptionPane.showMessageDialog(null, "Student Data Deleted");
             }
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void fillStudentJtable(JTable table, String valueToSearch)
    {
        Connection con = SqlConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `student` WHERE  CONCAT(`Student ID`,`First_name`,`Last_name`,`Department`,`Sex`,`Birth_date`,`Phone`,`Email`)LIKE ?");
            ps.setString(1, "%"+valueToSearch+"%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[8];
                row[0]= rs.getInt(1);
                row[1]= rs.getString(2);
                row[2]= rs.getString(3);
                row[3]= rs.getString(4);
                row[4]= rs.getString(5);
                row[5]= rs.getString(6);
                row[6]= rs.getString(7);
                row[7]= rs.getString(8);
                
                model.addRow(row);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
