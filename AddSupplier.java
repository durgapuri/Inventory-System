
package SupplierPackage;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddSupplier {

    private Connection con=null;
    private JFrame jfrm1=new JFrame("Add New Supplier");
    private JPanel jpan=new JPanel();
    private JLabel supNameLabel=new JLabel("Supplier Name");
    private JLabel supAddrLabel=new JLabel("Address");
    private JLabel supContactLabel=new JLabel("Contact No");
    private JTextField textsupName = new JTextField(10);
    private JTextField textsupAddr = new JTextField(10);
    private JTextField textsupContact = new JTextField(10);
    private JButton jbn=new JButton("ADD");
        
		
		
    public AddSupplier(Connection con)
    {
        this.con=con;
        setLayoutBoundaries(); 
        addingItemFunction();
        addComponents();
        
    }
	
	
 public void addingItemFunction()
    {                   

        jbn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   
                if(supplierAdded()==1)
                {
                   JOptionPane.showMessageDialog(null,"Supplier Details Added");
                   jfrm1.dispose();
                }
                else
               {  
                    
               }
            }
             
        });   
    }
	
	
	
	
    public void setLayoutBoundaries()
    {  
        jpan.setLayout(null);
        jfrm1.setSize(500,500);
        jfrm1.setLocationRelativeTo(null);
       
        supNameLabel.setBounds(50,100,100,25);
        supAddrLabel.setBounds(50,130,100,25);
        supContactLabel.setBounds(50,240,150,25);
        textsupName.setBounds(200,100,150,25);
        textsupAddr.setBounds(200,130,150,100);
        textsupContact.setBounds(200,240,150,25);
        jbn.setBounds(300,330,100,30);
    }
    public int supplierAdded()
    {   
        Statement stm=null;
        ResultSet rs=null;
        String sql=null;
        int count=0;
        try
        {
            stm=con.createStatement();
            String supName=textsupName.getText().trim();
            String supAddr=textsupAddr.getText().trim();
            String supContact=textsupContact.getText().trim();
            if(supName.isEmpty()|| supAddr.isEmpty() || supContact.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"All details are mandatory.Please fill the details","",JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            
             if(verifyPhoneNo(textsupContact.getText().trim()) ){
                   
                   supContact=textsupContact.getText().trim();
                   
            }
             else{
                 return 0;
             }
            System.out.println("values taken");
            sql="INSERT INTO `supplierDetails`(supName,supAddress,supContact) VALUES ('"+supName+"','"+supAddr+"','"+supContact+"')";
            
            System.out.println(sql);
            stm.executeUpdate(sql);
            
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return 0;
        }
    }
	
	
	
	
    boolean verifyPhoneNo(String No){
		if(!(No.length()==10)){
            JOptionPane.showMessageDialog(null,"Incorrect Phone No : Phone no must be of 10 digit","Wrong Phone No",JOptionPane.ERROR_MESSAGE);
            return false;
		}
	
		if(!(No.chars().allMatch( Character::isDigit ))){
            JOptionPane.showMessageDialog(null,"Incorrect Phone No : Phone no must be only numeric","Wrong Phone No",JOptionPane.ERROR_MESSAGE);
            return false;
		}
		return true;
	}
	
	
	
	
    public void addComponents()
    {
        jpan.add(supNameLabel);
        jpan.add(supAddrLabel);
        jpan.add(supContactLabel);
        jpan.add(textsupName);
        jpan.add(textsupAddr);
        jpan.add(textsupContact);
        jpan.add(jbn);
        jfrm1.add(jpan);
        jfrm1.setVisible(true);
        
    }
    
}
