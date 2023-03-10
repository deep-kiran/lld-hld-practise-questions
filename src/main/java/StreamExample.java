package main.java;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingInt;
import static org.junit.Assert.assertEquals;

public class StreamExample {

//	public static void main(String[] args) {
//
//		List<Integer> myList = new ArrayList<>();
//		for(int i=0; i<100; i++) myList.add(i);
//
//		//sequential stream
//		Stream<Integer> sequentialStream = myList.stream();
//
//		//parallel stream
//		Stream<Integer> parallelStream = myList.parallelStream();
//
//		//using lambda with Stream API, filter example
//		Date now = new Date();
//		Stream<Integer> highNums = parallelStream.filter(p -> p > 90);
//		//using lambda in forEach
//		highNums.forEach(p -> System.out.println("High Nums parallel="+p));
//		Date newNow = new Date();
//		long diff = newNow.getTime() - now.getTime();
//		System.out.println("Time in milli seconds: " + diff + " seconds.");
//		Date now2 = new Date();
//		Stream<Integer> highNumsSeq = sequentialStream.filter(p -> p > 90);
//		highNumsSeq.forEach(p -> System.out.println("High Nums sequential="+p));
//		Date newNow2 = new Date();
//		long diff2 = newNow2.getTime() - now2.getTime();
//		System.out.println("Time in milli seconds: " + diff2 + " seconds.");
//
//	}

	public static class BlogPost {
		String title;
		String author;
		BlogPostType type;
		int likes;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public BlogPostType getType() {
			return type;
		}

		public void setType(BlogPostType type) {
			this.type = type;
		}

		public int getLikes() {
			return likes;
		}

		public void setLikes(int likes) {
			this.likes = likes;
		}
	}

	public static enum BlogPostType {
		NEWS,
		REVIEW,
		GUIDE
	}
	public static class Tuple {
		BlogPostType type;
		String author;

		public Tuple(BlogPostType type, String author) {
			this.type=type;
			this.author = author;
		}

		public BlogPostType getType() {
			return type;
		}

		public void setType(BlogPostType type) {
			this.type = type;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		BlogPost bp = new BlogPost();
		int num = 900_000_000;
		List<BlogPost> posts = Arrays.asList();
		Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream()
				.collect(groupingBy(BlogPost::getType));

		Map<Tuple, List<BlogPost>> postsPerTypeAndAuthor = posts.stream()
				.collect(groupingBy(post -> new Tuple(post.getType(), post.getAuthor())));

		Map<BlogPostType, IntSummaryStatistics> likeStatisticsPerType = posts.stream()
				.collect(groupingBy(BlogPost::getType,
						summarizingInt(BlogPost::getLikes)));


		CompletableFuture<String> completableFuture
				= CompletableFuture.supplyAsync(() -> "Hello")
				.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

		assertEquals("Hello World", completableFuture.get());

	}

}