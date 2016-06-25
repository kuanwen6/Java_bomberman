import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/* 道具在箱子裡的數字要和道具顯示出來的數字不同
 * 水柱特效如果用不同數字之後再改回0?
 * extends Thread?  implements Runnable?
 * 判斷下的位置?
 * x y不等於arr[x][y]
 * player是會記錄吃到的道具個數 還是真實的威力個數?(solved)
 */


public class WaterBall extends JLabel implements Runnable{
        
        private int power=1;
        private int maxRow = 13;
        private int maxCol = 15;
        private int step = 50;
        private int arr[][];
        private JLabel lb[][];
        private int locatX;   //arr[x][y]
        private int locatY;
        private int maxLeftY;         //左端最遠點
        private int maxRightY;//右端最遠點
        private int maxUpX ;          //上端
        private int maxDownX ; //下端
        private Player1 Player;                
        private Player1 P1;        
        private Player1 P2;        
        
        //球
        private ImageIcon icon= new ImageIcon(".\\image\\bomb.png");

        //水柱
    private static ImageIcon water_up = new ImageIcon(".\\image\\water_up.png");
    private static ImageIcon water_down = new ImageIcon(".\\image\\water_down.png");
    private static ImageIcon water_right = new ImageIcon(".\\image\\water_right.png");
    private static ImageIcon water_left = new ImageIcon(".\\image\\water_left.png");
        
    //道具
    public static ImageIcon waterball = new ImageIcon(".\\image\\waterball.png");
    public static ImageIcon skate = new ImageIcon(".\\image\\skate.png");
    public static ImageIcon drug = new ImageIcon(".\\image\\drug.png");
    public static ImageIcon redghost = new ImageIcon(".\\image\\redghost.png");
    public static ImageIcon pill = new ImageIcon(".\\image\\pill.png");
    
        public WaterBall(int arr[][],int x,int y,Player1 player,JLabel lb[][],Player1 p1,Player1 p2)        //arr[][] (x,y)表示要放水球的位置
        {
                this.setIcon(icon);
                this.setSize(step,step);
                this.setLocation(20+y*step,23+x*step);
                
                
                this.arr=arr;
                this.locatX=x;
                this.locatY=y;
            this.lb=lb;
            this.Player=player;                /////////////////////////
            this.P1=p1;                /////////////////////////
            this.P2=p2;
            
            lb[x][y].setIcon(icon);
            
                this.setVisible(true);
                
                power = player.getPower()>5 ? 5 : player.getPower();                //從玩家身上取得 看這個水球是誰放的
        }
        
        public void run()                //提供gui呼叫.start()
        {
                //arr[locatX][locatY]=55;
                try{
                        Thread.sleep(1000);
                }catch(InterruptedException e){
                        e.printStackTrace();
                }
                arr[locatX][locatY]=55;
                try{
                        Thread.sleep(4000);
                }catch(InterruptedException e){
                        e.printStackTrace();
                }
                //arr[locatX][locatY]=55;
                bomb(locatX,locatY);
                bombEnd();
        }
        
