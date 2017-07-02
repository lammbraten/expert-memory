package de.hsnr.inr.expertMemory.cluster;

public class Term {

	private String value;

	
	public Term(String value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		return value;
	}
	
	@Override
	public boolean equals(Object o){	
		if(o instanceof Term)
			return ((Term) o).value.equals(this.value);
		
		return false;
	}
	
	@Override 
	public int hashCode(){
		return value.hashCode();
	}
}
