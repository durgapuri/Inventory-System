
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

public class ViewByDateClass {
    
    private Connection con=null;
    private JFrame jfrm2=new JFrame("View Report By Date");
    private JPanel jpan=new JPanel();
    int getMonth1;
    int getYear1,getDate1;
    JComboBox month1=new JComboBox();
    JComboBox year1=new JComboBox();
    JComboBox date1=new JComboBox();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    JPanel jpn=new JPanel();
    JButton viewButton=new JButton("View");
    public ViewByDateClass(Connection con){
        this.con=con;
        getDate();
}
    public void getDate()
      { jfrm2.setSize(1000,1000);
        jfrm2.setVisible(true);
        jpan.setLayout(null);
        year1.setBounds(500,50,80,30);
        month1.setBounds(400,50,50, 30);
        date1.setBounds(300,50,50 , 30);
        viewButton.setBounds(600, 50, 100, 30);
        getSelectedComboItem();
        
        jpan.add(month1);
        jpan.add(year1);
        jpan.add(date1);
        
        jpan.add(viewButton);
        
        jfrm2.add(jpan);
        jpan.add(jpn);
       
      
      }
    public void getSelectedComboItem()
    {
        for(int i=01;i<=12;i++)
        {
            month1.addItem(i);
            
        }
        for(int j=1990;j<=2016;j++)
        {
            year1.addItem(j);
            
        }
        for(int k=01;k<=31;k++)
        {
            date1.addItem(k);
            
        }
        date1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getDate1=Integer.parseInt(String.valueOf(date1.getSelectedItem()));
                    
        }
        });
        
        month1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getMonth1=Integer.parseInt(String.valueOf(month1.getSelectedItem()));
                    
        }
        });
        
         year1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getYear1=Integer.parseInt(String.valueOf(year1.getSelectedItem()));
                    
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
           
            String onDateString=getDate1+"-"+getMonth1+"-"+getYear1;
            Date ondate = formatter.parse(onDateString);
            
            String sql = "SELECT * FROM purchase WHERE purchaseDate = ?";
            
            System.out.println("executed1");
            Statement stmt = con.createStatement();
            columnNames.addElement("SNO.");
            columnNames.addElement("ITEM ID");
            columnNames.addElement("SUPPLIER ID");
            columnNames.addElement("QUANTITY");
            columnNames.addElement("SUM");
            
           PreparedStatement ps=con.prepareStatement(sql);
           ps.setDate(1, new java.sql.Date(ondate.getTime()));
           
           ResultSet rs=ps.executeQuery();
            
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
           
         System.out.println(columns);  
        while (rs.next()) {
            System.out.println("got it");
                Vector row = new Vector(columns);
        for (int i = 1; i < columns; i++){
                row.addElement( rs.getObject(i) );
        }
        data.addElement( row );
        }
        System.out.println("ni aayi");
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
        for (int i = 0; i < (table.getColumnCount()-1); i++) {
                col = table.getColumnModel().getColumn(i);
        
        }
        
        
        jpn.add(table);
        
        
        jfrm2.add(jpan);
        jpn.setBounds(100, 150,900,900);
        JScrollPane scrollPane = new JScrollPane(table);
        jpn.add(scrollPane);
        scrollPane.setBounds(100,150,900,900);
        table.getTableHeader().setReorderingAllowed(false);
 }
    
        
    
    
  
}

    
    


