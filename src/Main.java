import javax.xml.bind.DatatypeConverter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.StringJoiner;
import java.io.*;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
public class Main {
    public static void main(String[] args) {
        int[] a = encrypt("Hello");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        int[] b = {72, 33, -73, 84, -12, -3, 13, -13, -68};
        System.out.println(decrypt(b));
        System.out.println(canMove("King", "C2", "D2"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        int[] c = {16, 28};
        System.out.println(sumDigProd(c));
        String[] d = {"toe", "ocelot", "maniac"};
        String[] q = sameVowelGroup(d);
        for (int i = 0; i < q.length; i++) {
            if (q[i] != null) System.out.println(q[i]);
        }
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(21));
    }

    public static int[] encrypt(String str) {
        char[] a = str.toCharArray();
        int[] mas = new int[a.length];
        mas[0] = (int) a[0];
        for (int i = 1; i < a.length; i++) {
            mas[i] = (int) a[i] - (int) a[i - 1];
        }
        return mas;
    }

    public static String decrypt(int[] mas) {
        String s = "";
        int x = mas[0];
        s = s + (char) mas[0];
        for (int i = 1; i < mas.length; i++) {
            x += mas[i];
            s = s + (char) x;
        }
        return s;
    }

    private static boolean canMove(String figure, String from, String to) {
        // константы фигур
        final String HORSE = "Horse";
        final String BISHOP = "Bishop";
        final String QUEEN = "Queen";
        final String ROOK = "Rook";
        final String PAWN = "Pawn";
        final String KING = "King";

        char fromX = from.charAt(0);
        int fromY = Integer.parseInt(String.valueOf(from.charAt(1)));
        char toX = to.charAt(0);
        int toY = Integer.parseInt(String.valueOf(to.charAt(1)));
        if ((fromY == toY && fromX != toX) || (fromY != toY && fromX == toX)) {
            if (figure.equals(QUEEN) || figure.equals(ROOK)) {
                return true;
            }
            if ((fromY == toY && Math.abs(fromX - toX) == 1) || (fromX == toX && Math.abs(fromY - toY) == 1)) {
                if (figure.equals(KING)) {
                    return true;
                }
            }
            if (fromX == toX && toY - fromY == 1) {
                return figure.equals(PAWN);
            }
        } else if (Math.abs(fromX - toX) == Math.abs(fromY - toY)) {
            if (figure.equals(QUEEN) || figure.equals(BISHOP)) {
                return true;
            }
            if (Math.abs(fromX - toX) == 1) {
                return figure.equals(KING);
            }
        } else if ((Math.abs(fromX - toX) == 1 && Math.abs(fromY - toY) == 2) || (Math.abs(fromY - toY) == 1 && Math.abs(fromX - toX) == 2)) {
            return figure.equals(HORSE);
        }
        return false;
    }

    public static boolean canComplete(String str1, String str2) {
        int z = 0, x = 0;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = i; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j) && z == i) {
                    i++;
                    z++;
                }
            }
        }
        return z == str1.length();
    }

    public static int sumDigProd(int[] b) {
        int s, x;
        int a = 0;
        for (int i = 0; i < b.length; i++) {
            a = a + b[i];
        }
        if (a < 10 && a >= 0) {
        } else if (a > 9) {
            do {
                s = a;
                x = 1;
                while (s > 0) {
                    x *= s % 10;
                    s = (int) (s / 10);
                }
                a = x;
            } while (x > 9);
        }
        return a;
    }

    public static String[] sameVowelGroup(String[] a) {
        Set<Character> s = new HashSet<>();
        int z = 0;
        String[] b = new String[a.length];
        int w = 0;
        int x = 0;
        for (int j = 0; j < a[0].length(); j++) {
            switch (a[0].charAt(j)) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'y':
                    s.add(a[0].charAt(j));
            }
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length(); j++) {
                switch (a[i].charAt(j)) {
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'u':
                    case 'y':
                        if (s.contains(a[i].charAt(j)) == false) {
                            z++;
                        } else {
                            x++;
                        }
                }
            }
            if (x != 0 && z == 0) {
                b[w] = a[i];
                w++;
            }
            x = 0;
            z = 0;
        }
        return b;
    }

    private static boolean validateCard(Long number) {
        // проверяем что число в нужном диапазоне
        if (number < Math.pow(10, 14) || number >= Math.pow(10, 20)) {
            return false;
        }
        // шаг 1 - находим контрольную сумму
        int controlNum = (int) (number % 10);
        String num = number.toString();
        num = num.substring(0, num.length() - 1);
        // шаг 2-3-4, пробегаемся по числу в обратном порядке, и сразу же складываем найденные цифры (или удвоенные цифры на нечетных позициях)
        int sum = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int doubledInteger = Integer.parseInt(num.substring(i, i + 1));
            if ((num.length() - i) % 2 == 1) {
                doubledInteger *= 2;
                if (doubledInteger > 9) {
                    doubledInteger = doubledInteger % 10 + (doubledInteger / 10) % 10;
                }
            }
            sum += doubledInteger;
        }
        // шаг 5 - сравниваем число с контрольной цифрой
        return (10 - (sum % 10)) == controlNum;
    }

    private static String numToEng(int number) {
        class NumTransformer {
            // перевод цифры в слово
            String oneNumberToString(int n) {
                switch (n) {
                    case 1:
                        return "one";
                    case 2:
                        return "two";
                    case 3:
                        return "three";
                    case 4:
                        return "four";
                    case 5:
                        return "five";
                    case 6:
                        return "six";
                    case 7:
                        return "seven";
                    case 8:
                        return "eight";
                    case 9:
                        return "nine";
                    default:
                        return null;
                }
            }

            // перевод десятков в строки (кроме десятков = 0,1)
            String decToString(int decCount) {
                switch (decCount) {
                    case 2:
                        return "twenty";
                    case 3:
                        return "thirty";
                    case 4:
                        return "forty";
                    case 5:
                        return "fifty";
                    case 6:
                        return "sixty";
                    case 7:
                        return "seventy";
                    case 8:
                        return "eighty";
                    case 9:
                        return "ninety";
                    default:
                        return null;
                }
            }

            // перевод чисел от 10 до 19 в строки
            String dec1ToString(int n) {
                switch (n) {
                    case 0:
                        return "ten";
                    case 1:
                        return "eleven";
                    case 2:
                        return "twelve";
                    case 3:
                        return "thirteen";
                    case 4:
                        return "fourteen";
                    case 5:
                        return "fifteen";
                    case 6:
                        return "sixteen";
                    case 7:
                        return "seventeen";
                    case 8:
                        return "eighteen";
                    case 9:
                        return "nineteen";
                    default:
                        return null;
                }
            }
        }
        // находим число сотен десятков и едениц
        int hundred = (number / 100) % 10;
        int dec = (number / 10) % 10;
        int n = number % 10;

        NumTransformer numTransformer = new NumTransformer();
        // получаем строку для  сотен
        String hundredString = numTransformer.oneNumberToString(hundred);
        // получаем отдельно строки для десятков и едениц или общую строку для диапазона от 10 до 19
        String nString = null;
        String decString;
        if (dec != 1) {
            nString = numTransformer.oneNumberToString(n);
            if (n == 0) {
                nString = "zero";
            }
            decString = numTransformer.decToString(dec);
        } else {
            decString = numTransformer.dec1ToString(n);
        }

        // складываем все значения
        StringJoiner joiner = new StringJoiner(" ");
        if (hundredString != null) {
            joiner.add(hundredString);
            joiner.add("hundred");
        }
        if (decString != null) {
            joiner.add(decString);
        }
        if (nString != null) {
            joiner.add(nString);
        }
        return joiner.toString();
    }

    /**
     * 8 задание:
     *
     * @return отформатированный в виде шестнадцатеричной цифры хеш SHA-256 для строки
     */
    private static String getSha256Hash(String s) {
        try {
            // шифруем в байты строку через MessageDigest
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
            String sha256 = DatatypeConverter.printHexBinary(hash).toLowerCase();
            return sha256.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 9 задание:
     *
     * @return строка, в которой все слова кроме of, in, and, the - капитализированы
     */
    private static String correctTitle(String s) {
        // делим строку на слова
        String[] words = s.toLowerCase(Locale.ROOT).split(" ");
        StringJoiner joiner = new StringJoiner(" ");
        for (String word : words) {
            // капитализуем все слова, не являющиеся служебными
            if (!word.equals("of") && !word.equals("in") && !word.equals("and") && !word.equals("the")) {
                word = word.substring(0, 1).toUpperCase(Locale.ROOT) + word.substring(1);
            }
            joiner.add(word);
        }
        return joiner.toString();
    }

    /**
     * 10 задание:
     *
     * @return вывести на экран гексагональную решетку для центрированного шестиугольного числа
     */
    private static String hexLattice(int n) {
        // объявим циклы - это количество кругов решетки. Например для 7 - 2 цикла. для 37 - 4 цикла
        int hexCycle = 1;
        int currentElements = 1;
        // пытаемся подобрать цикл для данного числа эллементов.
        // Если цикл не удается подобрать - значит число не центрированное шестиугольное
        while (currentElements < n) {
            // число элементов для данного цикла = 1 + 6* сумма(k)по k от 2 до n, где n - цикл.
            currentElements += 6 * hexCycle;
            hexCycle += 1;
        }
        if (currentElements != n) {
            return "invalid";
        }
        // размер - количество строк = количество точек
        final int hexSize = hexCycle * 2 - 1;
        final int hexSizeWithSpaces = hexSize * 2 - 1;
        // массив строк
        String[] hex = new String[hexSize];
        // цикл с середины фигуры до ее начала. Можно рисовать только половину так как фигура зеркальная.
        for (int i = hexCycle - 1; i >= 0; i--) {
            // находим сколько будет символов для этой строки вместе с пробелами
            int rowSizeWithSpaces = (hexCycle + i) * 2 - 1;
            // находим какой отступ нужно будет сделать слева и справа для этой строки
            int spaceInLeft = (hexSizeWithSpaces - rowSizeWithSpaces) / 2;
            // рисуем строку - повторяем точки определенное количество раз, и ставим между ними пробелы. добавляем слева и срава отступы.
            hex[i] = new String(new char[spaceInLeft]).replace("\0", " ") + new String(new char[hexCycle + i]).replace("\0", "0").replaceAll(".(?=.)", "$0 ") + new String(new char[spaceInLeft]).replace("\0", " ");
            // если строка не на середине, то рисуем зеркальную строку
            if (i <= hexCycle - 1) {
                hex[hexSize - i - 1] = hex[i];
            }
        }
        // преобразуем массив в строку
        StringJoiner joiner = new StringJoiner("\n");
        for (String h : hex) {
            joiner.add(h);
        }
        return joiner.toString();
    }


}