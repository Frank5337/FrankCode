package code.zbl.command.remotecontrol;

/**
 * @Author: zbl
 * @Date: Created in 2021/2/17
 * @Description:
 * @Version: $
 */
public class Stereo {


    public void on() {
        System.out.println("开音响");
    }

    public void off() {
        System.out.println("关音响");
    }


    public void setCd() {
        System.out.println("装cd");
    }

    public void setDvd() {
        System.out.println("装dvd");
    }

    public void setRadio() {
        System.out.println("设置收音机");
    }

    public void setVolume(int volume) {
        System.out.println("设定音量: " + volume);
    }
}
