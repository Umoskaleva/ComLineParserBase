package Lab3Task1;

public class SimpleParser extends ComLineParserBase {
    private String inFile;
    private String outFile;

    // среда разработки указывала на отсутствие конструкторов, которые определены в супер классе
    public SimpleParser(String[] keys) {
        super(keys);
    }

    public String getInFile() { // получить значеие inFile
        return inFile;
    }

    public String getOutFile() { // получить значение outFile
        return outFile;
    }

    public void setInFile(String inFile) { // установить значение inFile
        this.inFile = inFile;
    }

    public void setOutFile(String outFile) { // установить значение outFile
        this.outFile = outFile;
    }

    @Override
    protected void OnUsage(String errorKey) {
        if (errorKey != null)
            System.out.println("Command-line switch error:" + errorKey);

        System.out.println("формат ком.строки: имяПрограммы [-r<input-fileName>] [-w<output-fileName>]");
        System.out.println("   -?  показать Help файл");
        System.out.println("   -r  задать имя входного файла");
        System.out.println("   -w  выполнить вывод в указанный файл");
    }

    @Override
    protected SwitchStatus OnSwitch(String key, String keyValue) {
        SwitchStatus status = SwitchStatus.NoError;
        switch (key) {
            case "?":
                status = SwitchStatus.ShowUsage;
                break;
            case "r":
                if (!keyValue.isEmpty()) {
                    setInFile(keyValue);
                } else {
                    System.out.println("Задать имя входного файла");
                    status = SwitchStatus.Error;
                }
                break;
            case "w":
                if (!keyValue.isEmpty()) {
                    setOutFile(keyValue);
                } else {
                    System.out.println("Задать имя выходного файла");
                    status = SwitchStatus.Error;
                }
                break;
        }
        return status;
    }
}
