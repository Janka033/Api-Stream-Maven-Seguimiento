import domain.enums.ToyType;
import domain.models.ToyTypes;
import mapping.dtos.ToyTypeDto;
import mapping.Mapper.ToyTypeMapper;
import repository.ToyTypeRepository;
import repository.impl.ToyTypeRepositoryImpl;

import java.util.List;

public class Main {
        public static void main(String[] args) {
            ToyTypeRepository toyRepository = new ToyTypeRepositoryImpl();

            List<ToyTypeDto> allToys = toyRepository.getAllToys();
            System.out.println("All the toys");
            allToys.forEach(System.out::println );
            System.out.println();

            ToyTypes newToy = ToyTypeMapper.mapFrom(new ToyTypeDto(7, "Remote Control Car", ToyType.MALE, 25.99, 10));
            toyRepository.addToy(newToy);
            System.out.println("newToy = " + newToy);
            System.out.println();

            toyRepository.reportToysByType();

            int totalToys = toyRepository.getTotalToys();
            System.out.println("total number of toys: " + totalToys);
            System.out.println();

            double totalValue = toyRepository.getTotalValue();
            System.out.println("Total values of toys: " + totalValue);
            System.out.println();

            toyRepository.decreaseStock(1, 5);

            toyRepository.increaseStock(2, 3);

            ToyType mostCommonType = toyRepository.getMostCommonToyType();
            System.out.println("best selling toys: " + mostCommonType);
            System.out.println();

            ToyType leastCommonType = toyRepository.getLeastCommonToyType();
            System.out.println("low selling toys: " + leastCommonType);
            System.out.println();

            double minValue = 2.0;
            List<ToyTypeDto> toysWithValueGreaterThanMin = toyRepository.getToysWithValueGreaterThan(minValue);
            System.out.println("toys with more value of " + minValue + ":");
            System.out.println();
            toysWithValueGreaterThanMin.forEach(System.out::println);
            System.out.println();

            List<ToyTypeDto> sortedToysByStock = toyRepository.getToysSortedByStock();
            System.out.println("Toys sorted by stock quantity:");
            System.out.println();
            sortedToysByStock.forEach(System.out::println);
        }
    }

