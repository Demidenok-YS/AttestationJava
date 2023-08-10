import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class ToyStoreRaffle {
    public static void main(String[] args) {

        ToyStoreRaffle raffle = new ToyStoreRaffle();

        raffle.addToy(1, "ToyA", 5);

        raffle.addToy(2, "ToyB", 3);

        raffle.addToy(3, "ToyC", 7);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"))) {

            for (int i = 0; i < 10; i++) {

                Toy winner = raffle.runRaffle();

                if (winner != null) {

                    writer.write("Розыгрыш " + (i + 1) + " выигрыш: " + winner.getName() + "\n");

                }

            }

            System.out.println("Розыгрыш завершен. Результаты сохранены в файл 'results.txt'.");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private Queue<Toy> toyQueue;

    public ToyStoreRaffle() {

        toyQueue = new PriorityQueue<>();

    }

    public void addToy(int id, String name, int frequency) {

        Toy toy = new Toy(id, name, frequency);

        toyQueue.add(toy);

    }

    public Toy runRaffle() {

        if (!toyQueue.isEmpty()) {

            int totalWeight = toyQueue.stream().mapToInt(Toy::getFrequency).sum();

            int randomIndex = (int) (Math.random() * totalWeight);

            int weightSum = 0;

            for (Toy toy : toyQueue) {

                weightSum += toy.getFrequency();

                if (randomIndex < weightSum) {

                    return toy;

                }

            }

        }

        return null;

    }
}

