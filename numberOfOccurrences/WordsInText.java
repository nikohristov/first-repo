package Company;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class WordsInText {
		private static final int amplitudeOfStatistic = 20;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your text: ");
		String text = sc.nextLine();
		text = text.toUpperCase();
		TreeMap<Character,Integer> statistics = new TreeMap<Character,Integer>();
		
		for(char c : text.toCharArray()){
			if(c < 'A' || c > 'Z'){
				continue;
			}
			if(statistics.get(c) == null){
				statistics.put(c, 0);
			}
			statistics.put(c, statistics.get(c)+1);
		}
		
		TreeMap<Integer,Character> sortedStatistics = new TreeMap<Integer,Character>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 >= o2)
					return -1;
				else
					return 1;
			}
		});

		for(Map.Entry<Character, Integer> map : statistics.entrySet()){
			sortedStatistics.put(map.getValue(), map.getKey());
		}
			
		double top = sortedStatistics.firstKey();
		for(Map.Entry<Integer, Character> map : sortedStatistics.entrySet()){
			System.out.print(map.getValue() + " - " +map.getKey()+" times. ");
			double numberOfRepeats = map.getKey();
			int koef =(int) (((numberOfRepeats/top))*amplitudeOfStatistic);
			if(koef < 1)
				koef = 1;
			for(int i=0; i<koef; i++){
				System.out.print("#");
			}
			System.out.println();
		}
		
	}

}
