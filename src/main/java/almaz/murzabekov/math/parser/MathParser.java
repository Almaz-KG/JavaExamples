package almaz.murzabekov.math.parser;

import almaz.murzabekov.math.parser.handlers.*;

import java.util.*;

/***
 *  Simple math formula parser
 *
 *  Рекурсивно последовательный парсер математического выражения
 *  Парсер поддерживает самые базовые математические операции
 *  такие, как:
 *   1. + и -
 *   2. * и /
 *
 *  Парсер последовательно в порядке приоритета от высшее к наименьшему
 *  передает введенное выражение к обработчикам операторов. А те, в свою
 *  очередь откусывают те участки выражения, которе они могут обработать
 *  и заменяют в исходном выражении обработнный оператор.
 *
 *  Алгоритм вычисления на примере вычисления выражения 1 + 2 * 3
 *  1. Вызвать хендлер для оператора / - результат: Та же строка (1 + 2 * 3)
 *  2. Вызвать хендлер для оператора * - результат: Строка (1 + 6)
 *  3. Вызвать хендлер для оператора - - результат: Та же строка (1 + 6)
 *  4. Вызвать хендлер для оператора + - результат: Строка (7)
 */
public class MathParser {
    private static String QUIT_COMMAND = "quit";
    private static MathParser parser = new MathParser();
    private List<BinaryOperatorParser> handlers = new ArrayList<>();

    public MathParser() {
        handlers.add(new DivideOperatorExecutor());
        handlers.add(new MultiplyOperatorExecutor());
        handlers.add(new MinusOperatorExecutor());
        handlers.add(new PlusOperatorExecutor());

        Collections.sort(handlers, Collections.reverseOrder());
    }

    public double parse(String text) throws ParseException {
        try {
            String expr = new String(text);

            for (BinaryOperatorParser handler : handlers) {
                expr = handler.execute(expr);
            }
            return Double.parseDouble(expr);
        } catch (NumberFormatException e) {
            throw new ParseException(e);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ans = "";

        do {
            try {
                System.out.print("Please, enter the formula (" +
                        QUIT_COMMAND + " - for close program)\n->");
                ans = sc.nextLine();
                if (isExitCommand(ans))
                    break;

                System.out.println(ans + " = " + parser.parse(ans));
            } catch (ParseException ex){
                System.out.println("Oooooops! Exception occurred\n" + ex);
                ex.printStackTrace();
            }
        } while (isExitCommand(ans) == false);
        System.out.println("Good luck");
    }

    private static boolean isExitCommand(String command) {
        return QUIT_COMMAND.equals(command.toLowerCase());
    }
}
