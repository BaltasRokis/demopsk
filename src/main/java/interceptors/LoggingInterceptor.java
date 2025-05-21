package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Logged
@Interceptor
public class LoggingInterceptor {

    private static final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class.getName());

    @AroundInvoke
    public Object logMethod(InvocationContext ctx) throws Exception {
        LOGGER.info(">> Entering method: " + ctx.getMethod().getName());
        long start = System.currentTimeMillis();
        try {
            return ctx.proceed();
        } finally {
            long end = System.currentTimeMillis();
            LOGGER.info("<<< Exiting method: " + ctx.getMethod().getName() + ", took " + (end - start) + "ms");
        }
    }
}
