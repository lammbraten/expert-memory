package de.hsnr.inr.expertMemory;

import java.io.File;

import de.hsnr.inr.expertMemory.cluster.ClusterIndex;

public class ExpertMemory {

	private String dirPath;
	private File corpus;
	private ClusterIndex index;
	
	public ExpertMemory(String dirPath){
		this.dirPath = dirPath;
		handleFiles();
		setIndex(new ClusterIndex(corpus));
	}
	
	public static void main(String[] args) {
		System.out.println("Expert memory started");

	}
	
	private void handleFiles() {
		setCorpus(new File(dirPath));
		checkIfDir();
	}
	
	private void checkIfDir() {
		if(!getCorpus().isDirectory())
			throw new IllegalArgumentException("Source file has to be dir!");
	}

	public File getCorpus() {
		return corpus;
	}

	public void setCorpus(File corpus) {
		this.corpus = corpus;
	}
	
	public ClusterIndex getIndex() {
		return index;
	}

	public void setIndex(ClusterIndex index) {
		this.index = index;
	}
}
