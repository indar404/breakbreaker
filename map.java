import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class map 
{
	public int ma[][];
	public int bwidth;
	public int bheight;
	
	public  map(int row,int col)
	{
		ma = new int[row][col];
		for(int i=0;i<ma.length;i++)
			{for(int j=0;j<ma[0].length;j++)
				{ma[i][j]=1;}}
		bwidth = 540/col;
		bheight = 150/row;
		
	}
	public  void draw(Graphics2D g)
	{
		for(int i=0;i<ma.length;i++)
			{for(int j=0;j<ma[0].length;j++)
				{if(ma[i][j]>0)
				{
					g.setColor(Color.white);
					g.fillRect(j*bwidth+80, i*bheight+50, bwidth, bheight);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*bwidth+80, i*bheight+50, bwidth, bheight);
					
				}}}
	}
	public void setvalue(int i, int i2, int j) {
		// TODO Auto-generated method stub
		ma[i2][j]=i;
		
	}
}
