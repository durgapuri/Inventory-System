
package RetailShopPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import ItemPackage.*;
import BillPackage.*;
import EmployeePackage.ViewEmployeeClass;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RetailShopClass {
    ResultSet rs=null;
    Statement stm=null;
    Connection con=null;
    String date;
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    JFrame jfrm=new JFrame("Retail Shop Employee");
    JMenuBar jmb=new JMenuBar();
    String empIdreceived;
    JLabel jbl=new JLabel();
    JLabel jbl1=new JLabel("Last Access Time:");
    public RetailShopClass(String empIdreceived)
    {   this.empIdreceived=empIdreceived;
        connect();
        setFrameBoundaries();
        creatingItemMenu();
        creatingBillMenu();
        displayDateTime();
        jfrm.setVisible(true);
        jfrm.setJMenuBar(jmb);
    }
    public void connect()
    {
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
    }
    public void setFrameBoundaries()
    {
        jfrm.setSize(500,300);
        
        jfrm.setLayout(null);
        jfrm.setLocationRelativeTo(null);
        jfrm.dispose();
    }
    public void creatingItemMenu()
    {
        JMenu item=new JMenu("Item");
        jmb.add(item);
        JMenuItem addItem=new JMenuItem("Add Item");
        item.add(addItem);
        addItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new AddItemClass(); 
               
        }});
        JMenuItem removeItem=new JMenuItem("Remove Item");
        item.add(removeItem);
        removeItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new DeleteItemClass(); 
               
        }});
        JMenuItem viewItem=new JMenuItem("View Item");
        item.add(viewItem);
        addItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new AddItemClass(); 
               
        }});
        viewItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
               new ViewItemClass(); 
               
        }});
        
    }
    public void creatingBillMenu()
    {
        JMenu bill=new JMenu("Bill");
        jmb.add(bill);
        JMenuItem createBill=new JMenuItem("Create Bill");
        bill.add(createBill);
        JMenuItem viewBill=new JMenuItem("View Bill");
        bill.add(viewBill);
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
    
    /* public static void main(String args[])
    {
        new RetailShopClass();
    }*/
            
            
    
}
