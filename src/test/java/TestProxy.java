import com.yy.proxy.OrderProxy;
import com.yy.service.OrderService;
import com.yy.service.impl.OrderServiceImpl;
import org.junit.Test;

/**
 * @Description TODO
 * @Date 2022/8/20 23:56
 */
public class TestProxy {


    @Test
    public void testProxy(){
        OrderService orderService = new OrderServiceImpl();
        OrderProxy proxy = new OrderProxy(orderService);
        orderService = (OrderService) proxy.getProxy();
        orderService.test1();
//        orderService.test2();
    }

    // 修改代码
    @Test
    public void test01(){
        System.out.println("hello world");
    }

    // 第二次修改代码
    public void test02(){
        System.out.println("hello world02");
    }

    // 在hot-fix分支上个第1次修改代码
    public void test03(){
        System.out.println("hello world02");
    }

    public void test04(){
        System.out.println("hot-fix test");
    }

    public void test05(){
        System.out.println("master test");
    }

    public void test06(){
        System.out.println("repository push~~~~");
    }

    public void test07(){
        System.out.println("repository push02~~~~");
    }
}
