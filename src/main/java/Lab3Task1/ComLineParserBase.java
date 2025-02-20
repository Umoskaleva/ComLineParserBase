package Lab3Task1;

public class ComLineParserBase {
    private String[] keys;           // ключи
    private String[] delimeters;     // разделители  "/", "-"

    protected enum SwitchStatus { NoError, Error, ShowUsage }; // protected, SwitchStatus может быть доступен из любого места внутри того же пакета, а также из подклассов (наследников) даже в других пакетах

    public ComLineParserBase(String[] keys) {
        this(keys, new String[] { "/", "-" });
    }
    public ComLineParserBase(String[] keys, String[] delimeters) {
        this.keys = keys;
        this.delimeters   = delimeters;
    }

    protected void OnUsage(String errorKey){

    }
    protected SwitchStatus OnSwitch(String key, String keyValue) {
        return SwitchStatus.Error;
    }

    public Boolean Parse(String[] args) {
        SwitchStatus ss = SwitchStatus.NoError;

        int argNum;
        for (argNum = 0; (ss == SwitchStatus.NoError) && (argNum < args.length); argNum++) {

            // проверка наличия правильного разделителя
            boolean isDelimeter = false;
            for (int n = 0; !isDelimeter && (n < delimeters.length); n++) {
                isDelimeter = args[argNum].regionMatches(0,delimeters[n], 0, 1);
            }

            if (isDelimeter) {
                // проверка наличия правильного ключа
                Boolean isKey = false;
                int i;
                for (i = 0; !isKey && (i < keys.length); i++) {
                    isKey = args[argNum].toUpperCase().regionMatches(1,
                            keys[i].toUpperCase(),0,keys[i].length());
                    if (isKey) break;
                }
                if (!isKey) {
                    ss = SwitchStatus.Error;
                    break;
                }
                else {
                    ss = OnSwitch(keys[i].toLowerCase(),
                            args[argNum].substring(1 + keys[i].length()));
                }
            }
            else {
                ss= SwitchStatus.Error;
                break;
            }
        }
        // завершение разбора командной строки
        if (ss == SwitchStatus.ShowUsage)    OnUsage(null);
        if (ss == SwitchStatus.Error)        OnUsage((argNum == args.length) ? null : args[argNum]);

        return ss == SwitchStatus.NoError;
    }
}

