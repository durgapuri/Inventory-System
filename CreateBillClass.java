package BillPackage;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;
import CustomerPackage.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateBillClass {
    
    private int total=0,price=0,tprice=0,count=0;
    private Connection con=null;
    private Statement stm=null;
    private ResultSet rs=null;
    private String td;
    private JFrame jfrm=new JFrame("Create Bill");
    private JPanel jpan=new JPanel();
    private JLabel CustomerPhoneNo=new JLabel("Phone No");
    private JLabel BillDate=new JLabel("Bill Date");
    private JLabel totalSum=new JLabel("Sum:");
    private JTextField textcustPhne=new JTextField();
    private JTextField textbilldate=new JTextField();
    private JTextField texttotalsum=new JTextField();
    private JLabel itemIdLabel=new JLabel("Item Id");
    private JLabel itemNameLabel=new JLabel("Name");

    private JLabel itemCompanyNameLabel=new JLabel("Company Name");
    private JLabel itemquantityLabel=new JLabel("Quantity");
    private JLabel itemTypeLabel=new JLabel("Type");
    private JComboBox comboId=new JComboBox();
    private JTextField comboName=new JTextField();
    private JTextField comboCompanyName=new JTextField();
    private JTextField comboItemType=new JTextField();
    private JTextField textquantity=new JTextField();
    private JButton jbn1=new JButton("Check");
    private JPanel jp1=new JPanel();
    private JButton jbn=new JButton("Add");
    private DefaultTableModel model;
    private JTable table;
    private String selectedId,selectedCompany,selectedName,selectedItemType,timeStamp;
    private SimpleDateFormat df;
    private int quantity;
    private JButton buttonCreateBill=new JButton("Print Bill");
    private java.util.Date date;
    private Date da;
    
    public CreateBillClass(Connection con)
    {   
        this.con=con;
        
        
        setLayoutBoundaries();
        retrieveValues();
        createTable();
        addingButton();
    }
    public  void createTable()
    {
        
       model=new DefaultTableModel();
        table=new JTable();
   
        table.setModel(model);
        model.addColumn("Item Id");
        model.addColumn("Item Name");
        model.addColumn("Item Company");
        model.addColumn("ItemType");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Total");
        JScrollPane scrollPane = new JScrollPane(table);
        jpan.add(scrollPane);
        scrollPane.setBounds(200,400,900,300);
        table.getTableHeader().setReorderingAllowed(false);
        
    }
    public void setLayoutBoundaries()
    {   
        CustomerPhoneNo.setBounds(220,100,100,30);
        textcustPhne.setBounds(320, 100, 100, 30);
        BillDate.setBounds(600,100,100,30);
        textbilldate.setBounds(700, 100, 100, 30);
        itemIdLabel.setBounds(220,150,100,30);
        comboId.setBounds(320,150,100,30);
        itemNameLabel.setBounds(600, 150, 100, 30);
        comboName.setBounds(700,150,100,30);
        itemCompanyNameLabel.setBounds(220,200 ,150, 30);
        comboCompanyName.setBounds(320, 200, 100, 30);
        itemTypeLabel.setBounds(600,200 ,100, 30);
        comboItemType.setBounds(700,200 ,100, 30);
        itemquantityLabel.setBounds(220,250,100,30);
        textquantity.setBounds(320, 250, 100, 30);
        jbn1.setBounds(420,100,100,30);
        jpan.add(jbn1);
        jbn.setBounds(300,350,100,30);
        buttonCreateBill.setBounds(800,350,100,30);
        totalSum.setBounds(1150,670,100,30);
        texttotalsum.setBounds(1200,670,100,30);
        texttotalsum.setText(String.valueOf(0));
        
        addingComponents();
        
    }
    public void addingComponents()
    {   
        jpan.setLayout(null);
        jfrm.setSize(1000,1000);
        jfrm.setVisible(true);
        jfrm.setLocationRelativeTo(null);
        jfrm.add(jpan);
        jpan.add(itemIdLabel);
        jpan.add(comboId);
        jpan.add(itemNameLabel);
        jpan.add(comboName);
        jpan.add(itemCompanyNameLabel);
        jpan.add(comboCompanyName);
        jpan.add(itemquantityLabel);
        jpan.add(textquantity);
        jpan.add(itemTypeLabel);
        jpan.add(comboItemType);
        jpan.add(CustomerPhoneNo);
        jpan.add(textcustPhne);
        jpan.add(BillDate);
        jpan.add(textbilldate);
        jpan.add(jbn);
        jpan.add(jp1);
        jpan.add(totalSum);
        jpan.add(texttotalsum);
        jpan.add(buttonCreateBill);
        
        jbn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            { 
                checkCustomer();
            }
        });
       setBillDate();
   }
    public void checkCustomer()
    {  
        int count=0;
        String phn=textcustPhne.getText();
        try
        {   
            stm=con.createStatement();
            String sql="select * from customer where phoneNo='"+phn+"'";
            rs=stm.executeQuery(sql);
            while(rs.next())
            {
                count+=1;
            }
            
            if(count!=1)
                new AddCustomerClass(con);
            
            else
                JOptionPane.showMessageDialog(null,"Details already added");
            rs.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    public void setBillDate()
    {
      try
      {
      
      timeStamp=new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
      textbilldate.setText(timeStamp);
      /*df = new SimpleDateFormat("dd-MM-yyyy");
      date=df.parse(timeStamp);*/
      
      
    }catch(Exception e)
    {
        System.out.println(e);
        
    }
    }
   public void retrieveValues()
   {  try
       {   
            stm=con.createStatement();
            rs = stm.executeQuery("select itemId from item");
            while(rs.next()){
            String sc= rs.getString("itemId");
            comboId.addItem(sc);
            }
            comboId.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                selectedId=String.valueOf(comboId.getSelectedItem());
                System.out.println(selectedId);
            try
            {
            String itemName = "Select itemName from item where itemId= '" +selectedId+ "'";
            ResultSet rs = stm.executeQuery(itemName);
            while(rs.next()){
              selectedName = rs.getString("itemName");
                comboName.setText(selectedName); 
            }
            
            rs.close();
            
            String itemType = "Select itemType from item where itemId= '" +selectedId+ "'";
            rs = stm.executeQuery(itemType);
            while(rs.next()){
             selectedItemType = rs.getString("itemType");
              comboItemType.setText(selectedItemType); 
            }
            rs.close();
            String itemCompanyName = "Select itemCompanyName from item where itemId= '" +selectedId+ "'";
            rs = stm.executeQuery(itemCompanyName);
            while(rs.next()){
              selectedCompany= rs.getString("itemCompanyName");
              comboCompanyName.setText(selectedCompany); 
            }
            rs.close();
            String itemPrice = "Select itemPrice from stock where itemId= '" +selectedId+ "'";
            rs = stm.executeQuery(itemPrice);
            while(rs.next()){
                
              price= rs.getInt("itemPrice");
              System.out.println(price+"hi");
            }
            rs.close();
            }catch(Exception e)
            {
                System.out.println(e);
            }
                }});
            
             
        }
       catch(Exception e)
       {
           System.out.println(e);
        }
       
        
    }
   public void addingButton()
   {
       jbn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            { 
                quantity=Integer.parseInt(textquantity.getText());
                stockAvailable();
               
            }
       });
       
       buttonCreateBill.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            { 
              updatebill();  
               
            }
       });
   }
   public void stockAvailable()
   {
       try
       {    stm=con.createStatement();
            int s=0;
            String sql="select itemStock from stock where itemId='"+selectedId+"'";
            rs = stm.executeQuery(sql);
            while(rs.next()){
               
                s= rs.getInt("itemStock");
            
            }  
            if(s>quantity)
            {
                //JOptionPane.showMessageDialog(null,"Item  Available");
                updateStock(s);
                billCreate();
                updatePurchase();
            }
            else
                JOptionPane.showMessageDialog(null,"Item Not Available");
           
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
   }
   public void updateStock(int m)
   {
       try
       {   System.out.println("updating");
           stm=con.createStatement();
            System.out.println(m);
            System.out.println(quantity);
            String s="Update stock set itemStock=? where itemId=?";
            PreparedStatement ps=con.prepareStatement(s);
            ps.setInt(1,m-quantity);
            ps.setString(2,selectedId);
            ps.executeUpdate();
            ps.close();
           
       }
       catch(Exception e)
       {
          System.out.println(e);
       }
   }
   public void billCreate()
   {   
      
                tprice=price*quantity;
                total+=tprice;
                model.addRow(new Object[]{selectedId,selectedName,selectedCompany,selectedItemType,quantity,price,tprice});
                          
                System.out.println(total);
                texttotalsum.setText(String.valueOf(total));
                System.out.println();
               
   }
   public void updatePurchase() throws NullPointerException
   {
       try
       {  
           SimpleDateFormat f=new SimpleDateFormat("dd-MM-yyyy");
           System.out.println("purchasing");
          
          stm=con.createStatement();
          String d=textbilldate.getText();
          System.out.println(d);
         da=f.parse(d);
          
       
            //java.sql.Date sqlDate = new java.sql.Date(date.getTime());
           //String str = form.format(date); 
           //System.out.println("hvh"+sqlDate);
             
          //String check="select itemId from purchase where itemId='"+selectedId+"'and purchaseDate = '"+str+"'";
          PreparedStatement check=con.prepareStatement("SELECT itemId FROM purchase WHERE itemId =? and purchaseDate= ?");
           check.setString(1, selectedId);
           check.setDate(2,new java.sql.Date(da.getTime()));
         
          //System.out.println(check);   
          rs=check.executeQuery();
          System.out.println("problem");
          int count=0;
          while(rs.next())
          {     
              ++count;
              System.out.println("count"+count);
          }
          rs.close();
          System.out.println(count+"wiew of count");
          if(count==1)
          {   int prevQuantity=0;
              int prevSum=0;
              System.out.println("prachee");
              PreparedStatement ps=con.prepareStatement("select purchaseQuantity,totalSum from purchase where itemId=? and purchaseDate = ?");
              ps.setString(1, selectedId);
              ps.setDate(2,new java.sql.Date(da.getTime()));
          
           
          ResultSet rs1=ps.executeQuery();
//String sp="select purchaseQuantity,totalSum from purchase where itemId= '"+selectedId+"' and purchaseDate = '"+str+"'";
              //System.out.println(sp);
              //rs=stm.executeQuery(sp);
              System.out.println("executed");
              while(rs1.next())
              {   
                  prevQuantity=Integer.parseInt(rs1.getString("purchaseQuantity"));
                  System.out.println(prevQuantity);
                  prevSum=Integer.parseInt(rs1.getString("totalSum"));
                  System.out.println(prevSum);
              }
              PreparedStatement ps1=con.prepareStatement("Update purchase set purchaseQuantity=?,totalSum=? where itemId=?");
              ps1.setInt(1,(prevQuantity+quantity));
              ps1.setInt(2,(prevSum+tprice));
              ps1.setString(3,selectedId);
              ps1.executeUpdate();
          }
          else
          {   int supId=0;
              String s="select SupId from stock where itemId="+selectedId;
              System.out.println("jkliuy");
              rs=stm.executeQuery(s);
                 while(rs.next())
                {
                    supId=Integer.parseInt(rs.getString("SupId"));
                }
                 System.out.println("vfd"+supId);
                rs.close();
              String sp="insert into `purchase`(itemId,supId,purchaseQuantity,purchaseDate,totalSum) values(?,?,?,?,?)";
              PreparedStatement ps=con.prepareStatement(sp);
              ps.setString(1,selectedId);
              ps.setInt(2, supId);
              ps.setInt(3,quantity);
              ps.setTimestamp(4,new java.sql.Timestamp(date.getTime()));
              ps.setInt(5,tprice);
              ps.executeUpdate();
                
              
          }
                  
    }
       catch(Exception e)
       {
           System.out.println(e);
       }
   }
    
    public void updatebill(){
        try{
        String sql="INSERT into `bill` (billDate,cusPhoneNo,billtotalSum) values ?,?,?";
        PreparedStatement ps=con.prepareStatement(sql);
             
               ps.setDate(1,new java.sql.Date(da.getTime()));
              ps.setString(2,textcustPhne.getText());
              ps.setInt(3,total);
        
    }
        catch(Exception e){
            
        }
    
}
}
