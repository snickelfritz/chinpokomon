package pokemons;
   import java.awt.image.*;
   import javax.swing.*; 
   

	/***********************************************************************************
	* An Ivysaur is a Pokemon that maintains information about its level, speed, 
	* strength, special, defense, total health, current health, experience points, name, 
	* attack names, and it's pokemon number.  
	* An Ivysaur knows how to return its level, speed, total health, current health, 
	* experience points taken, experience points given, name, attack names, attack strength,
	* pokemon number, image, and damage taken.
	* An Ivysaur can set its experience points and its current health.  An Ivysaur can
	* also level up and heal itself. 
	* @author Siebach, Howard, Minshew
	***********************************************************************************/
   public class Ivysaur extends Object implements Pokemon
   {
      public int myLevel, mySpeed, myStrength, mySpecial, myDefense, myTotalHealth, myHealth, myExp;
      public String myName = "Ivysaur";
      public String firstAttack = "Leaf Dance";
      public String secondAttack = "Vine Whip";
      public int myNumber = 8;
   /***********************************************************************************
   * Creates an Ivysaur with all attributes based on the Ivysaur's level specified by 
   * x.
   * @param x       initial level
   ***********************************************************************************/
      public Ivysaur(int x)
      {
         myLevel = x;
         myStrength = (int)(15 + myLevel * 2);
         mySpeed = (int)(15 + myLevel * 2.5);
         myDefense = (int)(10 + myLevel * 2.5);
         mySpecial = (int)(20 + myLevel * 2.5);
         myTotalHealth = (int)(50 + myLevel * 5);
         myHealth = myTotalHealth;
      }
   /***********************************************************************************
   * Increases all of the attributes by increasing the level by one
   ***********************************************************************************/
      public void levelUp()
      {
         myLevel++;
         myStrength = (int)(15 + myLevel * 2);
         mySpeed = (int)(15 + myLevel * 2.5);
         myDefense = (int)(10 + myLevel * 2.5);
         mySpecial = (int)(20 + myLevel * 2.5);
         myTotalHealth = (int)(50 + myLevel * 5);
         myHealth = myTotalHealth;
      }
   /***********************************************************************************
   * Returns the Ivysaur's current health
   * @return current health
   ***********************************************************************************/
      public int getCurrentHealth()
      {
         return myHealth;
      }
   /***********************************************************************************
   * Returns the Pokemon the Ivysaur will become after evolution
   * @param y     sends this number as an argument of the returned pokemon
   * @return 		next evolutionary stage
   ***********************************************************************************/
      public Pokemon getEvolve(int y)
      {
         return new Venusaur(y);
      }
   /***********************************************************************************
   * Returns whether or not the Ivysaur has evolved once
   * @return whether or not the Ivysaur has evolved once
   ***********************************************************************************/
      public boolean getEvolvedOnce()
      {
         return true;
      }
   /***********************************************************************************
   * Returns whether or not the Ivysaur has evolved twice
   * @return whether or not the Ivysaur has evolved twice
   ***********************************************************************************/
      public boolean getEvolvedTwice()
      {
         return false;
      }
   /***********************************************************************************
   * Sets the experience points to the input number.  Also levels up the Ivysaur if it 
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
   * Returns the amount of experience points the Ivysaur gives out
   * @return experience given
   ***********************************************************************************/
      public int giveExp()
      {
         return myLevel * 19;
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
   * Returns the amount of damage the Ivysaur's first attack does
   * @return damage of first attack
   ***********************************************************************************/
      public int attack1()
      {
         return (int)(Math.random() * 15 + myStrength * 1.7);  //Scratch
      }
   /***********************************************************************************
   * Returns the amount of damage the Ivysaur's second attack does
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
   * Animates the Ivysaur's first attack
   ***********************************************************************************/
   
      public void animateAttack1()
      
      {
      }
   /***********************************************************************************
   * Animates the Ivysaur's second attack
   ***********************************************************************************/
   
      public void animateAttack2()
      
      {
      }
   /***********************************************************************************
   * Returns the amount of damage the Ivysaur will take based on the input number
   * @param x   decreases myHealth based on x     
   * @return damage taken
   ***********************************************************************************/
      public int takeDamage(int x)
      {
         int k = 1;
         x = (int)(x/1.5);
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
         ImageIcon x = new ImageIcon("pokemons/images/Ivysaur.jpg");
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