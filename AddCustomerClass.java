
package CustomerPackage;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class AddCustomerClass {
   // String driver="net.ucanaccess.jdbc.UcanaccessDriver";
  // String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    private Connection con=null;
    private JFrame jfrm1=new JFrame("Add New Customer");
    private JPanel jpan=new JPanel();
    private JLabel cusPhnLabel=new JLabel("Customer Phone No");
    private JLabel cusNameLabel=new JLabel("Customer Name");
    private JLabel cusAddrLabel=new JLabel("Customer Address");
    private JLabel cusEmailIdLabel=new JLabel("Customer Email Id");
    //private JLabel cusVisitLabel=new JLabel("Recent Visit Date");
    //private JLabel cusFreqLabel=new JLabel("Frequency Of Date");
    private JTextField textPhn = new JTextField(10);
    private JTextField textName = new JTextField(10);
    private JTextField textAddr = new JTextField(10);
    private JTextField textEmail = new JTextField(10);
   // private JTextField textVisit = new JTextField(10);
    //private JTextField textFreq = new JTextField(10);
    
    private JButton jbn=new JButton("ADD");
    String timeStamp;
    SimpleDateFormat df;
    java.util.Date date;
    public AddCustomerClass(Connection con)
            
    {
        this.con=con;
       setLayoutBoundaries(); 
       /*try
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
       addingItemFunction();
       addComponents();
        
        
        
    }
     public void setDateCustomer()
    {
      try
      {
        timeStamp=new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
      
      df = new SimpleDateFormat("dd/MM/yyyy");
      date=df.parse(timeStamp);
      //td=textbilldate.getText();
    }catch(Exception e)
    {
        System.out.println(e);
        
    }
    }
    public void addingItemFunction()
    {                   

        jbn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   System.out.println("button working");
                setDateCustomer();
                if(ItemAdded()==1)
                {
                   JOptionPane.showMessageDialog(null,"Customer Details Added");
                   jfrm1.dispose();
                }
                else
               {   //JOptionPane.showMessageDialog(null,"Customer Details Not Added");
               }
                //jfrm1.dispose();
            }
        });
    }
    public void setLayoutBoundaries()
    {  
        jpan.setLayout(null);
        jfrm1.setSize(500,500);
        
        cusPhnLabel.setBounds(50,100,120,25);
        cusNameLabel.setBounds(50,130,120,25);
        cusAddrLabel.setBounds(50,160,120,25);
        cusEmailIdLabel.setBounds(50, 190, 120, 25);
        //cusVisitLabel.setBounds(50, 220, 120, 25);
        //cusFreqLabel.setBounds(50, 250, 120, 25);
        textPhn.setBounds(200,100,150,25);
        textName.setBounds(200,130,150,25);
        textAddr.setBounds(200,160,150,25);
        textEmail.setBounds(200, 190, 150, 25);
        //textVisit.setBounds(200,220,150,25);
        //textFreq.setBounds(200,250,150,25);
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
            String name=textName.getText().trim();
            
            String addr=textAddr.getText().trim();
            String phnno="";
            String email="";
            //String visit=textVisit.getText().trim();
            //String freq=textFreq.getText().trim();
            if(verifyPhoneNo(textPhn.getText().trim()) && verifyEmailId(textEmail.getText().trim())){
                   
                   phnno=textPhn.getText().trim();
                   email=textEmail.getText().trim();
            }
            else{
                return 0;
            }
            
            System.out.println("values taken");
            sql="INSERT INTO `customer`(phoneNo,cusName,address,emailId,recentVisitDate,frequencyOfVisit) VALUES ('"+phnno+"','"+name+"','"+addr+"','"+email+"','"+new java.sql.Timestamp(date.getTime())+"','"+1+"')";
            
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



	/**********************************************************************
	* Function :                                       					  *
	* Details : 														  *
	***********************************************************************/

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
        jpan.add(cusPhnLabel);
        jpan.add(cusNameLabel);
        jpan.add(cusAddrLabel);
        jpan.add(cusEmailIdLabel);
        //jpan.add(cusVisitLabel);
        //jpan.add(cusFreqLabel);
        jpan.add(textPhn);
        jpan.add(textName);
        jpan.add(textAddr);
        jpan.add(textEmail);
        //jpan.add(textVisit);
        //jpan.add(textFreq);
        jpan.add(jbn);
        jfrm1.add(jpan);
        jfrm1.setVisible(true);
        
    }
    /*public static void main(String args[])
    {
        new AddCustomerClass();
    }*/
    
}
