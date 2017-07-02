package de.hsnr.inr.expertMemory.cluster;

public class WeightedCluster extends Cluster {

	private static final long serialVersionUID = 8246591937462104958L;
	private float weight;
	
	public WeightedCluster(Cluster c, float weight) {
		super(c);
		this.setWeight(weight);
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	public String toString(){
		return super.toString() + ": " + getWeight();
	}
	
}
