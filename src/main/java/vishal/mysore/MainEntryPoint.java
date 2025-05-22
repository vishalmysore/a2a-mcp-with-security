package vishal.mysore;


import io.github.vishalmysore.SecureSpringJSONController;
import io.github.vishalmysore.a2a.domain.JsonRpcRequest;
import io.github.vishalmysore.common.server.SpringAwareJSONRpcController;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Expose the Json-RPC endpoint for the tasks
 *  This will handle all the JSON RPC Requests for a2a such as
 *  tasks/send
 *  tasks/get
 *  tasks/sendSubscribe
 *  tasks/cancel
 *  tasks/setPushNotification etc
 * */
@RestController
@RequestMapping("/")
@Log
public class MainEntryPoint extends SecureSpringJSONController {

    @Autowired
    public MainEntryPoint(ApplicationContext applicationContext) {
        super(applicationContext);
    }





    @PostMapping
    public Object handleRpc(@RequestBody JsonRpcRequest request) {
        log.info(request.toString());
        // Add these lines to debug authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Principal: " + authentication.getPrincipal());
        log.info("Authorities: " + authentication.getAuthorities());
        log.info("Is Authenticated: " + authentication.isAuthenticated());
        return super.handleRpc(request);
    }



}
