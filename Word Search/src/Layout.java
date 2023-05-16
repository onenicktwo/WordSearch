import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;

public class Layout 
{
	private String randomWord = "x";
	private List<String> words = new ArrayList<String>();
	Random rand = new Random();
	
	private String[][] layout;
	private Hyperlink[][] textLayout;
	private ArrayList<String> wordBank = new ArrayList<String>();
	private Text[] wordBankText;
	private String word;
	private String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private int row;
	private int col;
	private int tempR;
	private int tempC;
	private int rc;
	private int count;
	private boolean placed = false;
	private boolean stop = false;
	
	private int dif;

	public String getRandomItem() 
	{	    
		randomWord = words.get(rand.nextInt(words.size()));
	    return randomWord;
	}
	
	public Layout(int difficulty)
	{		
		dif = difficulty;
		
		try
	    {
	        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Owner\\OneDrive\\Documents\\Wordbank.txt"));
	        String line = reader.readLine();
	        while(line != null) 
	        {
	            String[] wordsLine = line.split(" ");
	            for(String word : wordsLine) 
	            {
	                words.add(word);
	            }
	            line = reader.readLine();
	        }
	    } 
	    catch (IOException e) 
	    {
	    	e.printStackTrace();
	    }
		
		if(dif == 1)
		{
			layout = new String[12][12];
			textLayout = new Hyperlink[12][12];
			wordBankText = new Text[9];
			
			while(wordBank.size() < 9)
			{
				placed = false;
				stop = false;
				
				word = getRandomItem();
				
				if(word.length() < 9)
				{
					row = rand.nextInt(layout.length);
					col = rand.nextInt(layout.length);
					rc = rand.nextInt(8);
					
					tempR = row;
					tempC = col;
					
					while(!placed && !stop)
					{
						getWord();
						CheckRandSpot();
						
						//Ends the checking if it has gone through one cycle of the 2D array
						if(tempR == row && tempC == col)
						{
							stop = true;
						}
						else
						{
							System.out.println(tempR + " " + tempC);
						}
					}
				}
			}
			fillLayout();
			getLayout();
			getWordBank();
		}
		else if(dif == 2)
		{
			layout = new String[16][16];
			textLayout = new Hyperlink[16][16];
			wordBankText = new Text[12];
			
			while(wordBank.size() < 12)
			{
				placed = false;
				stop = false;
				
				word = getRandomItem();
				
				if(word.length() < 11)
				{
					row = rand.nextInt(layout.length);
					col = rand.nextInt(layout.length);
					rc = rand.nextInt(8);
					
					tempR = row;
					tempC = col;
					
					while(!placed && !stop)
					{
						getWord();
						CheckRandSpot();
						
						//Ends the checking if it has gone through one cycle of the 2D array
						if(tempR == row && tempC == col)
						{
							stop = true;
						}
						else
						{
							System.out.println(tempR + " " + tempC);
						}
					}
				}
			}
			fillLayout();
			getLayout();
			getWordBank();
		}
		else if(dif == 3)
		{
			layout = new String[20][20];
			textLayout = new Hyperlink[20][20];
			wordBankText = new Text[15];
			
			while(wordBank.size() < 15)
			{
				placed = false;
				stop = false;
				
				word = getRandomItem();
				
				if(word.length() < 13)
				{
					row = rand.nextInt(layout.length);
					col = rand.nextInt(layout.length);
					rc = rand.nextInt(8);
					
					tempR = row;
					tempC = col;
					
					while(!placed && !stop)
					{
						getWord();
						CheckRandSpot();
						
						//Ends the checking if it has gone through one cycle of the 2D array
						if(tempR == row && tempC == col)
						{
							stop = true;
						}
						else
						{
							System.out.println(tempR + " " + tempC);
						}
					}
				}
			}
			fillLayout();
			getLayout();
			getWordBank();
		}
	}
	
