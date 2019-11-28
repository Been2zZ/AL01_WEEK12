package DijkstraAlgorithm;

import java.io.*;
import java.util.*;

public class TestDijkstraAlgorithm {
    public static void main(String[] args) throws FileNotFoundException {
        DijkstraAlgorithm test = new DijkstraAlgorithm();

        File file = new File("data12.txt");

        test.insert(file);
    }
}
