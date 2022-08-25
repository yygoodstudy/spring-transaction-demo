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
}
