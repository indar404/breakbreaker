import javax.swing.*;
public class main
{
	public static void main(String [] args)
	{
		JFrame obj = new JFrame();
		play gameplay = new play();
		obj.setBounds(10,10,700,600);
		obj.setVisible(true);
		obj.setTitle("Brick Breaker");
		obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
	}

}
