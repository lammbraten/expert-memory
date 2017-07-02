package de.hsnr.inr.expertMemory.cluster;

import java.util.Comparator;

public class WeightedClusterComparator implements Comparator<WeightedCluster> {

	public int compare(WeightedCluster arg0, WeightedCluster arg1) {
		return ((Float)arg1.getWeight()).compareTo(arg0.getWeight());
	}

}
