import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*
	<applet code=Demo5.class
			width=1920
			height=	1080>
	</applet>
*/
public class Demo5 extends Applet implements ActionListener {
    Label L1, L2,login;
    TextField T1, T2;
    Button B1;
	Image backgroundImage;  // Image for the background
	String userEnteredID;
	String userEnteredPassword;
	String userName="syco";
	String password="osym";
	int flag;
    public void init() {
        setLayout(null);
		backgroundImage = getImage(getDocumentBase(), "ec1.jpg");//jar possible asel tr login na frame hoil ani glass effect vatel as edit karuya--https://youtu.be/AkK2VclgfgQ?si=vdmHghpA8EPNSvao
        L1 = new Label("Enter User ID :");
        L2 = new Label("Enter Password :");
        T1 = new TextField(15);
        T2 = new TextField(15);
        B1 = new Button("Login");
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

		Font textFont = new Font("Arial", Font.PLAIN, 20);
        T1.setFont(textFont);
        T2.setFont(textFont);
		setForeground(Color.white);
		add(login);
        add(L1);
        add(T1);
        add(L2);
        add(T2);
        add(B1);
		
        B1.addActionListener(this);
		login.setBounds(760,240,100,30);
        L1.setBounds(560, 325, 170, 20);
        T1.setBounds(750, 320, 250, 30);
        L2.setBounds(560, 415, 170, 20);
        T2.setBounds(750, 410, 250, 30);
        B1.setBounds(920, 450, 80, 30);
    }
	public void paint(Graphics g) 
	 {
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		Font lineFont = new Font("Arial", Font.BOLD, 20);
		g.setFont(lineFont);
		g.setColor(Color.white);

		
		if(userName.equals(userEnteredID) && password.equals(userEnteredPassword)) 
		{
			showStatus("Login Successful ");
			
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
    public void actionPerformed(ActionEvent ae)
	{
       
		 if (ae.getSource() == B1)
        {	
            userEnteredID = T1.getText();
			userEnteredPassword = T2.getText();
			repaint();
			flag++;
        }
		 
    }
	 
}
