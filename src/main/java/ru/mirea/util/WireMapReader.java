package ru.mirea.util;

import ru.mirea.entity.Point;
import ru.mirea.entity.WireMap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WireMapReader {
    public static WireMap readWireMap(String pathToFile) throws IOException {
        Point[][] res = null;
        int numberOfPoints = 0;
        File file;
        Scanner scanner;
        //генерируем файл
        file = WireMapReader.getFileFromStr(pathToFile);
        //создаем сканнер для чтения файлов
        scanner = new Scanner(file);

        //получаем количество точек из первого числа в файле
        numberOfPoints = scanner.nextInt();

        if (numberOfPoints < 1) {
            throw new IllegalStateException("Illegal number of points:\t" + numberOfPoints);
        }
        //опредлеяем размер карты точек
        res = new Point[numberOfPoints][numberOfPoints];

        //считываем матрицу
        for (int i = 0; i < numberOfPoints; i++) {
            if (!scanner.hasNextLine())
                throw new IOException("Illegal number of lines in matrix: error occurred on line number:" + ++i);
            String str = scanner.nextLine();
            String[] points = str.split("\t");
            if (points.length != numberOfPoints) {
                throw new IOException("Illegal number of rows in matrix: " + points.length);
            }
            for (int j = 0; j < numberOfPoints; j++) {
                String pointStr = points[numberOfPoints];
                Point point = Point.getInstanceFromString(pointStr);
                res[i][j] = point;
            }
        }

        return new WireMap(res);
    }

    private static File getFileFromStr(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Illegal path to file: " + path);
        }
        return file;
    }
}
