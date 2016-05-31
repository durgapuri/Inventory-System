
package CustomerPackage;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;

public class DeleteCustomerClass {
    
//String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    //String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    private String s;
    private int deleted;
    private JPanel jpn=new JPanel(new BorderLayout());
    private Connection con=null;
    private Statement stm=null;
    
    private JFrame jfrm1=new JFrame("Delete Customer Details");
    private JPanel jpan=new JPanel();
    private JLabel phoneNoLabel=new JLabel("Phone Number");
    private JButton jbn=new JButton("Delete");
    private ResultSet rs=null;
    private String [] cusDetails= new String[4];
    final private JComboBox jComboBox1=new JComboBox();
    private DefaultTableModel tblModel;
    private JTable table= new JTable(tblModel);
    private JScrollPane scrollPane = new JScrollPane(table);
    
    public DeleteCustomerClass(Connection con)
    {   
        this.con=con;
        setLayoutBoundaries();
    
        
        /* try
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
        }*/
        
         addComponents();
         addingToComboBox();
         delete();
         
    }
    public void setLayoutBoundaries()
    {   
        jpan.setLayout(null);
        jfrm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm1.setSize(700,300);
        phoneNoLabel.setBounds(100,50,150,25);
        jComboBox1.setBounds(200,50,100,25);
        jpn.setBounds(50,100,600,50);
        jbn.setBounds(400,50,100,25);
        scrollPane.setBounds(200,100,1200,400);
        
        
    }
    public void addComponents()
    {   
        jpan.add(phoneNoLabel);
        jComboBox1.setVisible(true);
        jpan.add(jComboBox1);
        jfrm1.add(jpan);
        jpan.add(jpn);
        jpan.add(jbn);
        jpn.add(scrollPane);
        table.getTableHeader().setReorderingAllowed(false);
        jfrm1.setLocationRelativeTo(null);   
        jfrm1.setVisible(true);
        
    }
    public void addingToComboBox()
    {   
        try
        {
            stm=con.createStatement();
            rs = stm.executeQuery("select phoneNo from customer");
            while(rs.next()){
            
            String sc= rs.getString("phoneNo");
            System.out.println(sc);
            jComboBox1.addItem(sc);
            
            }
        
       
        
         jComboBox1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.out.println("entered");
                s=String.valueOf(jComboBox1.getSelectedItem());
                
                 viewDetailsOfCustomer(s);
                        
                table.setModel(tblModel);
                tblModel.fireTableDataChanged();
            } 
        });
        System.out.println(s);
        rs.close();
        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    private void setColumns() {
       
        tblModel.setColumnCount(0);
        tblModel.addColumn("PHONE NO");
        tblModel.addColumn("CUSTOMER NAME");
        tblModel.addColumn("ADDRESS");
        tblModel.addColumn("EMAIL ID");
       }
    public void viewDetailsOfCustomer(String s)
    { 
       
        tblModel = (DefaultTableModel) table.getModel();
        setColumns();
        try {
              
            String sql ="Select * from customer where phoneNo="+s+""; 
                                                                                               
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
                                               
        while (rs.next()) {
                                                               
                tblModel.setRowCount(0);
                cusDetails[0] = rs.getString("phoneNo").trim();
                cusDetails[1] = rs.getString("cusName").trim();
                cusDetails[2] = rs.getString("address").trim();
                cusDetails[3] = rs.getString("emailId").trim();
           
                tblModel.insertRow(tblModel.getRowCount(),cusDetails);
            
        }
        rs.close();
        stmt.close();
       }catch(Exception e){
                System.out.println(e);
        }
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
                    jfrm1.dispose();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Unable To Delete");
                }
            }
        
    });
    }
   /*public static void main(String args[])
    {
        new DeleteCustomerClass();
    }*/
    
}
