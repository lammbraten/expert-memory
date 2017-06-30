package de.hsnr.inr.expertMemory.cluster;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ClusterIndex extends HashSet<Term>{
	
	private static final long serialVersionUID = -196938510438540540L;
	private HashSet<Cluster> clusters;
	private HashSet<Document> documents;
	
	
	public ClusterIndex(File corpus) {
		this.clusters = new HashSet<Cluster>();
		this.documents = new HashSet<Document>();
		
		for(File f : corpus.listFiles()){
			try {
				addAll(extractTerms(f));
			} catch (IOException e) {
				System.err.println("Couldn't open file " + f + "\n" + e);
			}
		}

		//for(Term t : this)
		//	System.out.println(t);
		
		for(Cluster c : clusters)
			System.out.println("Cluster " + c + "in clusters");

	}
	


	public void addAll(Set<Term> terms){
		for(Term t : terms)
			add(t);
	}
	
	private Set<Term> extractTerms(File f) throws IOException{
		Document doc = new Document(f.getName());
		
		for(String termStr : Tokenizer.tokenize(f))
			doc.add(new Term(termStr));

		documents.add(doc);
		
		return doc;
	}

}
