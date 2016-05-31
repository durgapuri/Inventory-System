
package AdminPackage;

import ItemPackage.AddItemClass;
import ItemPackage.ViewItemClass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import StockPackage.*;
import BillPackage.*;
import EmployeePackage.*;
import CustomerPackage.*;
import SupplierPackage.*;
import ItemPackage.*;
import BillPackage.*;
import java.sql.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class AdminClass {
    //String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    //String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    
    Connection con=null;
    ResultSet rs=null;
    Statement stm=null;
    
    JFrame jfrm=new JFrame("Admin");
    JMenuBar jmb=new JMenuBar();
    String empIdreceived;
    String date;
    JLabel jbl=new JLabel();
    JLabel jbl1=new JLabel("Last Access Time:");
    
    public AdminClass(String empIdreceived,Connection con)
    {   
        this.empIdreceived=empIdreceived;
        this.con=con;
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
        settingFrameBoundaries();
        displayDateTime();
        creatingEmployeeMenu();
        creatingCustomerMenu();
        creatingSupplierMenu();
        creatingItemMenu();
        creatingStockMenu();
        creatingBillMenu();
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
        JMenuItem addEmployee=new JMenuItem("Add Employee");
        menuEmployee.add(addEmployee);
         addEmployee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new AddEmployeeClass(con); 
               
        }});
        JMenuItem removeEmployee=new JMenuItem("Remove Employee");
        menuEmployee.add(removeEmployee);
         removeEmployee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new DeleteEmployeeClass(con); 
               
        }});
        JMenuItem updateEmployee=new JMenuItem("Update Employee");
        menuEmployee.add(updateEmployee);
        updateEmployee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new UpdateEmployeeClass(con); 
               
        }});
        JMenuItem viewEmployee=new JMenuItem("View Employee Details");
        menuEmployee.add(viewEmployee);
        viewEmployee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewEmployeeClass(con); 
               
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
               new AddCustomerClass(con); 
               
        }});
        JMenuItem removeCust=new JMenuItem("Remove Customer");
        menuCust.add(removeCust);
         removeCust.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new DeleteCustomerClass(con); 
               
        }});
        JMenuItem updateCust=new JMenuItem("Update Customer");
        menuCust.add(updateCust);
        updateCust.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new UpdateCustomerClass(con); 
               
        }});
        JMenuItem viewCust=new JMenuItem("View Customer");
        menuCust.add(viewCust);
        viewCust.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewCustomerClass(con); 
               
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
               new AddSupplier(con); 
               
        }});
        JMenuItem removeSup=new JMenuItem("Remove Supplier");
        menuSup.add(removeSup);
         removeSup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new DeleteSupplierClass(con); 
               
        }});
        JMenuItem updateSup=new JMenuItem("Update Supplier");
        menuSup.add(updateSup);
        updateSup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new UpdateSupplierClass(con); 
               
        }});
        JMenuItem viewSup=new JMenuItem("View Suppliers");
        menuSup.add(viewSup);
        viewSup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewSupplierClass(con); 
               
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
               new AddItemClass(con); 
               
        }});
        JMenuItem removeItem=new JMenuItem("Remove Item");
        menuItem.add(removeItem);
        removeItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new DeleteItemClass(con);
               
        }});
        JMenuItem updateItem=new JMenuItem("Update Item");
        menuItem.add(updateItem);
        updateItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new UpdateItemClass(con); 
               
        }});
        JMenuItem viewItem=new JMenuItem("View Item");
        menuItem.add(viewItem);
        viewItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewItemClass(con); 
               
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
               new ViewStockClass(con); 
               
        }});
    }
    public void creatingBillMenu()
    {
        JMenu menuBill=new JMenu("Bill");
        jmb.add(menuBill);
        JMenuItem createBill=new JMenuItem("Create Bill");
        menuBill.add(createBill);
        createBill.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new CreateBillClass(); 
               
        }});
        JMenuItem viewBill=new JMenuItem("View Bill");
        menuBill.add(viewBill);
        viewBill.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewBillClass(con); 
               
        }});
    }
    public void creatingSalesMenu()
    {
        JMenu menuSales=new JMenu("Sales");
        jmb.add(menuSales);
        JMenuItem viewSales=new JMenuItem("View Sales");
        menuSales.add(viewSales);
    }
   public void displayDateTime()
    {   try
        {   System.out.println("entered");
            stm=con.createStatement();
            System.out.println("statement created");
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
    
   
   /* public static void main(String args[])
    {
        new AdminClass();
    }*/
}
