import java.awt.*;
import java.applet.*;
import java.awt.event.*;
/*
	<applet code=Demo8.class
			width=1920
			height=	1080>
	</applet>
*/

public class Demo8 extends Applet implements ActionListener, ItemListener 
{
	class info
	{
		String month;
		float prevUnit;
		float currentUnit;
		float consumedUnit=currentUnit-prevUnit;
		float perUnitRate;
		float totalPerUnitRate;
		
		float fuelAdjustmentRatePerUnit;
		float totalFuelAdjustmentRatePerUnit;
		float fixedRate;
		float transferRate;
		float totalTransferRate;
		float electricityTax=16f;
		float totalElectricityTax;
		float total;
		void calculate()
		{
			if(consumedUnit<=100)
			{	
				perUnitRate=4.41f;
				fuelAdjustmentRatePerUnit=0.250f;
				totalPerUnitRate=perUnitRate*consumedUnit;
				totalFuelAdjustmentRatePerUnit=fuelAdjustmentRatePerUnit*consumedUnit;
			}	
			else if (consumedUnit<=300&&consumedUnit>=101)
			{
				perUnitRate=9.64f;
				fuelAdjustmentRatePerUnit=0.450f;
				totalPerUnitRate=(float)(perUnitRate*(consumedUnit-100)+4.41*100);
				totalFuelAdjustmentRatePerUnit=(float)(fuelAdjustmentRatePerUnit*(consumedUnit-100)+0.250*100);
				
			}
			else if (consumedUnit<=500&&consumedUnit>=301)
			{
				perUnitRate=13.61f;
				fuelAdjustmentRatePerUnit=0.600f;				
				totalPerUnitRate=(float)(perUnitRate*(consumedUnit-300)+4.41*100+9.64*200);
				totalFuelAdjustmentRatePerUnit=(float)(fuelAdjustmentRatePerUnit*(consumedUnit-300)+0.250*100+0.450*200);
				
			}
			else
			{
				perUnitRate=15.57f;
				fuelAdjustmentRatePerUnit=0.650f;				
				totalPerUnitRate=(float)(perUnitRate*(consumedUnit-500)+4.41*100+9.64*200+13.61*200);
				totalFuelAdjustmentRatePerUnit=(float)(fuelAdjustmentRatePerUnit*(consumedUnit-500)+0.250*100+0.450*200+0.650*200);
			}
			fixedRate=116f;
			
			
			transferRate=1.17f;
			totalTransferRate=transferRate*consumedUnit;
			totalElectricityTax=(fixedRate+totalPerUnitRate+totalTransferRate+totalFuelAdjustmentRatePerUnit)*0.16f;
			total=fixedRate+totalPerUnitRate+totalTransferRate+totalFuelAdjustmentRatePerUnit+totalElectricityTax;
			
			totalPerUnitRate= Float.parseFloat(String.format("%.2f", totalPerUnitRate));
			totalTransferRate= Float.parseFloat(String.format("%.2f", totalTransferRate));
			totalFuelAdjustmentRatePerUnit= Float.parseFloat(String.format("%.2f", totalFuelAdjustmentRatePerUnit));
			totalElectricityTax= Float.parseFloat(String.format("%.2f", totalElectricityTax));
			total= Float.parseFloat(String.format("%.2f", total));
		}
	}
	String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	info in[]=new info[12];
    Label L1, L2,login,chooseSector,chooseMonth,prevMonthReading,currentMonthReading,usedUnit,fixRate,electricityRateLabel,transferRateLabel,fuelAdjustmentRateLabel,electricityTaxLabel,totalElectricityPaymentLabel;
	Label fixRateShow,electricityRateLabelShow,transferRateLabelShow,fuelAdjustmentRateLabelShow,electricityTaxLabelShow,totalElectricityPaymentLabelShow;
	Label arm1,arm2,arm3,arm4,arm5,arm6,arm7,arm8,arm9,arm10,arm11,arm12;
    TextField T1, T2;
	TextField enterPrevReading,enterCurrentReading,consumedReading;
    Button B1,logout,exit,get,next,previous,generateBill,annualReport;
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
		backgroundImage = getImage(getDocumentBase(), "ec1.jpg");//jar possible asel tr login na frame hoil ani glass effect vatel as edit karuya
        L1 = new Label("Enter User ID :");
        L2 = new Label("Enter Password :");
        T1 = new TextField(15);
        T2 = new TextField(15);
        B1 = new Button("Login");
		logout=new Button("Logout");
		get=new Button("Get");
		next=new Button("Next");
		previous=new Button("Previous");
		generateBill=new Button("Generate Bill");
		annualReport=new Button("Anuual Report");
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
		
		
		fixRate=new Label("Fix Rate");
		electricityRateLabel=new Label("Electricty Rate");
		transferRateLabel=new Label("Transfer Rate @1.17 Rs/U");
		fuelAdjustmentRateLabel=new Label("Fuel Adjustment Rate");
		electricityTaxLabel=new Label("Electricity Tax 16.00%");
		totalElectricityPaymentLabel=new Label("Total Electricity Payment");
		
