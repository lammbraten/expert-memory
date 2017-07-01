package de.hsnr.inr.expertMemory.cluster;

import java.util.HashSet;

/**
 * Change this in HashMap?
 * @author lammbraten
 *
 */
public class Document extends HashSet<Term>{
	
	private static final long serialVersionUID = 2340542788407482238L;
	private String name;

	public Document(String name){
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
