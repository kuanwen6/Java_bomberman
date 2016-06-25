import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import java.net.URL;
import javax.swing.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Gui extends JFrame implements KeyListener{
	private static ImageIcon background= new ImageIcon(".\\image\\background.png");
	
	public static JLabel[][] lb = new JLabel[13][15];
	public static ImageIcon box = new ImageIcon(".\\image\\box.png");
    public static ImageIcon blueHouse = new ImageIcon(".\\image\\house-blue.png");
    public static ImageIcon redHouse = new ImageIcon(".\\image\\house-red.png");
    public static ImageIcon yellowHouse = new ImageIcon(".\\image\\house-yellow.png");
    public static ImageIcon legoOran = new ImageIcon(".\\image\\lego-oran.png");
    public static ImageIcon legoRed = new ImageIcon(".\\image\\lego-red.png");
    public static ImageIcon tree = new ImageIcon(".\\image\\tree.png");
    
    //人物1
    public static ImageIcon p1_front = new ImageIcon(".\\image\\p1-front.png");
    public static ImageIcon p1_back = new ImageIcon(".\\image\\p1-back.png");
    public static ImageIcon p1_left = new ImageIcon(".\\image\\p1-left.png");
    public static ImageIcon p1_right = new ImageIcon(".\\image\\p1-right.png");
    //人物2
    public static ImageIcon p2_front = new ImageIcon(".\\image\\p2-front.png");
    public static ImageIcon p2_back = new ImageIcon(".\\image\\p2-back.png");
    public static ImageIcon p2_left = new ImageIcon(".\\image\\p2-left.png");
    public static ImageIcon p2_right = new ImageIcon(".\\image\\p2-right.png");
 
    public static int[][] arr = new int[][]{{11,22,33,2 ,0 ,44,20,0 ,20,44,0 ,2 ,33,22,11},
        									{22,33,2 ,1 ,0 ,0 ,0 ,20,0 ,0 ,0 ,1 ,2 ,33,22}, 
        									{33,2 ,1 ,2 ,1 ,44,20,0 ,20,44,1 ,2 ,1 ,2 ,33},
        									{0,0,0,1,2,0,0,20,0,0,2,1,0,0,0},
        									{0,11,20,22,1,44,20,0,20,44,1,22,20,11,0},
        									{1,2,1,2,1,2,0,20,0,2,1,2,1,2,1},
        									{20,11,1,33,2,44,20,0,20,44,2,33,1,11,20},
        									{1,2,1,2,1,2,0,20,0,2,1,2,1,2,1},
        									{0,11,20,22,1,44,20,0,20,44,1,22,20,11,0},
        									{0,0,0,1,2,0,0,20,0,0,2,1,0,0,0},
        									{33,2 ,1 ,2 ,1 ,44,20,0 ,20,44,1 ,2 ,1 ,2 ,33},
        									{22,33,2 ,1 ,0 ,0 ,0 ,20,0 ,0 ,0 ,1 ,2 ,33,22}, 
        									{11,22,33,2 ,0 ,44,20,0 ,20,44,0 ,2 ,33,22,11}};
    
    public static int[][] origin = new int[][]{{11,22,33,2 ,0 ,44,20,0 ,20,44,0 ,2 ,33,22,11},
												{22,33,2 ,1 ,0 ,0 ,0 ,20,0 ,0 ,0 ,1 ,2 ,33,22}, 
												{33,2 ,1 ,2 ,1 ,44,20,0 ,20,44,1 ,2 ,1 ,2 ,33},
												{0,0,0,1,2,0,0,20,0,0,2,1,0,0,0},
												{0,11,20,22,1,44,20,0,20,44,1,22,20,11,0},
												{1,2,1,2,1,2,0,20,0,2,1,2,1,2,1},
												{20,11,1,33,2,44,20,0,20,44,2,33,1,11,20},
												{1,2,1,2,1,2,0,20,0,2,1,2,1,2,1},
												{0,11,20,22,1,44,20,0,20,44,1,22,20,11,0},
												{0,0,0,1,2,0,0,20,0,0,2,1,0,0,0},
												{33,2 ,1 ,2 ,1 ,44,20,0 ,20,44,1 ,2 ,1 ,2 ,33},
												{22,33,2 ,1 ,0 ,0 ,0 ,20,0 ,0 ,0 ,1 ,2 ,33,22}, 
												{11,22,33,2 ,0 ,44,20,0 ,20,44,0 ,2 ,33,22,11}};
    
	private static int step = 50;
                                            
	public static int p1x;  // player1 x
	public static int p1y;  // player1 y
	public static int p1moveX;
	public static int p1moveY;
	
	public static int p2x;
	public static int p2y;
	public static int p2moveX;
	public static int p2moveY;

	private static int sc1,sc2;
	final int row=13,col=15;
	private static JLabel score1 = new JLabel();
	private static JLabel score2 = new JLabel();

	private static JFrame frame = new Gui();
	private static Player1 p1;
	private static Player1 p2;
	private static WaterBall b;
	private static JLabel time=new JLabel();
	private static Moving m;
	private static Moving1 m1;
	private static TimerRun t;
	private static boolean end;
	
	public static boolean up =false;
	public static boolean down =false;
	public static boolean left =false;
	public static boolean right =false;
	public static boolean enter =false;
	
	public static boolean w =false;
	public static boolean a =false;
	public static boolean s =false;
	public static boolean d =false;
	public static boolean bomb =false;
	
	public static Clip clip;
	public static Clip waiting;
	
	public Gui()
	{
		p1x=3;
		p1y=1;
		arr[p1x][p1y]=3;
		p1moveX=20+step*p1y;	//左右 col
		p1moveY=37+step*p1x;	//上下 row 
		
		p2x=9;
		p2y=13;
		arr[p2x][p2y]=4;
		p2moveX=20+step*p2y;	//左右 col
		p2moveY=37+step*p2x;	//上下 row 
		
		p1=new Player1(p1x,p1y,step,".\\image\\p1-front.png",1);
		p2=new Player1(p2x,p2y,step,".\\image\\p2-front.png",2);
		Thread t1=new Thread(p1);
		t1.start();
		Thread t2=new Thread(p2);
		t2.start();
		
		sc1=0;
		sc2=0;
		end=false;
		setscore();
		startBtn();
		addKeyListener(this);
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		
		if(t.start && !end)
		{
		if (key == KeyEvent.VK_RIGHT){ 
			right=true;
			
		}else if(key == KeyEvent.VK_LEFT){
			left=true;
			
		}
		else if(key == KeyEvent.VK_UP){
			up=true;
		}
		else if(key == KeyEvent.VK_DOWN){
			down=true;			
		}
		else if(key == KeyEvent.VK_ENTER){   //player1 bomber
			enter=true;
		}
		else  // player 2 control
		{
			if (key == KeyEvent.VK_D){ //right
				d=true;
				
			}else if(key == KeyEvent.VK_A){ //left
				a=true;
				
			}
			else if(key == KeyEvent.VK_W){ //up
				w=true;
				
			}
			else if(key == KeyEvent.VK_S){//down
				s=true;
			}
			else if(key == KeyEvent.VK_B){  //player2 bomb
				bomb=true;
			}
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		if(t.start && !end)
		{
		
		if (key == KeyEvent.VK_RIGHT){ 
			right=false;
			
		}else if(key == KeyEvent.VK_LEFT){
			left=false;
			
		}
		else if(key == KeyEvent.VK_UP){
			up=false;
		}
		else if(key == KeyEvent.VK_DOWN){
			down=false;			
		}
		else if(key == KeyEvent.VK_ENTER){  
			enter=false;
		
		}
		else  // player 2 control
		{
			if (key == KeyEvent.VK_D){ //right
				d=false;
				
			}else if(key == KeyEvent.VK_A){ //left
				a=false;
				
			}
			else if(key == KeyEvent.VK_W){ //up
				w=false;
				
			}
			else if(key == KeyEvent.VK_S){//down
				s=false;
			}
			else if(key == KeyEvent.VK_B){  //player2 bomb
				bomb=false;
			}
		}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	 public static void playWait()
     {
             try{
                     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(".//image/wait.wav"));
                     waiting =AudioSystem.getClip();
                     waiting.open(audioInputStream);
                     waiting.start();
                     
             }catch(Exception ex)
             {
                     System.out.println("Error with playing sound");
                     ex.printStackTrace();
             }
     }   
	
	public static void playSound()
	{
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(".\\image\\music1.wav"));
			clip =AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}catch(Exception ex)
		{
			System.out.println("Error with playing sound");
			ex.printStackTrace();
		}
	}
	

	public static void deadScore(Player1 p){
		if(p==p1)
			sc2++;
		else if(p==p2)
			sc1++;
	}

	public static void setscore()
	{
		score1.setText(":  "+sc1);
        score1.setFont(new java.awt.Font("Dialog", 1, 30));
        score1.setForeground(Color.RED);
		score1.setSize(100,100);
	
		score2.setText(":  "+sc2);
        score2.setFont(new java.awt.Font("Dialog", 1, 30));
        score2.setForeground(Color.RED);
		score2.setSize(100,100);
		
		//System.out.println(sc1+" "+sc2);
		
		if(sc1==5 || sc2==5)
		{
			finish();
		}
	}
	
	public static void startBtn()
	{
		JFrame menu = new JFrame("start");
		ImageIcon first = new ImageIcon(".\\image\\menu.jpg");
		
		JLabel label = new JLabel(first);             //menu label 
		menu.setSize(first.getIconWidth(),first.getIconHeight());
		label.setBounds(0, 0, first.getIconWidth(),first.getIconHeight());
		menu.setContentPane(label);
		
		
		JButton btn =new JButton("Start");
		//btn.setFont(new java.awt.Font("Dialog", 1, 25));
		btn.setSize(280,85);
		btn.setLocation(135,260);
		

		menu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		menu.add(btn);
		
		btn.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ae) {
		menu.dispose();    //close menu		
		waiting.close();
		playSound();
		
		setTime(time);
		frame.setVisible(true);  //main window visible
		frame.add(time,JLabel.TOP);
	}
	});
		
		menu.setResizable(false);
		menu.setVisible(true);
	}
	
	
	public static void finish()
	{
		JFrame result = new JFrame("Score");
		result.setLocation(180, 150);
		end=true;
		
		ImageIcon background;
		if(sc1>sc2)
		{
			background = new ImageIcon(".\\image\\p1win.png");
		}
		else if(sc1<sc2)
		{
			background = new ImageIcon(".\\image\\p2win.png");
		}
		else
		{
			background = new ImageIcon(".\\image\\tie.png");
		}
		
		JLabel label = new JLabel(background);            
		result.setSize(background.getIconWidth(),background.getIconHeight());
		label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
		result.setContentPane(label);
		
		JButton restart =new JButton("再來一場");
		restart.setSize(150,40);
		restart.setLocation(20,background.getIconHeight()-80);
		
		JButton quit =new JButton("離開");
		quit.setSize(150,40);
		quit.setLocation(background.getIconWidth()-170,background.getIconHeight()-80);
		

		result.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		quit.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent ae) {
		System.exit(1); 
	}
	});
		
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				result.dispose();
				clip.close();
				 playSound();
				end=false;
				t.cancel();
				
				for(int i=0;i<13;i++)
				{
					for(int j=0;j<15;j++)
					{
						arr[i][j]=origin[i][j];
					}
				}
				p1.setIcon(p1_front);
				p1x=3;
				p1y=1;
				arr[p1x][p1y]=3;
				p1moveX=20+step*p1y;	//左右 col
				p1moveY=37+step*p1x;	//上下 row 
				p1.setLocation(20+p1y*step,37+p1x*step); 
				
				p2.setIcon(p2_front);
				p2x=9;
				p2y=13;
				arr[p2x][p2y]=4;
				p2moveX=20+step*p2y;	//左右 col
				p2moveY=37+step*p2x;	//上下 row 
				p2.setLocation(20+p2y*step,37+p2x*step); 
				p1.init();
				p2.init();
				
				up =false;
				down=false;
				left =false;
				right=false;
				enter =false;
				
				w =false;
				a =false;
				s =false;
				d=false;
				bomb=false;
				
				sc1=0;
				sc2=0;
				setscore();
				setTime(time);
				
				setPicture();
		        setProps();
			}
			});
	
		
		result.add(restart);
		result.add(quit);
		result.setResizable(false);
		result.setVisible(true);
	}


	public  static void setTime(JLabel time){
		  t=new TimerRun(time);
		   //TimerTask t= new TimerTask()

		  Timer timer = new Timer();
		  timer.schedule(t , 1000, 1000);
		  
	}
	
	public static void setPicture()
    {
            for(int i = 0; i < 13; i++)
            {
                    for(int j = 0; j < 15; j++)
                    {
                            switch(arr[i][j]){
                            case 0:	//空白
                                    lb[i][j].setIcon(null);
                                    break;
                            case 1:	//空方塊
                                    lb[i][j].setIcon(legoRed);
                                    break;
                            case 2:	//空方塊
                                    lb[i][j].setIcon(legoOran);
                                    break;
                            case 11://紅房子
                                    lb[i][j].setIcon(redHouse);
                                    break;
                            case 20://空箱子
                                    lb[i][j].setIcon(box);
                                    break;
                            case 22://黃房子
                                    lb[i][j].setIcon(yellowHouse);
                                    break;
                            case 33://藍房子
                                    lb[i][j].setIcon(blueHouse);
                                    break;
                            case 44://樹
                                    lb[i][j].setIcon(tree);
                                    break;
                            default://有道具的???
                                    lb[i][j].setIcon(null);
                                    break;
                            }
                            
                    }
            }
    }
    
    public static void init()
    {
             for (int i = 0; i < 13; i++)
             {
                     for(int j = 0; j < 15; j++)
                     {
                    	 lb[i][j]=new JLabel(box);
                    	 int high=box.getIconHeight();
                    	 lb[i][j].setLocation(20+50*j,37+50*i);
                    	 lb[i][j].setSize(50,50);
                     }
             }
             setPicture();
    }
    
    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    public static void setProps()
    {
            int countWaterball = 0, countSkate = 0, countMedwater = 0, countRedghost = 0, countPill = 0;
            while(true){
                    if(countWaterball == 6) break;
                    int i = randInt(0,12);
                    int j = randInt(0,14);
                    if(arr[i][j] == 1 || arr[i][j] == 2 || arr[i][j] == 20 )
                    {
                            arr[i][j] = 5;//assign water ball
                            countWaterball++;
                    }
            }
            
            while(true){
                    if(countSkate == 6) break;
                    int i = randInt(0,12);
                    int j = randInt(0,14);
                    if(arr[i][j] == 1 || arr[i][j] == 2 || arr[i][j] == 20 )
                    {
                            arr[i][j] = 6;//assign skates
                            countSkate++;
                    }
            }
            
            while(true){
                    if(countMedwater == 6) break;
                    int i = randInt(0,12);
                    int j = randInt(0,14);
                    if(arr[i][j] == 1 || arr[i][j] == 2 || arr[i][j] == 20 )
                    {
                            arr[i][j] = 7;//assign Magic water
                            countMedwater++;
                    }
            }
            
            while(true){
                    if(countRedghost == 2) break;
                    int i = randInt(0,12);
                    int j = randInt(0,14);
                    if(arr[i][j] == 1 || arr[i][j] == 2 || arr[i][j] == 20 )
                    {
                            arr[i][j] = 8;//assign red ghost
                            countRedghost++;
                    }
            }
            
            while(true){
                    if(countPill == 2) break;
                    int i = randInt(0,12);
                    int j = randInt(0,14);
                    if(arr[i][j] == 1 || arr[i][j] == 2 || arr[i][j] == 20 )
                    {
                            arr[i][j] = 9;//assign red ghost
                            countPill++;
                    }
            }
            
    }
	
	public static void main(String[] args) {
		frame.setResizable(false);
		
		JPanel scoreboard=new JPanel();
		scoreboard.setSize(195,615);
		scoreboard.setLocation(785,75);
		scoreboard.setBackground(Color.blue.darker().darker());
		scoreboard.setLayout(new GridLayout(2,2));
		scoreboard.add(new JLabel(new ImageIcon(".\\image\\p1-front.png")));
		scoreboard.add(score1);
		scoreboard.add(new JLabel(new ImageIcon(".\\image\\p2-front.png")));
		scoreboard.add(score2);
		
		
        frame.setSize(1120, 1120); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        frame.setContentPane(new JLabel(new ImageIcon(".\\image\\background.png")));
      
        frame.pack();
   
        init();
        setProps();
        for(int i = 0; i < 13; i++)
        {
        	for(int j = 0; j < 15; j++){
            	frame.add(lb[i][j]);
        	}
        }
		
        m=new Moving(arr,lb,p1,p2);
        Thread t=new Thread(m);
        t.start();
        
        m1=new Moving1(arr,lb,p1,p2);
        Thread t1=new Thread(m1);
        t1.start();
        
		frame.add(scoreboard);
		frame.add(p1);
		frame.add(p2);
		//playSound();
		playWait();
	}

}