
package SupplierPackage;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UpdateSupplierClass {
    
    //String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    //String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    private Connection con=null;
    private JFrame jfrm1=new JFrame("Update Supplier");
    private JPanel jpan=new JPanel();
   
    private JLabel supId=new JLabel("Supplier Id");
    private JLabel supNameLabel=new JLabel("Supplier Name");
    private JLabel supAddrLabel=new JLabel("Address");
    private JLabel supContactLabel=new JLabel("Contact No");
    private JTextField textsupName = new JTextField(10);
    private JTextField textsupAddr = new JTextField(10);
    private JTextField textsupContact = new JTextField(10);
    private JComboBox jcom=new JComboBox();
    private String x;
    
    private  JButton jbn=new JButton("UPDATE");
        
    public UpdateSupplierClass(Connection con)
    {
        this.con=con;
       setLayoutBoundaries(); 
       addComponents();
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
        }
     */
       combox();
       updatingCustomerFunction();
       
        
        
        
    }
    public void combox(){
        
        
        
        
        try
        {
            Statement stm=con.createStatement();
            ResultSet rs = stm.executeQuery("select supId from supplierDetails");
            while(rs.next()){
            System.out.println("getting first value");
            String s= rs.getString("supId");
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
        
        String supName = "Select supName from supplierDetails where supId= '" +x+ "'";
                ResultSet rs = st.executeQuery(supName);
                while(rs.next()){
                    String value = rs.getString("supName");
                    textsupName.setText(value); 
                     
                }
                
        String address = "Select supAddress from supplierDetails where supId= '" +x+ "'";
                ResultSet rs1 = st.executeQuery(address);
                while(rs1.next()){
                    String value = rs1.getString("supAddress");
                    textsupAddr.setText(value); 
                }
            String contact = "Select supContact from supplierDetails where supId= '" +x+ "'";
                ResultSet rs2 = st.executeQuery(contact);
                while(rs2.next()){
                    String value = rs2.getString("supContact");
                    textsupContact.setText(value); 
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
        
        
        supId.setBounds(50,100,100,25);
        supNameLabel.setBounds(50,130,100,25);
        supAddrLabel.setBounds(50,160,100,25);
        supContactLabel.setBounds(50,280,150,25);
        jcom.setBounds(200,100,150,25);
        textsupName.setBounds(200,130,150,25);
        textsupAddr.setBounds(200,160,150,100);
        textsupContact.setBounds(200,280,150,25);
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
            String name=textsupName.getText().trim();
            String x=jcom.getSelectedItem().toString();
            String addr=textsupAddr.getText().trim();
            System.out.println(addr);
            String contact=textsupContact.getText().trim();
            
            
            

            PreparedStatement ps = con.prepareStatement(
                            "UPDATE supplierDetails SET supName = ?, supAddress = ? , supContact= ? " + "WHERE supId = ? ");
 
    
    ps.setString(1,name);
    ps.setString(2,addr);
    ps.setString(3,contact);
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
        jpan.add(supId);
        jpan.add(supNameLabel);
        jpan.add(supAddrLabel);
        jpan.add(supContactLabel);
        jpan.add(textsupName);
        jpan.add(textsupAddr);
        jpan.add(textsupContact);
        jpan.add(jbn);
        jpan.add(jcom);
        
        jfrm1.setVisible(true);
        jfrm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    /*public static void main(String args[])
    {
        new UpdateSupplierClass();
    }*/
    
} 

