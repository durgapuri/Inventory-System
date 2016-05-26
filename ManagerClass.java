
package ManagerPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import EmployeePackage.*;
import CustomerPackage.*;
import SupplierPackage.*;
import ItemPackage.*;
import StockPackage.*;
import BillPackage.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ManagerClass {
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    String date;
    Connection con=null;
    ResultSet rs=null;
    Statement stm=null;
    JFrame jfrm=new JFrame("Manager");
    JMenuBar jmb=new JMenuBar();
    String empIdreceived;
    JLabel jbl=new JLabel();
    JLabel jbl1=new JLabel("Last Access Time:");
    public ManagerClass(String empIdreceived)
    {   this.empIdreceived=empIdreceived;
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
        settingFrameBoundaries();
        displayDateTime();
        creatingEmployeeMenu();
        creatingCustomerMenu();
        creatingSupplierMenu();
        creatingItemMenu();
        creatingStockMenu();
        creatingBillMenu();
        creatingSalesMenu(); 
        jfrm.setJMenuBar(jmb);
        jfrm.setVisible(true);
    }
    public void settingFrameBoundaries()
    {
        jfrm.setSize(550,500);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLayout(null);
        jfrm.setLocationRelativeTo(null);
    }
    public void creatingEmployeeMenu()
    {
        JMenu menuEmployee=new JMenu("Employee");
        jmb.add(menuEmployee);
        JMenuItem viewEmployee=new JMenuItem("View Employee Details");
        menuEmployee.add(viewEmployee);
        viewEmployee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewEmployeeClass(); 
               
        }});
    }
    public void creatingCustomerMenu()
    {
        JMenu menuCust=new JMenu("Customer");
        jmb.add(menuCust);
        JMenuItem addCust=new JMenuItem("Add Customer");
        menuCust.add(addCust);
        addCust.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new AddCustomerClass(); 
               
        }});
        JMenuItem removeCust=new JMenuItem("Remove Customer");
        menuCust.add(removeCust);
        removeCust.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new DeleteCustomerClass(); 
               
        }});
        JMenuItem updateCust=new JMenuItem("Update Customer");
        menuCust.add(updateCust);
        updateCust.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new UpdateCustomerClass(); 
               
        }});
        JMenuItem viewCust=new JMenuItem("View Customer");
        menuCust.add(viewCust);
        viewCust.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewCustomerClass(); 
               
        }});
    }
    public void creatingSupplierMenu()
    {
        JMenu menuSup=new JMenu("Supplier");
        jmb.add(menuSup);
        JMenuItem addSup=new JMenuItem("Add Supplier");
        menuSup.add(addSup);
        addSup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new AddSupplier(); 
               
        }});
        JMenuItem removeSup=new JMenuItem("Remove Supplier");
        menuSup.add(removeSup);
        removeSup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new DeleteSupplierClass(); 
               
        }});
        JMenuItem updateSup=new JMenuItem("Update Supplier");
        menuSup.add(updateSup);
        updateSup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new UpdateSupplierClass(); 
               
        }});
        JMenuItem viewSup=new JMenuItem("View Suppliers");
        menuSup.add(viewSup);
        viewSup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewSupplierClass(); 
               
        }});
    }
    public void creatingItemMenu()
    {
        JMenu menuItem=new JMenu("Item");
        jmb.add(menuItem);
        JMenuItem addItem=new JMenuItem("Add Item");
        menuItem.add(addItem);
        addItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new AddItemClass(); 
               
        }});
        JMenuItem removeItem=new JMenuItem("Remove Item");
        menuItem.add(removeItem);
        removeItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new DeleteItemClass(); 
               
        }});
        JMenuItem updateItem=new JMenuItem("Update Item");
        menuItem.add(updateItem);
        updateItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new UpdateItemClass(); 
               
        }});
        JMenuItem viewItem=new JMenuItem("View Item");
        menuItem.add(viewItem);
        viewItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewItemClass(); 
               
        }});
    }
    public void creatingStockMenu()
    {
        JMenu menuStock=new JMenu("Stock");
        jmb.add(menuStock);
        JMenuItem viewStock=new JMenuItem("View Stock");
        menuStock.add(viewStock);
        viewStock.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewStockClass(); 
               
        }});
    }
    public void creatingBillMenu()
    {
        JMenu menuBill=new JMenu("Bill");
        jmb.add(menuBill);
        JMenuItem createBill=new JMenuItem("Create Bill");
        menuBill.add(createBill);
        JMenuItem viewBill=new JMenuItem("View Bill");
        menuBill.add(viewBill);
        viewBill.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewBillClass(); 
               
        }});
    }
    public void displayDateTime()
    {   try
        {   System.out.println("entered");
            stm=con.createStatement();
            String sql="select lastAccessTime from masterEmployee where empId='"+empIdreceived+"'";
            rs=stm.executeQuery(sql);
            while(rs.next())
            {   System.out.println("getting");
                date=rs.getString("lastAccessTime");
            }
            rs.close();
            System.out.println(date);
            jbl.setText(date);
            jbl.setBounds(410,0, 150, 30);
            jbl1.setBounds(300,0,200,30);
            jfrm.add(jbl1);
            jfrm.add(jbl);
            System.out.println(date);
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
            System.out.println(timeStamp);
            PreparedStatement ps=con.prepareStatement("Update masterEmployee SET lastAccessTime= ? WHERE empId=? ");
            ps.setString(1, timeStamp);
            ps.setString(2,empIdreceived);
            ps.executeUpdate();
            
        }
        catch(Exception e)
        {
           System.out.println(e);
        }
        
    }
    public void creatingSalesMenu()
    {
        JMenu menuSales=new JMenu("Sales");
        jmb.add(menuSales);
        JMenuItem viewSales=new JMenuItem("View Sales");
        menuSales.add(viewSales);
    }
    
}
