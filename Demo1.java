import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*
	<applet code=Demo1.class
			width=1280
			height=	720>
	</applet>
*/
public class Demo1 extends Applet implements ActionListener {
    Label L1, L2;
    TextField T1, T2;
    Button B1;
	Image backgroundImage;  // Image for the background

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
    }
    public void actionPerformed(ActionEvent ae) {
        String userEnteredID = T1.getText();
        String userEnteredPassword = T2.getText();
        // Add your login logic here
		 if (userEnteredID.equals("syco") && userEnteredPassword.equals("osym"))
		{
            showStatus("Login Successful");
        } 
		else 
		{
            showStatus("Login Failed. ");
        }
    }
	 
}
