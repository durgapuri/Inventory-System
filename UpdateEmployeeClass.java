

package EmployeePackage;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class UpdateEmployeeClass {
    

    private Connection con=null;
    private JFrame jfrm1=new JFrame("Update Employee details");
    private JPanel jpan=new JPanel();
    private JLabel empIdLabel=new JLabel("Employee ID");
    private JLabel empfNameLabel=new JLabel("First Name*");
    private JLabel empmNameLabel=new JLabel("Middle Name");
    private JLabel emplNameLabel=new JLabel("LastName");
    private JLabel addressLabel=new JLabel("Address*");
    private JLabel phoneNoLabel=new JLabel("Phone No*");
    private JLabel emailLabel=new JLabel("Email ID*");
    
    private JComboBox jcom=new JComboBox();
    private JTextField textempfName = new JTextField(10);
    private JTextField textempmName = new JTextField(10);
    private JTextField textemplName = new JTextField(10);
    private JTextField textaddress = new JTextField(10);
    private JTextField textphoneNo = new JTextField(10);
    private JTextField textemail = new JTextField(10);
    private String x; 
    private JButton jbn=new JButton("UPDATE");
    
	public UpdateEmployeeClass(){}
    public UpdateEmployeeClass(Connection con)
    {
		this.con=con;
       setLayoutBoundaries(); 
       addComponents();    
       combox();
       updatingEmployeeDetailsFunction();
       
        
        
        
    }
    public void combox(){
        
        
        
        
        try
        {
            Statement stm=con.createStatement();
            ResultSet rs = stm.executeQuery("select empId from employee");
            while(rs.next()){
            System.out.println("getting first value");
            String s= rs.getString("empId");
            System.out.println(s);
            jcom.addItem(s);
            
            
        }
            
           
             jcom.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.out.println("entered");
                x=String.valueOf(jcom.getSelectedItem());
                 fillDetail(x);
             
                        
                
            } 
              });
            
            
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
	
	
	
	
    public void fillDetail(String x){
        try{
        Statement st = con.createStatement();
        
       
         String fName = "Select fName from employee where empId= '" +x+ "'";
                ResultSet rs = st.executeQuery(fName);
                while(rs.next()){
                    String value = rs.getString("fName");
                    textempfName.setText(value); 
                     
                }
            String mName = "Select mName from employee where empId= '" +x+ "'";
                ResultSet rs1 = st.executeQuery(mName);
                while(rs1.next()){
                    String value = rs1.getString("mName");
                    textempmName.setText(value); 
                     
                }
            String lName = "Select lName from employee where empId= '" +x+ "'";
                ResultSet rs2 = st.executeQuery(lName);
                while(rs2.next()){
                    String value = rs2.getString("lName");
                    textemplName.setText(value); 
                     
                }
        String address = "Select address from employee where empId= '" +x+ "'";
                ResultSet rs3 = st.executeQuery(address);
                while(rs3.next()){
                    String value = rs3.getString("address");
                    textaddress.setText(value); 
                }
                
        String phoneNo="Select phoneNo from employee where empId='"+x+"'";
                ResultSet rs4=st.executeQuery(phoneNo);
                while(rs4.next()){
                    String value = rs4.getString("phoneNo");
                    textphoneNo.setText(value); 
                }
        String email = "Select emailId from employee where empId= '" +x+ "'";
                ResultSet rs5 = st.executeQuery(email);
                while(rs5.next()){
                    String value = rs5.getString("emailId");
                    textemail.setText(value); 
                }
               System.out.println("jcud");
             
             
            }catch(Exception ae){

            }
        
    }
	
public void updatingEmployeeDetailsFunction()
    {                   

        jbn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   System.out.println("button working");
                if(EmployeeDetailsUpdated()==1)
                {
                   JOptionPane.showMessageDialog(null,"Employee details Updated");
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
        jfrm1.setSize(500,400);
        jfrm1.add(jpan);
        jfrm1.setVisible(true);
        jfrm1.setLocationRelativeTo(null);
       
        empIdLabel.setBounds(50,100,100,25);
        empfNameLabel.setBounds(50,130,100,25);
        empmNameLabel.setBounds(50,160,150,25);
        emplNameLabel.setBounds(50, 190, 100, 25);
        addressLabel.setBounds(50, 220, 100, 25);
        phoneNoLabel.setBounds(50,250,100,25);
        emailLabel.setBounds(50,280,100,25);
        jcom.setBounds(200,100,150,25);
        textempfName.setBounds(200,130,150,25);
        textempmName.setBounds(200, 160, 150, 25);
        textemplName.setBounds(200,190,150,25);
        textaddress.setBounds(200,220,150,25);
        textphoneNo.setBounds(200,250,150,25);
        textemail.setBounds(200,280,150,25);
        jbn.setBounds(300,320,100,30);
        jbn.setBounds(300,320,100,30);
        
        
    }
	
	
public int EmployeeDetailsUpdated()
    {   
        Statement stm=null;
        ResultSet rs=null;
        String sql=null;
        int count=0;
        try
        {
            stm=con.createStatement();
            String fname=textempfName.getText().trim();
            String x=jcom.getSelectedItem().toString();
            String mname=textempmName.getText().trim();
            String lname=textemplName.getText().trim();
            String addr=textaddress.getText().trim();
            String phone=textphoneNo.getText().trim();
            String email=textemail.getText().trim();;
            if(fname.isEmpty() || addr.isEmpty() || phone.isEmpty() || email.isEmpty()){
                JOptionPane.showMessageDialog(null,"* fields are mandatory.Please fill the details","",JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            else{
            if(verifyPhoneNo(textphoneNo.getText().trim()) && verifyEmailId(textemail.getText().trim())){
                   
                   phone=textphoneNo.getText().trim();
                   email=textemail.getText().trim();
            }
            else{
                return 0;
            }
            }

            PreparedStatement ps = con.prepareStatement(
                            "UPDATE employee SET fName = ?, mName=?, lName=?, address = ? , phoneNo=?,emailId = ?" + "WHERE empId = ? ");
 
    
			ps.setString(1,fname);
			ps.setString(2,mname);
			ps.setString(3,lname);
			ps.setString(4,addr);
			ps.setString(5,phone);
			ps.setString(6,email);
			ps.setString(7,x);
   
  
    
    ps.executeUpdate();
    ps.close();
            System.out.println("values taken");
            return 1;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            return 0;
        }
    }
	boolean verifyPhoneNo(String No){
            System.out.println(No.length());
            System.out.println(No);
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
        jpan.add(jcom);
        jpan.add(textempfName);
        jpan.add(textempmName);
        jpan.add(textemplName);
        jpan.add(textaddress);
        jpan.add(textphoneNo);
        jpan.add(textemail);
        jpan.add(jbn);
        
        jfrm1.setVisible(true);
        
        
    }
 
    
} 

