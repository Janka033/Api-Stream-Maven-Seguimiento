package repository.impl;

import domain.enums.ToyType;
import domain.models.ToyTypes;
import mapping.dtos.ToyTypeDto;
import mapping.Mapper.ToyTypeMapper;
import repository.ToyTypeRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ToyTypeRepositoryImpl implements ToyTypeRepository {

    private List<ToyTypes> toyTypes;
    public ToyTypeRepositoryImpl() {
        toyTypes = new ArrayList<>();
        toyTypes.add(new ToyTypes(1,"Vegetta", ToyType.MALE,2.70,20));
        toyTypes.add(new ToyTypes(2,"Car", ToyType.MALE,1.70,20));
        toyTypes.add(new ToyTypes(3,"Make-up", ToyType.FEMALE,3.70,20));
        toyTypes.add(new ToyTypes(4,"Water gun", ToyType.UNISEX,3.20,20));
        toyTypes.add(new ToyTypes(5,"Barbie", ToyType.FEMALE,1.50,20));
        toyTypes.add(new ToyTypes(6,"musical piano", ToyType.UNISEX,3.0,20));
    }

    @Override
    public List<ToyTypeDto> getAllToys() {
        return ToyTypeMapper.mapFromDto(toyTypes);
    }
        @Override
        public void addToy(ToyTypes toy) {
            toyTypes.add(toy);
        }

        @Override
        public void reportToysByType() {
            System.out.println("number of toys for type:");
            toyTypes.stream()
                    .collect(Collectors.groupingBy(ToyTypes::getType, Collectors.counting()))
                    .forEach((type, count) -> System.out.println(type + ": " + count));System.out.println();
        }

        @Override
        public int getTotalToys() {
            return toyTypes.size();
        }

        @Override
        public double getTotalValue() {
            return toyTypes.stream()
                    .mapToDouble(toy -> toy.getPrice() * toy.getQuantityInStock())
                    .sum();
        }

        @Override
        public void decreaseStock(long nameId, int quantity) {
            ToyTypes toy = getToyByNameId(nameId);
            if (toy != null) {
                int newQuantity = Math.max(0, toy.getQuantityInStock() - quantity);
                toy.setQuantityInStock(newQuantity);
            }
        }

        @Override
        public void increaseStock(long nameId, int quantity) {
            ToyTypes toy = getToyByNameId(nameId);
            if (toy != null) {
                int newQuantity = toy.getQuantityInStock() + quantity;
                toy.setQuantityInStock(newQuantity);
            }
        }

        @Override
        public ToyType getMostCommonToyType() {
            return toyTypes.stream()
                    .collect(Collectors.groupingBy(ToyTypes::getType, Collectors.counting()))
                    .entrySet().stream()
                    .max(Comparator.comparingLong(java.util.Map.Entry::getValue))
                    .map(java.util.Map.Entry::getKey)
                    .orElse(null);
        }

        @Override
        public ToyType getLeastCommonToyType() {
            return toyTypes.stream()
                    .collect(Collectors.groupingBy(ToyTypes::getType, Collectors.counting()))
                    .entrySet().stream()
                    .min(Comparator.comparingLong(java.util.Map.Entry::getValue))
                    .map(java.util.Map.Entry::getKey)
                    .orElse(null);
        }

        @Override
        public List<ToyTypeDto> getToysWithValueGreaterThan(double minValue) {
            return toyTypes.stream()
                    .filter(toy -> toy.getPrice() > minValue)
                    .map(ToyTypeMapper::mapFrom)
                    .collect(Collectors.toList());
        }

        @Override
        public List<ToyTypeDto> getToysSortedByStock() {
            return toyTypes.stream()
                    .sorted(Comparator.comparingInt(ToyTypes::getQuantityInStock))
                    .map(ToyTypeMapper::mapFrom)
                    .collect(Collectors.toList());
        }


        private ToyTypes getToyByNameId(long nameId) {
            return toyTypes.stream()
                    .filter(toy -> toy.getNameId() == nameId)
                    .findFirst()
                    .orElse(null);
        }
    }