	public void CheckRandSpot()
	{		
		int c = 1;
		
		while(c < 8 && !placed)
		{
			if(rc == 0)
			{
				TL(tempR, tempC);
			}
			if(rc == 1)
			{
				T(tempR, tempC);
			}
			if(rc == 2)
			{
				TR(tempR, tempC);
			}
			if(rc == 3)
			{
				L(tempR, tempC);
			}
			if(rc == 4)
			{
				R(tempR, tempC);
			}
			if(rc == 5)
			{
				DL(tempR, tempC);
			}
			if(rc == 6)
			{
				D(tempR, tempC);
			}
			if(rc == 7)
			{
				DR(tempR, tempC);
			}
			
			rc++;
			c++;
			if(rc > 7)
			{
				rc = 0;
			}
		}
		
		if(tempC > layout[0].length - 2)
		{
			if(tempR != layout.length - 1)
			{
				tempC = 0;
				tempR++;
			}
			else
			{
				tempR = 0;
				tempC = 0;
			}
		}
		else
		{
			tempC++;
		}
	}
	public void TL(int r, int c)
	{
		count = 0;
		
		while(count < word.length() &&  !stop)
		{
			if(r + count > layout.length - 1 || r - count < 0 || c + count > layout.length - 1 || c - count < 0)
			{
				stop = true;
			}
			else if(layout[r - count][c - count] != null)
			{
				if(!layout[r - count][c- count].equals(word.substring(0 + count, 1 + count)))
				{
					stop = true;
				}
			}
			count++;
		}
		
		if(!stop)
		{
			placed = true;
			count = 0;
			
			while(count < word.length() &&  !stop)
			{
				if(layout[r - count][c - count] == null)
				{
					layout[r - count][c - count] = word.substring(0 + count, 1 + count);
				}
				else if(layout[r - count][c - count].equals(word.substring(0 + count, 1 + count)))
				{
					layout[r - count][c - count] = word.substring(0 + count, 1 + count);
				}
				count++;
			}
			wordBank.add(word);
		}
	}
	public void T(int r, int c)
	{
		count = 0;
		
		while(count < word.length() &&  !stop)
		{
			if(r + count > layout.length - 1 || r - count < 0 || c + count > layout.length - 1 || c - count < 0)
			{
				stop = true;
			}
			else if(layout[r - count][c] != null)
			{
				if(!layout[r - count][c].equals(word.substring(0 + count, 1 + count)))
				{
					stop = true;
				}
			}
			count++;
		}
		
		if(!stop)
		{
			placed = true;
			count = 0;
			
			while(count < word.length() &&  !stop)
			{
				if(layout[r - count][c] == null)
				{
					layout[r - count][c] = word.substring(0 + count, 1 + count);
				}
				else if(layout[r - count][c].equals(word.substring(0 + count, 1 + count)))
				{
					layout[r - count][c] = word.substring(0 + count, 1 + count);
				}
				count++;
			}
			wordBank.add(word);
		}
	}
	public void TR(int r, int c)
	{
		count = 0;
		
		while(count < word.length() &&  !stop)
		{
			if(r + count > layout.length - 1 || r - count < 0 || c + count > layout.length - 1 || c - count < 0)
			{
				stop = true;
			}
			else if(layout[r - count][c + count] != null)
			{
				if(!layout[r - count][c + count].equals(word.substring(0 + count, 1 + count)))
				{
					stop = true;
				}
			}
			count++;
		}
		
		if(!stop)
		{
			placed = true;
			count = 0;
			
			while(count < word.length() &&  !stop)
			{
				if(layout[r - count][c + count] == null)
				{
					layout[r - count][c + count] = word.substring(0 + count, 1 + count);
				}
				else if(layout[r - count][c + count].equals(word.substring(0 + count, 1 + count)))
				{
					layout[r - count][c + count] = word.substring(0 + count, 1 + count);
				}
				count++;
			}
			wordBank.add(word);
		}
	}
	public void L(int r, int c)
	{
		count = 0;
		
		while(count < word.length() &&  !stop)
		{
			if(r + count > layout.length - 1 || r - count < 0 || c + count > layout.length - 1 || c - count < 0)
			{
				stop = true;
			}
			else if(layout[r][c - count] != null)
			{
				if(!layout[r][c - count].equals(word.substring(0 + count, 1 + count)))
				{
					stop = true;
				}
			}
			count++;
		}
		
		if(!stop)
		{
			placed = true;
			count = 0;
			
			while(count < word.length() &&  !stop)
			{
				if(layout[r][c - count] == null)
				{
					layout[r][c - count] = word.substring(0 + count, 1 + count);
				}
				else if(layout[r][c - count].equals(word.substring(0 + count, 1 + count)))
				{
					layout[r][c - count] = word.substring(0 + count, 1 + count);
				}
				count++;
			}
			wordBank.add(word);
		}
	}
	public void R(int r, int c)
	{
		count = 0;
		
		while(count < word.length() &&  !stop)
		{
			if(r + count > layout.length - 1 || r - count < 0 || c + count > layout.length - 1 || c - count < 0)
			{
				stop = true;
			}
			else if(layout[r][c + count] != null)
			{
				if(!layout[r][c + count].equals(word.substring(0 + count, 1 + count)))
				{
					stop = true;
				}
			}
			count++;
		}
		
		if(!stop)
		{
			placed = true;
			count = 0;
			
			while(count < word.length() &&  !stop)
			{
				if(layout[r][c + count] == null)
				{
					layout[r][c + count] = word.substring(0 + count, 1 + count);
				}
				else if(layout[r][c + count].equals(word.substring(0 + count, 1 + count)))
				{
					layout[r][c + count] = word.substring(0 + count, 1 + count);
				}
				count++;
			}
			wordBank.add(word);
		}
	}
	public void DL(int r, int c)
	{
		count = 0;
		
		while(count < word.length() &&  !stop)
		{
			if(r + count > layout.length - 1 || r - count < 0 || c + count > layout.length - 1 || c - count < 0)
			{
				stop = true;
			}
			else if(layout[r + count][c - count] != null)
			{
				if(!layout[r + count][c - count].equals(word.substring(0 + count, 1 + count)))
				{
					stop = true;
				}
			}
			count++;
		}
		
		if(!stop)
		{
			placed = true;
			count = 0;
			
			while(count < word.length() &&  !stop)
			{
				if(layout[r + count][c - count]== null)
				{
					layout[r + count][c - count] = word.substring(0 + count, 1 + count);
				}
				else if(layout[r + count][c - count].equals(word.substring(0 + count, 1 + count)))
				{
					layout[r + count][c - count] = word.substring(0 + count, 1 + count);
				}
				count++;
			}
			wordBank.add(word);
		}
	}
	public void D(int r, int c)
	{
		count = 0;
		
		while(count < word.length() &&  !stop)
		{
			if(r + count > layout.length - 1 || r - count < 0 || c + count > layout.length - 1 || c - count < 0)
			{
				stop = true;
			}
			else if(layout[r + count][c] != null)
			{
				if(!layout[r + count][c].equals(word.substring(0 + count, 1 + count)))
				{
					stop = true;
				}
			}
			count++;
		}
		
		if(!stop)
		{
			placed = true;
			count = 0;
			
			while(count < word.length() &&  !stop)
			{
				if(layout[r + count][c] == null)
				{
					layout[r + count][c] = word.substring(0 + count, 1 + count);
				}
				else if(layout[r + count][c].equals(word.substring(0 + count, 1 + count)))
				{
					layout[r + count][c] = word.substring(0 + count, 1 + count);
				}
				count++;
			}
			wordBank.add(word);
		}
	}
	public void DR(int r, int c)
	{
		count = 0;
		
		while(count < word.length() &&  !stop)
		{
			if(r + count > layout.length - 1 || r - count < 0 || c + count > layout.length - 1 || c - count < 0)
			{
				stop = true;
			}
			else if(layout[r + count][c + count] != null)
			{
				if(!layout[r + count][c + count].equals(word.substring(0 + count, 1 + count)))
				{
					stop = true;
				}
			}
			count++;
		}
		
		if(!stop)
		{
			placed = true;
			count = 0;
			
			while(count < word.length() &&  !stop)
			{
				if(layout[r + count][c + count] == null)
				{
					layout[r + count][c + count] = word.substring(0 + count, 1 + count);
				}
				else if(layout[r + count][c + count].equals(word.substring(0 + count, 1 + count)))
				{
					layout[r + count][c + count] = word.substring(0 + count, 1 + count);
				}
				count++;
			}
			wordBank.add(word);
		}
	}
	public void getLayout()
	{
		for(int ro = 0; ro < layout.length; ro++)
		{
			for(int co = 0; co < layout[0].length; co++)
			{
				if(layout[ro][co] == null)
				{
					System.out.print("_ ");
				}
				else
				{
					System.out.print(layout[ro][co] + " ");
				}
			}
			System.out.println("");
		}
	}
	public void getWord()
	{
		System.out.println(word);
	}
	public void getWords()
	{
		for(int i = 0; i < wordBank.size(); i++)
		{
			System.out.println(wordBank.get(i));
		}
	}
	public void fillLayout()
	{
		int temp;
		
		for(int r = 0; r < layout.length; r++)
		{
			for(int c = 0; c < layout[0].length; c++)
			{
				temp = rand.nextInt(25);
				if(layout[r][c] == null)
				{
					layout[r][c] = alphabet.substring(0 + temp, 1 + temp);
				}
			}
		}
	}
	public String[][] getLayoutArray()
	{
		return layout;
	}
	public Hyperlink[][] getTextLayoutArray()
	{
		return textLayout;
	}
	public ArrayList<String> getWordBank()
	{
		return wordBank;
	}
	public Text[] getWordBankText()
	{
		return wordBankText;
	}
	public int getDif()
	{
		return dif;
	}
}
