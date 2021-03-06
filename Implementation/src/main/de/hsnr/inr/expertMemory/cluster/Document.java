package de.hsnr.inr.expertMemory.cluster;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * This acts like a multiset from guava
 * @author lammbraten
 *
 */
public class Document implements Iterable<Term>, Multiset<Term>, CosineAbleSet {
	
	private HashMultiset<Term> terms;
	private String name;
	private int length;
	private float weight;

	public Document(String name, int length) {
		this.setName(name);
		terms = HashMultiset.create();
		this.length = length;
	}

	public Document(Document toCopy) {
		this.setName(toCopy.getName());
		terms = toCopy.getTerms();
		this.length = toCopy.length;
	}

	private HashMultiset<Term> getTerms() {
		return terms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return getName() + "size: " + size() + ", weight: " + getWeight();
	}
	
	public boolean equals(Object o){
		if(o instanceof Document)
			return this.getName().equals(((Document)o).getName());
		return false;
	}

	public Iterator<Term> iterator() {
		return terms.iterator();
	}

	public boolean add(Term e) {
		return terms.add(e);
	}

	public boolean addAll(Collection<? extends Term> c) {
		return terms.addAll(c);
	}

	public void clear() {
		terms.clear();
	}

	public boolean contains(Object o) {
		return terms.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return terms.containsAll(c);
	}

	public boolean isEmpty() {
		return terms.isEmpty();
	}

	public boolean remove(Object o) {
		return terms.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return terms.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return terms.retainAll(c);
	}

	public int size() {
		return terms.size();
	}
	
	/**
	 * sums up the sum of every count.
	 * @return
	 */
	public int length(){
		return length;
	}

	public Object[] toArray() {
		return terms.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return terms.toArray(a);
	}

	public int add(Term arg0, int arg1) {
		return terms.add(arg0, arg1);
	}

	public int count(Object arg0) {
		return terms.count(arg0);
	}

	public Set<Term> elementSet() {
		return terms.elementSet();
	}

	public Set<com.google.common.collect.Multiset.Entry<Term>> entrySet() {
		return terms.entrySet();
	}

	public int remove(Object arg0, int arg1) {
		return terms.remove(arg0, arg1);
	}

	public int setCount(Term arg0, int arg1) {
		return terms.setCount(arg0, arg1);
	}

	public boolean setCount(Term element, int oldCount, int newCount) {
		return terms.setCount(element, oldCount, newCount);
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public CosineAbleSet create(CosineAbleSet toCopy, float weight) {
		CosineAbleSet weightedCopy = null;
		if(toCopy instanceof Document){
			weightedCopy = new Document((Document) toCopy);
			weightedCopy.setWeight(weight);
		}
		return weightedCopy;
	}

	public Document getDocumentRepresantive() {
		return this;
	}


}
