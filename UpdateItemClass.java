
package ItemPackage;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateItemClass {
    
   // String driver="net.ucanaccess.jdbc.UcanaccessDriver";
   // String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    
    private Connection con=null;
    private JFrame jfrm1=new JFrame("Update Item");
    private JPanel jpan=new JPanel();
    private JLabel itemIdLabel=new JLabel("Item Id");
    private JLabel itemNameLabel=new JLabel("Item Name");
    private JLabel itemTypeLabel=new JLabel("Item Type");
    private JLabel itemCompanyNameLabel=new JLabel("Item Company Name");
    private JLabel itemStockLabel=new JLabel("Item Stock");
    private JLabel itemPriceLabel=new JLabel("Item Price");
    private JComboBox jcom=new JComboBox();
    
    private String x;
    private JTextField textItemName = new JTextField(10);
    private JTextField textItemType = new JTextField(10);
    private JTextField textItemCompanyName = new JTextField(10);
    private JTextField textItemStock = new JTextField(10);
    private JTextField textItemPrice = new JTextField(10);
    private JButton jbn=new JButton("UPDATE");
        
    public UpdateItemClass(Connection con)
    {
        this.con=con;
       setLayoutBoundaries(); 
         addComponents();
      /* try
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
     
       combox();
       updatingItemFunction();
       
        
        
        
    }
    public void combox(){
        
        
        
        
        try
        {
            Statement stm=con.createStatement();
            ResultSet rs = stm.executeQuery("select itemId from item");
            while(rs.next()){
            System.out.println("getting first value");
            String s= rs.getString("itemId");
            System.out.println(s);
            jcom.addItem(s);
            
            
        }
            
           jcom.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                
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
        String itemName = "Select itemName from item where itemId= '" +x+ "'";
                ResultSet rs = st.executeQuery(itemName);
                while(rs.next()){
                    String value = rs.getString("itemName");
                    textItemName.setText(value); 
                     
                }
                
                
          String itemType = "Select itemType from item where itemId= '" +x+ "'";
                ResultSet rs1 = st.executeQuery(itemType);
                while(rs1.next()){
                    String value = rs1.getString("itemType");
                    textItemType.setText(value); 
                }
            String itemCompany = "Select itemCompanyName from item where itemId= '" +x+ "'";
                ResultSet rs2 = st.executeQuery(itemCompany);
                while(rs2.next()){
                    String value = rs2.getString("itemCompanyName");
                    textItemCompanyName.setText(value); 
                }
               System.out.println("jcud");
             String itemStock = "Select itemStock from stock where itemId= '" +x+ "'";
            
                ResultSet rs3 = st.executeQuery(itemStock);
                while(rs3.next()){
                    int id = Integer.parseInt(rs3.getString("itemStock"));
                    System.out.println("ooo"+id);
                    textItemStock.setText(String.valueOf(id)) ; 
                }
            String itemPrice="Select itemPrice from stock where itemId= '" +x+ "'";
                ResultSet rs4 = st.executeQuery(itemPrice);
                while(rs4.next()){
                    int a=Integer.parseInt(rs4.getString("itemPrice"));
                    
                    textItemPrice.setText(String.valueOf(a)); 
                }
            }catch(Exception ae){

            }
        
    }
    public void updatingItemFunction()
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
        itemIdLabel.setBounds(50,100,100,25);
        itemNameLabel.setBounds(50,130,100,25);
        itemTypeLabel.setBounds(50,160,100,25);
        itemCompanyNameLabel.setBounds(50,190,150,25);
        itemStockLabel.setBounds(50, 220, 100, 25);
        itemPriceLabel.setBounds(50, 250, 100, 25);
        textItemName.setBounds(200,130,150,25);
        textItemType.setBounds(200,160,150,25);
        textItemCompanyName.setBounds(200,190,150,25);
        jcom.setBounds(200,100,150,25);
        textItemStock.setBounds(200, 220, 150, 25);
        textItemPrice.setBounds(200,250,150,25);
        jbn.setBounds(300,300,100,30);
    }
    public int ItemUpdated()
    {   Statement stm=null;
        ResultSet rs=null;
        String sql=null;
        int count=0;
        try
        {
            stm=con.createStatement();
            String x=jcom.getSelectedItem().toString();
            String itemName=textItemName.getText().trim();
            String itemType=textItemType.getText().trim();
            String itemCompanyName=textItemCompanyName.getText().trim();
            String itemStock=textItemStock.getText().trim();
            String itemPrice=textItemPrice.getText().trim();
            System.out.println("values taken");
            
             PreparedStatement ps = con.prepareStatement(
                            "UPDATE item SET itemName = ?, itemType = ? , itemCompanyName = ? " + "WHERE itemId = ? ");
 
    
    ps.setString(1,itemName);
    ps.setString(2,itemType);
    ps.setString(3,itemCompanyName);
    ps.setString(4,x);
    
            PreparedStatement ps1=con.prepareStatement(
                                    "UPDATE stock SET itemStock = ?, itemPrice = ?"+"WHERE itemId=?");
    System.out.println(itemStock);        
    ps1.setString(1, itemStock);
    ps1.setString(2,itemPrice);
    ps1.setString(3,x);
    
    
    ps.executeUpdate();
    ps1.executeUpdate();
    ps.close();
    ps1.close();
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
        jpan.add(itemIdLabel);
        jpan.add(itemNameLabel);
        jpan.add(itemTypeLabel);
        jpan.add(itemCompanyNameLabel);
        jpan.add(itemStockLabel);
        jpan.add(itemPriceLabel);
        jpan.add(textItemName);
        jpan.add(textItemCompanyName);
        jpan.add(textItemStock);
        jpan.add(textItemType);
        jpan.add(textItemPrice);
        jpan.add(jbn);
        jpan.add(jcom);
        
        jfrm1.setVisible(true);
        jfrm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
   /* public static void main(String args[])
    {
        new UpdateItemClass();
    }*/
    
} 

