
package ItemPackage;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddItemClass {
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    Connection con=null;
    JFrame jfrm1=new JFrame("Add New Item");
    JPanel jpan=new JPanel();
    JLabel itemNameLabel=new JLabel("Item Name");
    JLabel itemTypeLabel=new JLabel("Item Type");
    JLabel itemCompanyNameLabel=new JLabel("Item Company Name");
    JLabel itemStockLabel=new JLabel("Item Stock");
    JLabel itemPriceLabel=new JLabel("Item Price");
    JTextField textItemName = new JTextField(10);
    JTextField textItemType = new JTextField(10);
    JTextField textItemCompanyName = new JTextField(10);
    JTextField textItemStock = new JTextField(10);
    JTextField textItemPrice = new JTextField(10);
    JButton jbn=new JButton("ADD");
        
    public AddItemClass()
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
                   
                }
                else
               {   JOptionPane.showMessageDialog(null,"Item not Added");
               }
                jfrm1.dispose();
            }
        });
    }
    public void setLayoutBoundaries()
    {  
        jpan.setLayout(null);
        jfrm1.setSize(500,500);
        
        itemNameLabel.setBounds(50,100,100,25);
        itemTypeLabel.setBounds(50,130,100,25);
        itemCompanyNameLabel.setBounds(50,160,150,25);
        itemStockLabel.setBounds(50, 190, 100, 25);
        itemPriceLabel.setBounds(50, 220, 100, 25);
        textItemName.setBounds(200,100,150,25);
        textItemType.setBounds(200,130,150,25);
        textItemCompanyName.setBounds(200,160,150,25);
        textItemStock.setBounds(200, 190, 150, 25);
        textItemPrice.setBounds(200,220,150,25);
        jbn.setBounds(300,300,100,30);
    }
    public int ItemAdded()
    {   Statement stm=null;
        ResultSet rs=null;
        String sql=null;
        int count=0;
        try
        {
            stm=con.createStatement();
            String itemName=textItemName.getText().trim();
            String itemType=textItemType.getText().trim();
            String itemCompanyName=textItemCompanyName.getText().trim();
            String itemStock=textItemStock.getText().trim();
            String itemPrice=textItemPrice.getText().trim();
            System.out.println("values taken");
            sql="INSERT INTO `item`(itemName,itemType,itemCompanyName) VALUES ('"+itemName+"','"+itemType+"','"+itemCompanyName+"')";
            
            System.out.println(sql);
            stm.executeUpdate(sql);
            
            String sql1="SELECT i.itemId FROM item as i WHERE i.itemName='"+itemName+"'";
            rs=stm.executeQuery(sql1);
            System.out.println("exception");
            while(rs.next())
            { int in=rs.getInt("itemId");
              System.out.println(in);
            
            
            sql = "INSERT INTO `stock`(itemId,itemStock,itemPrice) VALUES ("+in+","+itemStock+","+itemPrice+")";
            System.out.println(sql);
            
            stm.executeUpdate(sql);
            System.out.println("error");
            
            
            }
            rs.close();
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
        jfrm1.add(jpan);
        jfrm1.setVisible(true);
        
    }
    /*public static void main(String args[])
    {
        new AddItemClass();
    }*/
    
}
