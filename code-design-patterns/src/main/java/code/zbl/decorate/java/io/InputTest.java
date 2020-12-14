package code.zbl.decorate.java.io;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @Author: zbl
 * @Date: Create in 2020/12/14
 * @Email: zbl5337@gmail.com
 * @Description:
 */

public class InputTest {

    public static void main(String[] args) {
        int c;
        try {
            InputStream in =
                    new LowerCaseInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(InputTest.class.getResource("/test.txt").getPath())));
            while (( c = in.read()) >= 0) {
                System.out.print((char) c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
