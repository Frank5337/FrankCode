package code.zbl.command.remotecontrol;

import code.zbl.command.light.Command;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/17
 * @Description:
 * @Version: $
 */
public class StereoOffWithCDCommand implements Command {

    Stereo stereo;

    public StereoOffWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }

    @Override
    public void undo() {
        stereo.on();
    }
}
