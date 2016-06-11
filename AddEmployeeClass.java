package EmployeePackage;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployeeClass {

    private Connection con=null;
    private JFrame jfrm1=new JFrame("Add New Employee");
    private JPanel jpan=new JPanel();
	
    private JLabel empIdLabel=new JLabel("Employee ID*"),
			       empfNameLabel=new JLabel("First Name*"),
				   empmNameLabel=new JLabel("Middle Name"),
				   emplNameLabel=new JLabel("LastName"),
				   addressLabel=new JLabel("Address*"),
				   phoneNoLabel=new JLabel("Phone No*"),
				   emailLabel=new JLabel("Email ID*");
    
    private JTextField textempId = new JTextField(10),
					   textempfName = new JTextField(10),
					   textempmName = new JTextField(10),
			           textemplName = new JTextField(10),
					   textaddress = new JTextField(10),
					   textphoneNo = new JTextField(10),
					   textemail = new JTextField(10);
					   
    private JButton jbn=new JButton("ADD");
    
	public AddEmployeeClass(){}
    public AddEmployeeClass(Connection con)
    {
		this.con=con;
       setLayoutBoundaries(); 
       addingEmployeeFunction();
       addComponents();  
        
    }
	
	
    public void addingEmployeeFunction()
    {                   

        jbn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   System.out.println("button working");
                if(EmployeeAdded()==1)
                {
                   JOptionPane.showMessageDialog(null,"Employee details added");
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
      
        empIdLabel.setBounds(50,100,100,25);
        empfNameLabel.setBounds(50,130,100,25);
        empmNameLabel.setBounds(50,160,150,25);
        emplNameLabel.setBounds(50, 190, 100, 25);
        addressLabel.setBounds(50, 220, 100, 25);
        phoneNoLabel.setBounds(50,250,100,25);
        emailLabel.setBounds(50,280,100,25);
        textempId.setBounds(200,100,150,25);
        textempfName.setBounds(200,130,150,25);
        textempmName.setBounds(200, 160, 150, 25);
        textemplName.setBounds(200,190,150,25);
        textaddress.setBounds(200,220,150,25);
        textphoneNo.setBounds(200,250,150,25);
        textemail.setBounds(200,280,150,25);
        jbn.setBounds(300,320,100,30);
    }
	
	
	

    public int EmployeeAdded()
    {   Statement stm=null;
        ResultSet rs=null;
        String sql=null;
        int count=0;
        try
        {
            stm=con.createStatement();
            String id=textempId.getText().trim();
            String fName=textempfName.getText().trim();
            String mName=textempmName.getText().trim();
            String lName=textemplName.getText().trim();
            String address=textaddress.getText().trim();
           String phoneNo="";
            String email="";
            System.out.println("values taken");
            if(id.isEmpty() || fName.isEmpty() || address.isEmpty() || phoneNo.isEmpty() || email.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"* fields are mandatory.Please fill the details.");
                return 0;
            }
             if(verifyPhoneNo(phoneNoLabel.getText().trim()) && verifyEmailId(emailLabel.getText().trim())){
                   
                   phoneNo=phoneNoLabel.getText().trim();
                   email=emailLabel.getText().trim();
            }
            sql="INSERT INTO `employee`(empId,fName,mName,lName,address,phoneNo,emailId) VALUES ('"+id+"','"+fName+"','"+mName+"','"+lName+"','"+address+"','"+phoneNo+"','"+email+"')";
            
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
        boolean verifyEmailId(String emailid){
	if(!(org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(emailid))){ 
		JOptionPane.showMessageDialog(null,"Incorrect Email ID: Please enter correct emailid"   
									,"Email ID",JOptionPane.ERROR_MESSAGE);
		return false;
	}
	return true;
}
	
	
	
    public void addComponents()
    {
        jpan.add(empIdLabel);
        jpan.add(empfNameLabel);
        jpan.add(empmNameLabel);
        jpan.add(emplNameLabel);
        jpan.add(addressLabel);
        jpan.add(phoneNoLabel);
        jpan.add(emailLabel);
        jpan.add(textempId);
        jpan.add(textempfName);
        jpan.add(textempmName);
        jpan.add(textemplName);
        jpan.add(textaddress);
        jpan.add(textphoneNo);
        jpan.add(textemail);
        jpan.add(jbn);
        jfrm1.add(jpan);
        jfrm1.setVisible(true);
        
    }
    
    
}
