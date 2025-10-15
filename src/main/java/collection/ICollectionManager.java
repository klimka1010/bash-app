package collection;

import data.City;
import data.Government;

import java.io.File;

public interface ICollectionManager {
    String info();
    void show();
    int add(City city);
    void updateId(City city, Long id);
    String removeById(Long id);
    void clear();
    void executeScript(File filename);
    void removeFirst();
    void removeGreater(Float area);
    void groupCountingByClimate();
    void filterThanGovernment(Government government);
    void printAscending();

}