		fixRateShow=new Label("");
		electricityRateLabelShow=new Label("");
		transferRateLabelShow=new Label("");
		fuelAdjustmentRateLabelShow=new Label("");
		electricityTaxLabelShow=new Label("");
		totalElectricityPaymentLabelShow=new Label("");
		
		arm1=new Label("");
		arm2=new Label("");
		arm3=new Label("");
		arm4=new Label("");
		arm5=new Label("");
		arm6=new Label("");
		arm7=new Label("");
		arm8=new Label("");
		arm9=new Label("");
		arm10=new Label("");
		arm11=new Label("");
		arm12=new Label("");



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
		generateBill.setBackground(new Color(255, 51, 51));
		annualReport.setBackground(new Color(255, 51, 51));
		
		
		
		
		

		Font textFont = new Font("Arial", Font.PLAIN, 20);
        T1.setFont(textFont);
        T2.setFont(textFont);
		enterPrevReading.setFont(textFont);
		enterCurrentReading.setFont(textFont);
		usedUnit.setFont(labelFont);
		
		fixRate.setFont(labelFont);
		electricityRateLabel.setFont(labelFont);
		transferRateLabel.setFont(labelFont);
		fuelAdjustmentRateLabel.setFont(labelFont);
		electricityTaxLabel.setFont(labelFont);
		totalElectricityPaymentLabel.setFont(labelFont);
		
		fixRateShow.setFont(labelFont);
		electricityRateLabelShow.setFont(labelFont);
		transferRateLabelShow.setFont(labelFont);
		fuelAdjustmentRateLabelShow.setFont(labelFont);
		electricityTaxLabelShow.setFont(labelFont);
		totalElectricityPaymentLabelShow.setFont(labelFont);
		
		totalElectricityPaymentLabel.setBackground(new Color(249,213,199));
		fixRate.setBackground(new Color(249,213,199));
		electricityRateLabel.setBackground(new Color(249,213,199));
		transferRateLabel.setBackground(new Color(249,213,199));
		fuelAdjustmentRateLabel.setBackground(new Color(249,213,199));
		electricityTaxLabel.setBackground(new Color(249,213,199));
		
		
		fixRateShow.setBackground(new Color(249,213,199));
		electricityRateLabelShow.setBackground(new Color(249,213,199));
		transferRateLabelShow.setBackground(new Color(249,213,199));
		fuelAdjustmentRateLabelShow.setBackground(new Color(249,213,199));
		electricityTaxLabelShow.setBackground(new Color(249,213,199));
		totalElectricityPaymentLabelShow.setBackground(new Color(249,213,199));
		
