package games.civ;

import java.util.*;

class Die
{
 private Random rng;

 public Die(){}//bean default 
 
 public Die(int seed)
	{
	  rng = new Random(seed);
	} //end of Die method
 
 public int rollSix()
	{
	  return (Math.abs(rng.nextInt()) % 6) + 1;
	} //end of roll method
 
 public int rollThree()
	{
	  return (Math.abs(rng.nextInt()) % 3);
	} //end of roll method

 public int rollFour()
	{
	  return (Math.abs(rng.nextInt()) % 4);
	} //end of roll method

 public int rollFourteen()
	{
	  return (Math.abs(rng.nextInt()) % 14 + 1);
	} //end of roll method

 public int rollFifteen()
	{
	  return (Math.abs(rng.nextInt()) % 15);
	} //end of roll method

 public int rollEight()
	{
	  return (Math.abs(rng.nextInt()) % 8);
	} //end of roll method

 public int rollX(int x)
	{
	  return (Math.abs(rng.nextInt()) % x);
	} //end of roll method

 public void setRng(Random rng){
    this.rng = rng;
 }
 
 public Random getRng(){
    return rng;
 }
}//end of Die class