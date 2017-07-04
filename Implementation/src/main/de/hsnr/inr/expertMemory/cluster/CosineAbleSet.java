package de.hsnr.inr.expertMemory.cluster;

public interface CosineAbleSet  {

	float getWeight();
	void setWeight(float w);
	CosineAbleSet create(CosineAbleSet toCopy, float weight);
	Document getDocumentRepresantive();
	boolean contains(Object o);

	
}
