import java.util.Scanner;


// Команда для продолжения боя/торговли
public class Command {
    public static boolean goNext(String str){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\nВы хотите продолжить %s? (да/нет)", str);
        switch (scanner.next()){
            case "да" -> {
                return true;
            }
            case "нет" -> {
                return false;
            }
            default -> {
                System.out.println("\nНужно ответить да или нет!");
                return goNext(str);
            }
        }
    }
}
