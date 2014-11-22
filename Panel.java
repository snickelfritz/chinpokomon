   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
		/*****************************************************************
  * The panel is used to create the layout of the game.
  * @author William Minshew, Jeffrey Siebach, and Michael Howard
  ******************************************************************/
    public class Panel extends JPanel 
   {
	   	/******************************************************************
   * Creates a panel with a Panel2() and a BattlePanel1()
   ******************************************************************/
       public Panel()
      {
		Labels x = new Labels();
		Scoreboard y = new Scoreboard();
      BattlePanel1 bp = new BattlePanel1(x, y);
         setLayout(new BorderLayout());
         add(new Panel2(bp, x, y), BorderLayout.LINE_START);
         add(bp, BorderLayout.CENTER);
      }
   }
