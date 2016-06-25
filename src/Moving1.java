import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Moving1 implements Runnable{

	
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
		private static int speed;
		private static int modspeed;	
	   // private  int p1moveX;
		//private  int p1moveY;
		//private  int p2moveX;
		//private  int p2moveY;
		
		private static WaterBall b;
		
		final int row=13,col=15;
		final int step=50;
	
	Moving1(int[][] arr,JLabel[][] lb,Player1 p1,Player1 p2){
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
		//his.p2moveY=p2moveY;
	}
	
	
	
	public void run(){
		while(true){
			speed=50/p2.getSpeed();
			modspeed=50%p2.getSpeed();
			System.out.printf("");
			if (Gui.d ==true){ //right
				p2.setIcon(p2_right);
				if(Gui.p2y != col-1)
				 {
					/*********************************/
					if(arr[Gui.p2x][Gui.p2y+1]==0 || arr[Gui.p2x][Gui.p2y+1]>40)	//blank
					{for(int i=0;i<speed;i++){
						Gui.p2moveX +=p2.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p2.setLocation(Gui.p2moveX, Gui.p2moveY);
					}
					Gui.p2moveX+=modspeed;
					p2.setLocation(Gui.p2moveX,Gui.p2moveY);
						
						
						if(Gui.p2moveX>=20+step*(Gui.p2y+1))
						{
							arr[Gui.p2x][Gui.p2y]=0;
							Gui.p2y++;
							arr[Gui.p2x][Gui.p2y]=4;
						}
					}
					else if(arr[Gui.p2x][Gui.p2y+1]%10==0 && arr[Gui.p2x][Gui.p2y+1]>40)
					{for(int i=0;i<speed;i++){
						Gui.p2moveX +=p2.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p2.setLocation(Gui.p2moveX, Gui.p2moveY);
					}Gui.p2moveX+=modspeed;
					p2.setLocation(Gui.p2moveX,Gui.p2moveY);
					
						if(Gui.p2moveX>=20+step*(Gui.p2y+1))
						{
							arr[Gui.p2x][Gui.p2y]=0;
							Gui.p2y++;
							p2.add(arr[Gui.p2x][Gui.p2y]);
							lb[Gui.p2x][Gui.p2y].setIcon(null);
							arr[Gui.p2x][Gui.p2y]=4;
						}
					}
					else if(arr[Gui.p2x][Gui.p2y+1]==10)
					{
						for(int i=0;i<speed;i++){
							Gui.p2moveX +=p2.getSpeed();
							try {
								Thread.sleep(20);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							p2.setLocation(Gui.p2moveX, Gui.p2moveY);
						}
						Gui.p2moveX+=modspeed;
						p2.setLocation(Gui.p2moveX,Gui.p2moveY);
							
							
							if(Gui.p2moveX>=20+step*(Gui.p2y+1))
							{
								arr[Gui.p2x][Gui.p2y]=0;
								Gui.p2y++;
								arr[Gui.p2x][Gui.p2y]=4;
								p2.isDead(arr, p2);
								arr[Gui.p2x][Gui.p2y]=0;
								//lb[Gui.p2x][Gui.p2y].setIcon(null);
								Thread s=new Thread(new Reborn1(p2));
								s.start();
							}
					}
					else	//cannot move
					{
						
					}
					/*********************************/
				 }
				
			}else if(Gui.a ==true){ //left
				p2.setIcon(p2_left);
				if(Gui.p2y != 0)
				 {
					/*********************************/
					if(arr[Gui.p2x][Gui.p2y-1]==0)	//blank
					{for(int i=0;i<speed;i++){
						Gui.p2moveX -=p2.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p2.setLocation(Gui.p2moveX, Gui.p2moveY);
					}Gui.p2moveX-=modspeed;
					p2.setLocation(Gui.p2moveX,Gui.p2moveY);
					
						if(Gui.p2moveX<=20+50*(Gui.p2y-1))
						{
							arr[Gui.p2x][Gui.p2y]=0;
							Gui.p2y--;
							arr[Gui.p2x][Gui.p2y]=4;
						}
						//if(arr[p2x][p2y-1]>40)
						//{
						//	p2.add(arr[p2x][p2y-1]);
						//}
					}
					else if(arr[Gui.p2x][Gui.p2y-1]%10==0 && arr[Gui.p2x][Gui.p2y-1]>40)
					{for(int i=0;i<speed;i++){
						Gui.p2moveX -=p2.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p2.setLocation(Gui.p2moveX, Gui.p2moveY);
					}
					Gui.p2moveX-=modspeed;
					p2.setLocation(Gui.p2moveX,Gui.p2moveY);
					
						if(Gui.p2moveX<=20+50*(Gui.p2y-1))
						{
							arr[Gui.p2x][Gui.p2y]=0;
							Gui.p2y--;
							p2.add(arr[Gui.p2x][Gui.p2y]);
							lb[Gui.p2x][Gui.p2y].setIcon(null);
							arr[Gui.p2x][Gui.p2y]=4;
						}
					}
					else if(arr[Gui.p2x][Gui.p2y-1]==10)
					{
						for(int i=0;i<speed;i++){
							Gui.p2moveX -=p2.getSpeed();
							try {
								Thread.sleep(20);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							p2.setLocation(Gui.p2moveX, Gui.p2moveY);
						}Gui.p2moveX-=modspeed;
						p2.setLocation(Gui.p2moveX,Gui.p2moveY);
						
							if(Gui.p2moveX<=20+50*(Gui.p2y-1))
							{
								arr[Gui.p2x][Gui.p2y]=0;
								Gui.p2y--;
								arr[Gui.p2x][Gui.p2y]=4;
								p2.isDead(arr, p2);
								arr[Gui.p2x][Gui.p2y]=0;
								//lb[Gui.p2x][Gui.p2y].setIcon(null);
								Thread s=new Thread(new Reborn1(p2));
								s.start();
							}
					}
					else	//cannot move
					{
						
					}
					/*********************************/
				 }
				
			}
			else if(Gui.w ==true){ //up
				p2.setIcon(p2_back);
				if(Gui.p2x != 0)
				 {
					/*********************************/
					if(arr[Gui.p2x-1][Gui.p2y]==0)	//blank
					{for(int i=0;i<speed;i++){
						Gui.p2moveY -=p2.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p2.setLocation(Gui.p2moveX, Gui.p2moveY);
					}Gui.p2moveY-=modspeed;
					p2.setLocation(Gui.p2moveX,Gui.p2moveY);
					
					
						if(Gui.p2moveY<=37+step*(Gui.p2x-1))
						{
							arr[Gui.p2x][Gui.p2y]=0;
							Gui.p2x--;
							arr[Gui.p2x][Gui.p2y]=4;
						}
					}
					else if(arr[Gui.p2x-1][Gui.p2y]%10==0 && arr[Gui.p2x-1][Gui.p2y]>40)
					{for(int i=0;i<speed;i++){
						Gui.p2moveY -=p2.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p2.setLocation(Gui.p2moveX, Gui.p2moveY);
					}Gui.p2moveY-=modspeed;
					p2.setLocation(Gui.p2moveX,Gui.p2moveY);
					
						if(Gui.p2moveY<=37+step*(Gui.p2x-1))
						{
							arr[Gui.p2x][Gui.p2y]=0;
							Gui.p2x--;
							p2.add(arr[Gui.p2x][Gui.p2y]);
							lb[Gui.p2x][Gui.p2y].setIcon(null);
							arr[Gui.p2x][Gui.p2y]=4;
						}
					}
					else if(arr[Gui.p2x-1][Gui.p2y]==10)
					{
						for(int i=0;i<speed;i++){
							Gui.p2moveY -=p2.getSpeed();
							try {
								Thread.sleep(20);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							p2.setLocation(Gui.p2moveX, Gui.p2moveY);
						}Gui.p2moveY-=modspeed;
						p2.setLocation(Gui.p2moveX,Gui.p2moveY);
						
						
							if(Gui.p2moveY<=37+step*(Gui.p2x-1))
							{
								arr[Gui.p2x][Gui.p2y]=0;
								Gui.p2x--;
								arr[Gui.p2x][Gui.p2y]=4;
								p2.isDead(arr, p2);
								arr[Gui.p2x][Gui.p2y]=0;
								//lb[Gui.p2x][Gui.p2y].setIcon(null);
								Thread s=new Thread(new Reborn1(p2));
								s.start();
							}
					}
					else	//cannot move
					{
						
					}
					/*********************************/
				 }
				
			}
			else if(Gui.s ==true){//down
				p2.setIcon(p2_front);
				if(Gui.p2x != row-1)
				{
					/*********************************/
					if(arr[Gui.p2x+1][Gui.p2y]==0 )	//blank
					{for(int i=0;i<speed;i++){
						Gui.p2moveY +=p2.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p2.setLocation(Gui.p2moveX, Gui.p2moveY);
					}Gui.p2moveY+=modspeed;
					p2.setLocation(Gui.p2moveX,Gui.p2moveY);
					
						if(Gui.p2moveY>=37+step*(Gui.p2x+1))
						{
							arr[Gui.p2x][Gui.p2y]=0;
							Gui.p2x++;
							arr[Gui.p2x][Gui.p2y]=4;
						}
					}
					else if(arr[Gui.p2x+1][Gui.p2y]%10==0 && arr[Gui.p2x+1][Gui.p2y]>40)
					{for(int i=0;i<speed;i++){
						Gui.p2moveY +=p2.getSpeed();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p2.setLocation(Gui.p2moveX, Gui.p2moveY);
					}Gui.p2moveY+=modspeed;
					p2.setLocation(Gui.p2moveX,Gui.p2moveY);
					
						if(Gui.p2moveY>=37+step*(Gui.p2x+1))
						{
							arr[Gui.p2x][Gui.p2y]=0;
							Gui.p2x++;
							p2.add(arr[Gui.p2x][Gui.p2y]);
							lb[Gui.p2x][Gui.p2y].setIcon(null);
							arr[Gui.p2x][Gui.p2y]=4;
						}
					}
					else if(arr[Gui.p2x+1][Gui.p2y]==10)
					{
						for(int i=0;i<speed;i++){
							Gui.p2moveY +=p2.getSpeed();
							try {
								Thread.sleep(20);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							p2.setLocation(Gui.p2moveX, Gui.p2moveY);
						}Gui.p2moveY+=modspeed;
						p2.setLocation(Gui.p2moveX,Gui.p2moveY);
						
							if(Gui.p2moveY>=37+step*(Gui.p2x+1))
							{
								arr[Gui.p2x][Gui.p2y]=0;
								Gui.p2x++;
								arr[Gui.p2x][Gui.p2y]=4;
								p2.isDead(arr, p2);
								arr[Gui.p2x][Gui.p2y]=0;
								//lb[Gui.p2x][Gui.p2y].setIcon(null);
								Thread s=new Thread(new Reborn1(p2));
								s.start();
							}
					}
					else	//cannot move
					{
						
					}
					/*********************************/
				}	
			}else if(Gui.bomb==true){   //player1 bomber
				if(p2.getBall()!=0)	////////////////
				{
					p2.setBall(p2.getBall()-1);		////////////////
					b=new WaterBall(arr,Gui.p2x,Gui.p2y,p2,lb,p1,p2);	//////////
					Thread thread = new Thread(b);
					thread.start();
				}
				Gui.bomb=false;
			}
		}
		
		
	}
}
