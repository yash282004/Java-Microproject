import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*
	<applet code=Demo2.class
			width=1280
			height=	720>
	</applet>
*/
public class Demo2 extends Applet implements ActionListener {
    Label L1, L2;
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
		backgroundImage = getImage(getDocumentBase(), "background.jpg");
        L1 = new Label("Enter User ID :");
        L2 = new Label("Enter Password :");
        T1 = new TextField(15);
        T2 = new TextField(15);
        B1 = new Button("Login");
		
		Font labelFont = new Font("Arial", Font.BOLD, 20);
        L1.setFont(labelFont);
        L2.setFont(labelFont);
		
		Font textFont = new Font("Arial", Font.PLAIN, 20);
        T1.setFont(textFont);
        T2.setFont(textFont);

        add(L1);
        add(T1);
        add(L2);
        add(T2);
        add(B1);
		
        B1.addActionListener(this);

        L1.setBounds(460, 320, 170, 20);
        T1.setBounds(650, 320, 250, 30);
        L2.setBounds(460, 360, 170, 20);
        T2.setBounds(650, 360, 250, 30);
        B1.setBounds(820, 400, 80, 30);
    }
	public void paint(Graphics g) 
	 {
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		
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
				g.drawString("Please Enter Username and Password",100,150);
			}
			else if(userEnteredID.length()==0 )
			{
				g.drawString("Please Enter Username ",100,150);
			}
			else if(userEnteredPassword.length()==0)
			{
				g.drawString("Please Enter Password ",100,150);
			}
			else if(!userEnteredID.equals(userName))
			{
				g.drawString("Incorrect Username ",100,150);
			}
			else if(!userEnteredPassword.equals(password))
			{
				g.drawString("Incorrect Password ",100,150);
			}
			}
		}

			


			
		/* if (userName == null)
		{
			g.drawString("Username not entered", 100, 100);
		}
		else if (password == null) 
		{
			g.drawString("Password not entered", 100, 120);
		} 
		else if (userName.equals(userEnteredID) && password.equals(userEnteredPassword)) 
		{
			g.drawString("Login Successful", 100, 140);
			showStatus("Login Successful");
		} 
		else if(!userName.equals(userEnteredID) && password.equals(userEnteredPassword)) 
		{
			g.drawString("Login Failed", 100, 160);
			showStatus("Login Failed. ");
		}
		else
		{
		}*/
			
    }
    public void actionPerformed(ActionEvent ae)
	{
        
        // Add your login logic here
		 if (ae.getSource() == B1)
        {	
            userEnteredID = T1.getText();
			userEnteredPassword = T2.getText();
			repaint();
			flag++;
        }
		 
    }
	 
}
