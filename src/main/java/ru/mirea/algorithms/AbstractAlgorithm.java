package ru.mirea.algorithms;

import ru.mirea.entity.WireMap;
import ru.mirea.util.enums.TypeOfProcessing;

import java.util.Vector;

public abstract class AbstractAlgorithm {
    protected WireMap wireMap;
    protected Vector<Integer> path;
    protected Integer source;
    protected Integer dest;

    public abstract void makeBestPriceProcessing() throws Exception;

    public abstract void makeBestDurationProcessing() throws Exception;

    public abstract void makeBalancedProcessing() throws Exception;

    public void process(TypeOfProcessing typeOfProcessing, Integer source, Integer dest, WireMap wireMap) throws Exception {
        if (!(source >= 0 && source < wireMap.getMatrixOfPoints().length)) {
            throw new IllegalStateException("Illegal source:\t" + source);
        }
        if (!(dest >= 0 && dest < wireMap.getMatrixOfPoints().length)) {
            throw new IllegalStateException("Illegal dest:\t" + dest);
        }
        this.source = source;
        this.dest = dest;
        this.wireMap = wireMap;
        switch (typeOfProcessing) {
            case BEST_PRICE:
                makeBestPriceProcessing();
                break;
            case BEST_TIME:
                makeBestDurationProcessing();
                break;
            case BALANCED:
                makeBalancedProcessing();
                break;
        }
    }


    public WireMap getWireMap() {
        return wireMap;
    }

    public void setWireMap(WireMap wireMap) {
        this.wireMap = wireMap;
    }

    public Vector<Integer> getPath() {
        return path;
    }

    public void setPath(Vector<Integer> path) {
        this.path = path;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getDest() {
        return dest;
    }

    public void setDest(Integer dest) {
        this.dest = dest;
    }
}
