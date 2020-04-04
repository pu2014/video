package top.yinjinbiao.video.flowable.resource;

import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.flowable.ui.common.model.UserRepresentation;
import org.flowable.ui.common.security.SecurityUtils;
import org.flowable.ui.common.service.idm.RemoteIdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/app")
public class MyRemoteAccountResource {

    @GetMapping(
            value = {"/rest/account"},
            produces = {"application/json"}
    )
    public UserRepresentation getAccount() {
        User user = new UserEntityImpl();
        user.setId("yinId");
        SecurityUtils.assumeUser(user);
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId("yinjinbiao");
        userRepresentation.setFirstName("yin");
        List<String> privileges = new ArrayList<>();
        privileges.add("flowable-idm");
        privileges.add("flowable-modeler");
        privileges.add("flowable-admin");
        privileges.add("flowable-task");
        privileges.add("flowable-rest");
        userRepresentation.setPrivileges(privileges);
        return userRepresentation;
    }
}
