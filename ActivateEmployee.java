
package EmployeePackage;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel.*;
public class ActivateEmployee {
   
   
    private Connection con=null;
    private Statement stm=null;
    
    private String s;
    private int deleted;
    private JPanel jpn=new JPanel(new BorderLayout());
    private String [] empDetails= new String[7];
    private JFrame jfrm1=new JFrame("Activate Employee");
    private JPanel jpan=new JPanel();
    private JLabel itemNameLabel=new JLabel("Employee Id");
    private JButton jbna=new JButton("Activate");
    private JButton jbnd=new JButton("Deactivate");
    private ResultSet rs=null;
    private final JComboBox jComboBox1=new JComboBox();
    private DefaultTableModel tblModel;
    private JTable table= new JTable(tblModel);
    private JScrollPane scrollPane = new JScrollPane(table);
    
    public ActivateEmployee(Connection con)
    {   
        this.con=con;
        System.out.println("prachee");
        setLayoutBoundaries();
        
        
        
         addComponents();
         addingToComboBox();
         update();
         
    }
    public void setLayoutBoundaries()
    {  
        jpan.setLayout(null);
        jfrm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm1.setSize(700,300);
        itemNameLabel.setBounds(100,50,150,25);
        jComboBox1.setBounds(200,50,100,25);
        jpn.setBounds(50,100,600,50);
        jbna.setBounds(400,50,100,25);
        jbnd.setBounds(500,50,100,25);
        scrollPane.setBounds(200,100,1200,400);
        
    }
    public void addComponents()
    {   
        jpan.add(itemNameLabel);
        jComboBox1.setVisible(true);
        jpan.add(jComboBox1);
        jfrm1.add(jpan);
        jpan.add(jpn);
        jpan.add(jbna);
        jpan.add(jbnd);
        jpn.add(scrollPane);
        table.getTableHeader().setReorderingAllowed(false);
        jfrm1.setLocationRelativeTo(null);   
        jfrm1.setVisible(true);
    }
    public void comboaction()
    {
    
        jComboBox1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.out.println("entered");
                s=String.valueOf(jComboBox1.getSelectedItem());
       
                viewDetailsOfItem(s);
                        
                table.setModel(tblModel);
                tblModel.fireTableDataChanged();
            } 
        });}
    public void addingToComboBox()
    {   
        try
        {
            stm=con.createStatement();
            rs = stm.executeQuery("select empId from masterEmployee where ActiveStatus="+"'Deactivated'");
            while(rs.next()){
            
            String sc= rs.getString("empId");
            System.out.println(sc);
            jComboBox1.addItem(sc);
            
            }
  
        comboaction();
        rs.close();
        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    private void setColumns() {
        
        tblModel.setColumnCount(0);
        tblModel.addColumn("EMPLOYEE Id");
        tblModel.addColumn("FIRST NAME");
        tblModel.addColumn("MIDDLE NAME");
        tblModel.addColumn("LAST NAME");
        tblModel.addColumn("ADDRESS");
        tblModel.addColumn("PHONE NUMBER");
        tblModel.addColumn("EMAIL ID");
       }
    public void viewDetailsOfItem(String s)
    {  
        tblModel = (DefaultTableModel) table.getModel();
        setColumns();
        try {
                
            String sql ="Select * from employee where empId="+s+""; 
                                                                                               
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
                                               
        while (rs.next()) {
                                                               
            tblModel.setRowCount(0);
            empDetails[0] = rs.getString("empId").trim();
            empDetails[1] = rs.getString("fName").trim();
            empDetails[3] = rs.getString("lName").trim();
            empDetails[4] = rs.getString("address").trim();
            empDetails[5] = rs.getString("phoneNo").trim();
            empDetails[3] = rs.getString("emailId").trim();
           
            tblModel.insertRow(tblModel.getRowCount(),empDetails);
            
        }
        rs.close();
        stmt.close();
       }catch(Exception e){
                System.out.println(e);
        }
        
    }
    public void update()
    { jbna.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            { 
                try
                {   System.out.println("reached query");
                    stm=con.createStatement();
                    System.out.println(s);
                    
                    PreparedStatement ps=con.prepareStatement("UPDATE masterEmployee SET ActiveStatus = ?"+"WHERE empId = ? ");
                     ps.setString(1,"Activated");
                     ps.setString(2,s);
                   
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Employee Activated");
                    jfrm1.dispose();
                   
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Unable To Activate");
                }
            }
        
    });
    
    }
   
}

