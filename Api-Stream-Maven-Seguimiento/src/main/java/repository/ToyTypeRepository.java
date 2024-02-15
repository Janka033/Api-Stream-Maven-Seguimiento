package repository;

import domain.enums.ToyType;
import domain.models.ToyTypes;
import mapping.dtos.ToyTypeDto;

import java.util.List;

public interface ToyTypeRepository {
    List<ToyTypeDto> getAllToys();
    void addToy(ToyTypes toy);

    void reportToysByType();

    int getTotalToys();

    double getTotalValue();

    void decreaseStock(long nameId, int quantity);

    void increaseStock(long nameId, int quantity);

    ToyType getMostCommonToyType();

    ToyType getLeastCommonToyType();

    List<ToyTypeDto> getToysWithValueGreaterThan(double minValue);

    List<ToyTypeDto> getToysSortedByStock();
}
