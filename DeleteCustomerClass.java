
package CustomerPackage;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;

public class DeleteCustomerClass {
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    String s;
    int deleted;
    JPanel jpn=new JPanel(new BorderLayout());
    Connection con=null;
    Statement stm=null;
    
    JFrame jfrm1=new JFrame("Delete Customer Details");
    JPanel jpan=new JPanel();
    JLabel phoneNoLabel=new JLabel("Phone Number");
    JButton jbn=new JButton("Delete");
    ResultSet rs=null;
    final JComboBox jComboBox1=new JComboBox();
    
    public DeleteCustomerClass()
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
         addComponents();
         addingToComboBox();
         delete();
         
    }
    public void setLayoutBoundaries()
    {   jpan.setLayout(null);
        jfrm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm1.setSize(700,300);
        phoneNoLabel.setBounds(100,50,100,25);
        jComboBox1.setBounds(250,50,100,25);
        
        
    }
    public void addComponents()
    {   jpan.add(phoneNoLabel);
        jComboBox1.setVisible(true);
        jpan.add(jComboBox1);
        jfrm1.add(jpan);
        
        jfrm1.setVisible(true);
        
    }
    public void addingToComboBox()
    {   
        try
        {
            stm=con.createStatement();
            rs = stm.executeQuery("select phoneNo from customer");
            while(rs.next()){
            //System.out.println("getting first value");
            String sc= rs.getString("phoneNo");
            System.out.println(sc);
            jComboBox1.addItem(sc);
            
            }
        
       
        s=String.valueOf(jComboBox1.getSelectedItem());
        
        System.out.println(s);
        rs.close();
        viewDetailsOfItem();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    public void viewDetailsOfItem()
    {   System.out.println("creating table");
        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {

            String sql = "Select * from customer where phoneNo='"+s+"'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement( md.getColumnName(i) );
            }
        while (rs.next()) {
                Vector row = new Vector(columns);
        for (int i = 1; i <= columns; i++){
                row.addElement( rs.getObject(i) );
        }
        data.addElement( row );
        }
        rs.close();
        stmt.close();
        }
        catch(Exception e){
                System.out.println(e);
        }
        JTable table = new JTable(data, columnNames)
        {
            public boolean isCellEditable(int row,int cloumns){
            return false;
            }
            
        };
        TableColumn col;
        for (int i = 0; i < table.getColumnCount(); i++) {
                col = table.getColumnModel().getColumn(i);
        col.setMaxWidth(300);
        }
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        jpn.add(scrollPane);
        scrollPane.setBounds(200,100,900,300);
        table.getTableHeader().setReorderingAllowed(false);
        
        jpn.setBounds(20,100,800,50);
        jpan.add(jpn);
        jbn.setBounds(250,200,80,30);
        jpan.add(jbn);
    }
    public void delete()
    { jbn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            { 
                try
                {   System.out.println("reached query");
                    stm=con.createStatement();
                    
                    String st="Delete from customer where phoneNo='"+s+"'";
                    stm.executeUpdate(st);
                    JOptionPane.showMessageDialog(null,"Customer Details Deleted");
                   
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Unable To Delete");
                }
            }
        
    });
    }
    public static void main(String args[])
    {
        new DeleteCustomerClass();
    }
    
}
