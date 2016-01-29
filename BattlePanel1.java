   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.awt.geom.*;
   import java.lang.*;
   import pokemons.*;
  /***********************************************************************************
  * BattlePanel1 is the class responsible for any battles that take place in pokemon.
  * It takes care of all the animations for the battle, as well as communicating with
  * the user and executing the user's commands.  It interacts with pokemon to deal out
  * the damages as well as evolves them when they reach the right point.  It also
  * interacts with the trainer to dish out the spoils of battle and use their items.
  * @author William Minshew, Jeffrey Siebach, and Michael Howard
  ***********************************************************************************/
    public class BattlePanel1 extends JPanel
   {
   /*************************************************
   * Width of the panel
   *************************************************/
      public static final int M = 300;
   /*************************************************
   * Height of the panel
   *************************************************/
      public static final int N = 600;
   /*************************************************
   * Used to keep track of the damage dealt by
   * pokemon after it has been modified by their
   * defense.
   *************************************************/
      public int dam = 0;
   /*************************************************
   * Used to control the pokemon in the battle.
   *************************************************/
      public Pokemon a, b;
   /*************************************************
   * Used to communicate with the trainers of the
   * pokemon in battle.
   *************************************************/
      public Trainer trainer, trainer2;
   
   /*************************************************
   * Used to keep track of whether the battle is
   * still raging or not.
   *************************************************/
      public boolean battleOutcome = false;
   
   /*************************************************
   * Used to keep track of the raw damage dealt by
   * pokemon.
   *************************************************/
      public int damage;
	/*************************************************
   * Used to communicate with labels.
   *************************************************/
      public Labels label;
   /*************************************************
   * Used to communicate with the scoreboard.
   *************************************************/
      public Scoreboard score;
   /*************************************************
   * Used to call the listeners repeatedly to help 
   * create the look of animation.
   *************************************************/
      public Timer t;
   /*************************************************
   * Used to help turn the picture into the right
   * size.
   *************************************************/
      public BufferedImage myImage;
   /*************************************************
   * Used to draw out the scene.
   *************************************************/
      public Graphics2D myBuffer;
   /*************************************************
   * Used to keep track of the position of the icons
   * in battle and move them.
   *************************************************/
      public int xpos, ypos, xpos1, ypos1, dx, dx1;
   
   /***********************************************************************************
   * Constructs a BattlePanel1 that has contact to the scoreboard and labels so it
   * can keep them up to date.  It colors the entire battle screen black, and resets
   * the coordinates that the pokemon's icons will start at.
   * @param l	Assigns l to label
   * @param k	Assigns k to score
   ***********************************************************************************/
       public BattlePanel1(Labels l, Scoreboard k)
      {
         label = l;
         score = k;
         myImage =  new BufferedImage(M, N, BufferedImage.TYPE_INT_RGB);
         myBuffer = (Graphics2D)myImage.getGraphics();
         myBuffer.setColor(Color.black);
         myBuffer.fillRect(0, 0, M, N);
         xpos = -100;
         ypos = 480;
         xpos1 = 300;
         ypos1 = 0;
         dx = 0;
         dx1 = 0;
      }
   	/***********************************************************************************
   	* Paints the component with graphics g.
   	* @param g	Uses g to draw the Image.
   	***********************************************************************************/
       public void paintComponent(Graphics g)
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
   	/***********************************************************************************
   	* Starts a battle between the trainer and a wild pokemon.
   	* @param tr	Assigns tr to trainer.
   	* @param c	Assigns c to pokemon a (the trainer's active pokemon)
   	* @param d	Assigns d to b (the wild pokemon)
   	***********************************************************************************/
       public void commenceBattle(Trainer tr, Pokemon c, Pokemon d)
      {
         if(trainer2 != null)
            trainer2 = null;
         trainer = tr;
         score.update(trainer);
         a = c;
         b = d;
         label.update("A wild " + b.getName() + " appeared!");
         battleBegin();
         fight();
      }
   	/***********************************************************************************
   	* Starts a battle between 2 different trainers or is
   	* used when a pokemon is knocked out during a trainer battle and the trainer
   	* needs to call another pokemon.
   	* @param tr	Assigns tr to trainer (the user)
   	* @param tr2	Assigns tr2 to trainer2 (the trainer the user is fighting)
   	***********************************************************************************/
       public void commenceBattle(Trainer tr, Trainer tr2)
      {
         trainer = tr;
         trainer2 = tr2;
         int k = 0;
         if(trainer.getPokemon(0).getCurrentHealth()<=0)
            k = 1;
         if(trainer2.getPokemon(0).getCurrentHealth()<=0)
            k = 2;
         if(trainer2.getPokemon(0).getCurrentHealth() > 0)
            JOptionPane.showMessageDialog(this, trainer2.getMessage());
         a = trainer.getNextAlive();
         b = trainer2.getNextAlive();
         switch(k)
         {
            case 0:
               battleBegin();
               break;
            case 1:
               battleBegin2();
               break;
            case 2:
               battleBegin3();
               break;
         }
         fight();
      }
   	/***********************************************************************************
   	* Used as the animation for if an enemy trainer's pokemon is knocked out and
   	* they have to switch pokemon.
   	***********************************************************************************/
       public void battleBegin3()
      {
         xpos1 = 300;
         score.update(trainer);
         myBuffer.setColor(Color.white);
         myBuffer.fillRect(0, 0, M, N);
         t = new Timer(1, new Listener3());
         t.start();
      }
   	/***********************************************************************************
   	* Used for when a trainer is fighting a wild pokemon, and
   	* his active pokemon is knocked out.
   	* @param tr	Assigns tr to trainer
   	* @param c	Assigns c to pokemon (the pokemon that the trainer is bringing out)
   	***********************************************************************************/
       public void commenceBattle2(Trainer tr, Pokemon c)
      {
         battleOutcome = false;
         trainer = tr;
         score.update(trainer);
         a = c;
         label.update("Go, "+a.getName()+"!");
         battleBegin2();
         computerAttack();
         if(!battleOutcome)
            fight();
      }
   	/***********************************************************************************
   	* Used for when a battle first starts.
   	***********************************************************************************/
       public void battleBegin()
      {
         score.update(trainer);
         myBuffer.setColor(Color.white);
         myBuffer.fillRect(0, 0, M, N);
         xpos = -100;
         xpos1 = 300;
         t = new Timer(1, new Listener());
         t.start();
      }
   	/***********************************************************************************
   	* Used for the animation when one of the user's pokemon is
   	* knocked out and they are forced to switch pokemon (if they have any
   	* remaining.)
   	***********************************************************************************/
       public void battleBegin2()
      {
         xpos = -100;
         score.update(trainer);
         myBuffer.setColor(Color.white);
         myBuffer.fillRect(0, 0, M, N);
         t = new Timer(1, new Listener2());
         t.start();
      }
      /***********************************************************************************
   	* Prompts the user which course of action they would like to
   	* take in the battle, and then sending them down that course.
   	***********************************************************************************/
       public void fight()
      {
         battleOutcome = false;
         score.update(trainer);
         Object[] possibleValues = { "Attack", "Use an Item", "Switch Pokemon", "Run Away" };
      
         int choice = JOptionPane.showOptionDialog(this, "What would you like to do?", "Fight!", 
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, possibleValues, possibleValues[0])+1;
      
         switch(choice)
         {
            case 1: attack();
               break;
            case 2: useAnItem();
               break;
            case 3: switchPokemon();
               break;
            case 4: runAway();
               break;
            default: System.out.println("Not a valid selection.");
               fight();
               break;
         }
      
      }
      /***********************************************************************************
   	* Prompts the user which attack to use, and then executes it.
   	***********************************************************************************/
       public void attack()
      {
         score.update(trainer);
         Object[] possibleValues = { "" + a.getFirstAttack(), "" + a.getSecondAttack(), "Back" };
      
         int choice = JOptionPane.showOptionDialog(this, "Which attack?", "Fight!", 
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, possibleValues, possibleValues[0])+1;
      
         if(choice == 1 || choice == 2)
            if(a.getSpeed() < b.getSpeed())
            {
               computerAttack();
            }
         if(!battleOutcome)
            switch(choice)
            {
               case 1: a.animateAttack1();
                  damage = a.attack1();
                  int dam = b.takeDamage(damage);
                  label.update((a.getName() + " used " + a.getFirstAttack() + " and dealt " + dam + " damage!"));
                  if(b.getHealthPercent() <= 0)
                  {
                     battleOutcome = true;
                     endBattle(true);
                  }
                  break;
               case 2: a.animateAttack2();
                  damage = a.attack2();
                  dam = b.takeDamage(damage);
                  label.update((a.getName() + " used " + a.getSecondAttack() + " and dealt " + dam + " damage!"));         
                  if(b.getHealthPercent() <= 0)
                  {
                     battleOutcome = true;
                     endBattle(true);
                  }
                  break;
               case 3: fight();
                  break;            
               default: System.out.println("Not a valid selection.");
                  fight();
                  break;
            }
         if(choice == 1 || choice == 2)
            if(!battleOutcome)
               if(a.getSpeed() >= b.getSpeed())
               {
                  computerAttack();
               }
         if(choice == 1 || choice == 2)
            if(!battleOutcome)
               fight();
         score.update(trainer);
      }
     /***********************************************************************************
     * Code for the enemy pokemon attacking the user's pokemon.
     ***********************************************************************************/
       public void computerAttack()
      {
         if(Math.random() > .5)
         {
            b.animateAttack1();
            damage = b.attack1();
            dam = a.takeDamage(damage);
            label.update(b.getName() + " used " + b.getFirstAttack() + " and dealt " + dam + " damage!");
         }
         else
         {
            b.animateAttack2();
            damage = b.attack2();
            dam = a.takeDamage(damage);
            label.update(b.getName() + " used " + b.getSecondAttack() + " and dealt " + dam + " damage!");
         }
         if(a.getHealthPercent() <= 0)
         {
            battleOutcome = true;
            endBattle(false);
         }
      }
      /***********************************************************************************
   	* Called when a pokemon is knocked out, it either ends the battle or
   	* re-instantiates a new battle with one of the trainer's next pokemon.
   	* @param x	Used to determine whether the user's pokemon or the enemy pokemon
   	*				was knocked out.
   	***********************************************************************************/
       public void endBattle(boolean x)
      {
         score.update(trainer);
         t.stop();
         if(x)
         {
            b.setHealth(0);
            if(trainer2 != null)
            {
               if(trainer2.pokemonRemaining() == 0)
               {
                  label.update("You defeated " + trainer2.getName() + "!");
                  int gold = (int)(trainer2.getCash());
                  trainer.setCash(trainer.getCash() + gold);
                  label.update("You received " + gold + " pieces of gold!");
               }
               else
               {
                  label.update("You defeated " + trainer2.getName() + "'s " + b.getName() + "!");
                  int y = a.getLevel();
                  a.setExp(a.getExp() + b.giveExp());
                  label.update("" + a.getName() + " has received " + b.giveExp() + " exp!");
                  if(a.getLevel() > y)
                  {
                     label.update(a.getName() + " leveled up!");
                  }
                  if(a.getLevel() == 18)
                  {
                     if(!a.getEvolvedOnce())
                     {
                        label.update(a.getName() + " is evolving...");
                        int z = a.getExp();
                        trainer.setPokemon(0, trainer.getPokemon(0).getEvolve(18));
                        label.update("...into "+trainer.getPokemon(0).getName()+"!");
                        trainer.getPokemon(0).setExp(z);
                     }
                  }
                  if(a.getLevel() == 32)
                  {
                     if(!a.getEvolvedTwice())
                     {
                        label.update(a.getName() + " is evolving...");
                        int z = a.getExp();
                        trainer.setPokemon(0, trainer.getPokemon(0).getEvolve(32));
                        label.update("...into "+trainer.getPokemon(0).getName()+"!");
                        trainer.getPokemon(0).setExp(z);
                     }
                  }
                  commenceBattle(trainer, trainer2);
               }
            }
            else
            {
               label.update("You defeated the wild " + b.getName() + "!");
               int y = a.getLevel();
               a.setExp(a.getExp() + b.giveExp());
               label.update("" + a.getName() + " has received " + b.giveExp() + " exp!");
               if(a.getLevel() > y)
               {
                  label.update(a.getName() + " leveled up!");
               }
               if(a.getLevel() == 18)
               {
                  if(!a.getEvolvedOnce())
                  {
                     label.update(a.getName() + " is evolving...");
                     int z = a.getExp();
                     trainer.setPokemon(0, trainer.getPokemon(0).getEvolve(18));
                     label.update("...into "+trainer.getPokemon(0).getName()+"!");
                     trainer.getPokemon(0).setExp(z);
                  }
               }
               if(a.getLevel() == 32)
               {
                  if(!a.getEvolvedTwice())
                  {
                     label.update(a.getName() + " is evolving...");
                     int z = a.getExp();
                     trainer.setPokemon(0, trainer.getPokemon(0).getEvolve(32));
                     label.update("...into "+trainer.getPokemon(0).getName()+"!");
                     trainer.getPokemon(0).setExp(z);
                  }
               }
               if(Math.random() < .05)
               {
                  trainer.setBalls(trainer.getBalls() + 1);
                  label.update("You found a Pokeball!");
               }
               else if(Math.random() > .95)
               {
                  trainer.setPotions(trainer.getPotions() + 1);
                  label.update("You found a potion!");
               }
               else
               {
                  int gold = (int)(Math.random() * 25 + b.getLevel() * 5);
                  trainer.setCash(trainer.getCash() + gold);
                  label.update("You found " + gold + " pieces of gold!");
               }
            }
         }
         else
         {
            if(trainer2 != null)
            {
               label.update(a.getName() + " was knocked unconscious!");
               a.setHealth(0);
               if(trainer.pokemonRemaining() == 0)
               {
                  label.update(trainer2.getName() + " defeated you in battle!");
                  label.update("You were jacked by " + trainer2.getName());
                  trainer2.setCash(trainer2.getCash() + trainer.getCash());
                  trainer.setCash(0);
                  trainer.faint();
               }
               else
               {
                  trainer.switchActivePokemonFight();
               }
            }
            else
            {
               label.update(a.getName() + " was knocked unconscious!");
               a.setHealth(0);
               if(trainer.pokemonRemaining() == 0)
               {
                  trainer.faint();
               }
               else
               {
                  trainer.switchActivePokemonFight();
               }
               score.update(trainer);
            }
         }
         myBuffer.setColor(Color.black);
         myBuffer.fillRect(0, 0, M, N);
         xpos = -100;
         xpos1 = 300;
         repaint();
         score.update(trainer);
      }
      /***********************************************************************************
   	* Prompts the user which item to use.
   	***********************************************************************************/
       public void useAnItem()
      {
         Object[] possibleValues = {"Pokeball("+trainer.getBalls()+")", "Health Potion("+trainer.getPotions()+")", "Back"};
         int choice = JOptionPane.showOptionDialog(this, "What item?", "Items",
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]) + 1;
         switch(choice)
         {
            case 1: usePokeball();
               break;
            case 2: usePotion();
               break;
            case 3: fight();
         }
      }
      /***********************************************************************************
   	* Uses a potion on a pokemon to be selected by the user.
   	***********************************************************************************/
       public void usePotion()
      {
         if(trainer.getPotions() > 0)
         {
            Object[] possibleValues = new Object[trainer.getNumPokemon()+1];
            for(int x = 0; x < possibleValues.length; x++)
            {
               if(x == possibleValues.length - 1)
                  possibleValues[x] = "Back";
               else
                  possibleValues[x] = trainer.getPokemon(x);
            }
            int choice = JOptionPane.showOptionDialog(this, "Which Pokemon?", "Using Healing Potion...", 
                              JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);
            if(choice == possibleValues.length-1)
            {
               fight();
            }
            else
            {
               int h = trainer.getPokemon(choice).getTotalHealth() - trainer.getPokemon(choice).getCurrentHealth();
               trainer.getPokemon(choice).heal();
               label.update("Pikachu healed "+h+" health!");
               trainer.setPotions(trainer.getPotions() - 1);
               computerAttack();
               score.update(trainer);
               if(!battleOutcome)
               {
                  fight();
               }
            }
         }
         else
         {
            JOptionPane.showMessageDialog(this, "You have no potions!");
            fight();
         }
      }
      /***********************************************************************************
   	* Uses a pokeball and determines if the opposing pokemon is caught or not.
   	***********************************************************************************/
       public void usePokeball()
      {	
         if(trainer.getBalls() > 0)
         {
            trainer.setBalls(trainer.getBalls() - 1);
            if(Math.random()*100 > b.getHealthPercent())
            {	
               t.stop();
               myBuffer.setColor(Color.black);
               myBuffer.fillRect(0, 0, M, N);
               xpos = -100;
               xpos1 = 300;
               repaint();
               trainer.addPokemon(b);
               label.update("You have caught a level " + b.getLevel() + " " + b.getName() + "!");
            }
            else
            {
               label.update("The pokemon is too strong, you should weaken it some more");
               computerAttack();
               score.update(trainer);
               if(!battleOutcome)
                  fight();
            }
         }
         else
         {	
            JOptionPane.showMessageDialog(this, "You have no balls!");
            fight();
         }
      }
   /***********************************************************************************
   * Switches the trainers active pokemon during a fight.
   ***********************************************************************************/
       public void switchPokemon()
      {
         trainer.switchActivePokemonFight();
      }
   /***********************************************************************************
   * Decides whether the user can successfully run away from the battle or not.
   ***********************************************************************************/
       public void runAway()
      {
         if(trainer2 == null)
         {
            label.update("Trying to escape...");
            if(a.getSpeed() > b.getSpeed())
            {
               if(Math.random() > .33)
               {
                  label.update("You successfully escaped from the battle.");
                  endBattle();
               }
               else
               {
                  label.update("The other pokemon is too fast, you'll have to fight.");
                  computerAttack();
                  if(!battleOutcome)
                  {
                     fight();
                  }
               }
            }
            else
            {
               if(Math.random() > .85)
               {
                  label.update("You successfully escaped from the battle.");
                  endBattle();
               }
               else
               {
                  label.update("The other pokemon is too fast, you'll have to fight.");
                  computerAttack();
                  if(!battleOutcome)
                  {
                     fight();
                  }
               }
            }
         }
         else
         {
            JOptionPane.showMessageDialog(this, "You cannot run from a trainer!");
            fight();
         }
      }
   /***********************************************************************************
   * Ends the battle with the user running away (no outcome essentially)
   ***********************************************************************************/
       public void endBattle()
      {
         t.stop();
         repaint();
         xpos = -100;
         xpos1 = 300;
         repaint();
         myBuffer.setColor(Color.black);
         myBuffer.fillRect(0, 0, M, N);
      }
   /***********************************************************************************
   * Runs to animate the battle beginning, and keeps track of the pokemons' health.
   ***********************************************************************************/
       public class Listener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
            myBuffer.setColor(Color.white);
            myBuffer.fillRect(0, 0, M, N);
            myBuffer.drawImage(a.getIcon().getImage(), xpos, ypos, 100, 120, null);
            myBuffer.drawImage(b.getIcon().getImage(), xpos1, ypos1, 100, 120, null);
            xpos = xpos + dx;
            xpos1 = xpos1 + dx1;
            if(xpos < 30)
            {
               dx = 1;
            }
            else if(xpos >= 30)
            {
               dx = 0;
               myBuffer.setColor(Color.black);
               myBuffer.setFont(new Font("Sanserif", Font.BOLD, 18));
               myBuffer.drawString(a.getCurrentHealth() + "/" + a.getTotalHealth(), 170, 580);
               myBuffer.drawString("" + a.getName() + " Lv. " + a.getLevel(), 130, 545);
               myBuffer.drawRect(130, 550, 150, 10);
               if(a.getHealthPercent() > 50)
               {
                  myBuffer.setColor(Color.green);
               }
               else if(a.getHealthPercent() > 25)
               {
                  myBuffer.setColor(Color.yellow);
               }
               else
               {
                  myBuffer.setColor(Color.red);
               }
               myBuffer.fillRect(131, 551, (int)(150 * (a.getHealthPercent()/100.0)) - 1, 9);
            }
            if(xpos1 > 175)
            {
               dx1 = -1;
            }
            else if(xpos1 <= 175)
            {
               dx1 = 0;
               myBuffer.setColor(Color.black);
               myBuffer.setFont(new Font("Sanserif", Font.BOLD, 18));
               myBuffer.drawString(b.getCurrentHealth() + "/" + b.getTotalHealth(), 60, 60);
               myBuffer.drawString("" + b.getName() + " Lv. " + b.getLevel(), 20, 25);
               myBuffer.drawRect(20, 30, 150, 10);
               if(b.getHealthPercent() > 50)
               {
                  myBuffer.setColor(Color.green);
               }
               else if(b.getHealthPercent() > 25)
               {
                  myBuffer.setColor(Color.yellow);
               }
               else
               {
                  myBuffer.setColor(Color.red);
               }
               myBuffer.fillRect(21, 31, (int)(150 * (b.getHealthPercent()/100.0)) - 1, 9);
            }
            repaint();
         }
      }
   /***********************************************************************************
   * Runs to animate the battle beginning if the enemy trainer's pokemon is knocked
   * out, and keeps track of the pokemons' health.
   ***********************************************************************************/
       public class Listener3 implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
            myBuffer.setColor(Color.white);
            myBuffer.fillRect(0, 0, M, N);
            myBuffer.drawImage(a.getIcon().getImage(), 30, ypos, 100, 120, null);
            myBuffer.drawImage(b.getIcon().getImage(), xpos1, ypos1, 100, 120, null);
            myBuffer.setColor(Color.black);
            myBuffer.setFont(new Font("Sanserif", Font.BOLD, 18));
            myBuffer.drawString(a.getCurrentHealth() + "/" + a.getTotalHealth(), 170, 580);
            myBuffer.drawString("" + a.getName() + " Lv. " + a.getLevel(), 130, 545);
            myBuffer.drawRect(130, 550, 150, 10);
            if(a.getHealthPercent() > 50)
            {
               myBuffer.setColor(Color.green);
            }
            else if(a.getHealthPercent() > 25)
            {
               myBuffer.setColor(Color.yellow);
            }
            else
            {
               myBuffer.setColor(Color.red);
            }
            myBuffer.fillRect(131, 551, (int)(150 * (a.getHealthPercent()/100.0)) - 1, 9);
            xpos1 = xpos1 + dx1;
            if(xpos1 > 175)
            {
               dx1 = -1;
            }
            else if(xpos1 <= 175)
            {
               dx1 = 0;
               myBuffer.setColor(Color.black);
               myBuffer.setFont(new Font("Sanserif", Font.BOLD, 18));
               myBuffer.drawString(b.getCurrentHealth() + "/" + b.getTotalHealth(), 60, 60);
               myBuffer.drawString("" + b.getName() + " Lv. " + b.getLevel(), 20, 25);
               myBuffer.drawRect(20, 30, 150, 10);
               if(b.getHealthPercent() > 50)
               {
                  myBuffer.setColor(Color.green);
               }
               else if(b.getHealthPercent() > 25)
               {
                  myBuffer.setColor(Color.yellow);
               }
               else
               {
                  myBuffer.setColor(Color.red);
               }
               myBuffer.fillRect(21, 31, (int)(150 * (b.getHealthPercent()/100.0)) - 1, 9);
            }
            repaint();
         }
      }
   /***********************************************************************************
   * Runs to animate the battle beginning if the user's pokemon is knocked
   * out, and keeps track of the pokemons' health.
   ***********************************************************************************/
       public class Listener2 implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
            myBuffer.setColor(Color.white);
            myBuffer.fillRect(0, 0, M, N);
            myBuffer.drawImage(a.getIcon().getImage(), xpos, ypos, 100, 120, null);
            myBuffer.drawImage(b.getIcon().getImage(), xpos1, ypos1, 100, 120, null);
            xpos = xpos + dx;
            if(xpos < 30)
            {
               dx = 1;
            }
            else if(xpos >= 30)
            {
               dx = 0;
               myBuffer.setColor(Color.black);
               myBuffer.setFont(new Font("Sanserif", Font.BOLD, 18));
               myBuffer.drawString(a.getCurrentHealth() + "/" + a.getTotalHealth(), 170, 580);
               myBuffer.drawString("" + a.getName() + " Lv. " + a.getLevel(), 130, 545);
               myBuffer.drawRect(130, 550, 150, 10);
               if(a.getHealthPercent() > 50)
               {
                  myBuffer.setColor(Color.green);
               }
               else if(a.getHealthPercent() > 25)
               {
                  myBuffer.setColor(Color.yellow);
               }
               else
               {
                  myBuffer.setColor(Color.red);
               }
               myBuffer.fillRect(131, 551, (int)(150 * (a.getHealthPercent()/100.0)) - 1, 9);
            }
            myBuffer.setColor(Color.black);
            myBuffer.setFont(new Font("Sanserif", Font.BOLD, 18));
            myBuffer.drawString(b.getCurrentHealth() + "/" + b.getTotalHealth(), 60, 60);
            myBuffer.drawString("" + b.getName() + " Lv. " + b.getLevel(), 20, 25);
            myBuffer.drawRect(20, 30, 150, 10);
            if(b.getHealthPercent() > 50)
            {
               myBuffer.setColor(Color.green);
            }
            else if(b.getHealthPercent() > 25)
            {
               myBuffer.setColor(Color.yellow);
            }
            else
            {
               myBuffer.setColor(Color.red);
            }
            myBuffer.fillRect(21, 31, (int)(150 * (b.getHealthPercent()/100.0)) - 1, 9);
            repaint();
         }
      }
   }