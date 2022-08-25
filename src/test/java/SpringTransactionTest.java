import com.yy.config.JDBCTemplateConfig;
import com.yy.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description TODO
 * @Date 2022/8/20 14:32
 */

public class SpringTransactionTest {
    JdbcTemplate jdbcTemplate;
    AccountServiceImpl accountService;

    @Before
    public void before() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JDBCTemplateConfig.class);
        jdbcTemplate = context.getBean(JdbcTemplate.class);
        accountService = context.getBean(AccountServiceImpl.class);
    }

    @Test
    public void testTransfer() {
        accountService.transferOut("张三丰", 200d);
        int i = 10 / 0;
        accountService.transferIn("黄蓉", 200d);
    }

    @Test
    public void transferMoney() {
        accountService.transferMoney("张三丰", "黄蓉", 300d);
    }


    @Test
    public void testPropagation() {
        accountService.in("黄蓉", 500d);
    }
}
