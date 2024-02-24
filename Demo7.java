import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*
	<applet code=Demo7.class
			width=1920
			height=	1080>
	</applet>
*/

public class Demo7 extends Applet implements ActionListener, ItemListener 
{
	class info
	{
		String month;
		float prevUnit;
		float currentUnit;
		float consumedUnit=currentUnit-prevUnit;
	}
	info in[]=new info[12];
    Label L1, L2,login,chooseSector,chooseMonth,prevMonthReading,currentMonthReading,usedUnit;
    TextField T1, T2;
	TextField enterPrevReading,enterCurrentReading,consumedReading;
    Button B1,logout,exit,get,next,previous;
	Image backgroundImage;  // Image for the background
	String userEnteredID;
	String userEnteredPassword;
	String userName="syco";
	String password="osym";
	int flag;
	int loginFlag=0;
	int appletOpenFlag=1;
	String selectedsect;
	String selectedMonth;
	int monthSelected;
	Choice sect=new Choice();//choicefoe sector
	Choice mon=new Choice();
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
		get=new Button("Get");
		next=new Button("Next");
		previous=new Button("Previous");
		exit=new Button("exit");
		login = new Label("Login");
		chooseSector= new Label("Select a Sector :");
		chooseMonth= new Label("Select Month :");
		prevMonthReading= new Label("Previous Reading");
		enterPrevReading= new TextField(7);
		currentMonthReading= new Label("Current Reading");
		enterCurrentReading= new TextField(7);
		usedUnit= new Label("Consumed Units");
		consumedReading= new TextField(7);



		Font loginFont = new Font("Arial", Font.BOLD, 26);
		Font labelFont = new Font("Arial", Font.BOLD, 20);
        L1.setFont(labelFont);
        L2.setFont(labelFont);
		login.setFont(loginFont);
		chooseSector.setFont(labelFont);
		chooseMonth.setFont(labelFont);
		prevMonthReading.setFont(labelFont);
		currentMonthReading.setFont(labelFont);
		login.setBackground(new Color(254, 150, 111)); 
		L1.setBackground(new Color(254, 150, 111)); 
		T1.setBackground(new Color(254, 148, 109)); 
		L2.setBackground(new Color(252, 131, 114)); 
		T2.setBackground(new Color(253, 127, 112)); 
		B1.setBackground(new Color(251, 122, 108));  
		logout.setBackground(new Color(255, 51, 51));
		exit.setBounds(1457, 10, 70, 30);
		get.setBackground(new Color(255, 51, 51));
		next.setBackground(new Color(255, 51, 51));
		previous.setBackground(new Color(255, 51, 51));
		
		

		Font textFont = new Font("Arial", Font.PLAIN, 20);
        T1.setFont(textFont);
        T2.setFont(textFont);
		enterPrevReading.setFont(textFont);
		enterCurrentReading.setFont(textFont);

		
		usedUnit.setFont(labelFont);
		consumedReading.setFont(textFont);
		setForeground(Color.white);
		addLogin();
        B1.addActionListener(this);
		exit.addActionListener(this);

		login.setBounds(760,240,100,30);
        L1.setBounds(560, 325, 170, 20);
        T1.setBounds(750, 320, 250, 30);
        L2.setBounds(560, 415, 170, 20);
        T2.setBounds(750, 410, 250, 30);
        B1.setBounds(920, 450, 80, 30);
		logout.setBounds(1457, 10, 70, 30);
		exit.setBounds(1457, 10, 70, 30);
		chooseSector.setBounds(100,100,200,20);//label aahe
		sect.setBounds(265,97,175,20);//choice box  sector sathi
		chooseMonth.setBounds(100,150,150,20);//label aahe
		mon.setBounds(265,147,175,20);//choicebox aahe month select karnyasathi
		prevMonthReading.setBounds(100,250,200,25);//label aahe prevoius month chi reading ghenyasathi
		enterPrevReading.setBounds(100,280,150,30);//prev reading ghenyasathi textfield
		currentMonthReading.setBounds(300,250,200,25);//label previous month reading sathi
		enterCurrentReading.setBounds(300,280,150,30);//current reading ghenyasathi textfield
		usedUnit.setBounds(500,250,200,25);//label prevoius ani current cha diffrence sathi 
		consumedReading.setBounds(500,280,150,30);//diffrence reading ghenyasathi textfield
		get.setBounds(500,310,75,30);//consumed unit sathi button
		next.setBounds(500,350,75,30);//next month display
		previous.setBounds(575,350,75,30);//previous month display
		
		
		
		//choice box for selecting sector
		sect.add("Domestic");
		sect.add("Non-Domestic");
		sect.add("Agriculture");
		sect.add("Industrial");
		sect.setFont(labelFont);
		sect.addItemListener(this);
		
		//choice box for selecting month
		mon.add("January");
		mon.add("February");
		mon.add("March");
		mon.add("April");
		mon.add("May");
		mon.add("June");
		mon.add("July");
		mon.add("August");
		mon.add("September");
		mon.add("Octomber");
		mon.add("November");
		mon.add("December");
		mon.setFont(labelFont);
		mon.addItemListener(this);
		
		logout.addActionListener(this);
		get.addActionListener(this);
		next.addActionListener(this);
		previous.addActionListener(this);
		
			in[0]=new info();
			in[1]=new info();
			in[2]=new info();
			in[3]=new info();
			in[4]=new info();
			in[5]=new info();
			in[6]=new info();
			in[7]=new info();
			in[8]=new info();
			in[9]=new info();
			in[10]=new info();
			in[11]=new info();
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
					g.clearRect(0, 0, 1536, 798);
					g.setColor(Color.black);
					g.drawLine(768,0,768,798);
					g.drawLine(0,399,1536,399);
							
