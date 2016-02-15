package MusicalInstruments;

import java.util.Random;

public class DemoMusicalShop {
	static MusicalShop first = new MusicalShop();
	public static void main(String[] args) throws InterruptedException {
		first.sellInstrument("jojo", 200);
		System.out.println();
		first.sellInstrument("цигулка", 2);
		first.sellInstrument("контрабас", 300);
		System.out.println(first.getCash());
		
		
		first.start();
		
		Client one = new Client();
		one.start();
		
		first.getNewInstrument("контрабас", 300);
		first.sellInstrument("контрабас", 255);
		
		first.printCatalogByType();
		first.printCatalogSortByName();
		first.printCatalogSortByPrice("up");
		first.printCatalogSortByPrice("down");
		System.out.println();
		first.sellInstrument("бас-китара", 8);
		first.printCatalogOfSales();
		first.genereteSumOfSales();
		first.mostSoldProduct();
		first.mostNoSoldProduct();
		first.infoForMostSoldProductsByType();
		first.infoForMostSoldProductByIncome();
		first.sellInstrument("цигулка", 200);
		
	}

	static class Client extends Thread{
		private static final String[][] defaultInstruments = {{"цигулка", "виола", "контрабас", "арфа", "китара", "гъдулка"},
				{"барабани", "тарамбука", "тъпан", "дайре"},
				{"тромпет", "тромбон", "туба", "флейта", "кларинет"},	
				{"орган", "пиано", "акордеон"},
				{"синтезатор", "бас-китара", "електрическа цигулка"}};
		
		Random r = new Random();
		@Override
		public void run() {
			while(true){
				int row = r.nextInt(5);
				int coll = r.nextInt(this.defaultInstruments[row].length);
				int quantity = r.nextInt(10) + 5;
				try {
					first.sellInstrument(this.defaultInstruments[row][coll], quantity);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
