import com.zbl.code.common.conditional.ConditionConfig;
import com.zbl.code.common.conditional.ListService;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: zbl
 * @Date: Created in 15:04 2019/8/27
 * @Description:
 * @Version: $
 */
public class SpringConditionTest {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);

    @Test
    public void contextTest() throws Exception{
        ListService listService = context.getBean(ListService.class);

        System.out.println(
                context.getEnvironment().getProperty("os.name") +
                        "系统下的列表命令为: " +
                        listService.showListCmd() );
    }

    @Test
    public void test01() throws Exception{
        System.out.println(context.getEnvironment());
    }

    @After
    public void closeContext() {
        context.close();
    }

}