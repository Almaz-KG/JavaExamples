package almaz.murzabekov.math.parser.handlers;

import almaz.murzabekov.math.parser.ParseException;

public abstract class BinaryOperatorHandler implements Comparable<BinaryOperatorHandler>{
    private final String OPERATOR_MARKER;
    private final int priority;

    protected BinaryOperatorHandler(String operator, int priority) {
        this.OPERATOR_MARKER = operator;
        this.priority = priority;
    }

    public int getPriority(){return priority;}
    protected abstract String execute(String l, String r) throws ParseException;

    /***
     *  PROGRAM:
     *  Попытаться найти левый операнд Л,
     *  Если Л существует
     *      Попытаться найти правый П операнд
     *      Если П существует
     *           Заменить выражение Л operator П на значение {{Л operator П}}
     *           Если существует еще один оператор
     *               Рекурсивно вызвать PROGRAM (оставщийся участок выражения, для поиска других операторов)
     *           Иначе
     *               Вернуть оставщийся участок выражения
     *      Иначе бросить ошибку: ParseException('Не удалось найти правый операнд')
     *  Иначе вернуть исходную строку
     *
     * @param expression Строка выражение для парсинга
     * @return String Строка выходного выражения
     */
    public String execute(String expression) throws ParseException{
        String expr = expression.replaceAll("\\s+","");
        int index = getOperatorIndex(expr);
        char[] chrs = expr.toCharArray();

        if(index == -1)
            return expression;

        // Вычисляем индексы левого значения
        int lf = index - 1;
        int ls = lf;
        while (ls - 1 >= 0 && (isNumber(chrs[ls - 1]) || isNumberPointer(chrs[ls - 1])))
            ls--;

        // Аналогично ыычисляем индексы правого значения
        int rs = index + 1;
        int rf = rs;
        while (rf + 1 < chrs.length && (isNumber(chrs[rf + 1]) || isNumberPointer(chrs[rf + 1])))
            rf++;

        String left = expr.substring(ls, lf + 1);
        String right = expr.substring(rs, rf+1);
        if(rs == rf)
            right = chrs[rs] + "";

        String a = expr.substring(0, ls);
        String b = expr.substring(rf + 1);
        String answ = a + execute(left, right) + b;

        if(getOperatorIndex(answ) != -1)
            return execute(answ);

        return answ;
    }
    private int getOperatorIndex(String expression){
        return expression.indexOf(OPERATOR_MARKER);
    }
    private boolean isNumber(char c) {
        return "0123456789".contains(c + "");
    }
    private boolean isNumberPointer(char c){
        return '.' == c;
    }

    @Override
    public int compareTo(BinaryOperatorHandler o) {
        return getPriority() - o.getPriority();
    }
}