		arm1.setBackground(Color.GRAY);
		arm2.setBackground(Color.GRAY);
		arm3.setBackground(Color.GRAY);
		arm4.setBackground(Color.GRAY);
		arm5.setBackground(Color.GRAY);
		arm6.setBackground(Color.GRAY);
		arm7.setBackground(Color.GRAY);
		arm8.setBackground(Color.GRAY);
		arm9.setBackground(Color.GRAY);
		arm10.setBackground(Color.GRAY);
		arm11.setBackground(Color.GRAY);
		arm12.setBackground(Color.GRAY);


		
		
		consumedReading.setFont(textFont);
		setForeground(Color.white);
		addLogin();
        B1.addActionListener(this);
		exit.addActionListener(this);
		
		int c=20;
		login.setBounds(960+c,340,100,30);
        L1.setBounds(760+c, 425, 170, 20);
        T1.setBounds(950+c, 420, 250, 30);
        L2.setBounds(760+c, 515, 170, 20);
        T2.setBounds(950+c, 510, 250, 30);
        B1.setBounds(1120+c, 550, 80, 30);
		logout.setBounds(1457, 10, 70, 30);
		exit.setBounds(1457, 10, 70, 30);
		
		//inpute setbounds
		chooseSector.setBounds(50,100,200,20);//label aahe
		sect.setBounds(215,97,175,20);//choice box  sector sathi
		chooseMonth.setBounds(50,150,150,20);//label aahe
		mon.setBounds(215,147,175,20);//choicebox aahe month select karnyasathi
		prevMonthReading.setBounds(50,250,200,25);//label aahe prevoius month chi reading ghenyasathi
		enterPrevReading.setBounds(50,280,150,30);//prev reading ghenyasathi textfield
		currentMonthReading.setBounds(250,250,200,25);//label previous month reading sathi
		enterCurrentReading.setBounds(250,280,150,30);//current reading ghenyasathi textfield
		usedUnit.setBounds(450,250,200,25);//label prevoius ani current cha diffrence sathi 
		consumedReading.setBounds(450,280,150,30);//diffrence reading ghenyasathi textfield
		get.setBounds(450,310,75,30);//consumed unit sathi button
		next.setBounds(450,350,75,30);//next month display
		previous.setBounds(525,350,75,30);//previous month display
		
		generateBill.setBounds(700,50,75,30);
		
		
		fixRate.setBounds(700,100,250,25);
		electricityRateLabel.setBounds(700,150,250,25);
		transferRateLabel.setBounds(700,200,250,25);
		fuelAdjustmentRateLabel.setBounds(700,250,250,25);
		electricityTaxLabel.setBounds(700,300,250,30);
		totalElectricityPaymentLabel.setBounds(700,350,250,25);
		
		fixRateShow.setBounds(1115,100,100,25);
		electricityRateLabelShow.setBounds(1115,150,100,25);
		transferRateLabelShow.setBounds(1115,200,100,25);
		fuelAdjustmentRateLabelShow.setBounds(1115,250,100,25);
		electricityTaxLabelShow.setBounds(1115,300,100,30);
		totalElectricityPaymentLabelShow.setBounds(1115,350,100,25);
		
		
		annualReport.setBounds(1330, 80, 150, 30);
		arm1.setBounds(1330, 150, 150, 30);
		arm2.setBounds(1330, 180, 150, 30);
		arm3.setBounds(1330, 210, 150, 30);
		arm4.setBounds(1330, 240, 150, 30);
		arm5.setBounds(1330, 270, 150, 30);
		arm6.setBounds(1330, 300, 150, 30);
		arm7.setBounds(1330, 330, 150, 30);
		arm8.setBounds(1330, 360, 150, 30);
		arm9.setBounds(1330, 390, 150, 30);
		arm10.setBounds(1330, 420, 150, 30);
		arm11.setBounds(1330, 450, 150, 30);
		arm12.setBounds(1330, 480, 150, 30);


