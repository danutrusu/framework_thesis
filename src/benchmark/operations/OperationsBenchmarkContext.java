package benchmark.operations;

import benchmark.BenchmarkContext;
import benchmark.BenchmarkQuery;

/**
 * Created by danut on 23.03.17.
 */
public class OperationsBenchmarkContext extends BenchmarkContext {

    private static final int REPEAT_QUERIES_ONCE = 1;
    protected final double maxSelectSizePercent;
    protected final int queryNumber;

    protected long dataSize;

    public OperationsBenchmarkContext(double maxSelectSizePercent, long tileSize, int queryNumber, int repeatNumber, String dataDir, int timeout) {
        super(repeatNumber, dataDir, timeout, TYPE_OPERATIONS);
        this.maxSelectSizePercent = maxSelectSizePercent;
        this.tileSize = tileSize;
        this.queryNumber = queryNumber;
        cleanQuery = false;
        updateArrayName();

    }


//    public OperationsBenchmarkContext(String dataDir) {
//        super(REPEAT_QUERIES_ONCE, dataDir, -1, TYPE_OPERATIONS);
//        arrayDimensionality = 2;
//        arraySize = OperationsBenchmarkDataManager.ARRAY_SIZE;
//        arraySizeShort = OperationsBenchmarkDataManager.ARRAY_SIZE_SHORT;
//        cleanQuery = false;
//        updateArrayName();
//    }



    public long getDataSize() {
            return dataSize;
        }

    public long getMaxSelectSize() {
        return (long) (((double) arraySize * maxSelectSizePercent) / 100.0);
    }

    public double getMaxSelectSizePercent() {
        return maxSelectSizePercent;
    }

    public int getQueryNumber() {
    return queryNumber;
}


    public void setDataSize(long dataSize) {
            this.dataSize = dataSize;
        }

    @Override
    public String getBenchmarkSpecificHeader() {
//        return "Query, Data size, Execution time (ms), ";
        String ret = "Query, Query type, Array dimension, Array size, Max select size, ";
        for (int i = 0; i < repeatNumber; i++) {
            ret += "Execution time " + i + " (ms), ";
        }
        return ret;
    }

    @Override
    public String getBenchmarkResultLine(BenchmarkQuery query) {
//            return String.format("\"%s\", %s, ", query.getQueryString(), getDataSize());
        return String.format("\"%s\", %s, %d, %d, %d, ",
                query.getQueryString(), query.getQueryType().toString(),
                query.getDimensionality(), getArraySize(), getMaxSelectSize());
    }

}