import java.util.HashMap;

public class Database implements InMemoryDB{
    int key;
    int val;
    boolean newTransaction = false;
    boolean startOver = false;
    boolean endTransaction = false;

    int numOfTransactions = 0;
    HashMap<String, Integer> dBUncommitted = new HashMap<>();
    HashMap<String, Integer> dB = new HashMap<>();

    @Override
    public int get(String key) throws Exception {
        if(endTransaction){

            return dB.get(key);
        }
        throw new Exception();
    }

    @Override
    public void put(String key, int val) throws Exception {
        if(!dBUncommitted.containsKey(key) && newTransaction) {
            dBUncommitted.put(key, val);
            System.out.println("Successfully Added Value");
            System.out.println("--------------------------");
        } else if (dBUncommitted.containsKey(key) && newTransaction) {
            dBUncommitted.replace(key,val);
            System.out.println("Successfully Updated Value");
            System.out.println("---------------------------");
        } else {
            throw new Exception();
        }
    }

    @Override
    public void commit() throws Exception {
        if(endTransaction){
            throw new Exception();
        }
        System.out.println("Committed Change");
        System.out.println("--------------------");
        endTransaction = true;
        startOver = false;
        newTransaction = false;
        numOfTransactions = 0;
        dB.putAll(dBUncommitted);
        dBUncommitted.clear();

    }

    @Override
    public void rollback() throws Exception {
        if(endTransaction){
            throw new Exception();
        }
        System.out.println("Rolled Back Change");
        System.out.println("----------------------");
        startOver = true;
        numOfTransactions = 0;
        dBUncommitted.clear();
    }

    @Override
    public void begin_transaction() {
        if(numOfTransactions < 1){
            newTransaction = true;
            numOfTransactions++;
            System.out.println("Began Transaction");
            System.out.println("----------------------");
        }
        endTransaction = false;

    }
}
