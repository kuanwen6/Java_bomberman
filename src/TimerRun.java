import java.awt.Color;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class TimerRun extends TimerTask{
	int i;
	int m;
	int s;
	int tag;
	public static boolean start;
	
	JLabel label;
	TimerRun(JLabel j){
		label=j;
		i=3;
		m=3;
		s=0;
		tag=0;
		start=false;
		
		label.setOpaque(false);
	}
	public void  run(){
		
		
		label.setText(Integer.toString(i));
		i--;
		label.setFont(new java.awt.Font("Dialog", 1, 200));
	    label.setForeground(Color.WHITE);
	    label.setSize(200,200);
		label.setLocation(405,260);
				
		
		if(i<-1)
		{
			start=true;
			label.setFont(new java.awt.Font("Dialog", 1, 25));
		    label.setForeground(Color.RED);
		    label.setSize(195,50);
			label.setLocation(785,75);
			label.setOpaque(true);
			label.setBackground(Color.blue.darker().darker());
			if(m<0){
				
					label.setText("剩餘時間   "+"0 : 00");
				if(tag==0)
				{
					Gui.finish();
					this.cancel();
				}
				tag=1;
			}
			else if(s>9)
				label.setText("剩餘時間   "+Integer.toString(m)+" : "+Integer.toString(s));
			else
				label.setText("剩餘時間   "+Integer.toString(m)+" : 0"+Integer.toString(s));
			if(s<=0){
				m--;
				s=60;
  
			}
			s--;
		}
	}

}