package de.hsnr.inr.expertMemory.cluster;

import java.util.HashSet;

public class Cluster extends HashSet<CosineAbleSet> implements CosineAbleSet {

	private static final long serialVersionUID = -3459735767407752393L;
	private Document clusterLeader;
	private float weight;

	public Cluster(Document doc) {
		setClusterLeader(doc);
		setWeight(-1f);
	}
	
	public Cluster(Cluster c) {
		this.addAll(c);
		setWeight(c.getWeight());
		this.setClusterLeader(c.getClusterLeader());
	}
	
	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	public String toString(){
		return clusterLeader.getName() + ".size(): " + size();
	}
	
	@Override
	public boolean equals(Object o){	
		if(o instanceof Cluster)
			return ((Cluster) o).getClusterLeader().equals(this.getClusterLeader());
		return false;
	}
	
	@Override 
	public int hashCode(){
		return clusterLeader.hashCode();
	}

	public Document getClusterLeader() {
		return clusterLeader;
	}

	public void setClusterLeader(Document clusterLeader) {
		this.clusterLeader = clusterLeader;
	}


	public CosineAbleSet create(CosineAbleSet toCopy, float weight) {
		CosineAbleSet weightedCopy = null;
		if(toCopy instanceof Cluster){
			weightedCopy = new Cluster((Cluster) toCopy);
			weightedCopy.setWeight(weight);
		}
		return weightedCopy;
	}

	public Document getDocumentRepresantive() {
		return this.getClusterLeader();
	}
}
