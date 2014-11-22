   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
				/****************
  * Scoreboard is a JPanel used in Pokemon to display stats. It
  * takes a trainer and displays the trainers stats.
  * @author William Minshew, Jeffrey Siebach, and Michael Howard
  ************/
    public class Scoreboard extends JPanel 
   {
   					   	/*************
   * A label to display the trainers money
   ************/
      public JLabel cash;
   						   	/*************
   * A label to display the trainers pokemon's health
   ************/
      public JLabel health;
   						   	/*************
   * A label to display the trainers pokemon's level
   ************/
      public JLabel level;
   				   	/*************
   * Creates a default Scoreboard
   ************/
       public Scoreboard()
      {
         setLayout(new GridLayout(3, 1));
         cash = new JLabel("Money: $200     ");
         add(cash);
         health = new JLabel("Pikachu's Health: 30/100     ");
         add(health);
         level = new JLabel("Pikachu's Level: 1");
         add(level);
      }
   						   	/*************
   * Update takes a trainer argument and sets the labels to display
   * the trainers stats.
   * @param x  The trainer whos stats are to be displayed
   ************/
       public void update(Trainer x)
      {
         cash.setText("You have "+x.getCash()+" gold pieces ");
         health.setText(""+x.getPokemon(0).getName()+"'s health: " + x.getPokemon(0).getCurrentHealth()+"/"+x.getPokemon(0).getTotalHealth()+ " ");
         level.setText(""+x.getPokemon(0).getName()+"'s level: " + x.getPokemon(0).getLevel()+" ");
      }
   }
