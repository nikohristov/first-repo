package Bank;

import java.util.ArrayList;
import java.util.Random;

public class DemoBank {
	
	public static void main(String[] args) {
		Bank bg = new Bank("Bank-DSK", "gr.Sofia kv.Lesvki V 17");
		Client one = new Client("Niko", "Levski", 20000, 1500);
		Client two = new Client("Niko2", "Levski", 10000, 1750);
		Client tree = new Client("Niko3", "Levski", 12310, 2000);
		Client four = new Client("Niko4", "Levski", 13330, 1750);
		Client five = new Client("Niko5", "Levski", 20000, 2220);
		Client six = new Client("Niko6", "Levski", 20000, 2500);
		
		ArrayList<Client> clients = new ArrayList<Client>();
		clients.add(one);
		clients.add(two);
		clients.add(tree);
		clients.add(four);
		clients.add(five);
		clients.add(six);
		
		bg.insertTaxes(100000);
		
		Random r = new Random();
		bg.infoBank();

		for(int i=0; i < clients.size(); i++){
			double b = r.nextInt(20) +80;
			b /= 100;
			double sum = clients.get(i).getMoney()*b;
			clients.get(i).deposit(sum, 10, bg);
		}
		bg.insertTaxes(100000);
		bg.infoBank();
		for(int i=0; i < clients.size(); i++){
			int b = r.nextInt(30000) +200;
			clients.get(i).credit(b, 10, bg);
		}
		bg.infoBank();
		
		for(int i=0; i < clients.size(); i++){
			clients.get(i).payCreditTax(bg);
		}
		bg.infoBank();
		
		for(int i=0; i < clients.size(); i++){
			clients.get(i).printInfoOfClient();
		}
		
		bg.infoBank();
		for(int i=0; i < bg.getProducts().size(); i++){
			bg.getProducts().get(i).printInfoProduct();
		}
		bg.infoBank();
		
		System.out.println("Lets pay for 3 mounht");
		for(int i=0; i < clients.size(); i++){
			for(int j=0; j<3; j++){
				clients.get(i).payCreditTax(bg);
			}
		}
		bg.infoBank();
	}
	
}
