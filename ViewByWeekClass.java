
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

public class ViewByWeekClass {
   
    private Connection con=null;
    private JFrame jfrm2=new JFrame("View Report By Week");
    private JPanel jpan=new JPanel();
    int getMonth1,getMonth2;
    int getYear1,getYear2,getDate1,getDate2;
    
    JComboBox month1=new JComboBox();
    JComboBox year1=new JComboBox();
    JComboBox date1=new JComboBox();
    JComboBox month2=new JComboBox();
    JComboBox year2=new JComboBox();
    JComboBox date2=new JComboBox();
    JLabel from=new JLabel("From:");
    JLabel to=new JLabel("To:");
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    JPanel jpn=new JPanel();
    JButton viewButton=new JButton("View");
    public ViewByWeekClass(Connection con){
        this.con=con;
        getDate();
      
   
  
   
}
    public void getDate()
      {     jfrm2.setSize(1000,1000);
        jfrm2.setVisible(true);
        jpan.setLayout(null);
        from.setBounds(250, 50, 50, 30);
        year1.setBounds(500,50,80,30);
        month1.setBounds(400,50,50, 30);
        date1.setBounds(300,50,50 , 30);
        to.setBounds(250, 100, 50, 30);
        year2.setBounds(500,100,80,30);
        month2.setBounds(400,100,50, 30);
        date2.setBounds(300,100,50 , 30);
        viewButton.setBounds(600, 100, 100, 30);
        getSelectedComboItem();
        
        jpan.add(month1);
        jpan.add(year1);
        jpan.add(date1);
        jpan.add(month2);
        jpan.add(year2);
        jpan.add(date2);
        jpan.add(from);
        jpan.add(to);
        jpan.add(viewButton);
        
        jfrm2.add(jpan);
        jpan.add(jpn);
       
      
      }
    public void getSelectedComboItem()
    {
        for(int i=01;i<=12;i++)
        {
            month1.addItem(i);
            month2.addItem(i);
        }
        for(int j=1990;j<=2016;j++)
        {
            year1.addItem(j);
            year2.addItem(j);
        }
        for(int k=01;k<=31;k++)
        {
            date1.addItem(k);
            date2.addItem(k);
        }
        date1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getDate1=Integer.parseInt(String.valueOf(date1.getSelectedItem()));
                    
        }
        });
        
        date2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getDate2=Integer.parseInt(String.valueOf(date2.getSelectedItem()));
                    
        }
        });
        month1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getMonth1=Integer.parseInt(String.valueOf(month1.getSelectedItem()));
                    
        }
        });
        month2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getMonth2=Integer.parseInt(String.valueOf(month2.getSelectedItem()));
                    
        }
        });
         year1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getYear1=Integer.parseInt(String.valueOf(year1.getSelectedItem()));
                    
        }
        });
         year2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                getYear2=Integer.parseInt(String.valueOf(year2.getSelectedItem()));
                   
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
           
            String fromDateString=getDate1+"-"+getMonth1+"-"+getYear1;
            Date fromdate = formatter.parse(fromDateString);
            
            String toDateString=getDate2+"-"+getMonth2+"-"+getYear2;
            Date todate = formatter.parse(toDateString);
            String sql = "SELECT * FROM purchase WHERE purchaseDate BETWEEN ? and ?";
            
           
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
           ResultSet rs=ps.executeQuery();
            
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            
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
        
        }
        
        
        jpn.add(table);
        
        
        jfrm2.add(jpan);
        jpn.setBounds(50, 200,1000,900);
        JScrollPane scrollPane = new JScrollPane(table);
        jpn.add(scrollPane);
        scrollPane.setBounds(50,200,1000,900);
        table.getTableHeader().setReorderingAllowed(false);
 }
    
        
    
    
    

}

    
    


