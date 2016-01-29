   package pokemons;
   

   import java.awt.image.*;
   import javax.swing.*; 

   public class Rose extends Object implements Pokemon
   {
      public int myLevel, mySpeed, myStrength, mySpecial, myDefense, myTotalHealth, myHealth, myExp;
      public String myName = "Rose";
      public String firstAttack = "Tell boring story";
      public String secondAttack = "Be really unclear about quiz directions";
      public int myNumber = 15;
   
   /***********************************************************************************
   * Creates a Rose with all attributes based on the Rose's level specified by 
   * x.
   * @param x       initial level
   ***********************************************************************************/
      public Rose(int x)
      {
         myLevel = x;
         myStrength = (int)(15 + myLevel * 2.5);
         mySpeed = (int)(25 + myLevel * 2);
         myDefense = (int)(20 + myLevel * 1.5);
         mySpecial = (int)(35 + myLevel * 3);
         myTotalHealth = (int)(60 + myLevel * 4);
         myHealth = myTotalHealth;
      }
   /***********************************************************************************
   * Increases all of the attributes by increasing the level by one
   ***********************************************************************************/
      public void levelUp()
      {
         myLevel++;
         myStrength = (int)(15 + myLevel * 2.5);
         mySpeed = (int)(25 + myLevel * 2);
         myDefense = (int)(20 + myLevel * 1.5);
         mySpecial = (int)(35 + myLevel * 3);
         myTotalHealth = (int)(60 + myLevel * 4);
         myHealth = myTotalHealth;
      }
   /***********************************************************************************
   * Returns the Rose's current health
   * @return current health
   ***********************************************************************************/
      public int getCurrentHealth()
      {
         return myHealth;
      }
   /***********************************************************************************
   * Returns the Pokemon the Rose will become after evolution
   * @param y     sends this number as an argument of the returned pokemon
   * @return 		next evolutionary stage
   ***********************************************************************************/
      public Pokemon getEvolve(int y)
      {
         return this;
      }
   /***********************************************************************************
   * Returns whether or not the Rose has evolved once
   * @return whether or not the Rose has evolved once
   ***********************************************************************************/
      public boolean getEvolvedOnce()
      {
         return true;
      }
   /***********************************************************************************
   * Returns whether or not the Rose has evolved twice
   * @return whether or not the Rose has evolved twice
   ***********************************************************************************/
      public boolean getEvolvedTwice()
      {
         return true;
      }
   /***********************************************************************************
   * Sets the experience points to the input number.  Also levels up the Rose if it 
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
   * Returns the amount of experience points the Rose gives out
   * @return experience given
   ***********************************************************************************/
      public int giveExp()
      {
         return myLevel * 24;
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
   * Returns the amount of damage the Rose's first attack does
   * @return damage of first attack
   ***********************************************************************************/
      public int attack1()
      {
         return (int)(Math.random() * 20 + myStrength * 1.6);  //boring story
      }
   /***********************************************************************************
   * Returns the amount of damage the Rose's second attack does
   * @return damage of second attack
   ***********************************************************************************/
      public int attack2()
      {
         return (int)(Math.random() * 15 + 1.9 * mySpecial); //unclear directions
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
   * Animates the Rose's first attack
   ***********************************************************************************/
      public void animateAttack1()
      {
      }
   /***********************************************************************************
   * Animates the Rose's second attack
   ***********************************************************************************/
      public void animateAttack2()
      {
      }
   /***********************************************************************************
   * Returns the amount of damage the Rose will take based on the input number
   * @param x   decreases myHealth based on x     
   * @return damage taken
   ***********************************************************************************/
      public int takeDamage(int x)
      {
         int k = 1;
         x = (int)(x/1.1);
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
         ImageIcon x = new ImageIcon("pokemons/images/SteveRose.jpg");
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
