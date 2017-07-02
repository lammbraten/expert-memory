package de.hsnr.inr.expertMemory.cluster;

import java.util.HashSet;

public class Cluster extends HashSet<Document> {


	private static final long serialVersionUID = -3459735767407752393L;
	private Document clusterLeader;

	public Cluster(Document doc) {
		setClusterLeader(doc);
	}
	
	public Cluster(Cluster c) {
		this.addAll(c);
		this.setClusterLeader(c.getClusterLeader());
	}

	@Override
	public String toString(){
		return clusterLeader.getName() + "(size): " + this.size();
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
	
	
	
}
