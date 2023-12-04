
public class Main {
    public static void main(String[] args) throws Exception {
        Database inMemoryDB = new Database();
        // should return null, because A doesn’t exist in the DB yet
        try {
            inMemoryDB.get("A");
        } catch (Exception e){
            System.out.println("Null: A doesn't exist");
            System.out.println("----------------------");
        }
// should throw an error because a transaction is not in progress
        try {
            inMemoryDB.put("A", 5);
        } catch (Exception e){
            System.out.println("Error: Transaction not in progress");
            System.out.println("------------------------------------");
        }
// starts a new transaction
        inMemoryDB.begin_transaction();
// set’s value of A to 5, but its not committed yet
        inMemoryDB.put("A", 5);
// should return null, because updates to A are not committed yet
        try {
            inMemoryDB.get("A");
        }catch (Exception e){
            System.out.println("Null: Updates to A are not committed");
            System.out.println("---------------------------------------");
        }
// update A’s value to 6 within the transaction
        inMemoryDB.put("A", 6);
// commits the open transaction
        inMemoryDB.commit();
        // should return 6, that was the last value of A to be committed
        System.out.println("Successfully Retrieved Value: " + inMemoryDB.get("A"));
        System.out.println("-------------------------------------------------------");

// throws an error, because there is no open transaction
        try {
            inMemoryDB.commit();
        }catch (Exception e){
            System.out.println("Error: No open transaction");
            System.out.println("----------------------------");
        }

// throws an error because there is no ongoing transaction

        try{
            inMemoryDB.rollback();
        } catch (Exception e){
            System.out.println("Error: No ongoing transaction");
            System.out.println("-------------------------------");
        }



// should return null because B does not exist in the database
        try{
            inMemoryDB.get("B");
        } catch (Exception e){
            System.out.println("Null: B does not exist");
            System.out.println("----------------------");
        }


// starts a new transaction
        inMemoryDB.begin_transaction();

// Set key B’s value to 10 within the transaction
        inMemoryDB.put("B", 10);

// Rollback the transaction - revert any changes made to B
        inMemoryDB.rollback();

// Should return null because changes to B were rolled back
        try{
            inMemoryDB.get("B");
        } catch (Exception e){
            System.out.println("Null: B's changes were rolled back");
            System.out.println("-----------------------------------");
        }

    }
}