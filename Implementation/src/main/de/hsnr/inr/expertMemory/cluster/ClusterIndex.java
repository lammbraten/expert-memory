package de.hsnr.inr.expertMemory.cluster;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
		

		buildCluster();
		
		for(Cluster c : clusters)
			System.out.println("Cluster " + c + " in clusters");
		
	}


	private void buildCluster() {
		for(Document doc : documents){
			assignToNearestClusterLeaders(doc);
		}
	}


	private void assignToNearestClusterLeaders(Document doc) {
		Set<Cluster> nearestClusters = new HashSet<Cluster>();

		nearestClusters.addAll(cosineScore(doc, 1));
		for(Cluster c : clusters){
			if(nearestClusters.contains(c)){
				//System.out.println("Nearest-Cluster for " + doc + ": " + c);
				c.add(doc);				
			}
		}
	}

	/**
	 * Only for Cluster-Leaders!
	 * @param t
	 * @param d
	 * @return
	 */
	private float calcW_td(Term t, Document d){
		return (float) (1+ Math.log10(d.count(t)) * (Math.log((float)clusters.size()/(getDf_t(t)+1))));
	}
	
	/**
	 * Only for Cluster Leaders!
	 * @param t
	 * @return
	 */
	private int getDf_t(Term t) {
		int df_t = 0;
		for(Cluster c : clusters)
			if(c.contains(t))
				df_t++;
		return df_t;
	}
	
	/**
	 * TODO: This can be precalculated once.
	 * @param t
	 * @return
	 */
	private List<Cluster> getClusterPostingList(Term t){
		LinkedList<Cluster> postings = new LinkedList<Cluster>();
		
		for(Cluster c : clusters)
			if(c.getClusterLeader().contains(t))
				postings.add(c);
		
		return postings;
	}


	private List<Cluster> cosineScore(Document q, int k) {
		HashMap<Cluster, Float> scores = new HashMap<Cluster, Float>();
		LinkedList<Cluster> returnVal = new LinkedList<Cluster>();
		PriorityQueue<WeightedCluster> pq = new PriorityQueue<WeightedCluster> (new WeightedClusterComparator());
		
		for(Cluster c : clusters)
			scores.put(c, 0f);
		
		for(Term t : q){
			float w_tq = calcW_td(t, q);
			
			for(Cluster d : getClusterPostingList(t)){
				float w_td = calcW_td(t, d.getClusterLeader());
				float n_val = scores.get(d) + (w_tq * w_td);
				scores.put(d, n_val);
			}	
		}
		
		for(Cluster c : scores.keySet()){
			//scores.put(c,((float) scores.get(c)/ c.getClusterLeader().length()));
			if(scores.get(c) <= 0)
				continue;
			float val = ((float) scores.get(c)/ c.getClusterLeader().length());
			pq.add(new WeightedCluster(c, ((float) scores.get(c)/ c.getClusterLeader().length())));
		}

		for(int i = 0; i < k; i++)
			returnVal.add(pq.poll());
		
		return returnVal;
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
		List<String> tokens = Tokenizer.tokenize(f);
		Document doc = new Document(f.getName(), tokens.size());
		
		for(String termStr : tokens)
			doc.add(new Term(termStr));

		documents.add(doc);
		
		return doc;
	}

}
