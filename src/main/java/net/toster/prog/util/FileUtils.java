package net.toster.prog.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import net.toster.prog.model.Question;


public class FileUtils {
	
	public static void writeToFile(List<Question> questions) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("questions.txt", true)));
		
		for(int i = 0; i < questions.size(); i++) {
			bw.write(questions.get(i).getName() + " | " + questions.get(i).getLink());
			bw.newLine();
			bw.newLine();
		}
		
		bw.close();
	}
}
