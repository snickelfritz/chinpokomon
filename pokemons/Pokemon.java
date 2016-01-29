package pokemons;
   import javax.swing.*;

	/***********************************************************************************
	* A Pokemon maintains information about its level, speed, strength, special,
	* defense, total health, current health, experience points, name, 
	* attack names, and it's pokemon number.  
	* A Pokemon knows how to return its level, speed, total health, current health, 
	* experience points taken, experience points given, name, attack names, attack strength,
	* pokemon number, image, and damage taken.
	* A Pokemon can set its experience points and its current health.  A Pokemon can
	* also level up and heal itself. 
	* @author Siebach, Howard, Minshew
	**********************************************************************************/
   public interface Pokemon
   {
   /***********************************************************************************
   * Increases all of the attributes by increasing the level by one
   ***********************************************************************************/
      public abstract void levelUp();
   /***********************************************************************************
   * Returns the pokemon number
   * @return pokemon number
   ***********************************************************************************/
      public abstract int getNumber();
   /***********************************************************************************
   * Sets the experience points to the input number.  Also levels up the Pokemon if it 
   * gains enough experience.
   * @param x    assigns x to myExp
   ***********************************************************************************/
      public abstract void setExp(int x);
   /***********************************************************************************
   * Returns the amount of experience points the Pokemon gives out
   * @return experience given
   ***********************************************************************************/
      public abstract int giveExp();
   /***********************************************************************************
   * Returns the experience points
   * @return experience 
   ***********************************************************************************/
      public abstract int getExp();
   /***********************************************************************************
   * Returns the level
   * @return level
   ***********************************************************************************/
      public abstract int getLevel();
   /***********************************************************************************
   * Returns the amount of damage the Pokemon's first attack does
   * @return damage of first attack
   ***********************************************************************************/
      public abstract int attack1();
   /***********************************************************************************
   * Returns the amount of damage the Pokemon's second attack does
   * @return damage of second attack
   ***********************************************************************************/
      public abstract int attack2();
   /***********************************************************************************
   * Animates the Pokemon's first attack
   ***********************************************************************************/
      public abstract void animateAttack1();
   /***********************************************************************************
   * Animates the Pokemon's second attack
   ***********************************************************************************/
      public abstract void animateAttack2();
   /***********************************************************************************
   * Returns the amount of damage the Pokemon will take based on the input number
   * @param x   decreases myHealth based on x     
   * @return damage taken
   ***********************************************************************************/
      public abstract int takeDamage(int x);
   /***********************************************************************************
   * Returns the speed
   * @return speed
   ***********************************************************************************/
      public abstract int getSpeed();
   /***********************************************************************************
   * Returns the image
   * @return image
   ***********************************************************************************/
      public abstract ImageIcon getIcon();
   /***********************************************************************************
   * Returns the name
   * @return name
   ***********************************************************************************/
      public abstract String getName();
   /***********************************************************************************
   * Returns the health percentage
   * @return health percentage
   ***********************************************************************************/
      public abstract int getHealthPercent();
   /***********************************************************************************
   * Returns the total health
   * @return total health
   ***********************************************************************************/
      public abstract int getTotalHealth();
   /***********************************************************************************
   * Sets the current health equal to the total health
   ***********************************************************************************/
      public abstract void heal();
   /***********************************************************************************
   * Returns the name of the first attack
   * @return name of first attack
   ***********************************************************************************/
      public abstract String getFirstAttack();
   /***********************************************************************************
   * Returns the name of the second attack
   * @return name of second attack
   ***********************************************************************************/
      public abstract String getSecondAttack();
   /***********************************************************************************
   * Returns the name follwed by the level
   * @return name and level
   ***********************************************************************************/
      public abstract String toString();
   /***********************************************************************************
   * Returns the Pokemon's current health
   * @return current health
   ***********************************************************************************/
      public abstract int getCurrentHealth();
   /***********************************************************************************
   * Sets the health to the input number
   * @param k    assigns k to myHealth
   ***********************************************************************************/
      public abstract void setHealth(int k);
   /***********************************************************************************
   * Returns the new Pokemon the current Pokemon will become after evolution
   * @param y     sends this number as an argument of the returned pokemon
   * @return 		next evolutionary stage
   ***********************************************************************************/
      public abstract Pokemon getEvolve(int y);
   /***********************************************************************************
   * Returns whether or not the Pokemon has evolved once
   * @return whether or not the Pokemon has evolved once
   ***********************************************************************************/
      public abstract boolean getEvolvedOnce();
   /***********************************************************************************
   * Returns whether or not the Pokemon has evolved twice
   * @return whether or not the Pokemon has evolved twice
   ***********************************************************************************/
      public abstract boolean getEvolvedTwice();
   }
