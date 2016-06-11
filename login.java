package LoginPackage;

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
    
    private JFrame jfrm;
    private JPanel jpane;
    private JLabel empidLab,role,passLab;
    private JTextField  empidText;
    private JPasswordField passText;
    private JComboBox jcom;
    private JButton loginbutton,newUser;
    private String DBDriver="net.ucanaccess.jdbc.UcanaccessDriver",
            DBSource="jdbc:ucanaccess://C:\\Users\\me\\Desktop\\New folder\\databaseinv1.accdb",x,empIdpass;
    private Connection con;
    
    public login(){
    
    jfrm=new JFrame("Login ");
    jfrm.setSize(500,400);
    jfrm.setLocationRelativeTo(null);
    jfrm.setVisible(true);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    placeComponents();
    
    try{
        Class.forName(DBDriver);
        con=DriverManager.getConnection(DBSource);
        System.out.println("Connected Successfully");
        
        
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
        newUserComponent(jpane,gb);
        
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
        
        passText=new JPasswordField();
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
        
     public void newUserComponent(JPanel jpane,GridBagConstraints gb){
       
        newUser=new JButton("Not Registered: Sign-in");
        gb.gridx=0;
        gb.gridy=30;
        jpane.add(newUser,gb);
        
        newUser.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent ae){
             userClass user=new userClass(con);
             jfrm.dispose();
                       }  
        });   
    }    
	
	
	
    public void loginComponent(JPanel jpane,GridBagConstraints gb){
        
        loginbutton=new JButton("Login");
        gb.gridx=20;
        gb.gridy=30;
        jpane.add(loginbutton,gb);
        
        loginbutton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent ae){
             int value=loginStatus();
              if(value==2){
                  
                  JOptionPane.showMessageDialog(null,"Login Successful");
                  jfrm.dispose();
                  String x = jcom.getSelectedItem().toString();
                  System.out.println(x);
                  if(x=="Manager"){
                  ManagerClass mgcls=new ManagerClass(empIdpass,con);
              }
                  if(x=="Admin"){
                      AdminClass admcls=new AdminClass(empIdpass,con);
                  }
                  if(x=="RetailShopEmployee"){
                      RetailShopClass rsemp=new RetailShopClass(empIdpass,con);
                  }
              }
              else {
                  if(value==1)
                  {
                    JOptionPane.showMessageDialog(null,"Your current status is Deactivated. Please wait till activation is granted","",JOptionPane.ERROR_MESSAGE);
                  jfrm.dispose();
                  new login();  
                  }
              else {
                  
                  System.out.println("prachee");
              }
          } } 
        });
    }
    
	
	
        public int loginStatus(){
            
	     Statement st=null;
             ResultSet rs=null,rs1=null;
             String sql=null,sql1=null;
           try{
                int count=0;
                   
                st=con.createStatement();
                String emp=empidText.getText().trim(),
					   pass = new String(passText.getPassword()),
					   x = jcom.getSelectedItem().toString(),               
					   encpass=EncDecrptClass.encrypt(pass);
                String active="Activated";
                sql="select empId,password from masterEmployee where empId= '"+emp+"' and password= '"+encpass+"' and role= '"+x+"'";
                rs=st.executeQuery(sql);
					while(rs.next()){
						count+=1;
					}
                                        rs.close();
                                        System.out.println("n"+count);
                if(count==1)
                {
                    sql1="select empId from masterEmployee where empId= '"+emp+"'and ActiveStatus='"+active+"'";
                    rs1=st.executeQuery(sql1);
                    while(rs1.next()){
                        count+=1;
                    }
                    return count;
                }
                else{
                    
                    JOptionPane.showMessageDialog(null,"Invalid employee id or password","",JOptionPane.ERROR_MESSAGE);
                  //jfrm.dispose();
                  //new login();
                  return count;
                }
                
            }
                catch(Exception e){
                    System.out.print(e);
                    return 0;
               }
        } 
        
    

   public static void main(String []args){
	try 
    { 
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); 
    } 
    catch(Exception e){
		System.out.println(e);
    }
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new login();
            }
        });
    }
}