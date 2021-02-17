package code.zbl.command.remotecontrol;

import code.zbl.command.light.Command;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/17
 * @Description:
 * @Version: $
 */
public class StereoOnWithCDCommand implements Command {

    Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCd();
        stereo.setVolume(11);
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
