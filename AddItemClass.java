
package ItemPackage;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddItemClass {

    private Connection con=null;
    private JFrame jfrm1=new JFrame("Add New Item");
    private JPanel jpan=new JPanel();
    private JLabel itemNameLabel=new JLabel("Item Name");
    private JLabel itemTypeLabel=new JLabel("Item Type");
    private  JLabel itemCompanyNameLabel=new JLabel("Item Company Name");
    private JLabel itemStockLabel=new JLabel("Item Stock");
    private JLabel itemPriceLabel=new JLabel("Item Price");
    private JLabel suppId=new JLabel("Supplier ID");
    private JTextField textItemName = new JTextField(10);
    private  JTextField textItemType = new JTextField(10);
    private JTextField textItemCompanyName = new JTextField(10);
    private JTextField textItemStock = new JTextField(10);
    private  JTextField textItemPrice = new JTextField(10);
    private JTextField textSupId=new JTextField(10);
    private JButton jbn=new JButton("ADD");
	
	
    public AddItemClass(Connection con)
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
            {   System.out.println("button working");
                if(ItemAdded()==1)
                {
                   JOptionPane.showMessageDialog(null,"Item Added successfully");
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
        itemNameLabel.setBounds(50,100,100,25);
        itemTypeLabel.setBounds(50,130,100,25);
        itemCompanyNameLabel.setBounds(50,160,150,25);
        itemStockLabel.setBounds(50, 190, 100, 25);
        itemPriceLabel.setBounds(50, 220, 100, 25);
        suppId.setBounds(50, 250, 100, 25);
        textItemName.setBounds(200,100,150,25);
        textItemType.setBounds(200,130,150,25);
        textItemCompanyName.setBounds(200,160,150,25);
        textItemStock.setBounds(200, 190, 150, 25);
        textItemPrice.setBounds(200,220,150,25);
        textSupId.setBounds(200,250,150,25);
        jbn.setBounds(300,300,100,30);
    }
	
	
	
public int ItemAdded()
    {   
        Statement stm=null;
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
            String supplierId=textSupId.getText().trim();
            if(itemName.isEmpty() || itemType.isEmpty() || itemCompanyName.isEmpty()|| itemStock.isEmpty() || itemPrice.isEmpty() || supplierId.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"All details are mandatory.Please fill the details","",JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            
           boolean number=false;
           boolean number1=false;
            for (char c : itemPrice.toCharArray()){
            if (Character.isDigit(c))
                 number=true;
            }
            
            for (char ch : itemStock.toCharArray()){
                    if (Character.isDigit(ch))
                        number1=true;
            }
            if(!number || !number1)
            {
                JOptionPane.showMessageDialog(null,"Item price and stock must be an integer","",JOptionPane.ERROR_MESSAGE);
                return 0;
            }
            System.out.println("values taken");
            sql="INSERT INTO `item`(itemName,itemType,itemCompanyName) VALUES ('"+itemName+"','"+itemType+"','"+itemCompanyName+"')";
            
            System.out.println(sql);
            stm.executeUpdate(sql);
            
            String sql1="SELECT i.itemId FROM item as i WHERE i.itemName='"+itemName+"'";
            rs=stm.executeQuery(sql1);
            System.out.println("exception");
            while(rs.next())
            { 
                int in=rs.getInt("itemId");
             
            
            sql = "INSERT INTO `stock`(itemId,itemStock,itemPrice,SupId) VALUES ("+in+","+itemStock+","+itemPrice+",'"+supplierId+"')";
            System.out.println(sql);
            
            stm.executeUpdate(sql);
            
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
        jpan.add(suppId);
        jpan.add(textSupId);
        jfrm1.add(jpan);
        
        jfrm1.setVisible(true);
        
    }
}
