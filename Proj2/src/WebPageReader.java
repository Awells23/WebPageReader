import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 * A Java program that reads in a URL inputted by the user and stores its contents without 
 * the html tags into a local text file created by the program
 * @author Alex Wells, (3-15-2020)
 */
public class WebPageReader
{
  public static void main(String[] args)
  {
	  Scanner in = new Scanner(System.in);//Scanner for user input
	  
	  
	  //Prompt for user inputted URL
	  System.out.print("Please enter a URL and file name (ex:http://yourwebsite.com/index.html): ");
	  String output = in.nextLine();
	  
	  //getUrlContent retrieves html code and stores it into a string
	  String url = getUrlContent(output); 
	  
	  //Replaces html brackets with whitespace
	  url = url.replaceAll("\\<.*?\\>", ""); 
	  
	  //Prints html contents being stored into a file
	  System.out.print(url);
	  
	  //Prepares a text document for URL entered
	  try ( PrintWriter writer = new PrintWriter("urlInput.txt"); 
		   )
		{
		  writer.println(url); //Prints url contents to urlInput.txt
		} 
	  		catch (FileNotFoundException e) {
			
			System.out.print("File not found, please try again");
		}
  }

  //Method to retrieve URL contents
  private static String getUrlContent(String fileName)
  {
    StringBuilder html = new StringBuilder();

   
    //Wrapped all the statements that throw exceptions in one try/catch statement.
    try
    {
      // create a url object
      URL url = new URL(fileName);

      // create a urlconnection object
      URLConnection urlConnection = url.openConnection();

      // wrap the urlconnection in a bufferedreader
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

      String line;

      // read from the urlconnection via the bufferedreader and reads to the end of file
      while ((line = bufferedReader.readLine()) != null)
      {
        html.append(line + "\n");
      }
      bufferedReader.close(); 
    }
    catch(MalformedURLException ex)
    {
      System.out.print("MalformedURLException occured, please try again");
    }
    catch (IOException ex) {
    	System.out.println("IOException occured, please try again"); 
    }
    
    return html.toString(); //returns the html content with tags
  }
}