					if(loginFlag==1)
					{
						setForeground(Color.black);
						
						add(sect);
						add(logout);
						add(chooseSector);
						add(chooseMonth);
						add(prevMonthReading);
						add(mon);
						add(enterPrevReading);
						add(currentMonthReading);
						add(enterCurrentReading);
						add(usedUnit);
						add(consumedReading);
						add(get);
						add(next);
						add(previous);
						
						
						setForeground(Color.white);
					}
					validate();
					
				}
				else
				{
					if(flag>=1)
					{
					showStatus("Login Failed ");
					if(userEnteredID.length()==0 && userEnteredPassword.length()==0)//1536 width 798 height
					{
								
						g.setColor(Color.white);

						//int w=getHeight();
						//String temp=""+w;
						//g.drawString(temp,560,520);
						
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
			removeLogin();
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
			remove(mon);
			remove(logout);
			remove(chooseSector);
			remove(chooseMonth);
			remove(prevMonthReading);
			remove(enterPrevReading);
			remove(currentMonthReading);
			remove(enterCurrentReading);
			remove(usedUnit);
			remove(consumedReading);
			remove(get);
			remove(next);
			remove(previous);
			addLogin();
			userEnteredID = null;
			userEnteredPassword =null;
			repaint();
		}
		if(ae.getSource()==exit)
		{
			appletOpenFlag=0;
			remove(exit);
			repaint();
		}
		if(ae.getSource()==get)
		{
			in[monthSelected].prevUnit=Float.parseFloat(enterPrevReading.getText());
			in[monthSelected].currentUnit=Float.parseFloat(enterCurrentReading.getText());
			in[monthSelected].consumedUnit=in[monthSelected].currentUnit-in[monthSelected].prevUnit;
			
			String msg;
			msg=" "+in[monthSelected].consumedUnit;
			consumedReading.setText(msg);
			
						
		}
		if(ae.getSource()==next)
		{
			String nextMonth="";// if not intilise then we get error: variable nextMonth might not have been initialized mon.select(nextMonth);
			int m;
			m=monthSelected+1;
			monthSelected++;
			switch (m)
			{
				case 1:nextMonth="February";
								break;
				case 2:nextMonth="March";
								break;
				case 3:nextMonth="April";
								break;
				case 4:nextMonth="May";
								break;
				case 5:nextMonth="June";
								break;	
				case 6:nextMonth="July";
								break;
				case 7:nextMonth="August";
								break;
					
				case 8:nextMonth="September";
								break;
				case 9:nextMonth="October";
								break;	
				case 10:nextMonth="November";
								break;
				case 11:nextMonth="December";
								break;
				default:
			}
			mon.select(nextMonth);
			validate();
			String msg;
			msg=" "+in[monthSelected].currentUnit;
			enterCurrentReading.setText(msg);
			if(monthSelected>=1)
			{
				msg=" "+in[monthSelected-1].currentUnit;
				enterPrevReading.setText(msg);
			}
			String temp;
			temp=" "+in[monthSelected].consumedUnit;
			consumedReading.setText(temp);
			validate();
		}
		if(ae.getSource()==previous)
		{
			String previousMonth="";// if not intilise then we get error: variable nextMonth might not have been initialized mon.select(nextMonth);
			int m;
			m=monthSelected-1;
			monthSelected--;
			switch (m)
			{
				case 0:previousMonth="January";
								break;
				case 1:previousMonth="February";
								break;
				case 2:previousMonth="March";
								break;
				case 3:previousMonth="April";
								break;
				case 4:previousMonth="May";
								break;
				case 5:previousMonth="June";
								break;	
				case 6:previousMonth="July";
								break;
				case 7:previousMonth="August";
								break;
					
				case 8:previousMonth="September";
								break;
				case 9:previousMonth="October";
								break;	
				case 10:previousMonth="November";
								break;
				case 11:previousMonth="December";
								break;
				default:
			}
			mon.select(previousMonth);
			validate();
			String msg;
			msg=" "+in[monthSelected].currentUnit;
			enterCurrentReading.setText(msg);
			if(monthSelected>0)
			{
				msg=" "+in[monthSelected-1].currentUnit;
				enterPrevReading.setText(msg);
			}
			else
			{
				msg=" "+in[0].prevUnit;
				enterPrevReading.setText(msg);
			}
			String temp;
			temp=" "+in[monthSelected].consumedUnit;
			consumedReading.setText(temp);
			validate();
		}
		 
    }
	 public void itemStateChanged(ItemEvent i)
	{
       
		 if (i.getSource() == sect )
        {	
            selectedsect=sect.getSelectedItem();
			choiceAdded=true;
			
        }
		if (i.getSource() == mon )
        {	
            selectedMonth=mon.getSelectedItem();
			switch (selectedMonth)
			{
				case "January" :monthSelected=0;
								break;
				case "February" :monthSelected=1;
								break;
				case "March" :monthSelected=2;
								break;
				case "April" : monthSelected=3;
								break;
				case "May" :monthSelected=4;
								break;
				case "June" :monthSelected=5;
								break;
				case "July" :monthSelected=6;
								break;
				case "August" : monthSelected=7;
								break;
				case "September" :monthSelected=8;
								break;
				case "Octomber" :monthSelected=9;
								break;
				case "November" :monthSelected=10;
								break;
				case "December" : monthSelected=11;
								break;
				default:
					
				
			}
			String msg;
			msg=" "+in[monthSelected].currentUnit;
			enterCurrentReading.setText(msg);
			if(monthSelected>=1)
			{
				msg=" "+in[monthSelected-1].currentUnit;
				enterPrevReading.setText(msg);
			}
			
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
