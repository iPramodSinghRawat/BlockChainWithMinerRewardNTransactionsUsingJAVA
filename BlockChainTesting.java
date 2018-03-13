/*
#
# simple BlockChain working with Proof of Work
# idea and logic taken from below link
# https://www.youtube.com/watch?v=HneatE69814
# to understand more follow the link
#
*/

import java.util.ArrayList;

public class BlockChainTesting{

	//public ArrayList<Transaction> transactions_data;// =

	public static void main(String[] args) {

		//public static ArrayList<Transaction>
		//ArrayList<Transaction> transactions_data = new ArrayList<Transaction>();

		BlockChain block_chain_obj = new BlockChain();//2

		Transaction transaction_g =  new Transaction("gRoot","a",120);// initial transfer to a from system root
		Transaction transaction_1 =  new Transaction("a","b",100); // remain 20
		Transaction transaction_2 =  new Transaction("b","c",60);// remain 40
		Transaction transaction_3 =  new Transaction("c","d",30);// remain 30
		Transaction transaction_4 =  new Transaction("d","a",20);// remain 10 a now will have 40

		block_chain_obj.createTransaction(transaction_g);
		block_chain_obj.createTransaction(transaction_1);
		block_chain_obj.createTransaction(transaction_2);

		System.out.println("\n Starting the Miner...");

		block_chain_obj.minePendingTransactions("miner_a");

		System.out.println("\n miner_a Balance : "+block_chain_obj.getBalanceOfAddress("miner_a"));

		System.out.println("\nBlockchain valid? :"+block_chain_obj.isChainValid());

		System.out.println("\n A Balance : "+block_chain_obj.getBalanceOfAddress("a"));

		System.out.println("\n Starting the Miner Again ...");
		block_chain_obj.minePendingTransactions("miner_a");
		System.out.println("\n miner_a Balance : "+block_chain_obj.getBalanceOfAddress("miner_a"));

		block_chain_obj.createTransaction(transaction_3);
		block_chain_obj.createTransaction(transaction_4);

		System.out.println("\n Starting the Miner Again...");

		block_chain_obj.minePendingTransactions("miner_a");

		System.out.println("\n miner_a Balance : "+block_chain_obj.getBalanceOfAddress("miner_a"));
		System.out.println("\n A Balance : "+block_chain_obj.getBalanceOfAddress("a"));
		System.out.println("\n B Balance : "+block_chain_obj.getBalanceOfAddress("b"));
		System.out.println("\n C Balance : "+block_chain_obj.getBalanceOfAddress("c"));
		System.out.println("\n D Balance : "+block_chain_obj.getBalanceOfAddress("d"));

		System.out.println("\nBlockchain valid? :"+block_chain_obj.isChainValid());

	}
}
