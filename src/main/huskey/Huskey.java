import cmd.CommandRouting;
import utility.*;

/**
 * コマンドライン引数を分解 -> コマンドルーティングへ渡し、実行
 *
 * @author いっぺー
 */
public class Huskey {
    public static void main(String[] args) {
        SeparateArgs sepArgs = new SeparateArgs(args);
        CommandRouting cr = new CommandRouting(
            sepArgs.getCommand(),
            sepArgs.getValues(),
            sepArgs.getOptions()
        );

        try {
            cr.run();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
