package gaboqm.com.util;

import java.util.ArrayList;

public class UtilServer {
	private static ArrayList<Integer> elements = new ArrayList<>();
	private static final int min=0;
	private static final int max=1000;
	public static ArrayList<Integer> getElements() {
		return elements;
	}
	
	
	
	
	public static void  printElements() {
		System.out.println("Elements");
		elements.forEach(System.out::println);
	}

	
	
	public static int randomElement(){
        int num=(int)Math.floor(Math.random()*(max-min+1)+(min));
        return num;
    }
	
	public static void removeElement() {
		if(!elements.isEmpty()) {
			elements.remove(elements.size()-1);
		}
	}
}
