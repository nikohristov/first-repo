package Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class FileWrite {

	public static void main(String[] args) throws IOException {
		
		File file = new File("writerTest.txt");
		if(!(file.exists())){
			file.createNewFile();
		}
		
		
		FileWriter write = new FileWriter(file);
		write.write(LocalTime.now().toString());
		write.close();
		
		
	}

}
