import java.util.Date;
import java.util.ArrayList;

class Block{
  public String previousHash="0";
  public String hash;
  public ArrayList<Transaction> transactions_data;// = new ArrayList<Block>();
  //private Transaction transactions_data;
  public int flag;
  public long timeStamp; //as number of milliseconds since 1/1/1970.

  //Block Constructor.

	public Block(ArrayList<Transaction> transactions_data) {
		this.transactions_data = transactions_data;
    this.previousHash=null;
    this.flag = 0;
		this.timeStamp = new Date().getTime();
    this.hash = this.calculateHash();
    //System.out.println("This Block Hash: "+ this.hash);
	}

  public String calculateHash() {
    String json2String = "";
    //this.transactions_data
    //System.out.println("transactions_data size: "+ this.transactions_data.size());
    if(transactions_data != null){
  		//TransactionList2JSON transactionList2JSON = new TransactionList2JSON(this.transactions_data);
      //json2String = transactionList2JSON.list2JSON();
      json2String = this.transactions_data.toString();
    }else{
      json2String="null";
    }

    //System.out.println("This Pre Hash: "+ this.previousHash);
  	String calculatedhash = StringUtil.applySha256(
  			this.previousHash +
  			Long.toString(timeStamp) +
				Integer.toString(this.flag) +
  			json2String
  			);
    //System.out.println("this.flag: "+this.flag+" for json2String:"+json2String+"\n hash:"+calculatedhash);
  	return calculatedhash;
  }

  public void mineBlock(int difficulty){
    String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
		while(!this.hash.substring( 0, difficulty).equals(target)) {
			this.flag ++;
			this.hash = calculateHash();
		}
		//System.out.println("Block Mined!!! : " + hash);
  }
}
