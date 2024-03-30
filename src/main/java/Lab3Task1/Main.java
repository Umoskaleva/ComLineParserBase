package Lab3Task1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String outfileName = "-wOutfileName";
        String infileName = "-rInfileName";

        // я не нашла в IDEA как ввести командную строку в аргументы, поэтому сделала так...
        SimpleParser simpleParser = new SimpleParser(new String[]{"?", "r", "w"});
        args = new String[]{outfileName, infileName};
        simpleParser.Parse(args);
        System.out.println("Вы ввели: ");
        System.out.println(Arrays.toString(args));
    }
}