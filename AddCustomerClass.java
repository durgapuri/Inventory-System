
package CustomerPackage;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomerClass {
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    Connection con=null;
    JFrame jfrm1=new JFrame("Add New Customer");
    JPanel jpan=new JPanel();
    JLabel cusPhnLabel=new JLabel("Customer Phone No");
    JLabel cusNameLabel=new JLabel("Customer Name");
    JLabel cusAddrLabel=new JLabel("Customer Address");
    JLabel cusEmailIdLabel=new JLabel("Customer Email Id");
    JLabel cusVisitLabel=new JLabel("Recent Visit Date");
    JLabel cusFreqLabel=new JLabel("Frequency Of Date");
    JTextField textPhn = new JTextField(10);
    JTextField textName = new JTextField(10);
    JTextField textAddr = new JTextField(10);
    JTextField textEmail = new JTextField(10);
    JTextField textVisit = new JTextField(10);
    JTextField textFreq = new JTextField(10);
    
    JButton jbn=new JButton("ADD");
        
    public AddCustomerClass()
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
                   JOptionPane.showMessageDialog(null,"Customer Details Added");
                   
                }
                else
               {   JOptionPane.showMessageDialog(null,"Customer Details Not Added");
               }
                jfrm1.dispose();
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
        cusVisitLabel.setBounds(50, 220, 120, 25);
        cusFreqLabel.setBounds(50, 250, 120, 25);
        textPhn.setBounds(200,100,150,25);
        textName.setBounds(200,130,150,25);
        textAddr.setBounds(200,160,150,25);
        textEmail.setBounds(200, 190, 150, 25);
        textVisit.setBounds(200,220,150,25);
        textFreq.setBounds(200,250,150,25);
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
            String phnno=textPhn.getText().trim();
            String addr=textAddr.getText().trim();
            String email=textEmail.getText().trim();
            String visit=textVisit.getText().trim();
            String freq=textFreq.getText().trim();
            System.out.println("values taken");
            sql="INSERT INTO `customer`(phoneNo,cusName,address,emailId,recentVisitDate,frequencyOfVisit) VALUES ('"+phnno+"','"+name+"','"+addr+"','"+email+"','"+visit+"','"+freq+"')";
            
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
        jpan.add(cusPhnLabel);
        jpan.add(cusNameLabel);
        jpan.add(cusAddrLabel);
        jpan.add(cusEmailIdLabel);
        jpan.add(cusVisitLabel);
        jpan.add(cusFreqLabel);
        jpan.add(textPhn);
        jpan.add(textName);
        jpan.add(textAddr);
        jpan.add(textEmail);
        jpan.add(textVisit);
        jpan.add(textFreq);
        jpan.add(jbn);
        jfrm1.add(jpan);
        jfrm1.setVisible(true);
        
    }
    public static void main(String args[])
    {
        new AddCustomerClass();
    }
    
}