        private void bomb(int x,int y)        //水球炸裂 (含判斷炸到人or可炸or不可炸) 所需參數:炸彈要放的位置arr[x][y] 
        {
                arr[locatX][locatY]=55;
                maxLeftY = y-power >= 0 ? y-power : 0;         //左端最遠點
                maxRightY = y+power <= (maxCol-1) ? y+power : 14;//右端最遠點
                maxUpX = x-power >= 0 ? x-power : 0;          //上端
                maxDownX = x+power <= (maxRow-1) ? x+power : 12; //下端
                
                //考慮炸彈範圍
                for(int j=y-1;j>=maxLeftY;j--)        //左端最遠
                {
                        if(arr[x][j]%11==0 && arr[x][j]!=0)        //不可炸之物11 22 33 44
                        {        
                                maxLeftY = j+1;
                                break;
                        }
                        else if(arr[x][j]==3)        //炸到player1
                        {                
                                P1.isDead(arr,P1);
                                arr[x][j]=10;
                                lb[x][j].setIcon(water_left);
                                Thread t=new Thread(new Reborn(P1));
                                t.start();
                        }
                        else if(arr[x][j]==4)        //炸到player2
                        {        
                                P2.isDead(arr,P2);
                                arr[x][j]=10;
                                lb[x][j].setIcon(water_left);
                                Thread s=new Thread(new Reborn1(P2));
                                s.start();
                                
                        }
                        else if( arr[x][j]==1 || arr[x][j]==2 || arr[x][j]==20)        //沒有道具的箱子(1 2 20)
                        {
                                arr[x][j]=10;
                                maxLeftY = j;
                                lb[x][j].setIcon(water_left);
                                break;
                        }        
                        else if(arr[x][j]>40 && arr[x][j]%10==0)        //外顯道具
                        {        
                                arr[x][j]=10;
                                lb[x][j].setIcon(water_left);
                        }
                        else if(arr[x][j]==0)        //blank
                        {
                                arr[x][j]=10;
                                lb[x][j].setIcon(water_left);
                        }
                        else //道具在箱子裡
                        {        
                                switch(arr[x][j])
                                {
                                case 5:arr[x][j]=50;
                                        break;
                                case 6:arr[x][j]=60;
                                        break;
                                case 7:arr[x][j]=70;
                                        break;
                                case 8:arr[x][j]=80;
                                        break;
                                case 9:arr[x][j]=90;
                                        break;
                                default:break;
                                }
                                maxLeftY = j;
                                lb[x][j].setIcon(water_left);
                                break;
                        }
                }
                for(int j=y+1;j<=maxRightY;j++)        //右端最遠
                {
                        if(arr[x][j]%11==0 && arr[x][j]!=0)        
                        {        
                                maxRightY = j-1;
                                break;        
                        }
                        else if(arr[x][j]==3)
                        {        
                                P1.isDead(arr,P1);
                                arr[x][j]=10;
                                lb[x][j].setIcon(water_right);
                                Thread t=new Thread(new Reborn(P1));
                                t.start();
                        }
                        else if(arr[x][j]==4)
                        {        
                                P2.isDead(arr,P2);
                                arr[x][j]=10;
                                lb[x][j].setIcon(water_right);
                                Thread s=new Thread(new Reborn1(P2));
                                s.start();
                        }
                        else if( arr[x][j]==1 || arr[x][j]==2 || arr[x][j]==20)        //沒有道具的箱子(1 2 20)
                        {
                                arr[x][j]=10;
                                maxRightY = j;
                                lb[x][j].setIcon(water_right);
                                break;
                        }        
                        else if(arr[x][j]>40 && arr[x][j]%10==0)        //外顯道具
                        {        
                                arr[x][j]=10;
                                lb[x][j].setIcon(water_right);
                        }
                        else if(arr[x][j]==0)
                        {
                                arr[x][j]=10;
                                lb[x][j].setIcon(water_right);
                        }
                        else
                        {                
                                switch(arr[x][j])
                                {
                                case 5:arr[x][j]=50;
                                        break;
                                case 6:arr[x][j]=60;
                                        break;
                                case 7:arr[x][j]=70;
                                        break;
                                case 8:arr[x][j]=80;
                                        break;
                                case 9:arr[x][j]=90;
                                        break;
                                default:break;
                                }
                                maxRightY = j;
                                lb[x][j].setIcon(water_right);
                                break;
                        }
                }
                for(int i=x-1;i>=maxUpX;i--)        //上端最遠
                {
                        if(arr[i][y]%11==0 && arr[i][y]!=0)        
                        {        
                                maxUpX = i+1;
                                break;        
                        }
                        else if(arr[i][y]==3)
                        {                
                                P1.isDead(arr,P1);
                                arr[i][y]=10;
                                lb[i][y].setIcon(water_up);
                                Thread t=new Thread(new Reborn(P1));
                                t.start();
                                
                        }
                        else if(arr[i][y]==4)
                        {                
                                P2.isDead(arr,P2);
                                arr[i][y]=10;
                                lb[i][y].setIcon(water_up);
                                Thread s=new Thread(new Reborn1(P2));
                                s.start();
                        }
                        else if( arr[i][y]==1 || arr[i][y]==2 || arr[i][y]==20)        //沒有道具的箱子(1 2 20)
                        {
                                arr[i][y]=10;
                                maxUpX = i;
                                lb[i][y].setIcon(water_up);
                                break;
                        }        
                        else if(arr[i][y]>40 && arr[i][y]%10==0)        //外顯道具
                        {        
                                arr[i][y]=10;
                                lb[i][y].setIcon(water_up);
                        }
                        else if(arr[i][y]==0)
                        {
                                arr[i][y]=10;
                                lb[i][y].setIcon(water_up);
                        }
                        else
                        {        
                                switch(arr[i][y])
                                {
                                case 5:arr[i][y]=50;
                                        break;
                                case 6:arr[i][y]=60;
                                        break;
                                case 7:arr[i][y]=70;
                                        break;
                                case 8:arr[i][y]=80;
                                        break;
                                case 9:arr[i][y]=90;
                                        break;
                                default:break;
                                }
                                maxUpX = i;
                                lb[i][y].setIcon(water_up);
                                break;
                        }
                }
                for(int i=x+1;i<=maxDownX;i++)        //往下
                {
                        if(arr[i][y]%11==0 && arr[i][y]!=0)        
                        {        
                                maxDownX = i-1;
                                break;        
                        }
                        else if(arr[i][y]==3)
                        {        
                                P1.isDead(arr,P1);
                                arr[i][y]=10;
                                lb[i][y].setIcon(water_down);
                                Thread t=new Thread(new Reborn(P1));
                                t.start();
                        }
                        else if(arr[i][y]==4)
                        {                
                                P2.isDead(arr,P2);
                                arr[i][y]=10;
                                lb[i][y].setIcon(water_down);
                                Thread s=new Thread(new Reborn1(P2));
                                s.start();
                        }
                        else if( arr[i][y]==1 || arr[i][y]==2 || arr[i][y]==20)        //沒有道具的箱子(1 2 20)
                        {
                                arr[i][y]=10;
                                maxDownX = i;
                                lb[i][y].setIcon(water_down);
                                break;
                        }        
                        else if(arr[i][y]>40 && arr[i][y]%10==0)        //外顯道具
                        {        
                                arr[i][y]=10;
                                lb[i][y].setIcon(water_down);
                        }
                        else if(arr[i][y]==0)
                        {
                                arr[i][y]=10;
                                lb[i][y].setIcon(water_down);
                        }
                        else
                        {        
                                switch(arr[i][y])
                                {
                                case 5:arr[i][y]=50;
                                        break;
                                case 6:arr[i][y]=60;
                                        break;
                                case 7:arr[i][y]=70;
                                        break;
                                case 8:arr[i][y]=80;
                                        break;
                                case 9:arr[i][y]=90;
                                        break;
                                default:break;
                                }
                                maxDownX = i;
                                lb[i][y].setIcon(water_down);
                                break;
                        }
                }
        }
        
