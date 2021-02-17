package code.zbl.command.light;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/17
 * @Description:
 * @Version: $
 */
public interface Command {

    public void execute();

    void undo();
}
