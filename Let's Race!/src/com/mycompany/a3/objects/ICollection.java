package com.mycompany.a3.objects;

public interface ICollection {
	public void addElement(GameObject newObject);
	public IIterator getIterator();
	public GameObject elementAt(int location);
	public int size();
    public GameObject remove(int i);
}
