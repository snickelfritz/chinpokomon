   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
			/******************************************************************
  * The panel2 is used to create the layout of the game.
  * @author William Minshew, Jeffrey Siebach, and Michael Howard
  ******************************************************************/
    public class Panel2 extends JPanel 
   {
   	   	/******************************************************************
   * Creates a panel with a Labels(), a Scoreboard(), and a World()
   ******************************************************************/
       public Panel2(BattlePanel1 x, Labels l, Scoreboard s)
      {
         setLayout(new BorderLayout());
         World w = new World(x);
         add(w, BorderLayout.PAGE_START);
         add(l, BorderLayout.LINE_START);
         add(s, BorderLayout.LINE_END);
      }
   }
