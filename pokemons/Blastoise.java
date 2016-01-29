package pokemons;

   import java.awt.image.*;
   import javax.swing.*; 


	/***********************************************************************************
	* A Blastoise is a Pokemon that maintains information about its level, speed, 
	* strength, special, defense, total health, current health, experience points, name, 
	* attack names, and it's pokemon number.  
	* A Blastoise knows how to return its level, speed, total health, current health, 
	* experience points taken, experience points given, name, attack names, attack strength,
	* pokemon number, image, and damage taken.
	* A Blastoise can set its experience points and its current health.  A Blastoise can
	* also level up and heal itself. 
	* @author Siebach, Howard, Minshew
	***********************************************************************************/
   public class Blastoise extends Object implements Pokemon
   {
      public int myLevel, mySpeed, myStrength, mySpecial, myDefense, myTotalHealth, myHealth, myExp;
      public String myName = "Blastoise";
      public String firstAttack = "Skull Bash";
      public String secondAttack = "Hydro Pump";
      public int myNumber = 6;
   /***********************************************************************************
   * Creates a Blastoise with all attributes based on the Blastoise's level specified by 
   * x.
   * @param x       initial level
   ***********************************************************************************/
      public Blastoise(int x)
      {
         myLevel = x;
         myStrength = (int)(15 + myLevel * 2.2);
         mySpeed = (int)(20 + myLevel * 1.5);
         myDefense = (int)(20 + myLevel * 2.5);
         mySpecial = (int)(20 + myLevel * 2.25);
         myTotalHealth = (int)(90 + myLevel * 6);
         myHealth = myTotalHealth;
      }
   /***********************************************************************************
   * Increases all of the attributes by increasing the level by one
   ***********************************************************************************/
      public void levelUp()
      {
         myLevel++;
         myStrength = (int)(15 + myLevel * 2.2);
         mySpeed = (int)(20 + myLevel * 1.5);
         myDefense = (int)(20 + myLevel * 2.5);
         mySpecial = (int)(20 + myLevel * 2.25);
         myTotalHealth = (int)(90 + myLevel * 6);
         myHealth = myTotalHealth;
      }
   /***********************************************************************************
   * Returns the Blastoise's current health
   * @return current health
   ***********************************************************************************/
      public int getCurrentHealth()
      {
         return myHealth;
      }
   /***********************************************************************************
   * Returns the Pokemon the Blastoise will become after evolution
   * @param y     sends this number as an argument of the returned pokemon
   * @return 		next evolutionary stage
   ***********************************************************************************/
      public Pokemon getEvolve(int y)
      {
         return this;
      }
   /***********************************************************************************
   * Returns whether or not the Blastoise has evolved twice
   * @return whether or not the Blastoise has evolved twice
   ***********************************************************************************/
      public boolean getEvolvedOnce()
      {
         return true;
      }
   /***********************************************************************************
   * Returns whether or not the Blastoise has evolved twice
   * @return whether or not the Blastoise has evolved twice
   ***********************************************************************************/
      public boolean getEvolvedTwice()
      {
         return true;
      }
   /***********************************************************************************
   * Sets the experience points to the input number.  Also levels up the Blastoise if it 
   * gains enough experience.
   * @param x    assigns x to myExp
   ***********************************************************************************/
   
      public void setExp(int x)
      
      {
         myExp = x;
         while(myExp > myLevel*myLevel*myLevel)
         {
            levelUp();
         }
      }
   /***********************************************************************************
   * Returns the experience points
   * @return experience 
   ***********************************************************************************/
   
      public int getExp()
      
      {
         return myExp;
      }
   /***********************************************************************************
   * Returns the amount of experience points the Blastoise gives out
   * @return experience given
   ***********************************************************************************/
      public int giveExp()
      {
         return myLevel * 26;
      }
   /***********************************************************************************
   * Returns the level
   * @return level
   ***********************************************************************************/
   
      public int getLevel()
      {
         return myLevel;
      }
   /***********************************************************************************
   * Returns the amount of damage the Blastoise's first attack does
   * @return damage of first attack
   ***********************************************************************************/
      public int attack1()
      {
         return (int)(Math.random() * 15 + myStrength * 1.9);  //Scratch
      }
   /***********************************************************************************
   * Returns the amount of damage the Blastoise's second attack does
   * @return damage of second attack
   ***********************************************************************************/
      public int attack2()
      {
         return (int)(Math.random() * 10 + 2.2 * mySpecial); //Ember
      }
   /***********************************************************************************
   * Returns the name of the first attack
   * @return name of first attack
   ***********************************************************************************/
   
      public String getFirstAttack()
      
      {
         return firstAttack;
      }
   /***********************************************************************************
   * Returns the name of the second attack
   * @return name of second attack
   ***********************************************************************************/
   
      public String getSecondAttack()
      
      {
         return secondAttack;
      }
   /***********************************************************************************
   * Animates the Blastoise's first attack
   ***********************************************************************************/
   
      public void animateAttack1()
      
      {
      }
   /***********************************************************************************
   * Animates the Blastoise's second attack
   ***********************************************************************************/
   
      public void animateAttack2()
      
      {
      }
   /***********************************************************************************
   * Returns the amount of damage the Blastoise will take based on the input number
   * @param x   decreases myHealth based on x     
   * @return damage taken
   ***********************************************************************************/
      public int takeDamage(int x)
      {
         int k = 1;
         x = (int)(x/2.2);
         if(x > myDefense)
         {
            k = x - myDefense;
            myHealth -= k;
         }
         else
         {
            myHealth -= 1;
         }
         return k;
      }
   /***********************************************************************************
   * Sets the health to the input number
   * @param k    assigns k to myHealth
   ***********************************************************************************/
   
      public void setHealth(int k)
      
      {
         myHealth = k;
      }
   /***********************************************************************************
   * Returns the speed
   * @return speed
   ***********************************************************************************/
   
      public int getSpeed()
      
      {
         return mySpeed;
      }
   /***********************************************************************************
   * Returns the image
   * @return image
   ***********************************************************************************/
      public ImageIcon getIcon()
      {
         ImageIcon x = new ImageIcon("pokemons/images/Blastoise.gif");
         return x;
      }
   /***********************************************************************************
   * Returns the name
   * @return name
   ***********************************************************************************/
   
      public String getName()
      
      {
         return myName;
      }
   /***********************************************************************************
   * Returns the health percentage
   * @return health percentage
   ***********************************************************************************/
   
      public int getHealthPercent()
      
      {
         double a = (double)myHealth;
         double b = (double)myTotalHealth;
         double c = a/b;
         return (int)(c*100);
      }
   /***********************************************************************************
   * Returns the total health
   * @return total health
   ***********************************************************************************/
   
      public int getTotalHealth()
      
      {
         return myTotalHealth;
      }
   /***********************************************************************************
   * Sets the current health equal to the total health
   ***********************************************************************************/
   
      public void heal()
      
      {
         myHealth = myTotalHealth;
      }
   /***********************************************************************************
   * Returns the name follwed by the level
   * @return name and level
   ***********************************************************************************/
   
      public String toString()
      
      {
         return myName + " (Level" + myLevel + ")";
      }
   /***********************************************************************************
   * Returns the pokemon number
   * @return pokemon number
   ***********************************************************************************/
   
      public int getNumber()
      
      {
         return myNumber;
      }
   }