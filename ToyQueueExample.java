import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

class Toy {
    private int id;
    private String name;
    private int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }
    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }
}

public class ToyQueueExample {
    public static void main(String[] args) {
        // Массивы для хранения данных
        int[] ids = {1, 2, 3};
        String[] names = {"конструктор", "робот", "кукла"};
        int[] frequencies = {2, 2, 6};

        // Создание коллекции PriorityQueue для хранения игрушек
        PriorityQueue<Toy> toyQueue = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.getFrequency(), t1.getFrequency()));

        // Заполнение коллекции игрушками
        for (int i = 0; i < ids.length; i++) {
            Toy toy = new Toy(ids[i], names[i], frequencies[i]);
            toyQueue.offer(toy);
        }

        // Создание и запись результатов в файл
        try (FileWriter fileWriter = new FileWriter("output.txt")) {
            for (int i = 0; i < 10; i++) {
                // Вызов метода get с учетом весов
                String result = getRandomToy(toyQueue);
                fileWriter.write(result + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomToy(PriorityQueue<Toy> toyQueue) {
        Random random = new Random();
        int totalFrequency = toyQueue.stream().mapToInt(Toy::getFrequency).sum();
        int randomValue = random.nextInt(totalFrequency);

        int cumulativeFrequency = 0;
        for (Toy toy : toyQueue) {
            cumulativeFrequency += toy.getFrequency();
            if (randomValue < cumulativeFrequency) {
                return String.valueOf(toy.getId());
            }
        }

        // Вернуть значение по умолчанию (в случае какой-то ошибки)
        return "1";
    }
}
