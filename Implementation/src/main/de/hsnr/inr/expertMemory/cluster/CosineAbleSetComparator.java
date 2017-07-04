package de.hsnr.inr.expertMemory.cluster;

import java.util.Comparator;

public class CosineAbleSetComparator implements Comparator<CosineAbleSet> {

	public int compare(CosineAbleSet arg0, CosineAbleSet arg1) {
		return ((Float)arg1.getWeight()).compareTo(arg0.getWeight());
	}
}
