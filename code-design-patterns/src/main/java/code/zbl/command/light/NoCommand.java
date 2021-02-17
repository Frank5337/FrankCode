package code.zbl.command.light;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/17
 * @Description:
 * @Version: $
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
        System.out.println("无命令执行");
    }

    @Override
    public void undo() {
        System.out.println("无命令执行");
    }
}
