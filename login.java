
//package javaapplication2;
 
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import RetailShopPackage.*;
import AdminPackage.*;
import ManagerPackage.*;

public class login {
    
    JFrame jfrm;
    JPanel jpane;
    JLabel empidLab;
    JTextField  empidText;
    JLabel passLab;
    JTextField passText;
    JLabel role;
    JComboBox jcom;
    JButton loginbutton;
    
    
    public login(){
    
    jfrm=new JFrame("Login: ");
    jfrm.setSize(500,400);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jfrm.setVisible(true);
    
    
    
    placeComponents();
    
    
}
    public void placeComponents(){
        
        jpane=new JPanel();
        jfrm.add(jpane);
        jpane.setLayout(new GridBagLayout());
        GridBagConstraints gb=new GridBagConstraints();
        gb.insets=new Insets(5,5,5,5);
        
        empComponent(jpane,gb);
        passwordComponent(jpane,gb);
        roleComponent(jpane,gb);
        loginComponent(jpane,gb);
        RetailShopClass rs=new RetailShopClass();
        AdminClass ac=new AdminClass();
        ManagerClass mc=new ManagerClass();
        
        
    }
    
    public void empComponent(JPanel jpane,GridBagConstraints gb){
        
        empidLab=new JLabel("Employee Id");
        gb.gridx=0;
        gb.gridy=0;
        jpane.add(empidLab,gb);
        
        empidText=new JTextField();
        gb.gridx=8;
        gb.gridy=0;
        empidText.setPreferredSize(new Dimension(180,25));
        jpane.add(empidText,gb);
        
    }
     public void passwordComponent(JPanel jpane,GridBagConstraints gb){
          
        passLab=new JLabel("Password: ");
        gb.gridx=0;
        gb.gridy=10;
        jpane.add(passLab,gb);
        
        passText=new JTextField();
        gb.gridx=8;
        gb.gridy=10;
        passText.setPreferredSize(new Dimension(180,25));
        jpane.add(passText,gb);
     }
     public void roleComponent(JPanel jpane,GridBagConstraints gb){
         
        role=new JLabel("Role");
        gb.gridx=0;
        gb.gridy=15;
        jpane.add(role,gb);
        
        String []role={"Admin","Manager","RetailShopEmployee"};
        jcom=new JComboBox();
        for(String x : role){
            jcom.addItem(x);
        }
        gb.gridx=8;
        gb.gridy=15;
        jcom.setPreferredSize(new Dimension(180,25));
        jpane.add(jcom,gb);
       }
        
    public void loginComponent(JPanel jpane,GridBagConstraints gb){
        
        loginbutton=new JButton("Login");
        gb.gridx=20;
        gb.gridy=25;
        jpane.add(loginbutton,gb);
        
        dbconnection(jpane);
    }
    public void dbconnection(JPanel jpane){
        loginbutton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               try{
                   Connection cn=null;
                   Statement st=null;
                   ResultSet rs=null;
                   String sql=null;
                   
                   String DBDriver="net.ucanaccess.jdbc.UcanaccessDriver";
                   String DBSource="jdbc:ucanaccess://C:\\Users\\me\\Desktop\\New folder\\database1.accdb";
                   Class.forName(DBDriver);
                   cn=DriverManager.getConnection(DBSource);
                   st=cn.createStatement();
                   String emp=empidText.getText().trim();
                   String pass=passText.getText().trim();
                   
                   sql="select empId,password from masterEmployee where empId '"+emp+"' and password '"+pass+"'";
                   rs=st.executeQuery(sql);
                   int count=0;
                   while(rs.next()){
                       count+=1;
                   }
                   if(count==1)
                       JOptionPane.showMessageDialog(null,"Login Successful");
                   else
                       JOptionPane.showMessageDialog(null,"Invalid User");
               }
               catch(Exception e){
                   
               }
               
           } 
        });
    }

    public static void main(String []args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new login();
            }
        });
    }
}
