
package PurchasePackage;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;

public class ViewByMonthClass {
    
    private Connection con=null;
    private JFrame jfrm2=new JFrame("View Report By Month");
    private JPanel jpan=new JPanel();
    int getMonth;
    int getYear;
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    JComboBox month=new JComboBox();
    JComboBox year=new JComboBox();
    
    JPanel jpn=new JPanel();
    JButton viewButton=new JButton("View");
    public ViewByMonthClass(Connection con){
        this.con=con;
        getDate();
      
  
}
    public void getDate()
      {     jfrm2.setSize(1000,1000);
        jfrm2.setVisible(true);
        jpan.setLayout(null);
        year.setBounds(500,50,80,30);
        month.setBounds(400,50,50, 30);
        viewButton.setBounds(600, 50, 100, 30);
        getSelectedComboItem();
        
        jpan.add(month);
        jpan.add(year);
        jpan.add(viewButton);
        
        jfrm2.add(jpan);
        jpan.add(jpn);
      
      
      }
    public void getSelectedComboItem()
    {
        for(int i=1;i<=12;i++)
        {
            month.addItem(i);
        }
        for(int j=1990;j<=2016;j++)
        {
            year.addItem(j);
        }
        month.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getMonth=Integer.parseInt(String.valueOf(month.getSelectedItem()));
                    
        }
        });
         year.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getYear=Integer.parseInt(String.valueOf(year.getSelectedItem()));
                    
        }
        });
        viewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                
                addViewFunction();
        }
        });
    }
    public void addViewFunction(){
       
        Vector columnNames = new Vector();
        Vector data = new Vector();
         try {
            String fromDateString="01"+"-"+getMonth+"-"+getYear;
            Date fromdate = formatter.parse(fromDateString);
           
            String toDateString="31"+"-"+getMonth+"-"+getYear;
            Date todate = formatter.parse(toDateString);
           
            String sql = "SELECT * FROM purchase WHERE purchaseDate BETWEEN ? and ?" ;
           
            
            Statement stmt = con.createStatement();
            columnNames.addElement("SNO.");
            columnNames.addElement("ITEM ID");
            columnNames.addElement("SUPPLIER ID");
            columnNames.addElement("QUANTITY");
            columnNames.addElement("SUM");
            columnNames.addElement("DATE");
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(fromdate.getTime()));
            ps.setDate(2, new java.sql.Date(todate.getTime()));
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
           
         
        while (rs.next()) {
            System.out.println("got it");
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
        
        }
        
        
        jpn.add(table);
        
        
        jfrm2.add(jpan);
        jpn.setBounds(100, 100,900,900);
        JScrollPane scrollPane = new JScrollPane(table);
        jpn.add(scrollPane);
        scrollPane.setBounds(100,100,900,900);
        table.getTableHeader().setReorderingAllowed(false);
 }
    
        
    
    
   

}

    
    


