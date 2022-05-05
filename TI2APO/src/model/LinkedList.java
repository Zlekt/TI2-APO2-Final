package model;

public class LinkedList {
	
	/*
	 * TABLERO
	 */
	
	public final int ROWS;
	public final int COLUMNS;
	
	private Node first;
	private Node last;
	private int numSeeds; 
	private int size;
	
	public LinkedList(int rows, int columns, int numSeeds) {
		this.ROWS = rows;
		this.COLUMNS = columns;
		this.numSeeds = numSeeds;
		this.size=ROWS*COLUMNS;
	}
	
	public void add(int value) {
		
		Node temp = new Node(value);
		
		if(isEmpty()) { 
			
			first = temp;
			last = temp;
			
			first.setNext(first);
			first.setPrev(first);
			
		} else {
			last.setNext(temp);
			temp.setPrev(last);
			last = temp;
			
			last.setNext(first);
			first.setPrev(last);	
		}
	}
	
	public boolean isEmpty() {
		if(first == null) {
			return true;
		} 
		
		return false;
	}
	
	public Node searchNode(int value) {
		return searchNodeHelper(first, value);
	}
	
	private Node searchNodeHelper(Node node, int value) {
		if(node.getValue() == value) {
			return node;
		} else {
			return searchNodeHelper(node.getNext(), value);
		}
	}
	
	public void makeSeed(int[] seeds, int numSeeds) {
		for(int i = 0; i < seeds.length; i++) {
			Node temp = searchNode(seeds[i]);
			temp.setHasSeed(true);
		}
		
	}
	
	//FIND PLAYERS
		public Node getMortysNode() {
		
			return getMortysNodeHelper(first);
		}
		
		private Node getMortysNodeHelper(Node current) {
			
			if(current.getMorty()) {
				return current;
			}
			else {
				return getMortysNodeHelper(current.getNext());
			}
		}
		
		public Node getRicksNode() {
			return getRicksNodeHelper(first);
		}
		
		private Node getRicksNodeHelper(Node current) {
			
			if(current.getRick()) {
				return current;
			}
			else {
				return getRicksNodeHelper(current.getNext());
			}
		}
		
		//CHECK IF MAP HAS MORE SEEDS
		public boolean countSeeds() {
			return countSeedsHelper(first,0);
		}
		
		private boolean countSeedsHelper(Node current, int seeds) {
			
			if(current==last) {
				if(current.getHasSeed()) {
					seeds++;
				}
				if(seeds!=0) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(current.getHasSeed()) {
					seeds++;
				}
				return countSeedsHelper(current.getNext(),seeds);
			}
		}
	
	//TO STRING PARA ARREGLAR -------------------------------------------
	public String toString() {
		String message="";
		Node node = first;
		int rowCounter = 1;
		do {
			if(rowCounter%2!=0) {
				message+=node.toString();
				if(node.getValue()/rowCounter==COLUMNS) {
					message+="\n";
					rowCounter++;
				}
				node = node.getNext();
			}
			else {
				String[] zForm = new String[COLUMNS];
				for(int i=0; i<COLUMNS; i++) {
					zForm[i] = node.toString();
					node = node.getNext();
				}
				for(int i=COLUMNS-1; i>=0; i--) {
					message += zForm[i];
				}
				rowCounter++;
				message+="\n";
			}
		}
		while(rowCounter<=ROWS);
		
		return message;
	}
	
	//MOSTRAR PORTALES
	public String portalToString() {
		String message="";
		Node node = first;
		int rowCounter = 1;
		do {
			if(rowCounter%2!=0) {
				message+=node.portalString();
				if(node.getValue()/rowCounter==COLUMNS) {
					message+="\n";
					rowCounter++;
				}
				node = node.getNext();
			}
			else {
				String[] zForm = new String[COLUMNS];
				for(int i=0; i<COLUMNS; i++) {
					zForm[i] = node.portalString();
					node = node.getNext();
				}
				for(int i=COLUMNS-1; i>=0; i--) {
					message += zForm[i];
				}
				rowCounter++;
				message+="\n";
			}
		}
		while(rowCounter<=ROWS);
		
		return message;
	}
	
	
	
	/*
	public String toString(Node node) {
		
		node = first;
		String message = "";
		if(node==first) {
			message+=node.toString();
		}
		else {
			if(node.getValue()==COLUMNS) {
				message += "\n";
			}
			else {
				message += toString(node.getNext());
			}
		}
		return message;
	}*/
	//-------------------------------------------------------------

	
	//Getters & Setters ---------------------------------------
	public Node getFirst() {
		return first;
	}
	public void setFirst(Node first) {
		this.first = first;
	}
	public Node getLast() {
		return last;
	}
	public void setLast(Node last) {
		this.last = last;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public int getNumSeeds() {
		return numSeeds;
	}

	public void setNumSeeds(int numSeeds) {
		this.numSeeds = numSeeds;
	}

}
