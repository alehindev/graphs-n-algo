package ru.mirea.service;

import ru.mirea.entity.WireMap;
import ru.mirea.util.WireMapReader;
import ru.mirea.util.enums.TypeOfProcessing;

import java.io.IOException;

public class ProcessorService {

    public static void process(String pathToFile, TypeOfProcessing type) throws IOException {
        WireMap matrix = WireMapReader.readWireMap(pathToFile);


        switch (type) {
            case BEST_TIME:
                break;
            case BEST_PRICE:
                break;
            case BALANCED:
                break;
        }


    }

}
