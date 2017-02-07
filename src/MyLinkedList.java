
public class MyLinkedList<K, V> {

	Node<K, V> head;

	void add(K key, V data) {
		Node<K, V> temp = new Node<K, V>(data, key);
		temp.next = head;
		head = temp;
	}

	KeyValuePair<K, V> remove() {
		KeyValuePair<K, V> pair = new KeyValuePair<K, V>(head.getKey(), head.getValue());
		head = head.next;
		return pair;
	}
	
	boolean contains(K key){
		Node<K,V> copy = head;
		while(copy!=null){
			if(copy.getKey() == key) return true;
		}
		return false;
	}

	void remove(K key){
		Node<K,V> copy = head;
		if(copy.getKey() == key) head = head.next;
		while(copy.next.getKey()!=key && copy!=null){
			copy = copy.next;
		}
		if(copy!=null) copy.next = copy.next.next;
	}
	
	V get(K key){
		Node<K,V> copy = head;
		while(copy!=null){
			if(copy.getKey() == key) return copy.getValue();
		}
		return (V)"NotFound";
	}

}

class Node<K, V> {
	private V data;
	private K key;
	Node<K, V> next;

	Node(V data, K key) {
		this.data = data;
		this.key = key;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return data;
	}
}
