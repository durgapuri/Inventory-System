
package ItemPackage;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class DeleteItemClass {
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    Connection con=null;
    Statement stm=null;
    //DefaultComboBoxModel model = new DefaultComboBoxModel();
    //model.setSelectedItem(null);
    JFrame jfrm1=new JFrame("Delete Item");
    JPanel jpan=new JPanel();
    JLabel itemNameLabel=new JLabel("Item Name");
    ResultSet rs=null;
    final JComboBox jComboBox1=new JComboBox();
    //jComboBox1.setEditable(true);
   //jComboBox1.setSelectedItem(null);
    DeleteItemClass()
    {   setLayoutBoundaries();
        
         try
    {
            Class.forName(driver);
            con=DriverManager.getConnection(source);
            System.out.println("connected successfully");
            
    }
        catch(ClassNotFoundException e)
        {   System.err.println("Failed To Load Driver");
            System.out.println(e);
            System.exit(1);
        }
        catch(SQLException e)
        {   System.err.println("Unable To Connect");
            System.out.println(e);
            System.exit(1);
        }
         System.out.println("reached");
         deleting();
         addComponents();
        
    }
    public void setLayoutBoundaries()
    {   jpan.setLayout(null);
        jfrm1.setSize(500,300);
        itemNameLabel.setBounds(50,100,100,25);
        jComboBox1.setBounds(50,200,100,25);
        
    }
    public void addComponents()
    {   jpan.add(itemNameLabel);
        jComboBox1.setVisible(true);
        jpan.add(jComboBox1);
        jfrm1.add(jpan);
        jfrm1.setVisible(true);
        
    }
    public void deleting()
    {   //jComboBox1.addItem("hii");
       // jComboBox1.setSelectedIndex();
        try
        {
            stm=con.createStatement();
            rs = stm.executeQuery("select itemName from item");
            while(rs.next()){
            //System.out.println("getting first value");
            String s= rs.getString("itemName");
            System.out.println(s);
            jComboBox1.addItem(s);
            
            
        }
        
        //jComboBox1.setEditable(true);
        //String s=String.valueOf(jComboBox1.getSelectedItem());
        //System.out.println(s);
       // jComboBox1.setSelectedItem(jComboBox1.getSelectedItem());
       //jComboBox1.setEnabled(true);
        //jComboBox1.setSelectedIndex(-1);
        String s=String.valueOf(jComboBox1.getSelectedItem());
        //jComboBox1.setSelectedIndex(jComboBox1.getSelectedItem());
        System.out.println(s);
        rs.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    public static void main(String args[])
    {
        new DeleteItemClass();
    }
    
}