		arm1.setFont(labelFont);
		arm2.setFont(labelFont);
		arm3.setFont(labelFont);
		arm4.setFont(labelFont);
		arm5.setFont(labelFont);
		arm6.setFont(labelFont);
		arm7.setFont(labelFont);
		arm8.setFont(labelFont);
		arm9.setFont(labelFont);
		arm10.setFont(labelFont);
		arm11.setFont(labelFont);
		arm12.setFont(labelFont);

		
		
		
		
		
		
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
		generateBill.addActionListener(this);
		annualReport.addActionListener(this);
		
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
					g.clearRect(0, 0, 1920, 1080);
					g.setColor(Color.black);
					g.drawLine(640,0,640,1080);
					g.drawLine(1280,0,1280,1920);
					g.setColor(new Color(249,213,199));
					g.fillRect(690,90,540,295);
					g.setColor(Color.black);
					g.drawRect(689,89,542,297);
					g.drawLine(1095,89,1095,385);
					g.drawLine(689,138,1231,138);
					g.drawLine(689,187,1231,187);
					g.drawLine(689,236,1231,236);
					g.drawLine(689,285,1231,285);						
					g.drawLine(689,334,1231,334);
					
						
						
									
					
					
							
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
						
						add(generateBill);
						
						
						
						
						add(fixRate);
						add(electricityRateLabel);
						add(transferRateLabel);
						add(fuelAdjustmentRateLabel);
						add(electricityTaxLabel);
						add(totalElectricityPaymentLabel);
						
						add(fixRateShow);
						add(electricityRateLabelShow);
						add(transferRateLabelShow);
						add(fuelAdjustmentRateLabelShow);
						add(electricityTaxLabelShow);
						add(totalElectricityPaymentLabelShow);
						
						
						add(annualReport);
						
						add(arm1);
						add(arm2);
						add(arm3);
						add(arm4);
						add(arm5);
						add(arm6);
						add(arm7);
						add(arm8);
						add(arm9);
						add(arm10);
						add(arm11);
						add(arm12);

						
						
						
						
						
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
			
			remove(generateBill);
			
			remove(fixRate);
			remove(electricityRateLabel);
			remove(transferRateLabel);
			remove(fuelAdjustmentRateLabel);
			remove(electricityTaxLabel);
			remove(totalElectricityPaymentLabel);
			
			remove(fixRateShow);
			remove(electricityRateLabelShow);
			remove(transferRateLabelShow);
			remove(fuelAdjustmentRateLabelShow);
			remove(electricityTaxLabelShow);
			remove(totalElectricityPaymentLabelShow);
			
			remove(annualReport);
			
			remove(arm1);
			remove(arm2);
			remove(arm3);
			remove(arm4);
			remove(arm5);
			remove(arm6);
			remove(arm7);
			remove(arm8);
			remove(arm9);
			remove(arm10);
			remove(arm11);
			remove(arm12);

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
		if(ae.getSource()==generateBill)
		{
			
			in[monthSelected].calculate();
			String msg;
			msg=""+in[monthSelected].fixedRate;
			fixRateShow.setText(msg);
			msg=""+in[monthSelected].totalPerUnitRate;
			electricityRateLabelShow.setText(msg);
			msg=""+in[monthSelected].totalTransferRate;
			transferRateLabelShow.setText(msg);
			msg=""+in[monthSelected].totalFuelAdjustmentRatePerUnit;
			fuelAdjustmentRateLabelShow.setText(msg);
			msg=""+in[monthSelected].totalElectricityTax;
			electricityTaxLabelShow.setText(msg);
			msg=""+in[monthSelected].total;
			totalElectricityPaymentLabelShow.setText(msg);
			validate();
			repaint();
		}
		if(ae.getSource()==annualReport)
			
		{
			arm1.setText("January");
			arm2.setText("February");
			arm3.setText("March");
			arm4.setText("April");
			arm5.setText("May");
			arm6.setText("June");
			arm7.setText("July");
			arm8.setText("August");
			arm9.setText("September");
			arm10.setText("October");
			arm11.setText("November");
			arm12.setText("December");

			
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
