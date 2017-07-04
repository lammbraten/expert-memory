package de.hsnr.inr.expertMemory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import de.hsnr.inr.expertMemory.cluster.ClusterIndex;
import de.hsnr.inr.expertMemory.cluster.Document;
import de.hsnr.inr.expertMemory.cluster.Term;

public class ApproximativeSearch {
	
	public static final String DEFAULT_SPLIT_CHARS = " ";
	
	private ClusterIndex index;
	private Document query;
	
	public ApproximativeSearch(ClusterIndex index){
		this.index = index;

	}
	
	void askForQuery() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		query = null;

		do {
			System.out.println("Was soll im Märchen vorkommen?");
			try {
				 setQuery(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while(query == null || query.isEmpty());
	}
	
	public void setQuery(Document query) {
		this.query = query;
	}
	
	public void setQuery(String query) {
		setQuery(parseQuery(query));
	}
	
	private static List<String> split(String str){
		return Lists.newArrayList(Splitter.on(CharMatcher.anyOf(DEFAULT_SPLIT_CHARS))
				.omitEmptyStrings()
				.trimResults()
				.split(str));
	}

	public static Document parseQuery(String query) {
		List<String> tokens = split(query);
		Document d = new Document("query", tokens.size());
		for(String token : split(query))
			d.add(new Term(token));
		return d;
	}
	
	//Get top K - nearest neighbors
	/*
	public Set<Document> search(){
		return search(query);
	}

	private Set<Document> search(Document query) {
		return index.cosineScore(query, 10);
	}
	*/
	
	

}
