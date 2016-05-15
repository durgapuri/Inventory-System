
package RetailShopPackage;
import javax.swing.*;

public class RetailShopClass {
    JFrame jfrm=new JFrame("Retail Shop Employee");
    JMenuBar jmb=new JMenuBar();
    public RetailShopClass()
    {   setFrameBoundaries();
        creatingItemMenu();
        creatingBillMenu();
        jfrm.setVisible(true);
        jfrm.setJMenuBar(jmb);
    }
    public void setFrameBoundaries()
    {
        jfrm.setSize(500,300);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLayout(null);
        jfrm.setLocationRelativeTo(null);
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
        
    }
    public void creatingBillMenu()
    {
        JMenu bill=new JMenu("Bill");
        jmb.add(bill);
        JMenuItem createBill=new JMenuItem("Create Bill");
        bill.add(createBill);
        JMenuItem viewBill=new JMenuItem("View Bill");
        bill.add(viewBill);
    }
            
            
    
}
