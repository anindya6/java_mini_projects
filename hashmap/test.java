import java.io.*;
public class test
{
	public static void main(String [] args)
	{
		HashMap<String,String> hm = new HashMap<>(10,0.75);
		hm.insert("Hello","How are you?");
		System.out.println(hm.get("Hello"));
		hm.insert("Ssup","123 ");
		hm.insert("No way","1414 ");
		hm.insert("Collide already","14156 ");
		hm.insert("haha","1lolhad ");
		hm.insert("nope","nothing ");
		hm.insert("sakdka","ajhajskj ");
		System.out.println("I got these: "+hm.get("Ssup")+hm.get("No way")+hm.get("Collide already")+hm.get("haha")+hm.get("nope")+hm.get("sakdka"));
	}
}
