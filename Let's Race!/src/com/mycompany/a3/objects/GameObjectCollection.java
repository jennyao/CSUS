package com.mycompany.a3.objects;

import java.util.Vector;

public class GameObjectCollection implements ICollection {
	private Vector<GameObject> theCollection;
	
	public GameObjectCollection() {
		theCollection = new Vector<GameObject>();
	}
	
	public int size() {
		return theCollection.size();
	}
	
	public void addElement(GameObject newObject) {
		theCollection.addElement(newObject);
	}

	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator { //7-14   UML for this on 7-15 (p88)
		private int currElementIndex;
		public GameObjectIterator() {
			currElementIndex = -1;
		}
		public boolean hasNext() {
			if (theCollection.size ( ) <= 0) return false;
			if (currElementIndex == theCollection.size()-1)	return false;
		
			return true;
		}
		public GameObject getNext() {
			currElementIndex++;
			return(theCollection.elementAt(currElementIndex));
		}
		
		public int getIndex() {
			// TODO Auto-generated method stub
			return currElementIndex;
		}
		public GameObject get(int location) {
			// TODO Auto-generated method stub
			return null;
		}
		public GameObject remove() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public GameObject elementAt(int location) {
		return theCollection.elementAt(location);
	}

	public void remove(GameObject newObj) {
		// TODO Auto-generated method stub
		theCollection.remove(newObj);
	}

	public Pylon getPylon(int currentPylon) {
		IIterator i = ((ICollection) theCollection).getIterator();
		while (i.hasNext()) {
			GameObject o = i.getNext();
			if (o instanceof Pylon) {
				int pyNum = ((Pylon) o).getSeqNo();
			
				if (pyNum == currentPylon) {
					return (Pylon) o;
				}
			}
			
		}
		return null;
	}

	public Cars getCar() {
		IIterator i = ((ICollection) theCollection).getIterator();
		while (i.hasNext()) {
			GameObject o = i.getNext();
			if (o instanceof Cars) {
					return (Cars) o;
			}
		}
		return null;
	}

	public GameObject remove(int i) {
		// TODO Auto-generated method stub
		return null;
	}

//	public void addElement(GameObject obj) {
//		// TODO Auto-generated method stub
//		
//	}
}


