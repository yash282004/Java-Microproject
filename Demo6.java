import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*
	<applet code=Demo6.class
			width=1920
			height=	1080>
	</applet>
*/
public class Demo6 extends Applet implements ActionListener, ItemListener 
{
    Label L1, L2,login;
    TextField T1, T2;
    Button B1,logout,exit;
	Image backgroundImage;  // Image for the background
	String userEnteredID;
	String userEnteredPassword;
	String userName="syco";
	String password="osym";
	int flag;
	int loginFlag=0;
	int appletOpenFlag=1;
	String selectedsect;
	Choice sect=new Choice();
	boolean choiceAdded=false;

    public void init()
	{
        setLayout(null);
		backgroundImage = getImage(getDocumentBase(), "ec1.jpg");//jar possible asel tr login na frame hoil ani glass effect vatel as edit karuya--https://youtu.be/AkK2VclgfgQ?si=vdmHghpA8EPNSvao
        L1 = new Label("Enter User ID :");
        L2 = new Label("Enter Password :");
        T1 = new TextField(15);
        T2 = new TextField(15);
        B1 = new Button("Login");
		logout=new Button("Logout");
		exit=new Button("exit");
		login = new Label("Login");


		Font loginFont = new Font("Arial", Font.BOLD, 26);
		Font labelFont = new Font("Arial", Font.BOLD, 20);
        L1.setFont(labelFont);
        L2.setFont(labelFont);
		login.setFont(loginFont);
		login.setBackground(new Color(254, 150, 111)); 
		L1.setBackground(new Color(254, 150, 111)); 
		T1.setBackground(new Color(254, 148, 109)); 
		L2.setBackground(new Color(252, 131, 114)); 
		T2.setBackground(new Color(253, 127, 112)); 
		B1.setBackground(new Color(251, 122, 108)); 
		logout.setBackground(new Color(255, 51, 51));
		exit.setBounds(1457, 10, 70, 30);

		Font textFont = new Font("Arial", Font.PLAIN, 20);
        T1.setFont(textFont);
        T2.setFont(textFont);
		setForeground(Color.white);
		addLogin();
        B1.addActionListener(this);
		login.setBounds(760,240,100,30);
        L1.setBounds(560, 325, 170, 20);
        T1.setBounds(750, 320, 250, 30);
        L2.setBounds(560, 415, 170, 20);
        T2.setBounds(750, 410, 250, 30);
        B1.setBounds(920, 450, 80, 30);
		logout.setBounds(1457, 10, 70, 30);
		exit.setBounds(1457, 10, 70, 30);
		
		//choice box for selecting sector
		sect.add("Domestic");
		sect.add("Non-Domestic");
		sect.add("Agriculture");
		sect.add("Industrial");
		sect.addItemListener(this);
		
		logout.addActionListener(this);
	}

	public void paint(Graphics g) 
	 {
        if(appletOpenFlag==1)
		{	// Draw the background image
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				Font lineFont = new Font("Arial", Font.BOLD, 20);
				g.setFont(lineFont);
				g.setColor(Color.white);

				
				if(userName.equals(userEnteredID) && password.equals(userEnteredPassword)) 
				{
					showStatus("Login Successful ");
					loginFlag++;
					//loginFlag=1;
					remove(B1);
					remove(T2);
					remove(L2);
					remove(T1);
					remove(L1);
					remove(login);
					remove(exit);
					g.clearRect(0, 0, 1920, 1080);
							
					if(loginFlag==1)
					{
						add(sect);
						add(logout);
					}
					validate();
					
				}
				else
				{
					if(flag>=1)
					{
					showStatus("Login Failed ");
					if(userEnteredID.length()==0 && userEnteredPassword.length()==0)
					{
								
						g.setColor(Color.white);

						
						g.drawString("Please Enter Username and Password",560,520);
					}
					else if(userEnteredID.length()==0 )
					{
						g.setColor(Color.white);

						
						g.drawString("Please Enter Username ",560,520);
					}
					else if(!userEnteredID.equals(userName))
					{
						g.setColor(Color.white);

						g.drawString("Incorrect Username ",560,520);
					}
					else if(userEnteredPassword.length()==0)
					{
						g.setColor(Color.white);

						g.drawString("Please Enter Password", 560,520);
					}
					
					else if(!userEnteredPassword.equals(password))
					{
						g.setColor(Color.white);

						g.drawString("Incorrect Password ",560,520);
					}
				}
			}
		}
		if(appletOpenFlag==0)
		{
			g.clearRect(0, 0, 1920, 1080);
			setBackground(Color.white);
			g.setColor(Color.black);
			g.drawString("Thank you for using our software!!",500,500);
		}
		
		//after login
		

			
    }
	
    public void actionPerformed(ActionEvent ae)
	{
       
		 if (ae.getSource() == B1)
        {	
			
            userEnteredID = T1.getText();
			userEnteredPassword = T2.getText();
			repaint();
			flag++;
        }
		if(ae.getSource()==logout)
		{
			flag=0;
			loginFlag=0;
			showStatus("Logout ");
			remove(sect);
			remove(logout);
			addLogin();
			userEnteredID = null;
			userEnteredPassword =null;
			repaint();
		}
		if(ae.getSource()==exit)
		{
			appletOpenFlag=0;
			repaint();
		}
		 
    }
	 public void itemStateChanged(ItemEvent i)
	{
       
		 if (i.getSource() == sect )
        {	
            selectedsect=sect.getSelectedItem();
			choiceAdded=true;
			
        }
		 
    }
	void addLogin()
	{
		add(login);
        add(L1);
        add(T1);
        add(L2);
        add(T2);
        add(B1);
		add(exit);
	}
	void removeLogin()
	{
		remove(login);
        remove(L1);
        remove(T1);
        remove(L2);
        remove(T2);
        remove(B1);
	}
}
