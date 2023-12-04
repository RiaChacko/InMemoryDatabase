interface InMemoryDB{
    int get(String key) throws Exception;
    void put(String key, int val) throws Exception;
    void commit() throws Exception;
    void rollback() throws Exception;
    void begin_transaction();
}