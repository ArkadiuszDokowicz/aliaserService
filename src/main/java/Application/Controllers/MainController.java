package Application.Controllers;

import Application.Model.AliasedObjectResponse;
import Application.Services.Aliaser.Aliaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/AliaserPRO")
public class MainController {

    @Autowired
    Aliaser aliaser;

    @GetMapping(value = "SendRecord",produces = "application/json")
    public Object makeAlias(@RequestParam("id")int id,@RequestParam("text")String text)
    {
        String aliasedText=aliaser.getAliasedMessage(text);
        System.out.println(text);
        return new AliasedObjectResponse(id,aliasedText);
    }
}
