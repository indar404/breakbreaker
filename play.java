import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

//import com.sun.javafx.geom.Rectangle;

//import com.sun.prism.paint.Color;


public class play extends JPanel implements ActionListener,KeyListener
{

	private boolean p = false;
	private int score=0;
	private int totalBricks=21;
	private int playerX=310;
	private int delay=0;
	private Timer timer;
	private int ballposX=120;
	private int ballposY= 350;
	private float ballXdir=-1;
	private float ballYdir=-1;
	private map m;
	public play()
	{
		m = new map(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer =new Timer(delay,this);
		timer.start();
	}
	public void paint(Graphics g)
	{
		//background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 692, 592);
		
		//score
		g.setColor(Color.blue);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score, 590, 22);
		
		///map draw
		m.draw((Graphics2D)g);
		//border
		g.setColor(Color.yellow);
		g.fillRect(0,0,3,592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(680,0,3,592);
		
		//paddle
		g.setColor(Color.yellow);
		g.fillRect(playerX, 550, 100, 8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		
		
		//win
		if(totalBricks<=0)
		{
			p=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("You won ,Score="+score, 190,300);
			g.setColor(Color.blue);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Press Enter to restart", 160, 350);	
		}
		
		//gameover
		if(ballposY>570)
		{
			p=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game-Over ,Score="+score, 190,300);
			g.setColor(Color.blue);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Press Enter to restart", 190, 350);
			
			
		}
		g.dispose();
		
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(playerX>=600)
				playerX=600;
			else
				moveright();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(playerX<10)
				playerX=10;
			else
				moveleft();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(!p)
			{
				p=true;
				ballXdir=-1;
				ballYdir=-1;
				ballposX=120;
				ballposY=350;
				playerX=310;
				score=0;
				totalBricks=28;
				m = new map(4,7);
				repaint();
			}
		}
		
	}
	public void moveright()
	{
		p=true;
		playerX+=40;
	}
	public void moveleft(){
		p=true;
		playerX-=40;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		if(p)
		{
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
				ballYdir=-ballYdir;
				
			A:	for(int i=0;i<m.ma.length;i++)
			{
				for(int j=0;j<m.ma[0].length;j++)
				{
					if(m.ma[i][j]>0)
					{
						int bX = j*m.bwidth+80;
						int bY = i*m.bheight + 50;
						int bw = m.bwidth;
						int bh = m.bheight;
						Rectangle rect =new Rectangle(bX,bY,bw,bh);
						Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
						Rectangle brickrect = rect;
						if(ballRect.intersects(brickrect))
						{
							m.setvalue(0,i,j);
							totalBricks--;
							score+=5;
						
						if(ballposX+19<=brickrect.x||ballposX+1>=brickrect.x+brickrect.width)
						{
							ballXdir=-ballXdir;
						}
						else
							ballYdir=-ballYdir;
						
						break A;
						}
					}
				}
			}
			
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			if(ballposX<0)
			{
				ballXdir=-ballXdir;
			}
			if(ballposY<0)
			{
				ballYdir=-ballYdir;
			}
			if(ballposX>670)
			{
				ballXdir=-ballXdir;
			}
	
		}
		repaint();
	}

}
