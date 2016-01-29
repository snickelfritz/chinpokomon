package pokemons;
   import java.awt.image.*;
   import javax.swing.*;

	/***********************************************************************************
	* A Squirtle is a Pokemon that maintains information about its level, speed, 
	* strength, special, defense, total health, current health, experience points, name, 
	* attack names, and it's pokemon number.  
	* A Squirtle knows how to return its level, speed, total health, current health, 
	* experience points taken, experience points given, name, attack names, attack strength,
	* pokemon number, image, and damage taken.
	* A Squirtle can set its experience points and its current health.  A Squirtle can
	* also level up and heal itself. 
	* @author Siebach, Howard, Minshew
	***********************************************************************************/
   public class Squirtle extends Object implements Pokemon
   {
      public int myLevel, mySpeed, myStrength, mySpecial, myDefense, myTotalHealth, myHealth, myExp;
      public String myName = "Squirtle";
      public String firstAttack = "Tackle";
      public String secondAttack = "Bubble";
      public int myNumber = 4;
   /***********************************************************************************
   * Creates a Squirtle with all attributes based on the Squirtle's level specified by 
   * x.
   * @param x       initial level
   ***********************************************************************************/
      public Squirtle(int x)
      {
         myLevel = x;
         myStrength = (int)(5 + myLevel * 1);
         mySpeed = (int)(7 + myLevel * 1.5);
         myDefense = (int)(15 + myLevel * 2);
         mySpecial = (int)(7 + myLevel * 1.5);
         myTotalHealth = (int)(70 + myLevel * 5);
         myHealth = myTotalHealth;
      }
   /***********************************************************************************
   * Increases all of the attributes by increasing the level by one
   ***********************************************************************************/
   
      public void levelUp()
      
      {
         myLevel++;
         myStrength = (int)(5 + myLevel * 1);
         mySpeed = (int)(7 + myLevel * 1.5);
         myDefense = (int)(15 + myLevel * 2);
         mySpecial = (int)(7 + myLevel * 1.5);
         myTotalHealth = (int)(70 + myLevel * 5);
         myHealth = myTotalHealth;
      
      }
   /***********************************************************************************
   * Returns the Squirtle's current health
   * @return current health
   ***********************************************************************************/
      public int getCurrentHealth()
      {
         return myHealth;
      }
   /***********************************************************************************
   * Returns the Pokemon the Squirtle will become after evolution
   * @param y     sends this number as an argument of the returned pokemon
   * @return 		next evolutionary stage
   ***********************************************************************************/
      public Pokemon getEvolve(int y)
      {
         return new Wartortle(y);
      }
   /***********************************************************************************
   * Returns whether or not the Squirtle has evolved once 
   * @return whether or not the Squirtle has evolved once
   ***********************************************************************************/
      public boolean getEvolvedOnce()
      {
         return false;
      }
   /***********************************************************************************
   * Returns whether or not the Squirtle has evolved twice
   * @return whether or not the Squirtle has evolved twice
   ***********************************************************************************/
      public boolean getEvolvedTwice()
      {
         return false;
      }
   /***********************************************************************************
   * Sets the experience points to the input number.  Also levels up the Squirtle if it 
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
   * Returns the amount of experience points the Squirtle gives out
   * @return experience given
   ***********************************************************************************/
      public int giveExp()
      {
         return myLevel * 14;
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
   * Returns the amount of damage the Squirtle's first attack does
   * @return damage of first attack
   ***********************************************************************************/
      public int attack1()
      {
         return (int)(Math.random() * 15 + 1.8 * myStrength); //Tackle
      }
   /***********************************************************************************
   * Returns the amount of damage the Squirtle's second attack does
   * @return damage of second attack
   ***********************************************************************************/
      public int attack2()
      {
         return (int)(Math.random() * 10 + 2 * mySpecial); //Water gun
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
   * Animates the Squirtle's first attack
   ***********************************************************************************/
   
      public void animateAttack1()
      
      {
      }
   /***********************************************************************************
   * Animates the Squirtle's second attack
   ***********************************************************************************/
   
      public void animateAttack2()
      
      {
      }
   /***********************************************************************************
   * Returns the amount of damage the Squirtle will take based on the input number
   * @param x   decreases myHealth based on x     
   * @return damage taken
   ***********************************************************************************/
      public int takeDamage(int x)
      {
         int k = 1;
         x = (int)(x/1.6);
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
         ImageIcon x = new ImageIcon("pokemons/images/Squirtle.jpg");
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