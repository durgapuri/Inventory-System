
package ItemPackage;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel.*;

   public class DeleteItemClass {
               
    //private String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    //private String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    
    private Connection con=null;
    private Statement stm=null;
    
    private int deleted;
    private String [] itemDetails= new String[4];
    private String itemId;
               
    private JFrame jfrm1=new JFrame("Delete Item");
    private JLabel itemNameLabel=new JLabel("Item Name");
    private JButton jbn=new JButton("Delete");
    private JPanel jpn=new JPanel(new BorderLayout()),
    jpan=new JPanel();
    private DefaultTableModel tblModel;
    private JTable table= new JTable();
    private JScrollPane scrollPane = new JScrollPane(table);
                                               
                                               
    private ResultSet rs=null;
    private final JComboBox jComboBox1=new JComboBox();
   
    public DeleteItemClass(Connection con)
    {  
        this.con=con;       
        setLayoutBoundaries();
       
       /* try
                                                {
                                                                Class.forName(driver);
                                                                con=DriverManager.getConnection(source);
                                                                System.out.println("connected successfully");
           
                                                }catch(ClassNotFoundException e){
                                                               
                                                                System.err.println("Failed To Load Driver");
                                                                System.out.println(e);
                                                                System.exit(1);
                                                               
                                                }catch(SQLException e){  
                                                               
                                                                System.err.println("Unable To Connect");
                                                                System.out.println(e);
                                                                System.exit(1);
                                                }*/
        
         addingToComboBox();
         addComponents();
         getItemDetails();
         delete();
        
    }
               
               
               
    public void setLayoutBoundaries(){  
               
        jpan.setLayout(null);
        //jfrm1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm1.setSize(700,300);
        itemNameLabel.setBounds(175,50,100,25);
        jComboBox1.setBounds(275,50,100,25);       
        jpn.setBounds(50,100,600,50);
        jbn.setBounds(275,175,100,30);
        scrollPane.setBounds(200,100,1200,400);
    }
               
               
               
    public void addComponents(){
 
        jpan.add(itemNameLabel);
        jComboBox1.setVisible(true);
        jpan.add(jComboBox1);
        jfrm1.add(jpan);
        jpan.add(jpn);
        jpan.add(jbn);
        jpn.add(scrollPane);
        table.getTableHeader().setReorderingAllowed(false);
        jfrm1.setLocationRelativeTo(null);   // Used it place Jframe on center of screen , you can use it in other modules too
        jfrm1.setVisible(true);
       
    }
               
               
               
               
    public void addingToComboBox()
    {  
        try
       {
            stm=con.createStatement();
            rs = stm.executeQuery("select itemName from item");
                                                               
            while(rs.next()){
                String sc= rs.getString("itemName");
                jComboBox1.addItem(sc);
            }
                                                               
            rs.close();
                                                               
        }catch(Exception e){
            System.out.println(e);
       }
  }
               
               
    public void getItemDetails(){
        jComboBox1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                viewDetailsOfItem(String.valueOf(jComboBox1.getSelectedItem()));
                    table.setModel(tblModel);
                    tblModel.fireTableDataChanged();
        }
        });
  }
               
        private void setColumns() {
        tblModel.setColumnCount(0);
        tblModel.addColumn("ITEM NAME");
        tblModel.addColumn("ITEM TYPE");
        tblModel.addColumn("ITEM COMPANY NAME");
        tblModel.addColumn("ITEM STOCK STATUS");
       }
               
               
               
    public void viewDetailsOfItem(String Name)
    {  
                    
       tblModel = (DefaultTableModel) table.getModel();
        setColumns();
        try {
 
            String sql = "Select a.itemName,a.itemType,a.itemId," +"a.itemCompanyName,b.itemStock from item a, "+"stock b where a.itemName='"+Name+"'"+"and a.itemId=b.itemId";
                                                                                               
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
                                               
        while (rs.next()) {
                                                               
           itemId = rs.getString("itemId").trim();
                                                               
           tblModel.setRowCount(0);
            itemDetails[0] = rs.getString("itemName").trim();
           itemDetails[1] = rs.getString("itemType").trim();
           itemDetails[2] = rs.getString("itemCompanyName").trim();
           itemDetails[3] = rs.getString("itemStock").trim();
                                                               
            tblModel.insertRow(tblModel.getRowCount(),itemDetails);
        }
        rs.close();
        stmt.close();
       }catch(Exception e){
                System.out.println(e);
        }
                                               
 
    }
               
               
               
    public void delete()
    { jbn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                if(Integer.parseInt(itemDetails[3])==0){
                try
                {  
                                                                               
                    stm=con.createStatement();
                    String st="Delete from stock where itemId='"+itemId+"'";
                    stm.executeUpdate(st);
                                                                                               
                    st="Delete from purchase where itemId='"+itemId+"'";
                    stm.executeUpdate(st);
                                                                                               
                    st="Delete from sale where itemId='"+itemId+"'";
                    stm.executeUpdate(st);
                                                                                               
                    st="Delete from item where itemId='"+itemId+"'";
                    stm.executeUpdate(st);
                    JOptionPane.showMessageDialog(null,"Item Deleted");
                    jfrm1.dispose();
               }catch(Exception e){
               System.out.println(e);
               JOptionPane.showMessageDialog(null,"Item not Deleted","Delete Operation Failed",JOptionPane.ERROR_MESSAGE);
           }
           }else{
                                                                               
                JOptionPane.showMessageDialog(null,"You can not detete item : "+itemDetails[0]+"\n\n Wait till out of stock",itemDetails[0]+" still in stock",JOptionPane.ERROR_MESSAGE);
           }
            }
       
             });
    }
               
               
    /*public static void main(String args[])
    {
        new DeleteItemClass();
    }*/
   
}
    

