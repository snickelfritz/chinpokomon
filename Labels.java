   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
			/****************
  * Labels is a class extending JPanel.  When sent a String,
  * it cuts it into multiple strings of smaller length (to
  * make it fit) and adds them to the bottom of the JPanel.
  * All others are shifted upwards to make room for the new String.
  * @author William Minshew, Jeffrey Siebach, and Michael Howard
  ************/
    public class Labels extends JPanel 
   {
      public JLabel[] labels = new JLabel[8];
			   	/*************
   * Creates a default Labels
   ************/
       public Labels()
      {
         setLayout(new GridLayout(8, 1, 2, 2));
         for(int h = 0; h < 8; h++)
         {
            labels[h] = new JLabel(" ");
            add(labels[h]);
         }
      }
					   	/*************
   * Adds a new string to the bottom of the list
	* @param s  The string to be added
   ************/
       public void update(String s)
      {
         if(s.length() > 40)
         {
         int k = 40;
         while(s.charAt(k)!= ' ')
         k--;
            update(s.substring(0, k));
            update("   " + s.substring(k, s.length()));
         }
         else
         {
            for(int h = 0; h < 7; h++)
            {
               labels[h].setText(""+labels[h+1].getText());
            }
            labels[7].setText(s+" ");
            if(labels[0].getText().charAt(0) == ' ')
         	labels[0].setText(" "); 
         }
      }
   }
