
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
    
    JTextField textItemName = new JTextField(10);
    JTextField textItemType = new JTextField(10);
    JTextField textItemCompanyName = new JTextField(10);
    JTextField textItemStock = new JTextField(10);
    JTextField textItemPrice = new JTextField(10);
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
            sql="INSERT INTO `employee`(empId,fName,mName,lName,address,phoneNo) VALUES ('"+itemName+"','"+itemType+"','"+itemCompanyName+"')";
            //sql = "INSERT INTO 'item'(itemName,itemType,itemCompanyName) VALUE ('"+itemName+"','"+itemType+"','"+itemCompanyName+"')";
            System.out.println(sql);
            stm.executeUpdate(sql);
            //rs.close();
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
            
            //rs.close();
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
