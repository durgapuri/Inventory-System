
package EmployeePackage;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployeeClass {
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    Connection con=null;
    JFrame jfrm1=new JFrame("Add New Item");
    JPanel jpan=new JPanel();
    JLabel empIdLabel=new JLabel("Employee ID");
    JLabel empfNameLabel=new JLabel("First Name");
    JLabel empmNameLabel=new JLabel("Middle Name");
    JLabel emplNameLabel=new JLabel("LastName");
    JLabel addressLabel=new JLabel("Address");
    JLabel phoneNoLabel=new JLabel("Phone No");
    JLabel emailLabel=new JLabel("Email ID");
    
    JTextField textempId = new JTextField(10);
    JTextField textempfName = new JTextField(10);
    JTextField textempmName = new JTextField(10);
    JTextField textemplName = new JTextField(10);
    JTextField textaddress = new JTextField(10);
    JTextField textphoneNo = new JTextField(10);
    JTextField textemail = new JTextField(10);
    JButton jbn=new JButton("ADD");
        
    public AddEmployeeClass()
    {
       setLayoutBoundaries(); 
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
       addingItemFunction();
       addComponents();
        
        
        
    }
    public void addingItemFunction()
    {                   

        jbn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   System.out.println("button working");
                if(ItemAdded()==1)
                {
                   JOptionPane.showMessageDialog(null,"Item Added");
                   jfrm1.dispose();
                }
                else
               {   JOptionPane.showMessageDialog(null,"Item not Added");
               }
            }
        });
    }
    public void setLayoutBoundaries()
    {  
        jpan.setLayout(null);
        jfrm1.setSize(500,500);
        jfrm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    public int ItemAdded()
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
            String phoneNo=textphoneNo.getText().trim();
            String email=textemail.getText().trim();
            System.out.println("values taken");
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
    /*public static void main(String args[])
    {
        new AddEmployeeClass();
    }*/
    
    
}
