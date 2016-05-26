import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import RetailShopPackage.*;
import ManagerPackage.*;
import AdminPackage.*;

public class login {
    
    JFrame jfrm;
    JPanel jpane;
    JLabel empidLab;
    private JTextField  empidText;
    JLabel passLab;
    private JTextField passText;
    JLabel role;
    String empIdpass;
    JComboBox jcom;
    JButton loginbutton;
    String x;
    
    String DBDriver="net.ucanaccess.jdbc.UcanaccessDriver";
    String DBSource="jdbc:ucanaccess://E:\\tcs\\databaseinv.accdb";
    Connection cn;
    
    public login(){
    
    jfrm=new JFrame("Login: ");
    jfrm.setSize(500,400);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jfrm.setVisible(true);
    
    try{
        Class.forName(DBDriver);
        cn=DriverManager.getConnection(DBSource);
        System.out.println("Connected Successfully");
        placeComponents();
        
    }
    catch(ClassNotFoundException e){
        System.err.println("Failed to load driver");
        System.out.println(e);
        System.exit(1);
    }
    catch(SQLException e){
        System.err.println("Unable to connect");
        System.out.println(e);
        System.exit(1);
    }
    
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
        
        loginbutton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent ae){
             
              if(loginStatus()==1){
                  JOptionPane.showMessageDialog(null,"Login Successful");
                  jfrm.dispose();
                  String x = jcom.getSelectedItem().toString();
                  System.out.println(x);
                  if(x=="Manager"){
                  ManagerClass mgcls=new ManagerClass(empIdpass);
              }
                  if(x=="Admin"){
                      AdminClass admcls=new AdminClass(empIdpass);
                  }
                  if(x=="RetailShopEmployee"){
                      RetailShopClass rsemp=new RetailShopClass(empIdpass);
                  }
              }
              else{
                  JOptionPane.showMessageDialog(null,"Invalid user");
                  jfrm.dispose();
                  new login();
                  
              }
          }  
        });
    }
    
        public int loginStatus(){
            
           try{
                Statement st=null;
                ResultSet rs=null;
                String sql=null;
                int count=0;
                   
                st=cn.createStatement();
                String emp=empidText.getText().trim();
                String pass=passText.getText().trim();
                String x = jcom.getSelectedItem().toString();
                System.out.println(x);
                
                sql="select empId,password from masterEmployee where empId= '"+emp+"' and password= '"+pass+"' and role= '"+x+"'";
                rs=st.executeQuery(sql);
                while(rs.next()){
                    count+=1;
                }
                empIdpass=emp;
                return count;
            }
                catch(Exception e){
                    System.out.print(e);
                    return 0;
               }
        } 
        
    

    public static void main(String []args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new login();
            }
        });
    }
}