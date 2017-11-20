package mainProgram;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Thread;
import java.text.SimpleDateFormat;

import interfaces.RL;

//http://zetcode.com/tutorials/javaswingtutorial/firstprograms/

public class Front_End extends JFrame implements RL{
	
    public static void main(String[] args) {
    	//do not call method here. only use for testing
    	createWindow();
    }
    public static void createWindow() {
    	EventQueue.invokeLater(() -> {
        	initUI();
        });
    }
    
    
    
	public Front_End() { //this is a constructor, it calls a method to give the window settings
        initUI();
    }
    private static void initUI() { //window settings
    	JFrame frame = new JFrame();
    	
    	//MENU BAR
    	frame.setJMenuBar(MenuBar.createMenuBar());

    	//ICON IMAGE (Like a favicon for websites), also changes icon in Taskbar
    	
    	ImageIcon webIcon = new ImageIcon("assets/logos/RestroomLogsLogo.png"); //create and icon with the image, "web.png" should be in the root of the project

    	frame.setIconImage(webIcon.getImage()); //sets the icon to be displayed,  .getImmage returns the icon image
    	
    	//CONTENT -------------------------------------------------------------------------------
	    
    	//Container
    	Container pane = frame.getContentPane();
    	pane.setBackground(RL.color("SMOKEY_BLACK"));
    	
    	//-----------
    	JSplitPane MajorLeftAndRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    	pane.add(MajorLeftAndRight);
    	
    	//-----------
    	JPanel TitleBarAndScanAndMessage = new JPanel();
    	MajorLeftAndRight.setLeftComponent(TitleBarAndScanAndMessage);
    	
    	//-----------
    	JPanel TitleBar = new JPanel();
    	TitleBarAndScanAndMessage.add(TitleBar, BorderLayout.PAGE_START);
    	//TitleBar.add(BANNER, BorderLayout.LINE_START);
    	//TitleBar.add(STATS, BorderLayout.LINE_END);
    	
    	//-----------
    	JSplitPane ScanAndMessage = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	TitleBarAndScanAndMessage.add(ScanAndMessage, BorderLayout.PAGE_END);
    	
    	//-----------
    	JPanel scan = new JPanel();
    	scan.setOpaque(true);
    	scan.setBackground(RL.color(4));
    	ScanAndMessage.setTopComponent(scan);
    	
    	//-----------
    	JPanel message = new JPanel();
    	
    	JEditorPane messageTitle = new JEditorPane();
    	messageTitle.setContentType("text/html");
    	messageTitle.setText("<html><h1>MESSAGES</h1></html>");
    	messageTitle.setEditable(false);
    	//messageContent.setBackground(Color.WHITE);
    	//Border b=new LineBorder(Color.black,1);
    	//messageContent.setBorder(b);
    	message.add(messageTitle, BorderLayout.PAGE_START);
    	
    	JEditorPane messageContent = new JEditorPane();
    	try {
    		messageContent.setPage("http://coding2kids.com/"); //HAS NO CSS
    	}
    	catch (IOException e) {
    		messageContent.setContentType("text/html");
    	 	messageContent.setText("<html>Could not load message from http://coding2kids.com/>");
    	}
    	messageContent.setEditable(false);
    	message.add(messageContent, BorderLayout.PAGE_END);
    	
    	
    	ScanAndMessage.setBottomComponent(message);
    	
    	//--test display
    	JTextArea test = new JTextArea("asdfjkl;");
    	test.setMinimumSize(test.getPreferredSize());
    	
    	JLabel label = new JLabel();
    	ImageIcon RestroomLogsLogoWideBanner = new ImageIcon("assets/logos/RestroomLogsWideBanner.png");
    	label.setIcon(RestroomLogsLogoWideBanner);
    	
    	
    	MajorLeftAndRight.setRightComponent(test);
    	MajorLeftAndRight.setBorder(BorderFactory.createLineBorder(Color.black));
    	Dimension d = new Dimension(20, 50);
    	MajorLeftAndRight.setPreferredSize(d);
    	MajorLeftAndRight.setMinimumSize(MajorLeftAndRight.getPreferredSize());
    	TitleBar.add(label, BorderLayout.LINE_START);
    	TitleBar.add(test, BorderLayout.LINE_END);
    	TitleBar.setMinimumSize(TitleBar.getPreferredSize());
    	TitleBar.setBorder(BorderFactory.createLineBorder(Color.black));
    	message.setMinimumSize(TitleBar.getPreferredSize());
    	message.setBorder(BorderFactory.createLineBorder(Color.black));
    	TitleBarAndScanAndMessage.setBorder(BorderFactory.createLineBorder(Color.black));
    	messageTitle.setBorder(BorderFactory.createLineBorder(Color.black));
    	messageContent.setBorder(BorderFactory.createLineBorder(Color.black));
    	scan.add(test);
    	scan.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	
		
    	
    	
    	
    	//ScanAndMessage.setResizeWeight(0.5);
    	//MajorLeftAndRight.setDividerLocation(150);
		//Provide minimum sizes for the two components in the split pane
		//Dimension minimumSize = new Dimension(100, 50);
		//listScrollPane.setMinimumSize(minimumSize);
    	
    	
    	//OLD--------------------------------------
    	/*
    	
        JButton button;
	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    
	    //FIXME: scaling picture, keep ratio
	    JLabel logo = new JLabel();
	    ImageIcon RestroomLogsLogoWideBanner = new ImageIcon("assets/logos/RestroomLogsWideBanner.png");
	    //find aspect ratios
	    double orginalHeight = RestroomLogsLogoWideBanner.getIconHeight();
	    double orginalWidth = RestroomLogsLogoWideBanner.getIconWidth();
	    double aspectRatio = orginalWidth/orginalHeight;
	    //find screen size
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
	    //find new dimensions
		final double HEIGHT_ASPECT_RATIO_MULTIPLIER = 0.15;
	    int newHeight = (int) (screenHeight * HEIGHT_ASPECT_RATIO_MULTIPLIER);
	    int newWidth = (int) (newHeight * aspectRatio);
	    //resize image
	    Image img = RestroomLogsLogoWideBanner.getImage() ;  
	    Image newimg = img.getScaledInstance( newWidth, newHeight,  java.awt.Image.SCALE_SMOOTH ) ;  
	    ImageIcon RestroomLogsLogoWideBannerResized = new ImageIcon( newimg );
	    logo.setIcon(RestroomLogsLogoWideBannerResized);
	    c.weightx = 0.5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    pane.add(logo, c);
	 
	    String teacherName = "Mr. Sabo";
	    Date date = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM/dd/yyyy hh:mm:ss a");
	    String timeStamp = "Current Date: " + dateFormat.format(date);
	    JLabel stats = new JLabel("<html><h1>"+teacherName+"</h1><Br><p>"+timeStamp+"<p></htlm>");
		    ActionListener actionListener = new ActionListener() {
		        public void actionPerformed(ActionEvent actionEvent) {
		        	Date date = new Date();
		            SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM/dd/yyyy hh:mm:ss a");
		            String timeStamp = "Current Date: " + dateFormat.format(date);
		            stats.setText("<html><h1>"+teacherName+"</h1><Br><p>"+timeStamp+"<p></htlm>");
		            //System.out.println(timeStamp);
		        }
		    };
		    Timer timer = new Timer(1000, actionListener);
		    timer.start();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = 0;
	    pane.add(stats, c);
	 
	    button = new JButton("Button 3");
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 0.5;
	    c.gridx = 2;
	    c.gridy = 0;
	    c.gridheight = GridBagConstraints.REMAINDER;
	    c.anchor = GridBagConstraints.LINE_END;
	    pane.add(button, c);
	 
	    button = new JButton("Long-Named Button 4");
	    c.fill = GridBagConstraints.BOTH;
	    //c.ipady = 40;      //make this component tall
	    c.weightx = 0.1;
	    c.weighty = 1;
	    c.gridwidth = 2;
	    c.gridheight = 3;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.anchor = GridBagConstraints.LINE_START;
	    pane.add(button, c);
	 
	    button = new JButton("5");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 0;       //reset to default
	    c.weighty = 0.0;
	    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    c.gridx = 0;       //aligned with button 2
	    c.gridwidth = 2;   //2 columns wide
	    c.gridy = 4;       //third row
	    pane.add(button, c);
	    
	    	
    	
    	*/
    	//END OF CONTENT ----------------------------------------------------------------
    	
        
        //WINDOW SETTINGS
    	frame.setTitle("Restroom Logs");
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	frame.setResizable(false);
    	frame.setUndecorated(true);
    	frame.setLocationRelativeTo(null); //DON'T KNOW WHAT THIS DOES
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setAlwaysOnTop(true); //DO NOT USE THIS, THIS WILL PREVENT DIALOG FROM TOPPING THIS
    	frame.setVisible(true);
    }
}