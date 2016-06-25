import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Reborn1 implements Runnable{
	Player1 p;
	private static ImageIcon p_front =new ImageIcon(".\\image\\p2-front.png");
	
	Reborn1(Player1 p)
	{
		this.p=p;	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int k=0;k<10;k++)
	    {
	    	if(k%2==0)
	    	{
	    		p.setIcon(null);
	    		try{
	    			Thread.sleep(100);
	    		}catch(Exception ex)
	    		{
	    			ex.printStackTrace();
	    		}
	    	}
	    	else
	    	{
	    		p.setIcon(p_front);
	    		try{
	    			Thread.sleep(100);
	    		}catch(Exception ex)
	    		{
	    			ex.printStackTrace();
	    		}
	    	}
	    }
	    p.setIcon(p_front);
	}
	
}
