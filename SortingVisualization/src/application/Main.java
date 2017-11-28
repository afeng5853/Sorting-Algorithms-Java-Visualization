package application;
	
import Lab3_2.ArrayMethods2;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.stage.Stage; 
import javafx.scene.text.Text; 
import javafx.scene.control.Button;

public class Main extends Application { 
	private Button btnMergeSort;
   @Override 
   public void start(Stage stage) {  
	   //btnMergeSort.setLayoutX(50);
	  //btnMergeSort.setLayoutY(50);
	  //btnMergeSort.setText("hi");
	   
	   	String[] test1 = {"test", "zebra", "lol", "year"};
	   	ArrayMethods2.mergeSort(test1);
      //Creating a Text object 
      Text text = new Text();      
      
      //Setting the text to be added. 
    	  text.setText(test1[2]);
       
      //setting the position of the text 
      text.setX(50); 
      text.setY(50); 
         
      //Creating a Group object  
      Group root = new Group(text);   
               
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);  
     
      
      //Setting title to the Stage 
      stage.setTitle("Sorting Visualizations"); 
      //Adding scene to the stage 
      stage.setScene(scene); 
      //Displaying the contents of the stage 
      stage.show(); 
   }      
   
   public static void main(String args[]){ 
      launch(args); 
   } 
} 
