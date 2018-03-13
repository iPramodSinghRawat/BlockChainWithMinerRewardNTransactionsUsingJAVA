import java.util.ArrayList;

class BlockChain{
  public int difficulty = 0;
  public ArrayList<Block> chain;// = new ArrayList<Block>();
  public ArrayList<Transaction> pendingTransactions;// = new ArrayList<Block>();
  public float miningReward;
  //Block transactionBlock;

  public BlockChain(){//int difficulty
    this.difficulty = difficulty;
    this.chain = new ArrayList<Block>();
    this.chain.add(createGenesisBlock());

    this.pendingTransactions = new ArrayList<Transaction>();
    this.miningReward = 10;
    this.difficulty = 2;

    //System.out.println("Genesis Hash: "+ createGenesisBlock().hash);
  }

  public Block createGenesisBlock(){
    ArrayList<Transaction> gTransactions = new ArrayList<Transaction>();

    Transaction gTransaction = new Transaction("gRoot","gRoot",0);
    gTransactions.add(gTransaction);

    System.out.println("Genesis Block Created");
    return new Block(gTransactions);
  }

  public Block getLastBlock(){
    return this.chain.get(this.chain.size() - 1);//[len(self.chain) - 1]
  }

  public void createTransaction(Transaction transactionData){
    this.pendingTransactions.add(transactionData);
  }

  public void minePendingTransactions(String miningRewardAddress){
    //System.out.println("minePendingTransactions");

    Block cTransactionBlock = new Block(this.pendingTransactions);
      cTransactionBlock.previousHash = this.getLastBlock().hash;
      cTransactionBlock.mineBlock(this.difficulty);

    this.chain.add(cTransactionBlock);

    System.out.println("Block successfully mined!");

    //System.out.println("Block successfully mined! Size: "+ pendingTransactions.size());

    /*
    System.out.println("transactionBlock Flag: "+ cTransactionBlock.flag);
    System.out.println("transactionBlock Hash: "+ cTransactionBlock.hash);
    System.out.println("transactionBlock previousHash: "+ cTransactionBlock.previousHash);
    System.out.println("transactionBlock (transactions_data): " + cTransactionBlock.transactions_data);
    System.out.println("transactionBlock (timestamp): " + cTransactionBlock.timeStamp);
    System.out.println("Before array List: "+this.pendingTransactions.size());
    */

    this.pendingTransactions=new ArrayList<Transaction>();
    //System.out.println("after clear array List: "+this.pendingTransactions.size());
    this.pendingTransactions.add(new Transaction("gRoot", miningRewardAddress, this.miningReward));// = [];
    //System.out.println("after readding array List: "+this.pendingTransactions.size());

  }

  public Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[this.difficulty]).replace('\0', '0');

    //System.out.println("Chain size: "+this.chain.size());
    //System.out.println("2transactionBlock (transactions_data): " + this.chain.get(this.chain.size()-1).transactions_data);
		//loop through blockchain to check hashes:
		for(int i=1; i < this.chain.size(); i++) {
			currentBlock = this.chain.get(i);
			previousBlock = this.chain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
        System.out.println("Current Hashes not equal");
        /*
        System.out.println("Current Hashes not equal at: "+i);
        System.out.println("currentBlock Flag: "+ currentBlock.flag);
        System.out.println("currentBlock Hash: "+ currentBlock.hash);
        System.out.println("currentBlock previousHash: "+ currentBlock.previousHash);
        System.out.println("currentBlock (transactions_data): " + currentBlock.transactions_data);
        System.out.println("currentBlock (timestamp): " + currentBlock.timeStamp);
        System.out.println("Current Hashes 2(calculateHash): " + currentBlock.calculateHash());
        System.out.println("previousBlock (transactions_data): " + previousBlock.transactions_data);
        //System.out.println("Current Hashes 2(calculateHash): " + currentBlock.calculateHash());
        */
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring(0, this.difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}

  public float getBalanceOfAddress(String address){
    float balance = 0;

      for(int i=0;i<(this.chain.size());i++){
        ArrayList<Transaction> blockTransactionsData = this.chain.get(i).transactions_data;
        for(int j=0;j<(blockTransactionsData.size());j++){
              Transaction transactionData = blockTransactionsData.get(j);
/*
              System.out.println("transactionData: "+transactionData);
              System.out.println("transactionData.receiver: "+transactionData.receiver);

              System.out.println("transactionData.sender: "+transactionData.sender);

              System.out.println("transactionData.amount: "+transactionData.amount);

              System.out.println("address: "+address);

              float amount = transactionData.amount;

              String sender = transactionData.sender;
              System.out.println("sender: "+sender);
*/
              //String receiver = transactionData.receiver;
              if(transactionData.receiver.equals(address)){
                balance = balance + transactionData.amount;
              }
              if(transactionData.sender.equals(address)){
                balance =balance - transactionData.amount;
              }

        }
      }

    return balance;
  }

  /*
  def get_balance_of_address(self, address):
        balance = 0

        for i in range(len(self.chain)):

            block_transactions_data = self.chain[i].transactions_data

            for j in range(len(block_transactions_data)):
                transaction_data = block_transactions_data[j]

                if(transaction_data.receiver == address):
                    balance += transaction_data.amount

                if(transaction_data.sender == address):
                    balance -= transaction_data.amount

        return balance
  */

}
