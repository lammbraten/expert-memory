package de.hsnr.inr.expertMemory.cluster;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ClusterIndex extends HashSet<Term>{
	
	private static final long serialVersionUID = -196938510438540540L;
	private HashSet<Cluster> clusters;
	private HashSet<Document> documents;
	
	
	public ClusterIndex(File corpus) {
		this.clusters = new HashSet<Cluster>();
		this.documents = new HashSet<Document>();
		
		readCorpus(corpus);
		
		for(Cluster c : clusters)
			System.out.println("Cluster " + c + " in clusters");
		
		buildCluster();

	}


	private void buildCluster() {
		for(Document doc : documents){
			assignToNearestClusterLeader(doc);
		}
	}


	private void assignToNearestClusterLeader(Document doc) {
		Cluster nearestCluster = null;
		double minDistance = Double.MAX_VALUE;
		double actDistance;
		
		for(Cluster c : clusters){
			actDistance = cosineScore(c.getClusterLeader(), doc);
			if(actDistance < minDistance){
				minDistance = actDistance;
				nearestCluster = c;
			}
		}
		if(nearestCluster == null)
			throw new NullPointerException("No Clusters?");
		
		nearestCluster.add(doc);
	}

//	private float calcW_td(Term t, Document d){
//		return (float) (1+ Math.log10(t.getTf_td(d)) * (Math.log((float)getNumberOfDocs()/t.getDf_t())));
//	}
	
	private double cosineScore(Document clusterLeader, Document doc) {
		
		for(Term t : doc)
			break;
		// TODO Auto-generated method stub
		return 0;
	}


	private Set<Integer> pickRandomClusterLeader(int size){
		double sqrt_n = Math.sqrt(size);
		HashSet<Integer> indices = new HashSet<Integer>();
		Random rand = new Random();
		for(int i = 0; i < sqrt_n; i++){
			boolean added = false;
			do{
				added = indices.add(rand.nextInt(size));
			} while(!added);
			
		}
		return indices;
	}

	private void readCorpus(File corpus) {
		Set<Integer> clusterLeaderIndices = pickRandomClusterLeader(corpus.listFiles().length);
		int i = 0;
		
		for(File f : corpus.listFiles()){
			Document doc = null;
			try {
				doc = extractTerms(f);
			} catch (IOException e) {
				System.err.println("Couldn't open file " + f + "\n" + e);
			}
			if(doc != null){
				addAll(doc);
				if(clusterLeaderIndices.contains(i)) //new ClusterLeader
					clusters.add(new Cluster(doc));
			}
			i++;
		}
	}
	
	public void addAll(Set<Term> terms){
		for(Term t : terms)
			add(t);
	}
	
	private Document extractTerms(File f) throws IOException{
		Document doc = new Document(f.getName());
		
		for(String termStr : Tokenizer.tokenize(f))
			doc.add(new Term(termStr));

		documents.add(doc);
		
		return doc;
	}

}
