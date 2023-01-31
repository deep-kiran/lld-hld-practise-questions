package main.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample {

	public static void main(String[] args) {
		
		List<Integer> myList = new ArrayList<>();
		for(int i=0; i<100; i++) myList.add(i);
		
		//sequential stream
		Stream<Integer> sequentialStream = myList.stream();
		
		//parallel stream
		Stream<Integer> parallelStream = myList.parallelStream();
		
		//using lambda with Stream API, filter example
		Date now = new Date();
		Stream<Integer> highNums = parallelStream.filter(p -> p > 90);
		//using lambda in forEach
		highNums.forEach(p -> System.out.println("High Nums parallel="+p));
		Date newNow = new Date();
		long diff = newNow.getTime() - now.getTime();
		System.out.println("Time in milli seconds: " + diff + " seconds.");
		Date now2 = new Date();
		Stream<Integer> highNumsSeq = sequentialStream.filter(p -> p > 90);
		highNumsSeq.forEach(p -> System.out.println("High Nums sequential="+p));
		Date newNow2 = new Date();
		long diff2 = newNow2.getTime() - now2.getTime();
		System.out.println("Time in milli seconds: " + diff2 + " seconds.");

	}

}