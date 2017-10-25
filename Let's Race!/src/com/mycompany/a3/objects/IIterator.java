package com.mycompany.a3.objects;

public interface IIterator {
	public boolean hasNext();
	public GameObject getNext();
	
    public GameObject  get(int location);
    public GameObject  remove();
    public int         getIndex();
}
