package vishal.mysore;


import io.github.vishalmysore.SecureRTAgentCardController;
import io.github.vishalmysore.a2a.domain.AgentCard;
import io.github.vishalmysore.a2a.domain.Authentication;
import io.github.vishalmysore.a2a.server.RealTimeAgentCardController;
import io.github.vishalmysore.a2a.server.SpringAwareAgentCardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RealTimeAgentCardController.WELL_KNOWN_PATH)
public class CarBookingAgentCardController extends SecureRTAgentCardController {


    @Autowired
    public CarBookingAgentCardController(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @GetMapping(value = RealTimeAgentCardController.AGENT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentCard> getAgentCardForMyApp() {

        AgentCard card = getCachedAgentCard();
        Authentication authentication = Authentication.fromAuthorizationHeader("Basic");
        card.setAuthentication(authentication);
        return ResponseEntity.ok(card);

    }

}
