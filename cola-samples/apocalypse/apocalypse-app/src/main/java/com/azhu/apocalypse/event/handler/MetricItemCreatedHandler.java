package com.azhu.apocalypse.event.handler;


import com.azhu.cola.catchlog.CatchAndLog;
import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.api.UserProfileServiceI;
import com.azhu.apocalypse.dto.RefreshScoreCmd;
import com.azhu.apocalypse.dto.domainevent.MetricItemCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;

@CatchAndLog
public class MetricItemCreatedHandler {

    @Autowired
    private UserProfileServiceI userProfileService;

    public Response execute(MetricItemCreatedEvent event) {
        RefreshScoreCmd cmd = new RefreshScoreCmd(event.getUserId());
        userProfileService.refreshScore(cmd);
        return Response.buildSuccess();
    }
}
