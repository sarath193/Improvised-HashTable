
/*
 * HashTable Implementation using separate chaining.
 * 
 * 
 * To check rehashing in this hashtable make the initial size 100000.
 */
import java.util.Hashtable;

public class MyHashTable {

	private static int bucketSize = 0;
	private static int currentSize = 0;
	private static int maxTreeDepth = 0;
	private MyLinkedList<String, String>[] theList;

	private final double LAMBDA = 0.7;

	private int prime;

	@SuppressWarnings("unchecked")
	public MyHashTable() {
		bucketSize = nextPrime(200000);
		theList = new MyLinkedList[bucketSize];
		prime = nextPrime(bucketSize * 5);
	}
	
	public void put(String str){
		put(str, str);
	}

	public void put(String key, String value) {
		if((float)currentSize/bucketSize > 0.7) rehash(key, value);
		currentSize++;
		int hashValue = hashcode(key);
		MyLinkedList<String, String> temp_tree;
		if(theList[hashValue] == null) temp_tree = new MyLinkedList<>();
		else temp_tree = theList[hashValue];

		temp_tree.add(key, value);
		theList[hashValue] = temp_tree;		
	}
	
	public String get(String key){
		int hashValue = hashcode(key);
		MyLinkedList<String, String> list = theList[hashValue];
		if(list!=null){
			String value = list.get(key);
			return value;
		}
		else return "Empty bucket";
	}

	/*
	 * Rehashing is done if ratio of total number of entries in the hash table to
	 * the total number of buckets exceed the lambda ratio.
	 */
	@SuppressWarnings("unchecked")
	private void rehash(String key, String value) {
		bucketSize = nextPrime(bucketSize * 2);
		MyLinkedList<String, String>[] theTempList = theList;
		theList = new MyLinkedList[bucketSize];
		KeyValuePair<String, String> pair;
		for (MyLinkedList<String, String> list : theTempList) {
			pair = list.remove();
			put(pair.key, pair.value);
		}
		put(key, value);
	}

	public void remove(String key) {
		int hashValue = hashcode(key);
		MyLinkedList<String, String> temp = theList[hashValue];
		if (temp == null) {
			System.out.println(key + " not found !");
		} else {
			if (!temp.contains(key))
				System.out.println(key + " not found !");
			else {
				temp.remove(key);
				
				System.out.println(key + " removed successfully!");
			}

		}
	}
	/*
	 * Gives the next prime number
	 */

	private int nextPrime(int num) {
		if ((num & 1) == 0)
			num += 1;
		while (!isPrime(num)) {
			num += 2;
		}
		return num;
	}

	private boolean isPrime(int num) {
		if (num == 1)
			return false;
		if (num == 2)
			return true;
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	private int hashcode(String str) {
		long hash = str.hashCode();
		hash = hash % prime;
		for (int i = 0; i < str.length(); i++) {
			int t = (int) str.charAt(i);
			hash = ((hash << 5) - hash) + ((t << 3) - t);
		}

		if (hash < 0)
			hash %= Integer.MAX_VALUE;
		return (int) (Math.abs(hash) % bucketSize);

	}
	
	public int getCurrentSize(){
		return currentSize;
	}


}
