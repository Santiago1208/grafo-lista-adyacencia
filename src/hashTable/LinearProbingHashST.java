package hashTable;

import tadCola.*;

public class LinearProbingHashST<Key, Value> {

	private static final int INIT_CAPACITY = 4;

	private int N; // number of key-value pairs in the symbol table
	private int M; // size of linear probing table
	private Key[] keys; // the keys
	private Value[] vals; // the values

	
	public LinearProbingHashST() {
		this(INIT_CAPACITY);
	}

	
	public LinearProbingHashST(int capacity) {
		M = capacity;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}

	
	public int size() {
		return N;
	}

	
	public boolean isEmpty() {
		return size() == 0;
	}

	
	public boolean contains(Key key) {
		return get(key) != null;
	}

	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	
	private void resize(int capacity) {
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(
				capacity);
		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		M = temp.M;
	}

	
	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}

		// double table size if 50% full
		if (N >= M / 2)
			resize(2 * M);

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	
	public Value get(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	public int getIntWhere(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return i;
		return -1;
	}

	
	public void delete(Key key) {
		if (!contains(key))
			return;

		// find position i of key
		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (i + 1) % M;
		}

		// delete key and associated value
		keys[i] = null;
		vals[i] = null;

		// rehash all keys in same cluster
		i = (i + 1) % M;
		while (keys[i] != null) {
			// delete keys[i] an vals[i] and reinsert
			Key keyToRehash = keys[i];
			Value valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % M;
		}

		N--;

		// halves size of array if it's 12.5% full or less
		if (N > 0 && N <= M / 8)
			resize(M / 2);

		assert check();
	}

	
	public Queue<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				queue.enqueue(keys[i]);
		return queue;
	}

	
	private boolean check() {

		// check that hash table is at most 50% full
		if (M < 2 * N) {
			System.err.println("Hash table size M = " + M + "; array size N = "
					+ N);
			return false;
		}

		// check that each key in table can be found by get()
		for (int i = 0; i < M; i++) {
			if (keys[i] == null)
				continue;
			else if (get(keys[i]) != vals[i]) {
				System.err.println("get[" + keys[i] + "] = " + get(keys[i])
						+ "; vals[i] = " + vals[i]);
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
		st.put("hola", 5);

		System.out.println(st.get("hola"));

	}

	public void setValue(int pos, Value value) {
		vals[pos] = value;
	}
}
