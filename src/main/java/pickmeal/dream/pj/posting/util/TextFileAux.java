package pickmeal.dream.pj.posting.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class TextFileAux {
	public void uploadTextFile(String tagData) { //String fileName, 
		try {
			FileWriter fw = new FileWriter(new File("C:\\IT\\text/text.txt"));
			fw.write(tagData);
			
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String downloadTextFile() {
		String tagData = "";
		try {
			File file = new File("C:/IT/text/text.txt");
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				tagData += sc.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tagData;
	}
}
