   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import javax.swing.JOptionPane;
   import java.io.*;
   import java.lang.*;
   import java.util.Scanner;
	/*
  * The World is the visual display of the character walking around his city
  * in Pokemon.  It also contains the menus for when a character enters his
  * house or a shop.  Through the menu, users can also save and load their 
  * games.
  * @author William Minshew, Jeffrey Siebach, and Michael Howard
  */
    public class World extends JPanel implements KeyListener
   {
   /*************************
   *The amount of money in the bank
   *************************/
      public int bank;
         /*************************
   *The array of JLabels to display icons
   *************************/
      public JLabel[][] world;
         /*************************
   *The BattlePanel1
   *************************/
      private BattlePanel1 battle;
         /*************************
   *The array of ints corresponding to different images
   *************************/
      private int[][] loc = new int[58][58];
         /*************************
   *The characters x coordinate in the world.
   *************************/
      private int px = 13;
               /*************************
   *The characters y coordinate in the world.
   *************************/
      private int py = 10;
   	         /*************************
   *The x coordinate of the piece of the world being displayed
   *************************/
      private int pxw = 0;
   	   	         /*************************
   *The y coordinate of the piece of the world being displayed
   *************************/
      private int pyw = 0;
         /*************************
   *The trainer of the user
   *************************/
      public Trainer trainer;
         /*************************
   *A scanner used to save and load games
   *************************/
      public Scanner x;
         /*************************
   *Boolean determining which way the character is facing
   *************************/
      public boolean up;
               /*************************
   *Boolean determining which way the character is facing
   *************************/
      public boolean down;
   	         /*************************
   *Boolean determining which way the character is facing
   *************************/
      public boolean left;
   	         /*************************
   *Boolean determining which way the character is facing
   *************************/
      public boolean right;
         /*************************
   *Count used in the timers
   *************************/
      public int count = 0;
               /*************************
   *Count used in the timers
   *************************/
      public int count2 = 10;
         /*************************
   *A Timer to animate the start of a battle
   *************************/
      public Timer m = new Timer(75, new intoBattle());
         /*************************
   *a Timer to animate the end of a battle
   *************************/
      public Timer n = new Timer(75, new outOfBattle());
   	/*************************
   * Creates a world and sends it the BattlePanel as an argument.
   * This allows for easier communication between the world and 
   * the BattlePanel, for when a character gets into a fight or
   * enters the Gym. It displays basic instructions for the user.
   * @param k	Assigns the BattlePanel to battle.
   *************************/
       public World(BattlePanel1 k)
      {
         up = false;
         right = false;
         left = false;
         down = true;
         bank = 0;
         battle = k;
         battle.label.update("Welcome to Pokemon!");
         battle.label.update("");
         battle.label.update("Controls:");
         battle.label.update("[Arrow keys].......Move");
         battle.label.update("[Space bar].........Read Sign");
         battle.label.update("[Escape]..............Menu");
         battle.label.update("");
         battle.label.update("Good luck!");
         trainer = new Trainer(battle);
         battle.score.update(trainer);
         setFocusable(true);
         this.addKeyListener(this); 
         requestFocus();
         setLayout(new GridLayout(20, 20, 0, 0));
         world = new JLabel[20][20];
         for(int x = 0; x < 20; x++)
         {
            for(int y = 0; y < 20; y++)
            {
               world[x][y] = new JLabel(new ImageIcon("grass.jpg"));
               world[x][y].setHorizontalAlignment(SwingConstants.CENTER);
               world[x][y].setBackground(Color.gray.brighter());
               world[x][y].setOpaque(false);
               add(world[x][y]);
            }
         }
      
         fillWorld();
         loc[13][10]+=1;
         drawAll();
      }
   /*************************
   	* Draws an image for a square based on its int value.
   	* @param x	X coordinate of location
   	* @param y	Y coordinate of location
   	*************************/
       public void draw(int x, int y)
      {
         switch(loc[x+pxw][y+pyw])
         {
            case -2:world[x][y].setIcon(new ImageIcon("house14.jpg"));
               break;
            case -1:world[x][y].setIcon(new ImageIcon("manhouse14.jpg"));
               break;
            case 0:world[x][y].setIcon(new ImageIcon("grass.jpg"));
               break;
            case 1: 
               if(up)
                  world[x][y].setIcon(new ImageIcon("grassback.jpg"));
               if(down)
                  world[x][y].setIcon(new ImageIcon("mangrass.jpg"));
               if(left)
                  world[x][y].setIcon(new ImageIcon("grassleft.jpg"));
               if(right)
                  world[x][y].setIcon(new ImageIcon("grassright.jpg"));
               break;
            case 2:world[x][y].setIcon(new ImageIcon("field.jpg"));
               break;
            case 3:world[x][y].setIcon(new ImageIcon("manfield.jpg"));
               if(up)
                  world[x][y].setIcon(new ImageIcon("fieldback.jpg"));
               if(down)
                  world[x][y].setIcon(new ImageIcon("manfield.jpg"));
               if(left)
                  world[x][y].setIcon(new ImageIcon("fieldleft.jpg"));
               if(right)
                  world[x][y].setIcon(new ImageIcon("fieldright.jpg"));
               break;
            case 4:  world[x][y].setIcon(new ImageIcon("house14.jpg"));
               break;
            case 5:world[x][y].setIcon(new ImageIcon("manhouse14.jpg"));
               break;
            case 6:world[x][y].setIcon(new ImageIcon("house14.jpg"));
               break;
            case 7:world[x][y].setIcon(new ImageIcon("manhouse14.jpg"));
               break;
            case 8:world[x][y].setIcon(new ImageIcon("house14.jpg"));
               break;
            case 9:world[x][y].setIcon(new ImageIcon("manhouse14.jpg"));
               break;
            case 10:world[x][y].setIcon(new ImageIcon("tree.jpg"));
               break;
            case 12:world[x][y].setIcon(new ImageIcon("sign.jpg"));
               break;
            case 13:world[x][y].setIcon(new ImageIcon("house1.jpg"));
               break;
            case 14:world[x][y].setIcon(new ImageIcon("house2.jpg"));
               break;
            case 15:world[x][y].setIcon(new ImageIcon("house3.jpg"));
               break;
            case 16:world[x][y].setIcon(new ImageIcon("house4.jpg"));
               break;
            case 17:world[x][y].setIcon(new ImageIcon("house5.jpg"));
               break;
            case 18:world[x][y].setIcon(new ImageIcon("house6.jpg"));
               break;
            case 19:world[x][y].setIcon(new ImageIcon("house7.jpg"));
               break;
            case 20:world[x][y].setIcon(new ImageIcon("house8.jpg"));
               break;
            case 21:world[x][y].setIcon(new ImageIcon("house9.jpg"));
               break;
            case 22:world[x][y].setIcon(new ImageIcon("house10.jpg"));
               break;
            case 23:world[x][y].setIcon(new ImageIcon("house11.jpg"));
               break;
            case 24:world[x][y].setIcon(new ImageIcon("house12.jpg"));
               break;
            case 25:world[x][y].setIcon(new ImageIcon("house13.jpg"));
               break;
            case 27:world[x][y].setIcon(new ImageIcon("house15.jpg"));
               break;
            case 28:world[x][y].setIcon(new ImageIcon("house16.jpg"));
               break;
            case 29:world[x][y].setIcon(new ImageIcon("mart1.jpg"));
               break;
            case 30:world[x][y].setIcon(new ImageIcon("mart2.jpg"));
               break;
            case 31:world[x][y].setIcon(new ImageIcon("mart3.jpg"));
               break;
            case 32:world[x][y].setIcon(new ImageIcon("mart4.jpg"));
               break;
            case 33:world[x][y].setIcon(new ImageIcon("heal1.jpg"));
               break;
            case 34:world[x][y].setIcon(new ImageIcon("heal2.jpg"));
               break;
            case 35:world[x][y].setIcon(new ImageIcon("white.jpg"));
               break;
            case 36:world[x][y].setIcon(new ImageIcon("gym1.jpg"));
               break;
            case 37:world[x][y].setIcon(new ImageIcon("gym2.jpg"));
               break;
            case 38:world[x][y].setIcon(new ImageIcon("sign.jpg"));
               break;
            case 39:world[x][y].setIcon(new ImageIcon("sign.jpg"));
               break;
            case 40:world[x][y].setIcon(new ImageIcon("sign.jpg"));
               break;
         } 
      }
   	   /*************************
   	* Draws every square on the board
   	*************************/
       public void drawAll()
      {
         for(int x = 0; x < 20; x++)
         {
            for(int y = 0; y < 20; y++)
            {
               draw(x,y);            
            }
         }
      }
   	   /*************************
   	* Changes the int values in the array so they draw a house
   	* @param t	X coordinate of location
   	* @param u	Y coordinate of location
   	*************************/
       public void drawHouse(int t, int u)
      {
         for(int x = 0; x<4; x++)
            loc[t][u+x] = 13+x;
         for(int x = 0; x<4; x++)
            loc[t+1][u+x] = 17+x;
         for(int x = 0; x<4; x++)
            loc[t+2][u+x] = 21+x;
         for(int x = 0; x<4; x++)
            loc[t+3][u+x] = 25+x;
         loc[t+3][u+1] = 4;
         loc[t+3][u-1] = 12;
      }
   			   /*************************
   	* Changes the int values in the array so they draw a Gym
   	* @param t	X coordinate of location
   	* @param u	Y coordinate of location
   	*************************/
       public void drawGym(int t, int u)
      {
         loc[t][u] = 13;
         for(int o = u+1; o < u+7; o++)
            loc[t][o] = 14;
         loc[t][u+7] = 16;
      
         loc[t+1][u] = 17;
         for(int o = u+1; o < u+7; o++)
            loc[t+1][o] = 18;
         loc[t+1][u+7] = 20;
      
         loc[t+2][u] = 21;
         loc[t+2][u+1] = 36;
         loc[t+2][u+2] = 37;
         for(int o = u+3; o < u+7; o++)
            loc[t+2][o] = 23;
         loc[t+2][u+7] = 24;
      
         loc[t+3][u] = 25;
         loc[t+3][u+1] = -2;
         loc[t+3][u+2] = -2;
         for(int o = u+3; o < u+7; o++)
            loc[t+3][o] = 27;
         loc[t+3][u+7] = 28;
         loc[t+3][u-1] = 40;
      }
   			   /*************************
   	* Changes the int values in the array so they draw a Mart
   	* @param h	X coordinate of location
   	* @param j	Y coordinate of location
   	*************************/
       public void drawMart(int h, int j)
      {
         drawHouse(h,j);
         loc[h+2][j+2] = 29;
         loc[h+2][j+3] = 30;
         loc[h+3][j+2] = 31;
         loc[h+3][j+3] = 32;
         loc[h+3][j+1] = 6;
         loc[h+3][j-1] = 38;
      }
   			   /*************************
   	* Changes the int values in the array so they draw a Healing center
   	* @param g	X coordinate of location
   	* @param p	Y coordinate of location
   	*************************/
       public void drawHeal(int g, int p)
      {
         drawMart(g,p);
         loc[g+3][p+2] = 33;
         loc[g+3][p+3] = 34;
         loc[g+3][p+1] = 8;
         loc[g+3][p-1] = 39;
      }
   			   /*************************
   	* This is the menu for when a user walks into a Gym
   	*************************/
       public void displayGym()
      {
         Pokemon a, b, c;
         int z = (int)(Math.random() * 11);
         int z2 = (int)(Math.random() * 11);
         int z3 = (int)(Math.random() * 11);
         Object[] possibleValues = { "Train", "Fight Gym Leader", "Leave" };
         int choice = JOptionPane.showOptionDialog(this, "What would you like to do?", "Gym", 
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, possibleValues, possibleValues[0]);
         switch(choice)
         {
            case 0: Object[] possibleValues2 = { "Jeff", "Mike", "Will", "Nevermind!" };
               int choice2 = JOptionPane.showOptionDialog(this, "Who would you like to fight?", "Gym Training Room", 
                                 JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                 null, possibleValues2, possibleValues2[0]);
               switch(choice2)
               {
                  case 0:
                     a = new Bulbasaur(20 + z);
                     b = new Wartortle(20 + z2);
                     c = new Charizard(20 + z3);
                     Trainer jeff = new Trainer(battle, "Jeff", a, b, c);
                     battle.commenceBattle(trainer, jeff);
                     break;
                  case 1:
                     a = new Charmander(20 + z);
                     b = new Ivysaur(20 + z2);
                     c = new Blastoise(20 + z3);
                     Trainer mike = new Trainer(battle, "Mike", a, b, c);
                     battle.commenceBattle(trainer, mike);
                     break;
                  case 2:
                  
                     a = new Squirtle(20 + z);
                     b = new Charmeleon(20 + z2);
                     c = new Venusaur(20 + z3);
                     Trainer will = new Trainer(battle, "Will", a, b, c);
                     battle.commenceBattle(trainer, will);
                     break;
                  case 3:
                     displayGym();
                     break;
               }
               break;
            case 1:
               int choice3 = JOptionPane.showConfirmDialog(battle, "Are you sure you want"
                                 + " to fight the gym leader?",
                                 "Gym Leader Chamber",
                                 JOptionPane.YES_NO_OPTION,
                                 JOptionPane.QUESTION_MESSAGE); 
               switch(choice3)
               {
                  case 0:
                     a = new Raichu(40);
                     b = new Haunter(45);
                     c = new Alakazam(50);
                     Trainer leader = new Trainer(battle, "Minsuk", a, b, c);
                     battle.commenceBattle(trainer, leader);
                     break;
                  case 1:
                     displayGym();
                     break;
               }
               break;
            case 2:
               {
               }
         }
      
      }
   					   /*************************
   	* This is the menu for when a user walks into a House
   	*************************/
       public void displayHouse()
      {
         int mon = 0;
         Object[] possibleValues = { "Deposit Money", "Withdraw Money" , "Leave", "Tell your mom something" };
         int choice = JOptionPane.showOptionDialog(this, "You have " + bank + " gold in your safe", "Your House", 
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, possibleValues, possibleValues[0]);
         switch(choice)
         {
            case 0: mon = Integer.parseInt(JOptionPane.showInputDialog("How much?"));
               if(mon > trainer.getCash())
                  mon = trainer.getCash();
               bank += mon;
               trainer.setCash(trainer.getCash()-mon);
            
               break;
            case 1: mon = Integer.parseInt(JOptionPane.showInputDialog("How much?"));
               if(mon > bank)
                  mon = bank;
               bank -= mon;
               trainer.setCash(trainer.getCash() + mon);
               break;
            case 2:
               {
                  break;
               }
            case 3:
               String k = JOptionPane.showInputDialog("What do you say?");
               JOptionPane.showMessageDialog(this, "Hey Mom, "+k);
         }
         battle.score.update(trainer);
      }
   					   /*************************
   	* This is the menu for when a user walks into a Pokemart
   	*************************/
       public void displayMart()
      {
         int item = 0;
         Object[] possibleValues = { new ImageIcon("ball.jpg"), new ImageIcon("potion.jpg"), "Leave" };
         int choice = 0;
         choice = JOptionPane.showOptionDialog(this, 
                              "What would you like to buy?"+"\n You have $"+trainer.getCash(), "Pokemart", 
                              JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                              null, possibleValues, possibleValues[0]) + 1;
         if((choice == 1 || choice == 2) && (trainer.getCash() >= 100) && (trainer.getCash() >= choice*100))
         {
            trainer.setCash(trainer.getCash() - 100*choice);
            battle.score.update(trainer);
            if(choice == 1)
               trainer.setBalls(trainer.getBalls()+1);
            else 
               trainer.setPotions(trainer.getPotions() + 1);
            displayMart();
         } 
         else if(choice != 1 && choice != 2)
         {
         }
         else if(!(trainer.getCash() >= 100*choice))
         {
            JOptionPane.showMessageDialog(this, "You dont have enough gold!");
            displayMart();
         }
      }
   					   /*************************
   	* This is the menu for when a user walks into a healing center
   	*************************/
       public void displayHeal()
      {
         Object[] possibleValues = { "Heal Pokemon", "Leave" };
      
         int choice = JOptionPane.showOptionDialog(this, 
                           "What would you like to do?", "PokeCenter", 
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, possibleValues, possibleValues[0]);
      
         if(choice == 0)
            trainer.healAll();
         battle.score.update(trainer);
      }
   					   /*************************
   	* This is the method to save the game data to a text file
   	*************************/
       public void save()
      {
         Object[] possibleValues = { "File 1", "File 2", "File 3" };
      
         int choice = JOptionPane.showOptionDialog(this, 
                           "Which file?", "Save", 
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, possibleValues, possibleValues[0]);
         try
         {
            switch(choice)
            {
               case 0:
                  System.setOut(new PrintStream(new FileOutputStream(new File("save1.txt"))));
                  break;
               case 1:
                  System.setOut(new PrintStream(new FileOutputStream(new File("save2.txt"))));
                  break;
               case 2:
                  System.setOut(new PrintStream(new FileOutputStream(new File("save3.txt"))));
                  break;
            }
         }
             catch(FileNotFoundException e)
            {
            }
         System.out.println("" + trainer.getBalls());
         System.out.println("" + trainer.getPotions());
         System.out.println("" + trainer.getCash());
         System.out.println("" + trainer.getNumPokemon());
         for(int y = 0; y < trainer.getNumPokemon(); y++)
         {
            System.out.println("" + trainer.getPokemon(y).getNumber());
            System.out.println("" + trainer.getPokemon(y).getLevel());
         }
      }
   							   /*************************
   	* This is the method to load the game data from a text file
   	*************************/
       public void load()
      {
         Object[] possibleValues = { "File 1", "File 2", "File 3" };
         int choice = JOptionPane.showOptionDialog(this, 
                           "Which file?", "Load", 
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, possibleValues, possibleValues[0]);
         try
         {
            switch(choice)
            {
               case 0:
                  x = new Scanner(new File("save1.txt"));
                  break;
               case 1:
                  x = new Scanner(new File("save2.txt"));
                  break;
               case 2:
                  x = new Scanner(new File("save3.txt"));
                  break;
            }
         }
             catch(FileNotFoundException e)
            {
            }
         trainer.setBalls(x.nextInt());
         trainer.setPotions(x.nextInt());
         trainer.setCash(x.nextInt());
         int f = x.nextInt();
         trainer.numPokemon = f;
         int l = 0;
         int j = 0;
         for(int k = 0; k < f; k++)
         {
            j = x.nextInt();
            l = x.nextInt();
            switch(j)
            {
               case 1:
                  trainer.setPokemon(k, new Charmander(l));
                  break;
               case 2:
                  trainer.setPokemon(k, new Charmeleon(l));
                  break;
               case 3:
                  trainer.setPokemon(k, new Charizard(l));
                  break;
               case 4:
                  trainer.setPokemon(k, new Squirtle(l));
                  break;
               case 5:
                  trainer.setPokemon(k, new Wartortle(l));
                  break;
               case 6:
                  trainer.setPokemon(k, new Blastoise(l));
                  break;
               case 7:
                  trainer.setPokemon(k, new Bulbasaur(l));
                  break;
               case 8:
                  trainer.setPokemon(k, new Ivysaur(l));
                  break;
               case 9:
                  trainer.setPokemon(k, new Venusaur(l));
                  break;
               case 10:
                  trainer.setPokemon(k, new Pikachu(l));
                  break;
               case 11:
                  trainer.setPokemon(k, new Raichu(l));
                  break;
               case 12:
                  trainer.setPokemon(k, new Haunter(l));
                  break;
               case 13:
                  trainer.setPokemon(k, new Rattata(l));
                  break;
               case 14:
                  trainer.setPokemon(k, new Alakazam(l));
                  break;
            }
            battle.trainer = trainer;
            battle.score.update(trainer);
         }
      }
   							   /*************************
   	* This shows a pop-up window listing your characters stats
   	*************************/
       public void displayStats()
      {
         String message = "Potions: " + trainer.getPotions() + 
            "\nPokeballs: "+trainer.getBalls() + 
            "\nCash: "+trainer.getCash() +
            "\n\nPokemon:";
         for(int h = 0; h < trainer.getNumPokemon(); h++)
         {
            message = message + "\nLevel " + trainer.getPokemon(h).getLevel() + " " +trainer.getPokemon(h).getName() + " " + trainer.getPokemon(h).getCurrentHealth() + "/" + trainer.getPokemon(h).getTotalHealth(); 
            if(h == 0)
               message = message + " (active)";
         }
         JOptionPane.showMessageDialog(this, message);
      
      }
   							   /*************************
   	* This is the menu for when the user presses escape
   	*************************/
       public void escapeMenu()
      {
      
         Object[] possibleValues = { "Switch Pokemon", "Display Stats", "Save", "Load", "Return to Game", "Exit" };
      
         int choice = JOptionPane.showOptionDialog(this, 
                           "Menu", "Menu", 
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, possibleValues, possibleValues[0]);
      
         switch(choice)
         {
            case 0: trainer.switchActivePokemon();
               battle.score.update(trainer);
               break;
            case 1: displayStats();
               break;
            case 2: save();
               break;
            case 3: load();
               break;
            case 4:
               break;
            case 5:
               System.exit(0);
         }
      }
   	/*************************
   	* This is an unused method.
   	*		@param ke  The key typed
   	*************************/
       public void keyTyped(KeyEvent ke)
      {
      }     
      /*************************
   	* This is an unused method.
   	*		@param ke  The key released
   	*************************/ 
       public void keyReleased(KeyEvent ke)
      {
      }
      /*************************
   	* This changes the users location based on which key
   	* is pressed.
   	* @param ke  The key pressed
   	*************************/
       public void keyPressed(KeyEvent ke) 
      {
         switch (ke.getKeyCode()) 
         {
            case KeyEvent.VK_ESCAPE:
               escapeMenu();
               break;
            case KeyEvent.VK_LEFT:
               if(py >= 1 && loc[px][py - 1] < 10)
               {
                  if(py % 19 == 0 && py == pyw)
                  {
                     pyw -= 19;
                     drawAll();
                  }
                  loc[px][py - 1] +=1;
                  draw(px-pxw, py-1-pyw);
                  loc[px][py] -=1;
                  draw(px-pxw, py-pyw);
                  py -=1;
               }
               up = down = right = false;
               left = true;
               draw(px-pxw, py-pyw);
               break;
            case KeyEvent.VK_UP:
               if(px >= 1 && loc[px - 1][py] < 10)
               {
                  if(px % 19 == 0 && px == pxw)
                  {
                     pxw -= 19;
                     drawAll();
                  }
                  loc[px - 1][py] +=1;
                  draw(px-pxw-1, py-pyw);
                  loc[px][py] -=1;
                  draw(px-pxw, py-pyw);
                  px-=1;
                  if(loc[px][py] == 5)
                     displayHouse();
                  if(loc[px][py] == 7)
                     displayMart();
                  if(loc[px][py] == 9)
                     displayHeal();
                  if(loc[px][py] == -1)
                     displayGym();
               }
               down = left = right = false;
               up = true;
               draw(px-pxw, py-pyw);
               break;
            case KeyEvent.VK_DOWN:
               if(px <= 56 && loc[px + 1][py] < 10)
               {
                  if(px % 19 == 0 && px == pxw + 19)
                  {
                     pxw += 19;
                     drawAll();
                  }
                  loc[px + 1][py] +=1;
                  draw(px-pxw+1, py-pyw);
                  loc[px][py] -=1;
                  draw(px-pxw, py-pyw);
                  px+=1;
               }
               up = left = right = false;
               down = true;
               draw(px-pxw, py-pyw);
               break;
            case KeyEvent.VK_RIGHT:
               if(py <= 56 && loc[px][py + 1] < 10)
               {
                  if(py % 19 == 0 && py == pyw + 19)
                  {
                     pyw += 19;
                     drawAll();
                  }
                  loc[px][py + 1] +=1;
                  draw(px-pxw, py-pyw+1);
                  loc[px][py] -=1;
                  draw(px-pxw, py-pyw);
                  py+=1;
               }
               up = left = down = false;
               right = true;
               draw(px-pxw, py-pyw);
               break;
            case KeyEvent.VK_SPACE:
               switch(spaceFacing())
               {
                  case 12:
                     JOptionPane.showMessageDialog(this, "Your House"); 
                     break;
                  case 38:
                     JOptionPane.showMessageDialog(this, "Pokemart - for all your pokemon related needs!"); 
                     break;
                  case 39:
                     JOptionPane.showMessageDialog(this, "Pokecenter - We will heal your pokemon, free!"); 
                     break;
                  case 40:
                     JOptionPane.showMessageDialog(this, "Come in to train or fight the Gym Leader, Minsuk"); 
                     break;
               }}
         if(loc[px][py]==1 && Math.random()*6<1)
         {
            setFocusable(false);
            m.start();
         }
      }
            /*************************
   	* Returns the int value of the space the character
   	* is facing.  This is used to determine what sign
   	* to display if the user presses space
   	*************************/
       public int spaceFacing()
      {
         if(up)
            return loc[px-1][py];
         else if(left)
            return loc[px][py-1];
         else if(right)
            return loc[px][py+1];
         else
            return loc[px+1][py];
      }
                  /*************************
   	* Calls for the start of a battle to the BattlePanel and
   	* animates the world turning black.
     	*************************/
       public void battle()
      {
         m.stop();
         for(int x = 0; x < 20; x++)
         {
            for(int y = 0; y < 20; y++)
            {
               world[x][y].setOpaque(true);
               repaint();
            }
         }
         battle.commenceBattle(trainer, trainer.getPokemon(0), wildPokemon());
         for(int x = 0; x < 20; x++)
         {
            for(int y = 0; y < 20; y++)
            {
               world[x][y].setOpaque(false);
            }
         }
         setFocusable(true);
         n.start();
      }
                        /*************************
   	* Returns a random wild pokemon
     	*************************/
       public Pokemon wildPokemon()
      {
         double x = (Math.random() * 3);
         if(x < 1.2)
            return new Rattata(randomLevel());
         else if(x < 2)
            return new Pikachu(randomLevel());
         else if(x < 2.5)
            return new Alakazam(randomLevel() + 1);
         else if(x < 2.9)
            return new Raichu(randomLevel() + 3);
         else
            return new Haunter(randomLevel() + 5);
      }
      /*************************
   	* Returns a good level for a wild pokemon based on
   	* the users pokemon's levels.
     	*************************/
       public int randomLevel()
      {
         int x = trainer.getPokemon(0).getLevel();
         int random = (int)(Math.random() * 4);
         while(random > x - 1)
         {
            random--;
         }
         if(Math.random() > .5)
         {
            return x + random;
         }
         else
         {
            return x - random;
         }
      }
      /*************************
   	* Fills the array of ints for the world with their respective values.
     	*************************/
       public void fillWorld()
      {
         for(int x = 0; x < 58; x++)
         {
            for(int y = 0; y < 58; y++)
            {
               if(x <= 5 || x >= 52 || y <= 5 || y >= 52 || x + y <= 13)
                  loc[x][y] = 35;
               else if((x <= 7 || x >= 50 || y <= 7 || y >= 50 || x + y <= 17 || (x == 19 && y < 39)))
                  loc[x][y] = 10;
               else if((x <= 9 || x >= 48 || y <= 9 || y >= 48)  && !(x < 20 && y < 20)  || (y > x + 30))
                  loc[x][y] = 0;
               else
                  loc[x][y] = 2;
            }
         }
         for(int x = 19; x < 39; x++)
            for(int y = 19; y < 39; y++)
               loc[x][y] = 10;
         for(int x = 20; x < 38; x++)
            for(int y = 20; y < 38; y++)
               loc[x][y] = 2;
         for(int k = 21; k < 25; k++)
            loc[k][19] = 2;
         loc[19][6] = 35;
      
         loc[8][19] = loc[9][19] = loc[18][8] = loc[19][8] = 
            loc[19][9] = loc[19][7] = loc[18][6] = loc[17][8] = 
            loc[18][9] = loc[8][10] = loc[8][11] = loc[8][12] = 
            loc[10][8] = loc[11][8] = loc[8][20] = 10;
      
         drawHouse(9, 9);
         drawMart(12, 23);
         drawHeal(13, 30);
         drawGym(20, 30);
      
         for(int x = 13; x < 19; x++)
         {
            loc[8][x] = 10;
            loc[9][x] = 10;
         }
      }
            /*************************
   	* Animates the beginning of a battle.
     	*************************/
       private class intoBattle implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
            for(int x = 0; x < 20; x++)
            {
               for(int y = 0; y < 20; y++)
               {
                  if(x == 0 + count || y == 0 + count || x == 19 - count || y == 19 - count)
                  {
                     world[x][y].setIcon(new ImageIcon("black.jpg"));
                  }
               }
            }
            count++;
            if(count >= 10)
            {
               battle();
               count=-1;
            }
         }
      }
                  /*************************
   	* Animates the end of a battle.
     	*************************/
       private class outOfBattle implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
            for(int x = 0; x < 20; x++)
            {
               for(int y = 0; y < 20; y++)
               {
                  if(x == 0 + count2 || y == 0 + count2 || x == 19 - count2 || y == 19 - count2)
                  {
                     draw(x,y);
                  }
               }
            }
            count2--;
            if(count2 < 0)
            {
               count2= 10;
               n.stop();
               requestFocus();
            }
         }
      }
   }
