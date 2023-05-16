import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

//Make a sign that a button is clicked and refresh old buttons
public class Main extends Application 
{	
	int width = 500;
	int height = 500;
	
	Button op1 = new Button("Easy");
	Button op2 = new Button("Medium");
	Button op3 = new Button("Hard");
	
	String search = "";
	
	int prevCol = -1;
	int prevRow = -1;
	
	Layout l;
	int checker = 0;
	
	ArrayList<Hyperlink> correctLetters = new ArrayList<Hyperlink>(); 
	int count;
	
	Pane root = new Pane();
	
	@Override
	public void start(Stage primaryStage) 
	{	
		try 
		{				
			//Background
			Rectangle background = new Rectangle(10, 10, Color.LIGHTGREY);
			background.toBack();
			root.getChildren().add(background);
			
			//Set scene
			Scene scene = new Scene(root,width,height);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//Set Options
			op1.setLayoutX(width/2 - 100);
			op1.setLayoutY(height/2 - 100);
			op1.setPrefWidth(200);
			op1.setPrefHeight(50);
			op1.setOnAction(event -> setEasy(event));
			
			op2.setLayoutX(width/2 - 100);
			op2.setLayoutY(height/2 - 25);
			op2.setPrefWidth(200);
			op2.setPrefHeight(50);
			op2.setOnAction(event -> setMedium(event));
			
			op3.setLayoutX(width/2 - 100);
			op3.setLayoutY(height/2 + 50);
			op3.setPrefWidth(200);
			op3.setPrefHeight(50);
			op3.setOnAction(event -> setHard(event));
			
			root.getChildren().addAll(op1, op2, op3);
			
			//Sizing
			AnimationTimer timer = new AnimationTimer() 
			{
				@Override
				public void handle(long now) 
				{
					background.setWidth(scene.getWidth());
					background.setHeight(scene.getHeight());
				}
			};
				
			timer.start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void setEasy(ActionEvent event)
	{
		l = new Layout(1);
		offButtons();
		
		setGame(l);
	}
	
	private void setMedium(ActionEvent event)
	{
		l = new Layout(2);
		offButtons();
		
		setGame(l);
	}
	
	private void setHard(ActionEvent event)
	{
		l = new Layout(3);
		offButtons();
		
		setGame(l);
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public void offButtons()
	{
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
	}
	public void onButtons()
	{
		op1.setVisible(true);
		op2.setVisible(true);
		op3.setVisible(true);
	}
	public void setGame(Layout l)
	{
		int countY = 0;
		int countX = 0;
		
		Rectangle rect = new Rectangle(500, 400, Color.WHITE);
		root.getChildren().add(rect);	
		
		Rectangle bank = new Rectangle(480, 80, Color.WHITE);
		root.getChildren().add(bank);	
		bank.setY(0.8 * height + 10);
		bank.setX(10);
		
		if(l.getDif() == 1)
		{
			for(int r = 0; r < l.getLayoutArray().length; r++)
			{
				for(int c = 0; c < l.getLayoutArray()[0].length; c++)
				{
					Hyperlink temp = new Hyperlink(l.getLayoutArray()[r][c]);
					l.getTextLayoutArray()[r][c] = temp;
					temp.setScaleX(2);
					temp.setScaleY(2); 
					root.getChildren().add(temp);
					temp.setTranslateX(25 + c * 40);
					temp.setTranslateY(r * 33);
					
					//Changes hyperlink default look
					temp.setBorder(Border.EMPTY);
					temp.setUnderline(false);
					temp.setTextFill(Color.BLACK);
					
					temp.setOnAction(event -> Word(event));
				}
			}
			
			countY = 0;
			countX = 0;
			for(int i = 0; i < l.getWordBank().size(); i++)
			{	
				Text temp = new Text(l.getWordBank().get(i));
				l.getWordBankText()[i] = temp;		
				temp.setScaleX(1.5);
				temp.setScaleY(1.5); 
				root.getChildren().add(temp);
				
				temp.setX(30 + 200 * countX);	
				temp.setY(430 + 25 * countY);
				countY++;
				
				if(countY > 2)
				{
					countY = 0;
					countX++;
				}
			}
		}
		else if(l.getDif() == 2)
		{
			for(int r = 0; r < l.getLayoutArray().length; r++)
			{
				for(int c = 0; c < l.getLayoutArray()[0].length; c++)
				{
					Hyperlink temp = new Hyperlink(l.getLayoutArray()[r][c]);
					l.getTextLayoutArray()[r][c] = temp;
					temp.setScaleX(1.5);
					temp.setScaleY(1.5); 
					root.getChildren().add(temp);
					temp.setTranslateX(20 + c * 30);
					temp.setTranslateY(r * 25);
					
					//Changes hyperlink default look
					temp.setBorder(Border.EMPTY);
					temp.setUnderline(false);
					temp.setTextFill(Color.BLACK);
					
					temp.setOnAction(event -> Word(event));
				}
			}
			
			countY = 0;
			countX = 0;
			for(int i = 0; i < l.getWordBank().size(); i++)
			{	
				Text temp = new Text(l.getWordBank().get(i));
				l.getWordBankText()[i] = temp;		
				temp.setScaleX(1.5);
				temp.setScaleY(1.5); 
				root.getChildren().add(temp);
				
				temp.setX(30 + 130 * countX);	
				temp.setY(430 + 25 * countY);
				countY++;
				
				if(countY > 2)
				{
					countY = 0;
					countX++;
				}
			}
		}
		else if(l.getDif() == 3)
		{
			for(int r = 0; r < l.getLayoutArray().length; r++)
			{
				for(int c = 0; c < l.getLayoutArray()[0].length; c++)
				{
					Hyperlink temp = new Hyperlink(l.getLayoutArray()[r][c]);
					l.getTextLayoutArray()[r][c] = temp;
					temp.setScaleX(1.3);
					temp.setScaleY(1.3); 
					root.getChildren().add(temp);
					temp.setTranslateX(5 + c * 25);
					temp.setTranslateY(r * 19.75);
					
					//Changes hyperlink default look
					temp.setBorder(Border.EMPTY);
					temp.setUnderline(false);
					temp.setTextFill(Color.BLACK);
					
					temp.setOnAction(event -> Word(event));
				}
			}
			
			countY = 0;
			countX = 0;
			for(int i = 0; i < l.getWordBank().size(); i++)
			{	
				Text temp = new Text(l.getWordBank().get(i));
				l.getWordBankText()[i] = temp;		
				temp.setScaleX(1.5);
				temp.setScaleY(1.5); 
				root.getChildren().add(temp);
				
				temp.setX(30 + 100 * countX);	
				temp.setY(430 + 25 * countY);
				countY++;
				
				if(countY > 2)
				{
					countY = 0;
					countX++;
				}
			}
		}
	}
	
	private void Word(ActionEvent event)
	{		
		int col = getCol((Hyperlink)event.getSource());
		int row = getRow((Hyperlink)event.getSource());
		
		if(prevCol < 0 && prevRow < 0)
		{
			prevCol = getCol((Hyperlink)event.getSource());
			prevRow = getRow((Hyperlink)event.getSource());
			
			search += l.getTextLayoutArray()[prevRow][prevCol].getText();
			
			correctLetters.add((Hyperlink)event.getSource());
		}
		else if(checker == 0)
		{	
			if(col + 1 == prevCol && row + 1 == prevRow)
			{
				checker = 1;
			}
			else if(col == prevCol && row + 1 == prevRow)
			{
				checker = 2;
			}
			else if(col - 1 == prevCol && row + 1 == prevRow)
			{
				checker = 3;
			}
			else if(col + 1 == prevCol && row == prevRow)
			{
				checker = 4;
			}
			else if(col - 1 == prevCol && row == prevRow)
			{
				checker = 5;
			}
			else if(col + 1 == prevCol && row - 1 == prevRow)
			{
				checker = 6;
			}
			else if(col == prevCol && row - 1 == prevRow)
			{
				checker = 7;
			}
			else if(col - 1 == prevCol && row - 1 == prevRow)
			{
				checker = 8;
			}
			else
			{
				incorrectWord();
				search = "";
				setBlack();
			}
			correctLetters.add((Hyperlink)event.getSource());
			search += l.getTextLayoutArray()[row][col].getText();
			
			prevCol = getCol((Hyperlink)event.getSource());
			prevRow = getRow((Hyperlink)event.getSource());
		}
		else
		{
			if(checker == 1)
			{
				if(col + 1 == prevCol && row + 1 == prevRow)
				{
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					if(checkWords())
					{
						correct();
						search = "";
					}
				}
				else
				{
					correctLetters.add((Hyperlink)event.getSource());
					search = "";
					
					search += l.getTextLayoutArray()[row][col].getText();
					
					checker = 0;
					
					setBlack();
				}
			}
			if(checker == 2)
			{
				if(col == prevCol && row + 1 == prevRow)
				{
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					if(checkWords())
					{
						correct();
						search = "";
					}
				}
				else
				{
					incorrectWord();
					search = "";
					
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					checker = 0;
					
					setBlack();
				}
			}
			if(checker == 3)
			{
				if(col - 1 == prevCol && row + 1 == prevRow)
				{
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					if(checkWords())
					{
						correct();
						search = "";
					}
				}
				else
				{
					incorrectWord();
					search = "";
					
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();	
					
					checker = 0;
					
					setBlack();
				}
			}
			if(checker == 4)
			{
				if(col + 1 == prevCol && row == prevRow)
				{
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					if(checkWords())
					{
						correct();
						search = "";
					}
				}
				else
				{
					incorrectWord();
					search = "";
					
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					checker = 0;
					
					setBlack();
				}
			}
			if(checker == 5)
			{
				if(col - 1 == prevCol && row == prevRow)
				{
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
							
					if(checkWords())
					{
						correct();
						search = "";
					}
				}
				else
				{
					incorrectWord();
					search = "";
					
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					checker = 0;
					
					setBlack();
				}
			}
			if(checker == 6)
			{
				if(col + 1 == prevCol && row - 1 == prevRow)
				{
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					if(checkWords())
					{
						correct();
						search = "";
					}
				}
				else
				{
					incorrectWord();
					search = "";
					
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					checker = 0;
					
					setBlack();
				}
			}
			if(checker == 7)
			{
				if(col == prevCol && row - 1 == prevRow)
				{
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					if(checkWords())
					{
						correct();
						search = "";
					}
				}
				else
				{
					incorrectWord();
					search = "";
					
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					checker = 0;
					
					setBlack();
				}
			}
			if(checker == 8)
			{
				if(col - 1 == prevCol && row - 1 == prevRow)
				{
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					if(checkWords())
					{
						correct();
						search = "";
					}
				}
				else
				{
					incorrectWord();
					search = "";
					
					correctLetters.add((Hyperlink)event.getSource());
					search += l.getTextLayoutArray()[row][col].getText();
					
					checker = 0;
					
					setBlack();
				}
			}
		}
		prevCol = getCol((Hyperlink)event.getSource());
		prevRow = getRow((Hyperlink)event.getSource());
		l.getTextLayoutArray()[row][col].setTextFill(Color.LIGHTGREEN);
		
		System.out.println(search);
	}
	
	private boolean checkWords()
	{
		for(int i = 0; i < l.getWordBank().size(); i++)
		{
			if(l.getWordBank().get(i).equals(search))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private void incorrectWord()
	{
		count = search.length();
		
		while(count != 0)
		{
			correctLetters.remove(correctLetters.size() - 1);
			count--;
		}
	}
	
	private void correct()
	{
		for(int i = 0; i < l.getWordBank().size(); i++)
		{
			if(l.getWordBank().get(i).equals(search))
			{
				l.getWordBankText()[i].setVisible(false);
				l.getWordBank().set(i, "");
			}
		}
		setBlack();
	}
	
	private int getRow(Hyperlink h)
	{
		if(l.getDif() == 1)
		{
			return (int) (h.getTranslateY() / 33);
		}
		else if(l.getDif() == 2)
		{
			return (int) (h.getTranslateY() / 25);
		}
		else if(l.getDif() == 3)
		{
			return (int) (h.getTranslateY() / 19.75);
		}
		else
		{
			return 0;
		}
	}
	
	private int getCol(Hyperlink h)
	{
		if(l.getDif() == 1)
		{
			return (int) ((h.getTranslateX() - 25) / 40);
		}
		else if(l.getDif() == 2)
		{
			return (int) ((h.getTranslateX() - 20) / 30);
		}
		else if(l.getDif() == 3)
		{
			return (int) ((h.getTranslateX() - 5) / 25);
		}
		else
		{
			return 0;
		}
	}
	
	private void setBlack()
	{
		for(int r = 0; r < l.getTextLayoutArray().length; r++)
		{
			for(int c = 0; c < l.getTextLayoutArray()[0].length; c++)
			{
				l.getTextLayoutArray()[r][c].setTextFill(Color.BLACK);
				
				for(Hyperlink let : correctLetters)
				{
					if(l.getTextLayoutArray()[r][c] == let)
					{
						l.getTextLayoutArray()[r][c].setTextFill(Color.LAWNGREEN);
					}
				}
			}
		}
	}
}