import com.zbl.code.ClientApplication;
import com.zbl.code.email.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: zbl
 * @Date: Created in 10:17 2019/8/23
 * @Description: 测试用
 * @Version: $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class MailServiceTest {

    @Resource
    private MailService mailService;

    @Test
    public void test01() throws Exception {
        String to = "877910220@qq.com";
        String subject = "测试邮件";
        String content = "hello this is simple mail";
        mailService.sendSimpleMail(to, subject, content);
    }

    @Test
    public void test02() throws Exception {
        String to = "877910220@qq.com";
        String subject = "测试html邮件";
        String content = "<html>\n" +
                "<body>\n" +
                "      <h3>hello world ! 这是一封HTML邮件 !</h3>\n" +
                "      <img src=\"https://res.hulianhuxiang.com/user/577848373789327360/sendAvatar/e43ecb706c0a4cdca18b9699fce83f45.jpeg\">\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(to, subject, content);
    }

    @Test
    public void test03() throws Exception {
        String to = "877910220@qq.com";
        String subject = "测试带附件的邮件";
        String content = "有附件，请查收！";
        String filePath = "D:\\work\\develop\\IdeaProjects\\code\\code-client\\client.log";
        mailService.sendAttachmentsMail(to, subject, content, filePath);
    }

    @Test
    public void test04() throws Exception {
        String rscId = "ne07";
        String to = "877910220@qq.com";
        String subject = "测试：这是有图片的邮件";
        String content1 = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\ThinkPad\\Desktop\\2018101410470892.png";
        mailService.sendInlineResourceMail(to, subject, content1, imgPath, rscId);
    }

}
