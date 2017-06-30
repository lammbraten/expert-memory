package de.hsnr.inr.expertMemory.cluster;

import java.io.File;
import java.util.HashSet;

public class ClusterIndex {
	


	private HashSet<Cluster> clusters;
	
	
	public ClusterIndex(File corpus) {
		this.clusters = new HashSet<Cluster>();
		
		for(Cluster c : clusters)
			System.out.println("Cluster " + c + "in clusters");

	}

}
