import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Moving implements Runnable{
	Player1 p1;
	Player1 p2;
	//int p1x,p1y,p2x,p2y;
	public  int[][] arr;
	public JLabel[][] lb;
	
	  public static ImageIcon p1_front = new ImageIcon(".\\image\\p1-front.png");
	    public static ImageIcon p1_back = new ImageIcon(".\\image\\p1-back.png");
	    public static ImageIcon p1_left = new ImageIcon(".\\image\\p1-left.png");
	    public static ImageIcon p1_right = new ImageIcon(".\\image\\p1-right.png");

	    public static ImageIcon p2_front = new ImageIcon(".\\image\\p2-front.png");
	    public static ImageIcon p2_back = new ImageIcon(".\\image\\p2-back.png");
	    public static ImageIcon p2_left = new ImageIcon(".\\image\\p2-left.png");
	    public static ImageIcon p2_right = new ImageIcon(".\\image\\p2-right.png");
	
	    //private  int p1moveX;
		//private  int p1moveY;
		//private  int p2moveX;
		//private  int p2moveY;
		private static int speed;
		private static int modspeed;
		private static WaterBall b;
		
		final int row=13,col=15;
		final int step=50;
	
	Moving(int[][] arr,JLabel[][] lb,Player1 p1,Player1 p2){
		this.p1=p1;
		this.p2=p2;
		//this.p1x=p1x;
		//this.p1y=p1y;
		//this.p2x=p2x;
		//this.p2y=p2y;
		this.arr=arr;
		this.lb=lb;
		//this.p1moveX=p1moveX;
		//this.p1moveY=p1moveY;
		//this.p2moveX=p2moveX;
		//this.p2moveY=p2moveY;
	}
	
	public void run() {
		
		
		
		while(true)
		{
			System.out.printf("");
			speed=50/p1.getSpeed();
			modspeed=50%p1.getSpeed();
			//System.out.println(speed+" "+modspeed);
		if (Gui.right ==true ){ 
			
			p1.setIcon(p1_right);
			if(Gui.p1y != col-1)
			 {
				/*********************************/
				if(arr[Gui.p1x][Gui.p1y+1]==0)	//blank
				{for(int i=0;i<speed;i++){
					Gui.p1moveX +=p1.getSpeed();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p1.setLocation(Gui.p1moveX,Gui.p1moveY);
					
					}
					Gui.p1moveX+=modspeed;
					p1.setLocation(Gui.p1moveX,Gui.p1moveY);
					
					if(Gui.p1moveX>=20+50*(Gui.p1y+1))
					{
						arr[Gui.p1x][Gui.p1y]=0;
						Gui.p1y++;
						arr[Gui.p1x][Gui.p1y]=3;
					}
				}
				else if(arr[Gui.p1x][Gui.p1y+1]%10==0 && arr[Gui.p1x][Gui.p1y+1]>40)
				{for(int i=0;i<speed;i++){
					Gui.p1moveX +=p1.getSpeed();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					p1.setLocation(Gui.p1moveX, Gui.p1moveY);
					}
				Gui.p1moveX+=modspeed;
				p1.setLocation(Gui.p1moveX,Gui.p1moveY);
				
				
					if(Gui.p1moveX>=20+50*(Gui.p1y+1))
					{
						arr[Gui.p1x][Gui.p1y]=0;
						Gui.p1y++;
						p1.add(arr[Gui.p1x][Gui.p1y]);
						lb[Gui.p1x][Gui.p1y].setIcon(null);
						arr[Gui.p1x][Gui.p1y]=3;
					}
				}
				else if(arr[Gui.p1x][Gui.p1y+1]==10)
				{
					for(int i=0;i<speed;i++){
						Gui.p1moveX +=p1.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p1.setLocation(Gui.p1moveX,Gui.p1moveY);
						
						}
						Gui.p1moveX+=modspeed;
						p1.setLocation(Gui.p1moveX,Gui.p1moveY);
						
						if(Gui.p1moveX>=20+50*(Gui.p1y+1))
						{
							arr[Gui.p1x][Gui.p1y]=0;
							Gui.p1y++;
							arr[Gui.p1x][Gui.p1y]=3;
							p1.isDead(arr, p1);
							arr[Gui.p1x][Gui.p1y]=0;
							Thread t=new Thread(new Reborn(p1));
							t.start();
						}
				}
				else	//cannot move
				{
					
				}
				/*********************************/
			 }
			//System.out.println("p1x = "+Gui.p1x+" p1y = "+Gui.p1y+" p1moveX = "+Gui.p1moveX+" p1moveY = "+Gui.p1moveY);
		}else if(Gui.left ==true){
			p1.setIcon(p1_left);
			if(Gui.p1y != 0)
			 {
				/*********************************/
				if(arr[Gui.p1x][Gui.p1y-1]==0)	//blank
				{for(int i=0;i<speed;i++){
					Gui.p1moveX -=p1.getSpeed();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p1.setLocation(Gui.p1moveX, Gui.p1moveY);
					
				}
				Gui.p1moveX-=modspeed;
				p1.setLocation(Gui.p1moveX,Gui.p1moveY);
					if(Gui.p1moveX<=20+50*(Gui.p1y-1))
					{
						arr[Gui.p1x][Gui.p1y]=0;
						Gui.p1y--;
						arr[Gui.p1x][Gui.p1y]=3;
					}
				}
				else if(arr[Gui.p1x][Gui.p1y-1]%10==0 && arr[Gui.p1x][Gui.p1y-1]>40)
				{for(int i=0;i<speed;i++){
					Gui.p1moveX -=p1.getSpeed();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p1.setLocation(Gui.p1moveX, Gui.p1moveY);
					
				}Gui.p1moveX-=modspeed;
				p1.setLocation(Gui.p1moveX,Gui.p1moveY);
					if(Gui.p1moveX<=20+50*(Gui.p1y-1))
					{
						arr[Gui.p1x][Gui.p1y]=0;
						Gui.p1y--;
						p1.add(arr[Gui.p1x][Gui.p1y]);
						lb[Gui.p1x][Gui.p1y].setIcon(null);
						arr[Gui.p1x][Gui.p1y]=3;
					}
				}
				else if(arr[Gui.p1x][Gui.p1y-1]==10)
				{
					for(int i=0;i<speed;i++){
						Gui.p1moveX -=p1.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p1.setLocation(Gui.p1moveX, Gui.p1moveY);
						
					}
					Gui.p1moveX-=modspeed;
					p1.setLocation(Gui.p1moveX,Gui.p1moveY);
						if(Gui.p1moveX<=20+50*(Gui.p1y-1))
						{
							arr[Gui.p1x][Gui.p1y]=0;
							Gui.p1y--;
							arr[Gui.p1x][Gui.p1y]=3;
							p1.isDead(arr,p1);
							arr[Gui.p1x][Gui.p1y]=0;
							Thread t=new Thread(new Reborn(p1));
							t.start();
						}
				}
				else	//cannot move
				{
					
				}
				/*********************************/
			 }
			//System.out.println("p1x = "+Gui.p1x+" p1y = "+Gui.p1y+" p1moveX = "+Gui.p1moveX+" p1moveY = "+Gui.p1moveY);
		}
		else if(Gui.up ==true){
			p1.setIcon(p1_back);
			if(Gui.p1x != 0)
			 {
				/*********************************/
				if(arr[Gui.p1x-1][Gui.p1y]==0)	//blank
				{for(int i=0;i<speed;i++){
					Gui.p1moveY -=p1.getSpeed();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p1.setLocation(Gui.p1moveX, Gui.p1moveY);
				}
				Gui.p1moveY-=modspeed;
				p1.setLocation(Gui.p1moveX,Gui.p1moveY);
					if(Gui.p1moveY<=37+50*(Gui.p1x-1))
					{
						arr[Gui.p1x][Gui.p1y]=0;
						Gui.p1x--;
						arr[Gui.p1x][Gui.p1y]=3;
					}
				}
				else if(arr[Gui.p1x-1][Gui.p1y]%10==0 && arr[Gui.p1x-1][Gui.p1y]>40)
				{for(int i=0;i<speed;i++){
					Gui.p1moveY -=p1.getSpeed();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p1.setLocation(Gui.p1moveX, Gui.p1moveY);
				}Gui.p1moveY-=modspeed;
				p1.setLocation(Gui.p1moveX,Gui.p1moveY);
					
					if(Gui.p1moveY<=37+50*(Gui.p1x-1))
					{
						arr[Gui.p1x][Gui.p1y]=0;
						Gui.p1x--;
						p1.add(arr[Gui.p1x][Gui.p1y]);
						lb[Gui.p1x][Gui.p1y].setIcon(null);
						arr[Gui.p1x][Gui.p1y]=3;
					}
				}
				else if(arr[Gui.p1x-1][Gui.p1y]==10)//�I����W
				{
					for(int i=0;i<speed;i++){
						Gui.p1moveY -=p1.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p1.setLocation(Gui.p1moveX, Gui.p1moveY);
					}
					Gui.p1moveY-=modspeed;
					p1.setLocation(Gui.p1moveX,Gui.p1moveY);
						if(Gui.p1moveY<=37+50*(Gui.p1x-1))
						{
							arr[Gui.p1x][Gui.p1y]=0;
							Gui.p1x--;
							arr[Gui.p1x][Gui.p1y]=3;
							p1.isDead(arr,p1);
							arr[Gui.p1x][Gui.p1y]=0;
							Thread t=new Thread(new Reborn(p1));
							t.start();
						}
				}
				else	//cannot move
				{
					
				}
				/*********************************/
			 }
			//System.out.println("p1x = "+Gui.p1x+" p1y = "+Gui.p1y+" p1moveX = "+Gui.p1moveX+" p1moveY = "+Gui.p1moveY);
		}
		else if(Gui.down ==true){
			p1.setIcon(p1_front);
			if(Gui.p1x != row-1)
			{
				/*********************************/
				if(arr[Gui.p1x+1][Gui.p1y]==0)	//blank
				{for(int i=0;i<speed;i++){
					Gui.p1moveY +=p1.getSpeed();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p1.setLocation(Gui.p1moveX, Gui.p1moveY);
				}
				Gui.p1moveY+=modspeed;
				p1.setLocation(Gui.p1moveX,Gui.p1moveY);
				
					if(Gui.p1moveY>=37+50*(Gui.p1x+1))
					{
						arr[Gui.p1x][Gui.p1y]=0;
						Gui.p1x++;
						arr[Gui.p1x][Gui.p1y]=3;
					}
				}
				else if(arr[Gui.p1x+1][Gui.p1y]%10==0 && arr[Gui.p1x+1][Gui.p1y]>40)
				{for(int i=0;i<speed;i++){
					Gui.p1moveY +=p1.getSpeed();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p1.setLocation(Gui.p1moveX, Gui.p1moveY);
				}Gui.p1moveY+=modspeed;
				p1.setLocation(Gui.p1moveX,Gui.p1moveY);
				
					if(Gui.p1moveY>=37+50*(Gui.p1x+1))
					{
						arr[Gui.p1x][Gui.p1y]=0;
						Gui.p1x++;
						p1.add(arr[Gui.p1x][Gui.p1y]);
						lb[Gui.p1x][Gui.p1y].setIcon(null);
						arr[Gui.p1x][Gui.p1y]=3;
					}
				}
				else if(arr[Gui.p1x+1][Gui.p1y]==10)
				{
					for(int i=0;i<speed;i++){
						Gui.p1moveY +=p1.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p1.setLocation(Gui.p1moveX, Gui.p1moveY);
					}
					Gui.p1moveY+=modspeed;
					p1.setLocation(Gui.p1moveX,Gui.p1moveY);
					
						if(Gui.p1moveY>=37+50*(Gui.p1x+1))
						{
							arr[Gui.p1x][Gui.p1y]=0;
							Gui.p1x++;
							arr[Gui.p1x][Gui.p1y]=3;
							p1.isDead(arr,p1);
							arr[Gui.p1x][Gui.p1y]=0;
							Thread t=new Thread(new Reborn(p1));
							t.start();
						}
				}
				else	//cannot move
				{
					
				}
				/*********************************/
			}	
			//System.out.println("p1x = "+Gui.p1x+" p1y = "+Gui.p1y+" p1moveX = "+Gui.p1moveX+" p1moveY = "+Gui.p1moveY);
		}
		else if(Gui.enter==true){   //player1 bomber
			if(p1.getBall()!=0)	////////////////
			{
				arr[Gui.p1x][Gui.p1y]=55;
				p1.setBall(p1.getBall()-1);		////////////////////
				b=new WaterBall(arr,Gui.p1x,Gui.p1y,p1,lb,p1,p2);		/////////////
				Thread thread = new Thread(b);
				thread.start();
			}
			Gui.enter=false;
		}
	
		}
		}
	//}

}