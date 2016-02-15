package MusicalInstruments;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class SuplierOfInstruments extends Thread{
	
	private static final String[][] defaultInstruments = {{"цигулка", "виола", "контрабас", "арфа", "китара", "гъдулка"},
			{"барабани", "тарамбука", "тъпан", "дайре"},
			{"тромпет", "тромбон", "туба", "флейта", "кларинет"},	
			{"орган", "пиано", "акордеон"},
			{"синтезатор", "бас-китара", "електрическа цигулка"}};
	
	private TreeMap<MusicalInstrument,Integer> stock;
	private MusicalShop own;
	
	public SuplierOfInstruments(MusicalShop own) {
		this.stock = new TreeMap<MusicalInstrument,Integer>();
		Random r = new Random();
		for(int j=0; j<this.defaultInstruments.length; j++){
			for(int i=0; i<this.defaultInstruments[j].length; i++){
				int timeToReceive = (r.nextInt(20) + 10);
				this.stock.put(new MusicalInstrument(this.defaultInstruments[j][i], 0), timeToReceive);
			}
		}
		this.own = own;
	}
	
	private String takeInfoRequest(String name,int quantity){
		String period = null;
		for(Map.Entry<MusicalInstrument, Integer> map : this.stock.entrySet()){
			if(map.getKey().getName().equals(name)){
				period =name+" will arive after "+map.getValue()+ " days.";
			}
		}
		return period;
	}
	
	synchronized protected void giveRequest(String name,int quantity) throws InterruptedException{
		System.out.println(this.takeInfoRequest(name, quantity));
		Integer p = null;
		for(Map.Entry<MusicalInstrument, Integer> map : this.stock.entrySet()){
			if(map.getKey().getName().equals(name)){
				p = map.getValue();
			}
		}
		Thread.currentThread().sleep(p*200);
		this.own.getNewInstrument(name, quantity);
		System.out.println(quantity+ " of " +name+" have delivered.");
	}
	
}
