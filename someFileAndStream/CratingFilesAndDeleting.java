package FromClass;

import java.io.File;
import java.io.IOException;

public class CratingFilesAndDeleting {

	public static void main(String[] args) {
		
		File file = new File("FirstFile.txt");
		System.out.println(file.exists());
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.getMessage();
			}
		}
		
		File direc = new File("newDirecForTest");
		if(!direc.exists()){
			direc.mkdir();
			System.out.println("Created directory !");
		}
		
		for(int i=0; i<10; i++){
			String name = direc.getName()+"/File"+(i+1)+".txt";
			try {
				new File(name).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		File[] files = direc.listFiles();
		
		for(int i=0; i<files.length; i++){
			File f = files[i];
			if((f.getName().contains(String.valueOf(i)) && ((i) % 2 == 0))){
				f.delete();
			}
		}
		
		files = direc.listFiles();
		System.out.println(files.length);
		System.out.println(files[0].getName());

		System.out.println(files[1].getName());
		files[1] = files[0];
		System.out.println(files[1]);
		System.out.println(files[0]);
		System.out.println(files[0] == files[1]);
		System.out.println(files[0].equals(files[1]));
		
		
		
	}

}
