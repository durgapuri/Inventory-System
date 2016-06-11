
package SupplierPackage;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel.*;
public class DeleteSupplierClass {

    private String s;
    private int deleted;
    private JPanel jpn=new JPanel(new BorderLayout());
    private Connection con=null;
    private Statement stm=null;
    
    private JFrame jfrm1=new JFrame("Delete Supplier Details");
    private JPanel jpan=new JPanel();
    private JLabel supNameLabel=new JLabel("Supplier Id");
    private JButton jbn=new JButton("Delete");
    private ResultSet rs=null;
    private String [] supDetails= new String[4];
    private final JComboBox jComboBox1=new JComboBox();
    private DefaultTableModel tblModel;
    private JTable table= new JTable(tblModel);
    private JScrollPane scrollPane = new JScrollPane(table);
    
	
    public DeleteSupplierClass(Connection con)
    {   
        this.con=con;
        setLayoutBoundaries();
        addComponents();
        addingToComboBox();
        delete();
         
    }
    public void setLayoutBoundaries()
    {   
        jpan.setLayout(null);
        jfrm1.setSize(700,300);
        supNameLabel.setBounds(100,50,150,25);
        jComboBox1.setBounds(200,50,100,25);
        jpn.setBounds(50,100,600,50);
        jbn.setBounds(400,50,100,25);
        scrollPane.setBounds(200,100,1200,400);
        
        
    }
    public void addComponents()
    {   
        jpan.add(supNameLabel);
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
            rs = stm.executeQuery("select supId from supplierDetails");
            while(rs.next()){
            
            String sc= rs.getString("supId");
            System.out.println(sc);
            jComboBox1.addItem(sc);
            
            }
        
        
        
        jComboBox1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.out.println("entered");
                s=String.valueOf(jComboBox1.getSelectedItem());
                viewDetailsOfSupplier(s);
                        
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
        tblModel.addColumn("SUPPLIER Id");
        tblModel.addColumn("SUPPLIER NAME");
        
        tblModel.addColumn("ADDRESS");
        tblModel.addColumn("PHONE NUMBER");
       
       }
	   
	   
	   
	   
	   
	   
    public void viewDetailsOfSupplier(String s)
    {  tblModel = (DefaultTableModel) table.getModel();
        setColumns();
        try {
               
            String sql ="Select * from supplierDetails where supId='"+s+"'"; 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
                                              
        while (rs.next()) {
                                                               
            tblModel.setRowCount(0);
            supDetails[0] = rs.getString("supId").trim();
            supDetails[1] = rs.getString("supName").trim();
            supDetails[2] = rs.getString("supAddress").trim();
            supDetails[3] = rs.getString("supContact").trim();
            tblModel.insertRow(tblModel.getRowCount(),supDetails);
            
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
                {  
                    
                    stm=con.createStatement();
                    String st="Delete from supplierDetails where supId='"+s+"'";
                    stm.executeUpdate(st);
                    JOptionPane.showMessageDialog(null,"Supplier Details Deleted");
                    jfrm1.dispose();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Unable To Delete","",JOptionPane.ERROR_MESSAGE);
                }
            }
        
    });
    }
    
}
