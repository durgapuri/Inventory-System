package LoginPackage;

import LoginPackage.login;
import LoginPackage.EncDecrptClass;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class userClass {
    
    JFrame jfrm;
    JPanel jpane;
    JLabel empName ;
    JLabel role;
    JLabel createpassLab;
    JLabel confirmpassLab;
   
    private JTextField  empNameText;
    private JPasswordField createpassText;
    private JPasswordField confirmpassText;
    JComboBox jcom;
    
    JButton registerbutton;
    
    String DBDriver="net.ucanaccess.jdbc.UcanaccessDriver";
    String DBSource="jdbc:ucanaccess://C:\\Users\\me\\Desktop\\New folder\\databaseinv.accdb";
    Connection con;
    
    public userClass(){
    
    jfrm=new JFrame("Register");
    jfrm.setSize(500,400);
    
    jfrm.setVisible(true);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    components();
    
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
        System.err.println("Unnable to connect");
        System.out.println(e);
        System.exit(1);
    }
     
}
    public void components(){
        
        jpane=new JPanel();
        jfrm.add(jpane);
        jpane.setLayout(new GridBagLayout());
        GridBagConstraints gb=new GridBagConstraints();
        gb.insets=new Insets(5,5,5,5);
        
        empComponent(jpane,gb);
        passwordComponent(jpane,gb);
        roleComponent(jpane,gb);
        registerComponent(jpane,gb);
        
    }
    public void empComponent(JPanel jpane,GridBagConstraints gb){
        
        empName=new JLabel("Employee Name");
        gb.gridx=0;
        gb.gridy=0;
        jpane.add(empName,gb);
        
        empNameText=new JTextField();
        gb.gridx=8;
        gb.gridy=0;
        empNameText.setPreferredSize(new Dimension(180,25));
        jpane.add(empNameText,gb);
    }
    public void passwordComponent(JPanel jpane,GridBagConstraints gb){
        
        createpassLab=new JLabel("Create Password ");
        gb.gridx=0;
        gb.gridy=10;
        jpane.add(createpassLab,gb);
        
        createpassText=new JPasswordField();
        gb.gridx=8;
        gb.gridy=10;
        createpassText.setPreferredSize(new Dimension(180,25));
        jpane.add(createpassText,gb);
        
        confirmpassLab=new JLabel("Confirm Password");
        gb.gridx=0;
        gb.gridy=20;
        jpane.add(confirmpassLab,gb);
        
        confirmpassText=new JPasswordField();
        gb.gridx=8;
        gb.gridy=20;
        confirmpassText.setPreferredSize(new Dimension(180,25));
        //System.out.println("ghgj");
        jpane.add(confirmpassText,gb);
        
    }
    public void roleComponent(JPanel jpane,GridBagConstraints gb){
         
        role=new JLabel("Role");
        gb.gridx=0;
        gb.gridy=30;
        jpane.add(role,gb);
        
        String []role={"Admin","Manager","RetailShopEmployee"};
        jcom=new JComboBox();
        for(String x : role){
            jcom.addItem(x);
        }
        gb.gridx=8;
        gb.gridy=30;
        jcom.setPreferredSize(new Dimension(180,25));
        jpane.add(jcom,gb);
       }
    public void registerComponent(JPanel jpane,GridBagConstraints gb){
        
        registerbutton=new JButton("Register");
        gb.gridx=20;
        gb.gridy=40;
        jpane.add(registerbutton,gb);
        
        registerbutton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent ae){
             int i=registerStatus();
              if(i!=0){
                  JOptionPane.showMessageDialog(null,"Registeration successful: Employee id generated :"+i);
                  jfrm.dispose();
                  login log=new login();
                  
              }
              else if(i==0){
                  
              }
              else {
                  JOptionPane.showMessageDialog(null,"Registration unsuccessful:Please register again");
                  jfrm.dispose();
                  new userClass();
                  
              }
          }  
        });
    }  
 
        
public int registerStatus(){
           
        Statement stm=null;
        ResultSet rs=null;
        String sql=null;
        int count=0;
        try{
            stm=con.createStatement();
             String empName=empNameText.getText().trim();
             System.out.println(empName);
             String createpass = new String(createpassText.getPassword());
             String confirmpass = new String(confirmpassText.getPassword());
             String x=jcom.getSelectedItem().toString();
             String sql2;
             if((empName.isEmpty()) || (createpass.isEmpty())|| (confirmpass.isEmpty()))
             {
                
               JOptionPane.showMessageDialog(null,"All details are manadatory:Please fill again");
               return 0;
             }
           
             
             if(createpass.equals(confirmpass)){
                 
                sql2="SELECT * FROM masterEmployee WHERE password= '"+confirmpass+"'";
                rs=stm.executeQuery(sql2);
                while(rs.next()){
                     System.out.println("kkl");
                    count+=1;
                }
                if(count!=0){
                    System.out.println("lll");
                 JOptionPane.showMessageDialog(null,"Password too weak: Enter again");
                 return 0;
                }
                 String encpass=EncDecrptClass.encrypt(confirmpass);
                 sql="INSERT INTO `masterEmployee`(password,role) VALUES ('"+encpass+"','"+x+"')";
                 System.out.println(sql);
                 stm.executeUpdate(sql);
            //  
                 
                 String sql1="SELECT e.empId FROM masterEmployee as e WHERE e.password='"+encpass+"'";
                 ResultSet rs1=stm.executeQuery(sql1);
                int in;
                while(rs1.next())
                    {       in=rs1.getInt("empId");
                            System.out.println(in);
                            return in;
                    }
                
                }
             else{
                 
                    
                 JOptionPane.showMessageDialog(null,"Password doesn't match:Please register again");
                 return 0;
                }
          return 0;                 
                           
        }
        catch(Exception e){
          return 0;  
        }
            
}
       
            
                               
            
       /* public static void main(String args[])
    {
        new userClass();
    }*/
    
    
}

