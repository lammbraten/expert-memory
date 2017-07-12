package de.hsnr.inr.expertMemory;

import java.io.File;

import de.hsnr.inr.expertMemory.cluster.ClusterIndex;
import de.hsnr.inr.expertMemory.cluster.CosineAbleSet;

public class ExpertMemory {
	private static final String DIR_PATH_PAR = "-p";
	private static final String HELP_PAR = "-h";
	private static final String B1 = "-b1";

	private static boolean help = false;
	private static String dir_path;
	private static int b1 = 4;
	
	private String dirPath;
	private File corpus;
	private ClusterIndex index;
	
	public ExpertMemory(String dirPath){
		this.dirPath = dirPath;
		handleFiles();
		setIndex(new ClusterIndex(corpus, b1));
		ApproximativeSearch as = new ApproximativeSearch(index);
		
		while(true){
			as.askForQuery();	
			for(CosineAbleSet cs : as.search())
				System.out.println(cs);
		}

	}
	
	public static void main(String[] args) {
		System.out.println("Expert memory started");
		if(!parseArgs(args))
			throw new IllegalArgumentException("-h for help");	

		if(dir_path == null || dir_path.isEmpty())
			throw new IllegalArgumentException(" -p C:\\pfad\\zum\\Märchenordner");

		
		ExpertMemory em = new ExpertMemory(dir_path);
		

	}
	
	private static boolean parseArgs(String[] args) {
		for(int i = 0; i < (args.length - 1); i=i+2)			
			if(!parseKeyValue(args[i], args[i+1]))
				return false;
		return true;
	}
	
	private static boolean parseKeyValue(String key, String value) {
		if(isHelpSetHelp(key, value))
			return true;
		if(isDPsetDP(key, value))
			return true;
		if(isB1setB1(key, value))
			return true;
		return false;
	}
	
	private static boolean isB1setB1(String key, String value) {
		if(key.equals(B1)){
			b1 = Integer.parseInt(value);
			return true;
		}
		return false;
	}

	private static boolean isHelpSetHelp(String key, String value) {
		if(key.equals(HELP_PAR)){
			help = true;
			return true;
		}
		return false;
	}
	
	private static boolean isDPsetDP(String key, String value) {
		if(key.equals(DIR_PATH_PAR)){
			dir_path = value;
			return true;
		}
		return false;
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
