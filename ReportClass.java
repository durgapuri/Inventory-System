
package PurchasePackage;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class ReportClass {
    
    private Connection con=null;
    private JComboBox jcomboBox=new JComboBox();
    private JFrame jfrm2=new JFrame("Report");
    String selected;
    private JPanel jpan=new JPanel();
    
    
    public ReportClass(Connection con){
        this.con=con;
        jfrm2.setSize(500,300);
        jfrm2.setVisible(true);
        jfrm2.setLocationRelativeTo(null);
        viewDuration();
    
}
    public void viewDuration()
    {   
        jcomboBox.setBounds(275,550,100,25);
        jpan.add(jcomboBox);
        jfrm2.add(jpan);
        
        jcomboBox.addItem("View By Date");
        jcomboBox.addItem("View By Week");
        jcomboBox.addItem("View By Month");
        jcomboBox.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent ae){
                selected=String.valueOf(jcomboBox.getSelectedItem());
                System.out.println(selected);
          
        
        if(selected=="View By Date")
        {
            System.out.println("zindagi");
            new ViewByDateClass(con);
            
        }
        else if(selected=="View By Week")
        {
            new ViewByWeekClass(con);
           
        } 
        else
        {
            new ViewByMonthClass(con);
            
        }
      }});  
    }
   /* public void addViewFunction(){
       
        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {

            String sql = "Select * from employee";
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
    */
        
    
    
   
    }



    
    


