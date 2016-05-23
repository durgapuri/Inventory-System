
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

public class AdminClass {
    JFrame jfrm=new JFrame("Admin");
    JMenuBar jmb=new JMenuBar();
    public AdminClass()
    {   settingFrameBoundaries();
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
        jfrm.setSize(500,300);
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
        JMenuItem removeEmployee=new JMenuItem("Remove Employee");
        menuEmployee.add(removeEmployee);
        JMenuItem updateEmployee=new JMenuItem("Update Employee");
        menuEmployee.add(updateEmployee);
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
        JMenuItem updateCust=new JMenuItem("Update Customer");
        menuCust.add(updateCust);
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
        JMenuItem updateSup=new JMenuItem("Update Supplier");
        menuSup.add(updateSup);
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
        JMenuItem updateItem=new JMenuItem("Update Item");
        menuItem.add(updateItem);
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
    public void creatingSalesMenu()
    {
        JMenu menuSales=new JMenu("Sales");
        jmb.add(menuSales);
        JMenuItem viewSales=new JMenuItem("View Sales");
        menuSales.add(viewSales);
    }
    /* public static void main(String args[])
    {
        new AdminClass();
    }*/
}
