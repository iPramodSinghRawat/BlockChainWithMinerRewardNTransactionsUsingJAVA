import java.util.Date;

class Transaction{
  public String sender;
  public String receiver;
  public float amount = 0;
  private long timeStamp;

  //Block Constructor.
  public Transaction() {
    this.sender = null;
    this.receiver = null;
    this.amount = 0;
    this.timeStamp = 0;
    //this.hash = calculateHash();
  }

  //Block Constructor.
	public Transaction(String sender,String receiver, float amount) {
		this.sender = sender;
		this.receiver = receiver;
    this.amount = amount;
		this.timeStamp = new Date().getTime();
    //this.hash = calculateHash();
	}

  @Override
  public String toString() {
      //return "Transaction: [{sender:" + sender + ", receiver:" + receiver + ", amount:" + amount + ", timeStamp:" + timeStamp + "}]";
      return "{'sender':'" + sender + "', 'receiver':'" + receiver + "', 'amount':" + amount + ", timeStamp:" + timeStamp + "}";
  }

}
