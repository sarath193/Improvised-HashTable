import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestClass {

	public static void main(String[] args) {
		
		FileReader fr = null;
		try {
			fr = new FileReader("assets\\dictionary.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not Found");
		}
		BufferedReader br = new BufferedReader(fr);
		String str="";
		MyHashTable ht = new MyHashTable();
		int count = 0;
		try {
			while((str = br.readLine()) != null){
				ht.put(str);
				count++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(count);
		ht.put("sarath");
		System.out.println(ht.getCurrentSize());
		System.out.println(ht.get("sarth"));
		
		

	}

}
