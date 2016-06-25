
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Player1 extends JLabel implements Runnable{
	private int x;
	private int y;
	private static int maxball=6;
	private static int maxSpeed=10;			
	private static int maxPower=5;	
	private int ballnum;			
	private int waterballUpperBound;		
	private int power;		
	private int speed;
	private int pill;		
	private int drug;		
	private int skate;		
	private int redGhost;		
	private static int step;
	private int whoAmI;
	public JLabel picture ;
	private ImageIcon icon;
	public Player1(int x,int y,int high,String path,int iAm)
	{
		this.init();
		icon = new ImageIcon(path);
		picture=new JLabel();
		picture.setIcon(icon);
		picture.setVisible(true);
		picture.setSize(50,50);
	    setIcon(icon);
	    this.x = x;
	    this.y = y;
	    this.step =high;
	    this.whoAmI=iAm;
	    setLocation(20+y*step,37+x*step);
	    
		setSize(50,50);
	}
	public void init()
	{
		speed=4;
		ballnum=1;
		waterballUpperBound=1;
		power=1;
		pill=0;
		drug=0;
		skate=0;
		redGhost=0;
	}
	public void add(int num)
	{
		switch(num)
		{
			case 50:
				if(waterballUpperBound<maxball)
				{
					waterballUpperBound++;
				}
				ballnum++;
				break;
			case 60:
				if(speed<maxSpeed)
					speed+=1;
				skate++;
				break;
			case 70:
				if(power<maxPower)
					power++;
				drug++;
				break;
			case 80:
				redGhost++;
				speed=maxSpeed;
				break;
			case 90:
				pill++;
				power=maxPower;
				break;
				
		}

	}
	public void setBall(int i)
	{
		ballnum=i;
	}
	public void setPower(int i)
	{
		power=i;
	}
	public void setSpeed(int i)
	{
		speed=i;
	}
	public int getBall()
	{
		return ballnum;
	}
	public int getPower()
	{
		return power;
	}
	public int getSpeed()
	{
		return speed;
	}

	public void isDead(int [][]arr,Player1 deadMan)
	{
		
		for(int i=0;i<skate;i++)
		{
			int tmpx=(int)(Math.random() * 13);
			int tmpy=(int)(Math.random() * 15);
			//System.out.println("tmpx"+tmpx+" "+"tmpy"+tmpy+" ");
			while(arr[tmpx][tmpy]!=0)
			{
				tmpx=(int)(Math.random() * 13);
				tmpy=(int)(Math.random() * 15);
			}
			arr[tmpx][tmpy]=60;
		}
		for(int i=0;i<waterballUpperBound-1;i++)
		{
			int tmpx=(int)(Math.random() * 13);
			int tmpy=(int)(Math.random() * 15);
			while(arr[tmpx][tmpy]!=0)
			{
				tmpx=(int)(Math.random() * 13);
				tmpy=(int)(Math.random() * 15);
			}
			arr[tmpx][tmpy]=50;
		}
		for(int i=0;i<drug;i++)
		{
			int tmpx=(int)(Math.random() * 13);
			int tmpy=(int)(Math.random() * 15);
			while(arr[tmpx][tmpy]!=0)
			{
				tmpx=(int)(Math.random() * 13);
				tmpy=(int)(Math.random() * 15);
			}
			arr[tmpx][tmpy]=70;
		}
		for(int i=0;i<redGhost;i++)
		{
			int tmpx=(int)(Math.random() * 13);
			int tmpy=(int)(Math.random() * 15);
			while(arr[tmpx][tmpy]!=0)
			{
				tmpx=(int)(Math.random() * 13);
				tmpy=(int)(Math.random() * 15);
			}
			arr[tmpx][tmpy]=80;
		}
		for(int i=0;i<pill;i++)
		{
			int tmpx=(int)(Math.random() * 13);
			int tmpy=(int)(Math.random() * 15);
			while(arr[tmpx][tmpy]!=0)
			{
				tmpx=(int)(Math.random() * 13);
				tmpy=(int)(Math.random() * 15);
			}
			arr[tmpx][tmpy]=90;
		}

		deadMan.init();
		int tmpx=(int)(Math.random() * 13);
		int tmpy=(int)(Math.random() * 15);
		while(arr[tmpx][tmpy]!=0)
		{
		        tmpx=(int)(Math.random() * 13);
		         tmpy=(int)(Math.random() * 15);
		}

		if(deadMan.whoAmI==1)
		{
			arr[tmpx][tmpy]=3;
			Gui.p1x=tmpx;
			Gui.p1y=tmpy;
			Gui.p1moveX=20+tmpy*step;
			Gui.p1moveY=37+tmpx*step;
			//System.out.println(" new p1 : p1x = "+Gui.p1x+" p1y = "+Gui.p1y+" p1moveX = "+Gui.p1moveX+" p1moveY = "+Gui.p1moveY);
		}
		else if(deadMan.whoAmI==2)
		{
			arr[tmpx][tmpy]=4;
			Gui.p2x=tmpx;
			Gui.p2y=tmpy;
			Gui.p2moveX=20+tmpy*step;
			Gui.p2moveY=37+tmpx*step;
			
		}
		Gui.deadScore(this);
	    Gui.setscore();
		setIcon(icon);
	    setLocation(20+tmpy*step,37+tmpx*step);
	    //System.out.println(deadMan.ballnum+" "+deadMan.waterballUpperBound);
	    setIcon(null);
	}
	
	public int getBallUpperBound()                
    {
         return waterballUpperBound;                
    }
@Override
public void run() {
	// TODO Auto-generated method stub
	
}

}