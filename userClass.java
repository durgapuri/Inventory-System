
package LoginPackage;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class userClass {
    
    private JFrame jfrm; 
    private JPanel jpane;
    private JLabel empName,  
                            empFName, 
                            empMName, 
                            empLName, 
                            role,     
                            createpassLab, 
                            confirmpassLab, 
                            empIDLabel, 
                            empID, 
                            empAddressLabel,
                            empEmailIDLabel, 
                            msg,
                            empContactNoLabel;
				   
    private JTextField  empNameText,
                                    empFNameText,
                                    empMNameText,
                                    empLNameText,
                                    empAddress,
                                    empPhoneNo,
                                    empEmailID,
                                    empContactNo;
	
    
    
    private JPasswordField createpassText,confirmpassText;
    private JComboBox jcom;
    private JButton registerbutton,
                                    addDetailsButton,
                                    reset;
    private Connection con;   
    private Statement stm;
    private ResultSet rs;
    private String sql;
	
	
    public userClass(Connection con){   
	
		this.con=con;               
    
		jfrm=new JFrame("Register");
		jfrm.setSize(500,400);
		jfrm.setLocationRelativeTo(null);                        
		jfrm.setVisible(true);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		components();
    
     
	}
	
	
	
    void components(){
        
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
	
	
	
	
    void empComponent(JPanel jpane,GridBagConstraints gb){
        
        empName=new JLabel("Employee Name");
        gb.gridx=0;gb.gridy=0;
        jpane.add(empName,gb);
        
        empNameText=new JTextField();
        gb.gridx=8;gb.gridy=0;
        empNameText.setPreferredSize(new Dimension(180,25));
        jpane.add(empNameText,gb);
    }
	
	
	
    void passwordComponent(JPanel jpane,GridBagConstraints gb){
        
        createpassLab=new JLabel("Create Password ");
        gb.gridx=0;gb.gridy=10;
        jpane.add(createpassLab,gb);
        
        createpassText=new JPasswordField();
        gb.gridx=8;gb.gridy=10;
        createpassText.setPreferredSize(new Dimension(180,25));
        jpane.add(createpassText,gb);
		
        confirmpassLab=new JLabel("Confirm Password");
        gb.gridx=0;gb.gridy=20;
        jpane.add(confirmpassLab,gb);
        
        confirmpassText=new JPasswordField();
        gb.gridx=8;gb.gridy=20;
        confirmpassText.setPreferredSize(new Dimension(180,25));
        jpane.add(confirmpassText,gb);
        
    }
	
	
    void roleComponent(JPanel jpane,GridBagConstraints gb){
         
        role=new JLabel("Role");
        gb.gridx=0;gb.gridy=30;
        jpane.add(role,gb);
        
        String []role={"Admin","Manager","RetailShopEmployee"};
        jcom=new JComboBox();
		
			for(String x : role){
				jcom.addItem(x);
			}
			
        gb.gridx=8;gb.gridy=30;
        jcom.setPreferredSize(new Dimension(180,25));
        jpane.add(jcom,gb);
		
       }
    
	
	
    void registerComponent(JPanel jpane,GridBagConstraints gb){
        
	registerbutton=new JButton("Register");
	gb.gridx=20;gb.gridy=40;
	jpane.add(registerbutton,gb);
        
	registerbutton.addActionListener(new ActionListener(){
          
		public void actionPerformed(ActionEvent ae){
						
                    int i=registerStatus();
              
                    if(i!=0){
                            
                        JOptionPane.showMessageDialog(null,"Employee id generated :"+i+"\n\nPlease note your Emp ID and proceed further ");
			jfrm.dispose();
                        askToFillEmployeeDetails(empNameText.getText().trim(),i);
			}
                    else {
								
                    }
                }  
	});
    }  
 
        
		
    public int registerStatus(){
        int count=0;
       
        try{
             stm=con.createStatement();
			 
             String empName=empNameText.getText().trim(),
					createpass = new String(createpassText.getPassword()),
					confirmpass = new String(confirmpassText.getPassword()),
					role=jcom.getSelectedItem().toString(),
					sql2;
					
             if((empName.isEmpty()) || (createpass.isEmpty())|| (confirmpass.isEmpty()))
             {
                
               JOptionPane.showMessageDialog(null,"All details are manadatory:Please fill again");
               return 0;
             }
           
             
             if(createpass.equals(confirmpass)){
		
               if(constraintPassword(confirmpass)!=0){
                     JOptionPane.showMessageDialog(null,"Password must contain at least 8 characters and must contain a digit and a special character");
                     return 0;
                 }
                 
                ResultSet r = stm.executeQuery("SELECT COUNT(*) FROM masterEmployee");
                 r.next();
                int rowCount = r.getInt(1);
                if(rowCount==0)
                {
                  String encpass=EncDecrptClass.encrypt(confirmpass);
                sql="INSERT INTO `masterEmployee`(password,role) VALUES ('"+encpass+"','"+role+"')";
                stm.executeUpdate(sql);
                 
                String sql1="SELECT e.empId FROM masterEmployee as e WHERE e.password='"+encpass+"' and e.role='"+role+"'";
                ResultSet rs1=stm.executeQuery(sql1);
                int in;
                while(rs1.next())
                    {       
                        in=rs1.getInt("empId");
                        return in;
                    }  
                }
                int ID=0;
                sql="select max(empId)+1 from masterEmployee";
		rs=stm.executeQuery(sql);
		while(rs.next()){
                    ID=rs.getInt(1);
		}
				
                rs.close();
				
                String encpass=EncDecrptClass.encrypt(confirmpass);
                sql="INSERT INTO `masterEmployee`(password,role) VALUES ('"+encpass+"','"+role+"')";
                stm.executeUpdate(sql);
                 
                String sql1="SELECT e.empId FROM masterEmployee as e WHERE e.password='"+encpass+"' and e.empID='"+ID+"' and e.role='"+role+"'";
                ResultSet rs1=stm.executeQuery(sql1);
                int in;
                while(rs1.next())
                    {       
                        in=rs1.getInt("empId");
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
    
    int constraintPassword(String confirmpass){
    
    boolean isAtLeast8   = (confirmpass.length()>= 8);
    boolean hasSpecial= confirmpass.matches("[A-Za-z0-9 ]*");
     boolean number=false;
       for (char c : confirmpass.toCharArray()){
         if (Character.isDigit(c))
                 number=true;
     }
    int count=0;
    if(!isAtLeast8 || hasSpecial || !number ){
        
     return ++count;
     
    }

        return 0;  
}
  

    void askToFillEmployeeDetails(String Name,int Id){
	
	prepareFrame(Name);
	createComponent(Name,Id);
	setComponentLayout();
	addComponentOnFrame(Id);
	listenButtonActivity(Name,Id,this);
}



	

    void prepareFrame(String Name){
	
	jfrm=new JFrame("Welcome "+Name+" !!");
	jfrm.setSize(500,650);
	jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jpane=new JPanel();
	jpane.setLayout(null);
        jfrm.add(jpane);

}

        public 	void createComponent(String Name,int Id){
	
		msg=new JLabel("Please fill below details :- ");
		msg.setFont(new Font("Serif", Font.BOLD, 20));
		empIDLabel=new JLabel("Employee ID :");
                empID=new JLabel(" "+Id);
		empFName=new JLabel("First Name :");
		empMName=new JLabel("Middel Name :");
		empMName=new JLabel("Middel Name : ");
		empLName=new JLabel("Last Name :");
		empAddressLabel=new JLabel("Address :*");
		empEmailIDLabel=new JLabel("Email Id :*");
		empContactNoLabel=new JLabel("Phone No :*");
		addDetailsButton=new JButton("Add");
		reset=new JButton("Reset");
		
                String Name_Part[]=new String[5];
                System.out.println("reached");
		Name_Part=Name.split(" ");
		 System.out.println("reached2");
		empFNameText=new JTextField(Name_Part[0]);
			
                        
			if(Name_Part.length>=3)
			{
				empMNameText=new JTextField(Name_Part[1]);
				String LastName=Name_Part[2];
					for(int i=3;i<Name_Part.length;i++){LastName =LastName+" "+Name_Part[i];}
				empLNameText=new JTextField(LastName);
			}
			else
			{
				empMNameText=new JTextField();
				empLNameText=new JTextField(Name_Part[1]);
                                System.out.println("js");
			}
			
		empAddress=new JTextField();
		empEmailID=new JTextField();
		empContactNo=new JTextField();	
	}


	


    void addComponentOnFrame(int Id){
	
        jpane.add(msg);
        jpane.add(empIDLabel);jpane.add(empID);
		jpane.add(empFName);jpane.add(empFNameText);
		jpane.add(empMName);jpane.add(empMNameText);
		jpane.add(empLName);jpane.add(empLNameText);
		jpane.add(empAddressLabel);jpane.add(empAddress);
		jpane.add(empEmailIDLabel);jpane.add(empEmailID);
		jpane.add(empContactNoLabel);jpane.add(empContactNo);
		jpane.add(reset);jpane.add(addDetailsButton);

	}

	

    void setComponentLayout(){
	
		msg.setBounds(50,1,300,75);
		msg.setForeground(Color.blue);
		empIDLabel.setBounds(100,100,100,30);empID.setBounds(200,100,200,30);
		empFName.setBounds(100,150,100,30);empFNameText.setBounds(200,150,200,30);
		empMName.setBounds(100,200,100,30);empMNameText.setBounds(200,200,200,30);
		empLName.setBounds(100,250,100,30);empLNameText.setBounds(200,250,200,30);
		empEmailIDLabel.setBounds(100,300,100,30);empEmailID.setBounds(200,300,200,30);
		empContactNoLabel.setBounds(100,350,100,30);empContactNo.setBounds(200,350,200,30);	
		empAddressLabel.setBounds(100,400,100,30);empAddress.setBounds(200,400,200,75);
		reset.setBounds(100,500,100,40);addDetailsButton.setBounds(300,500,100,40);
		jfrm.setLocationRelativeTo(null);                        
		jfrm.setVisible(true);		
		
	}
	
	
	
void listenButtonActivity(String Name,int Id,userClass obj){
	
        addDetailsButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
                    if(insertEmloyeeDetails(Id)){
			JOptionPane.showMessageDialog(null,"Thank for registeration"+"\n\nRegistration successfull"+"\n\nPlease proceed to login page and wait till activation is granted.");
			jfrm.dispose();
			new login();
                    }
		}
	});
		
		reset.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
			jfrm.dispose();
			obj.askToFillEmployeeDetails(Name,Id);
		}
	});
	
}


    public boolean insertEmloyeeDetails(int Id){
	
	String fName=empFNameText.getText().trim(),
		   mName=empMNameText.getText().trim(),
		   lName=empLNameText.getText().trim(),
		   id=String.valueOf(Id),
		   address=empAddress.getText().trim(),
		   no=empContactNo.getText().trim(),
		   emailid=empEmailID.getText().trim(),
		   sqlQuery;
                   
                    if(address.isEmpty() || no.isEmpty() || emailid.isEmpty()){
                        
                        JOptionPane.showMessageDialog(null,"* fields are mandatory.Please fill the details","",JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                        
                    if(verifyPhoneNo(empContactNo.getText().trim()) && verifyEmailId(empEmailID.getText().trim())){
							no=empContactNo.getText().trim();
							emailid=empEmailID.getText().trim();
	
						if(mName.isEmpty()){sqlQuery="INSERT INTO `employee`(empId,fName,lName,address,phoneNo,emailid)"+
                            "VALUES ('"+id+"','"+fName+"','"+lName+"','"+address+"','"+no+"','"+emailid+"')"; }
				
						else{sqlQuery="INSERT INTO `employee`(empId,fName,mName,lName,address,phoneNo,emailid)"+
                            "VALUES ('"+id+"','"+fName+"','"+mName+"','"+lName+"','"+address+"','"+no+"','"+emailid+"')";}
	
						return insertIntoDataBase(sqlQuery);
					}
					return false;
	
}




    boolean verifyPhoneNo(String No){
	if(!(No.length()==10)){
            JOptionPane.showMessageDialog(null,"Incorrect Phone No : Phone no must be of 10 digit","Wrong Phone No",JOptionPane.ERROR_MESSAGE);
            return false;
	}
	if(!(No.chars().allMatch( Character::isDigit ))){
            JOptionPane.showMessageDialog(null,"Incorrect Phone No : Phone no must be only numeric","Wrong Phone No",JOptionPane.ERROR_MESSAGE);
            return false;
	}
	return true;
}



    boolean verifyEmailId(String emailid){
	if(!(org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(emailid))){ //Download : http://mirror.fibergrid.in/apache//commons/validator/binaries/commons-validator-1.5.1-bin.zip
		JOptionPane.showMessageDialog(null,"Incorrect Email ID: Please enter correct emailid"   // Unzip and add commons-validator-1.5.1.jar in class path or in library (Add Jar)
									,"Email ID",JOptionPane.ERROR_MESSAGE);
		return false;
	}
	return true;
}



	/**********************************************************************
	* Function :                                       					  *
	* Details : 														  *
	***********************************************************************/

    boolean insertIntoDataBase(String sql){
	try {
		stm=con.createStatement();
		stm.executeUpdate(sql);
		rs.close();
		stm.close();
	}
        catch(NullPointerException e){
            return true;
        }
        catch(Exception e){
                System.out.println(e);
		JOptionPane.showMessageDialog(null,"Unable to update database"+"\n"+e.getMessage(),"Failure",JOptionPane.ERROR_MESSAGE);
		return false;
	}
	return true;
	
}
	
}


