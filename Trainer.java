   import javax.swing.*;
   import java.awt.image.*;
   import java.awt.geom.*;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.JOptionPane;
   import java.lang.*;
	/*****************************************************************************
	* The trainer class is used to represent the user in the world of pokemon.
	* They know how much cash they have, their name, their number of their various
	* item types, their message, their pokemon, and their number of pokemon.
	* @authors William Minshew, Jeffrey Siebach, and Michael Howard
	*****************************************************************************/
    public class Trainer
   {
	/*****************************************************************************
	* Used for creating a message that an enemy trainer will say before
	* commencing in battle.
	*****************************************************************************/
      public String message;
	/*****************************************************************************
	* Used to keep track of the number of potions a trainer has.
	*****************************************************************************/
      public int myPotions;
	/*****************************************************************************
	* Used to keep track of the number of pokeballs a trainer has.
	*****************************************************************************/
		public int myBalls;
	/*****************************************************************************
	* Used to keep track of the number of pokemon a trainer has.
	*****************************************************************************/
		public int numPokemon;
	/*****************************************************************************
	* Used to keep track of how much cash a trainer has.
	*****************************************************************************/
      public int myCash = 600;
	/*****************************************************************************
	* Used to keep track of the trainer's pokemon.
	*****************************************************************************/
      public Pokemon[] myPokemon;
	/*****************************************************************************
	* Used frequently in JOptionPane's showOptionDialog method.
	*****************************************************************************/
      public Object[] possibleValues;
	/*****************************************************************************
	* Used to keep track of the trainer's name.
	*****************************************************************************/
      public String myName;
	/*****************************************************************************
	* Used to communicate with the BattlePanel1.
	*****************************************************************************/
      public BattlePanel1 bp;
   /*****************************************************************************
	* Constructs a trainer that is in contact with the BattlePanel1 and prompts
	* the user for a name and begins selecting a starting pokemon.
	* @ param a	Assigns a to bp.
	*****************************************************************************/
       public Trainer(BattlePanel1 a)
      {
         message = "I will defeat you!";
         myPokemon = new Pokemon[7];
         numPokemon = 1;
         bp = a;
         myPotions = myBalls = 0;
         myName = JOptionPane.showInputDialog("What is your name?");
         if(myName == null)
            myName = "Ash";
         chooseStartingPokemon();
      }
	/*****************************************************************************
	* Instantiates a trainer that has 3 starting pokemon and is in contact with 
	* the BattlePanel1.
	* @param ab	Assigns ab to bp.
	* @param name	Assigns name to myName.
	* @param a	Assigns a to myPokemon[0].
	* @param b	Assigns b to myPokemon[1].
	* @param c	Assigns c to myPokemon[2].
	*****************************************************************************/
       public Trainer(BattlePanel1 ab, String name, Pokemon a, Pokemon b, Pokemon c)
      {
         if(name.equals("Minsuk"))
            message = "You will never beat me!\nI am the great Minsuk!";
         else
            message = "You will never beat me!\nI am the ultimate pokemon master!";
         myPokemon = new Pokemon[7];
         numPokemon = 3;
         bp = ab;
         myName = name;
         myPotions = myBalls = 0;
         myPokemon[0] = a;
         myPokemon[1] = b;
         myPokemon[2] = c;
      }
	/*****************************************************************************
	* Prompts the user which pokemon they would like to start off with.
	*****************************************************************************/
       public void chooseStartingPokemon()
      {
         Object[] possibleValues = {"Charmander (Level 1)", "Squirtle (Level 1)", "Bulbasaur (Level 1)"};
      
         int choice = JOptionPane.showOptionDialog(bp, 
                           "Which pokemon do you want to start off with?", "Starting Pokemon", 
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                           null, possibleValues, possibleValues[0]) + 1;
      
         switch(choice)
         {
            case 1:choosePokemon(new Charmander(1));
               break;
            case 2:choosePokemon(new Squirtle(1));
               break;
            case 3:choosePokemon(new Bulbasaur(1));
               break;
         }
         myPokemon[6] = new Pikachu(1);
      }
	/*****************************************************************************
	* Provides the user with a confirmation page, and a short blurb about
	* charmander (the pokemon they have chosen in this case.)
	* @param x	If they confirm their choice, x will be assigned to myPokemon[0].
	*****************************************************************************/
       public void choosePokemon(Charmander x)
      {
         int choice = JOptionPane.showConfirmDialog(bp, "Charmander is an offensive "
                           + "Pokemon, dealing out large amounts of damage but only able to "
                           + "take a few hits before going down.",
                           "You chose Charmander",
                           JOptionPane.YES_NO_OPTION,
                           JOptionPane.QUESTION_MESSAGE, x.getIcon());
         switch(choice)
         {
            case 0: myPokemon[0] = x;
               break;
            default:chooseStartingPokemon();
               break;
         }
      }
	/*****************************************************************************
	* Provides the user with a confirmation page, and a short blurb about
	* squirtle (the pokemon they have chosen in this case.)
	* @param x	If they confirm their choice, x will be assigned to myPokemon[0].
	*****************************************************************************/
       public void choosePokemon(Squirtle x)
      {
         int choice = JOptionPane.showConfirmDialog(bp, "Squirtle is a defensive "
                           + "Pokemon, able to take large amounts of damage without going down, "
                           + "but only able to deal minimal damage",
                           "You chose Squirtle",
                           JOptionPane.YES_NO_OPTION,
                           JOptionPane.QUESTION_MESSAGE, x.getIcon());        
         switch(choice)
         {
            case 0:myPokemon[0] = x;
               break;
            default:chooseStartingPokemon();
               break;
         }
      }
	/*****************************************************************************
	* Provides the user with a confirmation page, and a short blurb about
	* bulbasaur (the pokemon they have chosen in this case.)
	* @param x	If they confirm their choice, x will be assigned to myPokemon[0].
	*****************************************************************************/
       public void choosePokemon(Bulbasaur x)
      {
         int choice = JOptionPane.showConfirmDialog(bp, "Bulbasaur starts out weaker "
                           + "than both of the other Pokemon, however, grows over time to be "
                           + "the strongest out of the 3 starting Pokemon",
                           "You chose Bulbasaur",
                           JOptionPane.YES_NO_OPTION,
                           JOptionPane.QUESTION_MESSAGE, x.getIcon());                
         switch(choice)
         {
            case 0:myPokemon[0] = x;
               break;
            default:chooseStartingPokemon();
               break;
         }
      }
	/*****************************************************************************
	* Returns the number of pokemon a trainer possesses.
	* @return	The number of pokemon the has.
	*****************************************************************************/
       public int getNumPokemon()
      {
         return numPokemon;
      }
	/*****************************************************************************
	* Sets the a pokemon into a certain place in the trainer's set.
	* @param k	The spot in the array to place the pokemon.
	* @param p	The pokemon to place into the specified slot.
	*****************************************************************************/
       public void setPokemon(int k, Pokemon p)
      {
         myPokemon[k] = p;
      }
	/*****************************************************************************
	* Returns the pokemon in a specific slot.
	* @param k	Spot specified in the array for the pokemon to be returned from.
	* @return	Certain pokemon in slot k.
	*****************************************************************************/
       public Pokemon getPokemon(int k)
      {
         return myPokemon[k];
      }
	/*****************************************************************************
	* Returns the trainer's name.
	* @return trainer's name.
	*****************************************************************************/
       public String getName()
      {
         return myName;
      }
	/*****************************************************************************
	* Returns the number of potions a trainer possesses.
	* @return number of potions the trainer has.
	*****************************************************************************/
       public int getPotions()
      {
         return myPotions;
      }
	/*****************************************************************************
	* Sets the number of potions a trainer possesses.
	* @param x	Assigns x to myPotions.
	*****************************************************************************/
       public void setPotions(int x)
      {
         myPotions = x;
      }
	/*****************************************************************************
	* Returns the number of pokeballs a trainer possesses.
	* @return	Number of pokeballs the trainer has.
	*****************************************************************************/
       public int getBalls()
      {
         return myBalls;
      }
	/*****************************************************************************
	* Sets the number of pokeballs a trainer possesses.
	* @param x	Assigns x to myBalls.
	*****************************************************************************/
       public void setBalls(int x)
      {
         myBalls = x;
      }
	/*****************************************************************************
	* Heals all of the trainer's pokemon.
	*****************************************************************************/
       public void healAll()
      {
         int x = 0;
         while(myPokemon[x] != null)
         {
            myPokemon[x].heal();
            x++;
         }
      }
	/*****************************************************************************
	* Returns the amount of cash a trainer possesses.
	* @return	Amount of cash the trainer has.
	*****************************************************************************/
       public int getCash()
      {
         return myCash;
      }
	/*****************************************************************************
	* Sets the amount of cash a trainer possesses.
	* @param x	Assigns x to myCash.
	*****************************************************************************/
       public void setCash(int x)
      {
         myCash = x;
      }
	/*****************************************************************************
	* Places a pokemon under the trainer's possession.
	* @param x	Assigns x to myPokemon[numPokemon] so long as the array has space.
	*****************************************************************************/
       public void addPokemon(Pokemon x)
      {
         if(numPokemon == 6)
         {
            JOptionPane.showMessageDialog(bp, "You can't carry anymore Pokemon.");
         }
         else
         {
            myPokemon[numPokemon] = x;
            numPokemon++;
         }
      }
	/*****************************************************************************
	* Switches the trainer's active pokemon.
	*****************************************************************************/
       public void switchActivePokemon()
      {
         int[] u = new int[pokemonRemaining()];
         int y = 0;
         Object[] possibleValues = new Object[pokemonRemaining()+1];
         for(int x = 0; x < numPokemon; x++)
         {
            if(myPokemon[x].getCurrentHealth() > 0)
            {
               u[y]=x;
               possibleValues[y] = myPokemon[x];
               y++;
            }
         }   
         possibleValues[pokemonRemaining()] = "Back";
      
         int choice = JOptionPane.showOptionDialog(bp, "Which pokemon do you want to use?", "Switching",
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);
         if(choice!=pokemonRemaining())
         {
            myPokemon[6] = myPokemon[u[choice]];	
            myPokemon[u[choice]] = myPokemon[0];
            myPokemon[0] = myPokemon[6];
         }
      }
	/*****************************************************************************
	* Returns the number of pokemon (not knocked out) a trainer has remaining.
	* @return	Number of battle-ready pokemon.
	*****************************************************************************/
       public int pokemonRemaining()
      {
         int t = 0;
         for(int x = 0; x < numPokemon; x++)
            if(myPokemon[x].getCurrentHealth()!=0)
               t++;
         return t;
      }
	/*****************************************************************************
	* Returns the trainer's message.
	* @return trainer's message.
	*****************************************************************************/
       public String getMessage()
      {
         return message;
      }
	/*****************************************************************************
	* Returns the first battle-ready pokemon.
	* @return	First pokemon without 0 health.
	*****************************************************************************/
       public Pokemon getNextAlive()
      {
         int k = 0;
         while(myPokemon[k].getCurrentHealth() == 0)
            k++;
         return myPokemon[k];
      }
	/*****************************************************************************
	* Used when a user is defeated in battle and has run out of remaining pokemon.
	*****************************************************************************/
       public void faint()
      {
         bp.label.update("You have fainted and lost " + myCash/2 + " gold!");
         myCash = myCash - myCash/2;
         for(int k = 0; k < numPokemon; k++)
            myPokemon[k].heal();
         bp.score.update(this);
      }
	/*****************************************************************************
	* Switches a trainer's active pokemon during a fight.
	*****************************************************************************/
       public void switchActivePokemonFight()
      {
         int[] u = new int[pokemonRemaining()];
         int y = 0;
         Object[] possibleValues = new Object[pokemonRemaining()+1];
         for(int x = 0; x < numPokemon; x++)
         {
            if(myPokemon[x].getCurrentHealth() > 0)
            {
               u[y]=x;
               possibleValues[y] = myPokemon[x];
               y++;
            }
         }   
         possibleValues[pokemonRemaining()] = "Back";
      
         int choice = JOptionPane.showOptionDialog(bp, "Which pokemon do you want to use?", "Switching",
                           JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);
         if(choice!=pokemonRemaining())
         {
            myPokemon[6] = myPokemon[u[choice]];	
            myPokemon[u[choice]] = myPokemon[0];
            myPokemon[0] = myPokemon[6];
            bp.t.stop();
            bp.xpos = -100;
            bp.ypos = 480;
            bp.xpos1 = 300;
            bp.ypos1 = 0;
            bp.repaint();
            bp.commenceBattle2(this, myPokemon[0]);
         }
         else
         {
            bp.fight();
         }
      }
   }

