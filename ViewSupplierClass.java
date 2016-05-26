
package SupplierPackage;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class ViewSupplierClass {
    
    String driver="net.ucanaccess.jdbc.UcanaccessDriver";
    String source="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    Connection con=null;
    JFrame jfrm2=new JFrame("View Item");
    
    JPanel jpan=new JPanel();
    
    public ViewSupplierClass(){
        jfrm2.setSize(500,300);
        jfrm2.setVisible(true);
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
        viewSupplierDetail();
    }
    public void viewSupplierDetail(){
        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {

            String sql = "Select * from supplierDetails";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement( md.getColumnName(i) );
            }
        while (rs.next()) {
                Vector row = new Vector(columns);
        for (int i = 1; i <= columns; i++){
                row.addElement( rs.getObject(i) );
        }
        data.addElement( row );
        }
        rs.close();
        stmt.close();
        }
        catch(Exception e){
                System.out.println(e);
        }
        JTable table = new JTable(data, columnNames)
        {
            public boolean isCellEditable(int row,int cloumns){
            return false;
            }
            
        };
        TableColumn col;
        for (int i = 0; i < table.getColumnCount(); i++) {
                col = table.getColumnModel().getColumn(i);
        col.setMaxWidth(250);
        }
        
        JPanel jp=new JPanel();
        jp.add(table);
        
        
        jfrm2.add(jp);
        JScrollPane scrollPane = new JScrollPane(table);
        jp.add(scrollPane);
        scrollPane.setBounds(200,100,900,300);
        table.getTableHeader().setReorderingAllowed(false);
 }
    
        
    
    
   /* public static void main(String []args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new ViewSupplierClass();
            }
        });
    }*/

}

        
    

