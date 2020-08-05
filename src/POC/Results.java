package POC;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Results {

	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
		new Transaction(brian, 2011, 300),
		new Transaction(raoul, 2012, 1000),
		new Transaction(raoul, 2011, 400),
		new Transaction(mario, 2012, 710),
		new Transaction(mario, 2012, 700),
		new Transaction(alan, 2012, 950)
		);
			
		//Find all transactions in the year 2011 and sort them by value (small to high).
		transactions.stream()
					.filter(t ->(2012 == t.getYear()))
					.sorted(comparing(Transaction::getValue))
					.forEach(System.out::println);
		System.out.println("------------------------------------");
		//What are all the unique cities where the traders work?
		transactions.stream()
					.map(t -> t.getTrader().getCity())
					.distinct()
					.forEach(System.out::println);
		System.out.println("------------------------------------");
		
		//Find all traders from Cambridge and sort them by name.
		transactions.stream()
					.map(Transaction::getTrader)
					.filter(t -> t.getCity().equals("Cambridge"))
					.distinct()
					.sorted(comparing(Trader::getName))
					.forEach(System.out::println);
		System.out.println("------------------------------------");
					
		//Return a string of all traders’ names sorted alphabetically.
		transactions.stream()
					.map(Transaction::getTrader)
					.distinct()
					.sorted(comparing(Trader::getName))
					.forEach(System.out::println);
		System.out.println("------------------------------------");
		
		//Are any traders based in Milan?
		/*
		 * transactions.stream() .map(Transaction::getTrader) .filter(t ->
		 * t.getCity().equals("Milan")) .distinct()
		 * //.sorted(comparing(Trader::getName)) .forEach(System.out::println);
		 * System.out.println("------------------------------------");
		 */
		
		boolean isany = transactions.stream()
					.map(Transaction::getTrader)
					.anyMatch(t -> t.getCity().equals("Milan"));
		System.out.println("isany: " +isany);
		System.out.println("------------------------------------");
		
		//Print the values of all transactions from the traders living in Cambridge.
		transactions.stream()
					.filter(t -> t.getTrader().getCity().equals("Cambridge"))
					.map(Transaction::getValue)
					.forEach(System.out::println);
		System.out.println("------------------------------------");
		
		//What’s the highest value of all the transactions?
		int val1 = transactions.stream()
					.map(Transaction::getValue)
					.max(Integer::compare)
					.get();
		System.out.println("val1: " +val1);	
		
		/*
		 * int highestValue = transactions.stream() .map(Transaction::getValue)
		 * .reduce(0, Integer::max); System.out.println("highestValue: "+highestValue);
		 */
		System.out.println("------------------------------------");
		
		//Find the transaction with the smallest value.
		int val2 = transactions.stream()
				.map(Transaction::getValue)
				.min(Integer::compare)
				.get();
		System.out.println("val2: " +val2);			
		System.out.println("------------------------------------");
		
		Optional<Transaction> smallestTransaction = transactions.stream()
		        												.min(comparing(Transaction::getValue));
		System.out.println(smallestTransaction.map(String::valueOf).orElse("No transactions found"));
	}

}
