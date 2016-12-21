package almaz.murzabekov.math.parser;

public abstract class BinaryOperatorParser implements Comparable<BinaryOperatorParser> {
    private final boolean isFunctionParser;
    private final String OPERATOR_MARKER;
    private final int priority;

    protected BinaryOperatorParser(String operator, int priority, boolean isFunctionParser) {
        this.OPERATOR_MARKER = operator;
        this.priority = priority;
        this.isFunctionParser = isFunctionParser;
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

        if(index == -1)
            return expression;

        if(isFunction())
            return parseFunction(index, expr);

        return parseOperator(index, expr);
    }

    protected int getOperatorIndex(String expression){
        return expression.indexOf(OPERATOR_MARKER);
    }

    private String parseFunction(int index, String expression) throws ParseException{
        String left = getFunctionLeftValue(expression, index);
        String right = getFunctionRightValue(expression, index);

        String a = getLeftOutside(expression);
        String b = getRightOutside(expression);
        String result = a + execute(left, right) + b;

        if (getOperatorIndex(result) != -1)
            return execute(result);

        return result;
    }
    private String parseOperator(int index, String expression) throws ParseException{
        char[] chrs = expression.toCharArray();
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

        String left = expression.substring(ls, lf + 1);
        String right = expression.substring(rs, rf + 1);
        if (rs == rf)
            right = chrs[rs] + "";

        String a = expression.substring(0, ls);
        String b = expression.substring(rf + 1);
        String answ = a + execute(left, right) + b;

        if (getOperatorIndex(answ) != -1)
            return execute(answ);

        return answ;
    }

    protected String getFunctionRightValue(String expression, int index) {
        // Expected expression is function(LEFT_VALUE, RIGHT_VALUE)
        String expr = expression.substring(index + OPERATOR_MARKER.length());
        char[] chrs = expr.toCharArray();

        index = expr.indexOf(",");

        int rs = index + 1;
        int rf = rs;
        while (rf + 1 < chrs.length && (isNumber(chrs[rf + 1]) || isNumberPointer(chrs[rf + 1])))
            rf++;

        String right = expr.substring(rs, rf + 1);
        if (rs == rf)
            right = chrs[rs] + "";

        return right;
    }
    protected String getFunctionLeftValue(String expression, int index){
        char[] chrs = expression.toCharArray();
        int ls = index + OPERATOR_MARKER.length() + 1;
        int lf = ls;
        while (lf + 1 < chrs.length && (isNumber(chrs[lf + 1]) || isNumberPointer(chrs[lf + 1])))
            lf++;

        return expression.substring(ls, lf + 1);
    }

    protected boolean isNumber(char c) {
        return "0123456789".contains(c + "");
    }
    protected boolean isNumberPointer(char c){
        return '.' == c;
    }

    protected String getLeftOutside(String expression) throws ParseException {
        int index = getLeftIndex(expression, getOperatorIndex(expression));

        if(index == expression.length())
            return "";

        return expression.substring(0, index);
    }
    protected String getRightOutside(String expression) throws ParseException {
        int index = getRightIndex(expression, getOperatorIndex(expression));

        if(index == expression.length())
            return "";

        return expression.substring(index);
    }
    private int getLeftIndex(String expression, int index) {
        while (index - 1 >= 0 && isNumber(expression.charAt(index - 1)))
            index--;

        return index;
    }
    private int getRightIndex(String expression, int index) {
        while (index + 1 < expression.length() &&
                isNumber(expression.charAt(index + 1)))
            index++;

        return index;
    }

    public boolean isFunction(){
        return isFunctionParser;
    }
    public int compareTo(BinaryOperatorParser o) {
        return getPriority() - o.getPriority();
    }
}