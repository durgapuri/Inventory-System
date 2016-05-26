
package CustomerPackage;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UpdateCustomerClass {
    
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://C:\\Users\\me\\Desktop\\New folder\\databaseinv.accdb";
    Connection con=null;
    JFrame jfrm1=new JFrame("Update Item");
    JPanel jpan=new JPanel();
    JLabel cusPhnLabel=new JLabel("Customer Phone No");
    JLabel cusNameLabel=new JLabel("Customer Name");
    JLabel cusAddrLabel=new JLabel("Customer Address");
    JLabel cusEmailIdLabel=new JLabel("Customer Email Id");
    
    JComboBox jcom=new JComboBox();
    DateFormat format = new SimpleDateFormat("MM/DD/YYYY"); //display your format.
    
    
    JTextField textPhn = new JTextField(10);
    JTextField textName = new JTextField(10);
    JTextField textAddr = new JTextField(10);
    JTextField textEmail = new JTextField(10);
   
    
    JButton jbn=new JButton("UPDATE");
        
    public UpdateCustomerClass()
    {
       setLayoutBoundaries(); 
       addComponents();
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
     
       combox();
       updatingCustomerFunction();
       
        
        
        
    }
    public void combox(){
        
        
        
        
        try
        {
            Statement stm=con.createStatement();
            ResultSet rs = stm.executeQuery("select phoneNo from customer");
            while(rs.next()){
            System.out.println("getting first value");
            String s= rs.getString("phoneNo");
            System.out.println(s);
            jcom.addItem(s);
            
            
        }
            
           String x=jcom.getSelectedItem().toString();
            
            System.out.println(x);  
            
            fillDetail(x);
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
        
        String cusName = "Select cusName from customer where phoneNo= '" +x+ "'";
                ResultSet rs = st.executeQuery(cusName);
                while(rs.next()){
                    String value = rs.getString("cusName");
                    textName.setText(value); 
                     
                }
                
        String address = "Select address from customer where phoneNo= '" +x+ "'";
                ResultSet rs1 = st.executeQuery(address);
                while(rs1.next()){
                    String value = rs1.getString("address");
                    textAddr.setText(value); 
                }
            String email = "Select emailId from customer where phoneNo= '" +x+ "'";
                ResultSet rs2 = st.executeQuery(email);
                while(rs2.next()){
                    String value = rs2.getString("emailId");
                    textEmail.setText(value); 
                }
               System.out.println("jcud");
             
              
            
            }catch(Exception ae){

            }
        
    }
   public void updatingCustomerFunction()
    {                   

        jbn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {   System.out.println("button working");
                if(ItemUpdated()==1)
                {
                   JOptionPane.showMessageDialog(null,"Item Updated");
                   
                }
                else
               {   JOptionPane.showMessageDialog(null,"Item not Updated");
               }
                jfrm1.dispose();
            }
        });
    }
    public void setLayoutBoundaries()
    {  
        jpan.setLayout(null);
        jfrm1.setSize(500,400);
        jfrm1.add(jpan);
        jfrm1.setVisible(true);
        cusPhnLabel.setBounds(50,100,120,25);
        cusNameLabel.setBounds(50,130,120,25);
        cusAddrLabel.setBounds(50,160,120,25);
        cusEmailIdLabel.setBounds(50, 190, 120, 25);
        
        jcom.setBounds(200,100,150,25);
        
        textName.setBounds(200,130,150,25);
        textAddr.setBounds(200,160,150,25);
        textEmail.setBounds(200,190, 150, 25);
        
        jbn.setBounds(300,320,100,30);
        
       
    }
    public int ItemUpdated()
    {   Statement stm=null;
        ResultSet rs=null;
        String sql=null;
        int count=0;
        try
        {
            stm=con.createStatement();
            String name=textName.getText().trim();
            String x=jcom.getSelectedItem().toString();
            String addr=textAddr.getText().trim();
            System.out.println(addr);
            String email=textEmail.getText().trim();
            

            PreparedStatement ps = con.prepareStatement(
                            "UPDATE customer SET cusName = ?, address = ? , emailId = ?" + "WHERE phoneNo = ? ");
 
    
    ps.setString(1,name);
    ps.setString(2,addr);
    ps.setString(3,email);
   
    ps.setString(4,x);
    
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
    public void addComponents()
    {
        jpan.add(cusPhnLabel);
        jpan.add(cusNameLabel);
        jpan.add(cusAddrLabel);
        jpan.add(cusEmailIdLabel);
        
        jpan.add(textPhn);
        jpan.add(textName);
        jpan.add(textAddr);
        jpan.add(textEmail);
        
        jpan.add(jbn);
        jpan.add(jcom);
        
        jfrm1.setVisible(true);
        jfrm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
   /* public static void main(String args[])
    {
        new UpdateCustomerClass();
    }*/
    
} 