        private void bombEnd()        
        {
        	bombSound();
                if(Player.getBall()<Player.getBallUpperBound())        
                {
            Player.setBall(Player.getBall()+1);
                }
                for(int i=maxUpX;i<=maxDownX;i++)
                {
                        if(arr[i][locatY]==3)
                        {
                                P1.isDead(arr,P1);
                                arr[i][locatY]=10;
                                Thread t=new Thread(new Reborn(P1));
                                t.start();
                        }
                        if(arr[i][locatY]==4)
                        {
                                P2.isDead(arr,P2);
                                arr[i][locatY]=10;
                                Thread s=new Thread(new Reborn1(P2));
                                s.start();
                                
                        }
                }
                for(int j=maxLeftY;j<=maxRightY;j++)
                {
                                if(arr[locatX][j]==3)
                                {
                                        P1.isDead(arr,P1);
                                        arr[locatX][j]=10;
                                        Thread t=new Thread(new Reborn(P1));
                                        t.start();
                                }
                                if(arr[locatX][j]==4)
                                {
                                        P2.isDead(arr,P2);
                                        arr[locatX][j]=10;
                                        Thread s=new Thread(new Reborn1(P2));
                                        s.start();
                                }
                }
                
                //休息一段時間 看效果
                try{
                        Thread.sleep(800);
                }catch(InterruptedException e){
                        e.printStackTrace();
                }
                //改變圖案
                for(int i=maxUpX;i<=maxDownX;i++)
                {
                        if((arr[i][locatY] % 10 == 0 && arr[i][locatY]!=20) || (arr[i][locatY]==3) || (arr[i][locatY]==4))
                        {
                                if(arr[i][locatY]>40)                //改成道具圖案
                                {
                                        switch(arr[i][locatY])
                                        {
                                        case 50:
                                                lb[i][locatY].setIcon(waterball);
                                                break;
                                        case 60:
                                                lb[i][locatY].setIcon(skate);
                                                break;
                                        case 70:
                                                lb[i][locatY].setIcon(drug);
                                                break;
                                        case 80:
                                                lb[i][locatY].setIcon(redghost);
                                                break;
                                        case 90:
                                                lb[i][locatY].setIcon(pill);
                                                break;
                                        default:break;
                                        }
                                }
                                else if(arr[i][locatY]==30 || arr[i][locatY]==3)        //player1 died
                                {
                                        arr[i][locatY]=0;
                                        P1.isDead(arr, P1);
                                        lb[i][locatY].setIcon(null);
                                                        
                                        Thread t=new Thread(new Reborn(P1));
                                        t.start();
                                                        
                                }
                                else if(arr[i][locatY]==40 || arr[i][locatY]==4)        //player2 died
                                {
                                        arr[i][locatY]=0;
                                        P2.isDead(arr, P2);
                                        lb[i][locatY].setIcon(null);
                                                        
                                        Thread s=new Thread(new Reborn1(P2));
                                        s.start();
                                }
                                else if(arr[i][locatY]==10)
                                {
                                        arr[i][locatY]=0;
                                        lb[i][locatY].setIcon(null);
                                }
                        }
                }
                for(int j=maxLeftY;j<=maxRightY;j++)
                {
                        if( (arr[locatX][j] % 10 == 0 && arr[locatX][j]!=20) || (arr[locatX][j]==3) || (arr[locatX][j]==4))
                        {
                                if(arr[locatX][j]>40)                //改成道具圖案
                                {
                                        switch(arr[locatX][j])
                                        {
                                        case 50:
                                                lb[locatX][j].setIcon(waterball);
                                                break;
                                        case 60:
                                                lb[locatX][j].setIcon(skate);
                                                break;
                                        case 70:
                                                lb[locatX][j].setIcon(drug);
                                                break;
                                        case 80:
                                                lb[locatX][j].setIcon(redghost);
                                                break;
                                        case 90:
                                                lb[locatX][j].setIcon(pill);
                                                break;
                                        default:break;
                                        }
                                }
                                else if(arr[locatX][j]==30 || arr[locatX][j]==3)        //player1 died
                                {
                                        arr[locatX][j]=0;
                                        P1.isDead(arr, P1);
                                        lb[locatX][j].setIcon(null);
                                                        
                                        Thread t=new Thread(new Reborn(P1));
                                        t.start();
                                                        
                                }
                                else if(arr[locatX][j]==40 || arr[locatX][j]==4)        //player2 died
                                {
                                        arr[locatX][j]=0;
                                        P2.isDead(arr, P2);
                                        lb[locatX][j].setIcon(null);
                                                        
                                        Thread s=new Thread(new Reborn1(P2));
                                        s.start();
                                }
                                else if(arr[locatX][j]==10)
                                {
                                        arr[locatX][j]=0;
                                        lb[locatX][j].setIcon(null);
                                }
                        }
                }
                
                
                /*for(int i=0;i<maxRow;i++)
                {
                        for(int j=0;j<maxCol;j++)
                        {
                                if(arr[i][j] % 10 == 0 && arr[i][j]!=20)
                                {
                                        if(arr[i][j]>40)                //改成道具圖案
                                        {
                                                switch(arr[i][j])
                                                {
                                                case 50:
                                                        lb[i][j].setIcon(waterball);
                                                        break;
                                                case 60:
                                                        lb[i][j].setIcon(skate);
                                                        break;
                                                case 70:
                                                        lb[i][j].setIcon(drug);
                                                        break;
                                                case 80:
                                                        lb[i][j].setIcon(redghost);
                                                        break;
                                                case 90:
                                                        lb[i][j].setIcon(pill);
                                                        break;
                                                default:break;
                                                }
                                        }
                                        else if(arr[i][j]==30)        //player1 died
                                        {
                                                arr[i][j]=0;
                                                P1.isDead(arr, P1);
                                                lb[i][j].setIcon(null);
                                                                
                                                Thread t=new Thread(new Reborn(P1));
                                                t.start();
                                                                
                                        }
                                        else if(arr[i][j]==40)        //player2 died
                                        {
                                                arr[i][j]=0;
                                                P2.isDead(arr, P2);
                                                lb[i][j].setIcon(null);
                                                                
                                                Thread s=new Thread(new Reborn1(P2));
                                                s.start();
                                        }
                                        else if(arr[i][j]==10)
                                        {
                                                arr[i][j]=0;
                                                lb[i][j].setIcon(null);
                                        }
                                }
                        }
                }*/
                lb[locatX][locatY].setIcon(null);
                arr[locatX][locatY]=0;
        }
        
        public static void bombSound()
    {
            try{
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(".\\image\\bomb.wav"));
                    Clip clip =AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
            }catch(Exception ex)
            {
                    System.out.println("Error with playing sound");
                    ex.printStackTrace();
            }
    }
}

