package model;

public class Node {
	
	private int value;
	private Node next;
	private Node prev;
	private Node connected;
	private char portalIndex;
	
	private boolean hasSeed;
	private boolean hasRick;
	private boolean hasMorty;
	
	
	//-----------------------------------------------
	
	//TELEPORT METHODS
	public void teleportRick() {
		hasRick = false;
		this.getConnected().setHasRick(true);
	}
	public void teleportMorty() {
		hasMorty = false;
		this.getConnected().setHasMorty(true);
	}
	
	public Node(int value) {
		this.setValue(value);
		this.hasSeed = false;
		this.hasRick = false;
		this.hasMorty = false;
	}
	
	public String toString() {
		String info = "";


		if(hasRick && hasMorty) {
			info = " [R M]";

		} else if(hasRick) {
			info = " [ R ]";

		}else if(hasMorty) {
			info = " [ M ]";

		}else if(hasSeed) {
			info = " [ * ]";

		} else {
			info= " ["+Integer.toString(value)+"] ";
		}

	return info;

		
		/*return " ["+String.valueOf(value)+"] ";

		if(this.hasRick && this.hasMorty) {
			return " [R M] ";

		} else if(this.hasRick) {
			return " [R] ";
			
		}else if(this.hasMorty) {
			return " [M] ";
			
		}else if(this.hasSeed) {
			return " [*] ";
			
		} else {
			return " ["+Integer.toString(value)+"] ";
		}*/

	}
	
	//TOSTRING NODO PORTAL
		public String portalString()
		{
			String out="";
			if(connected!=null)
			{
				out="["+portalIndex+"]";
			}
			else
			{
				out=" ["+Integer.toString(value)+"] ";
			}
			return out;
		}
	
	
	//Getter & setters -----------------------------
	public boolean getMorty() {
		return hasMorty;
	}
	public void setMorty(boolean has) {
		this.hasMorty = has;
	}
	public boolean getRick() {
		return hasRick;
	}
	public void setRick(boolean has) {
		this.hasRick = has;
	}
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getConnected() {
		return connected;
	}

	public void setConnected(Node connected) {
		this.connected = connected;
	}

	public boolean getHasSeed() {
		return hasSeed;
	}

	public void setHasSeed(boolean hasSeed) {
		this.hasSeed = hasSeed;
	}

	public boolean getHasRick() {
		return hasRick;
	}
	public void setHasRick(boolean hasRick) {
		this.hasRick = hasRick;
	}
	
	public boolean getHasMorty() {
		return hasRick;
	}
	public void setHasMorty(boolean hasMorty) {
		this.hasMorty = hasMorty;
	}
	public char getPortalIndex() {
		return portalIndex;
	}

	public void setPortalIndex(char portalIndex) {
		this.portalIndex = portalIndex;
	}

}
