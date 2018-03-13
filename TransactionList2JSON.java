/*
no need of this file used for testing
*/
import java.util.Date;
import java.util.ArrayList;

class TransactionList2JSON{

  ArrayList<Transaction> transactionsArrayList;

	public TransactionList2JSON(ArrayList<Transaction> transactionsArrayList) {
    this.transactionsArrayList = transactionsArrayList;
	}

  public String list2JSON(){
    String list2JsonString = "{Transactions:[";

    for (int i = 0; i < this.transactionsArrayList.size(); i++) {
      list2JsonString = list2JsonString +" "+ this.transactionsArrayList.get(i).toString()+",";
    }

    //list2JsonString = list2JsonString.substring(0, list2JsonString.length() - 1);

    if (list2JsonString.endsWith(",")) {
      list2JsonString = list2JsonString.substring(0, list2JsonString.length() - 1);
    }

    list2JsonString = list2JsonString + "]}";

    return list2JsonString;
  }

}
