import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class ChatBot extends JFrame implements KeyListener{

	/**
	 * Author: Dhavalkumar Prajapati
	 */
	private static final long serialVersionUID = 1L;
	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(20,50);
	JTextArea input=new JTextArea(1,50);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	public static boolean isDigit(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) 
	        	return false;
	    }
	    return true;
	}
	
	String[][] chatBot={
		//standard greetings
		{"hi","hello","hola","ola","howdy"},
		{"hi","hello","hey"},
		//question greetings
		{"how are you","how r you","how r u","how are u"},
		{"good","doing well","Fine","Fine,Thank you!"},
		//yes
		{"yes"},
		{"no","NO","NO!!!!!!!"},
		//default
		{"shut up","you're bad","stop talking",
		"(Sorry I don't know)","enter valide"}
	};
	
	public static void main(String[] args){
		new ChatBot();
	}
	
	public ChatBot(){
		super("DBpedia BOT");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
	
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,200,0));
		add(p);
		addText("Welcome! This is chat bot which convert arabic number to Roman");
		addText("\n");
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			
			//-----grab quote-----------
			String quote=input.getText();
			input.setText("");
			addText("YOU:\t"+quote);
			
			quote.trim();
			boolean war=false;
			 if ( isDigit(quote)  ) {
	                int arabic = Integer.valueOf(quote);
	                addText("\n");
	                    Roman N = new Roman(arabic);
	                    addText("BOT:\t"+N.convert());
	                    addText("\n");
	             }
			 else
			 {
				 war=true;
			 }
			
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote.trim();
			byte response=0;
			
			int j=0;
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\nBOT:\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			
			//-----default--------------
			if(war==true){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\nBOT:\t"+chatBot[chatBot.length-1][r]);
				war=false;
			}
			addText("\n");
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}
	
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
}