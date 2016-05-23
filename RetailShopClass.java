
package RetailShopPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import ItemPackage.*;
import BillPackage.*;

public class RetailShopClass {
    ResultSet rs=null;
    Statement stm=null;
    Connection con=null;
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    JFrame jfrm=new JFrame("Retail Shop Employee");
    JMenuBar jmb=new JMenuBar();
    public RetailShopClass()
    {   connect();
        setFrameBoundaries();
        creatingItemMenu();
        creatingBillMenu();
        
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
        //jfrm.setDefaultCloseOperation(JFrame.);
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
        JMenuItem removeItem=new JMenuItem("Remove Item");
        item.add(removeItem);
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
    /* public static void main(String args[])
    {
        new RetailShopClass();
    }*/
            
            
    
}
