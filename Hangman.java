import java.util.Scanner;
import java.io.*;

public class Hangman
{
    public static void main(String [] args) throws IOException, InterruptedException
    {
    	Scanner in = new Scanner(System.in);

    	//Output to Screen
    	System.out.println("Hello! Welcome to Hangman. Player 1, please enter a word: ");
        
        // First get the word and to see if it is actually a word.
        String word = "";

       	word = in.next();
   		boolean ifword = Hangman.checker(word);
   		while(!ifword)
   		{
   			System.out.println("Hey... that's not a word... try again.");
   			word = in.next();
   			ifword = Hangman.checker(word);
   		}

        //While each guess comes in we need to compare it to a base at the end
        String output = "";

        for(int i=0; i<word.length(); i++)
        {
            output += "_";
        }

        //Doubling, we are editing word so we need an original. 
        String original = word;
        
        //How many guesses are allowed.
        System.out.println("How many guesses is Player 2 allowed to have?");
        
        int j;
        try{
        	j = Integer.parseInt(in.next());
        }
        catch(NumberFormatException ex)
        {
        	System.out.print("Thats not a number. Player 2 gets 10 guesses by default");
        	j = 10;
        }
        int howmanyguesses = j;
        //Conditions is needed so you wont loose a guess if you get it correct.
        boolean condition;

        //Clearing the screen so player two doesn't see the word that has been put in.
        System.out.print("\033[H\033[2J");  
    	System.out.flush();

        System.out.println("The word is " + output + ". Player 2, It's your turn now! You have " + j + " guesses.");

        //Starting of the loop.
        while(howmanyguesses != 0)
        {
        	//Set condition to false so it is assumed that the guess is wrong.
        	condition = false;
            System.out.println("Guess a letter:");
            //Get in the guess.
            char guess = in.next().charAt(0);

            //Checking if the letter guessed is in the word
            word = Hangman.letter_contains(word, guess);
            

            for(int k=0; k<word.length(); k++)
            {
                if(word.charAt(k) == output.charAt(k))
                {
                    output = Hangman.replacement(k, output, original);
                    condition = true;
                }
            }

            //If the guess is wrong, try again.
            if(!condition)
            {
            	System.out.println("Oops, try again");
            	howmanyguesses--;
            }
            //Don't reduce a guess if you get it wrong

            //Game Ending.
            if(output.equals(original))
            {
                System.out.println("Well done!! The word was " + original + ". Game over");
            	break;
            }


            System.out.println(output + " ~~~~~ You have " + (howmanyguesses)  + " guesses left!");
        }
        if(howmanyguesses == 0)
        {
        	System.out.println("Damn! Ran out of guesses. Game Over.");
        }
    }


    //The compared string needs to be changed.
    public static String replacement(int c, String newword, String original)
    {
        return newword.substring(0,c) + Character.toString(original.charAt(c)) + newword.substring(c+1);
    }


    //To see if the letter guessed is in the word.
    public static String letter_contains(String word, char guess)
    {
    	for(int i=0; i<word.length(); i++)
        {
	    	char firstlet = word.charAt(i);
	        if(firstlet == guess)
	        {
	          	System.out.println("You got a letter!");
	            return word.replace(firstlet, '_');
	        }
	    }
	    return word;
    }



    //Checker to see if all the letters in the words are actually letters, if not look for another word.
    public static boolean checker(String ifword)
    {
    	if(ifword == null)
    	{
    		return false;
    	}
    	for(int i = 0; i < ifword.length();i++)
    	{
    		if(Character.isLetter(ifword.charAt(i))==false)
    		{
    			return false;
    		}
    	}
    	return true;
    }

}

