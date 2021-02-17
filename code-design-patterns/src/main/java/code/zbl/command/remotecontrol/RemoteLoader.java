package code.zbl.command.remotecontrol;

import code.zbl.command.light.Light;
import code.zbl.command.light.LightCommand;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/17
 * @Description:
 * @Version: $
 */
public class RemoteLoader {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light light = new Light();
        Stereo stereo = new Stereo();

        LightCommand lightCommand = new LightCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        StereoOffWithCDCommand stereoOffWithCDCommand = new StereoOffWithCDCommand(stereo);

        remoteControl.setCommands(1, lightCommand, lightOffCommand);
        remoteControl.setCommands(2, stereoOnWithCDCommand, stereoOffWithCDCommand);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(1);
        remoteControl.undoButtonWasPushed();
        remoteControl.onButtonWasPushed(2);
        remoteControl.undoButtonWasPushed();

        remoteControl.onButtonWasPushed(3);
        remoteControl.undoButtonWasPushed();

//        remoteControl.offButtonWasPushed(1);
//        remoteControl.offButtonWasPushed(2);
//        remoteControl.offButtonWasPushed(3);
    }

}
