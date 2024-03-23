package KUIS;
import java.util.*;

public class KnowledgeBasedSystem {
    // Membuat aturan-aturan (rules)
    static Map<Character, List<Character>> rules = new HashMap<>();
    static {
        rules.put('C', Arrays.asList('A', 'B'));
        rules.put('D', Collections.singletonList('C'));
        rules.put('F', Arrays.asList('A', 'E'));
        rules.put('G', Collections.singletonList('A'));
        rules.put('D', Arrays.asList('F', 'G'));
        rules.put('H', Arrays.asList('G', 'E'));
        rules.put('I', Arrays.asList('C', 'H'));
        rules.put('J', Arrays.asList('I', 'A'));
        rules.put('J', Collections.singletonList('G'));
        rules.put('K', Collections.singletonList('J'));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan huruf goal: ");
        char goal = scanner.next().charAt(0);

        System.out.print("Masukkan fakta-fakta (pisahkan dengan spasi atau tekan enter untuk selesai): ");
        scanner.nextLine(); // Membersihkan newline
        String input = scanner.nextLine();

        Set<Character> facts = new HashSet<>();
        for (char c : input.toCharArray()) {
            facts.add(c);
        }

        // Iterasi sesuai dengan aturan-aturan sampai goal tercapai atau tidak ada perubahan
        boolean goalReached = false;
        while (!goalReached) {
            int initialSize = facts.size();
            for (Map.Entry<Character, List<Character>> entry : rules.entrySet()) {
                char result = entry.getKey();
                List<Character> conditions = entry.getValue();
                if (!facts.contains(result)) { // Jika fakta belum ada
                    boolean allConditionsMet = true;
                    for (char condition : conditions) {
                        if (!facts.contains(condition)) {
                            allConditionsMet = false;
                            break;
                        }
                    }
                    if (allConditionsMet) {
                        facts.add(result);
                    }
                }
            }
            if (facts.contains(goal)) {
                goalReached = true;
            } else if (facts.size() == initialSize) {
                break; // Tidak ada perubahan, berhenti
            }
        }

        // Output hasil
        if (goalReached) {
            System.out.println(goal + " bernilai benar.");
        } else {
            System.out.println(goal + "tidak dapat dipastikan kebenarannya " +".");
        }
    }
}
