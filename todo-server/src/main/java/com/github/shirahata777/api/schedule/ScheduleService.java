package com.github.shirahata777.api.schedule;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.shirahata777.dao.repository.schedule.ScheduleRepository;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

public class ScheduleService implements Service {

	private static Logger log = LoggerFactory.getLogger(ScheduleService.class);

	@Override
	public void update(Routing.Rules rules) {
		rules.get("/schedule", this::serachScheduleHandler).get("/schedule/{scheduleNo}/detail",
				this::getDetailScheduleDataHandler);
	}

	private void serachScheduleHandler(ServerRequest request, ServerResponse response) {

		ObjectMapper mapper = new ObjectMapper();
		ScheduleRepository scheduleRepository = new ScheduleRepository();
		String jsonString = "";
		
		try {
			jsonString = mapper.writeValueAsString(scheduleRepository.findAll());
		} catch (IOException e) {
			jsonString = "No Data";
			log.warn(e.toString());
			e.printStackTrace();
		}

		response.send(jsonString);

	}

	public void getDetailScheduleDataHandler(ServerRequest request, ServerResponse response) {
		int scheduleNo = Integer.parseInt(request.path().param("scheduleNo"));

		ObjectMapper mapper = new ObjectMapper();
		ScheduleRepository detail = new ScheduleRepository();
		String jsonString = "";
		
		try {
			jsonString = mapper.writeValueAsString(detail.findDetail(scheduleNo));
		} catch (IOException e) {
			jsonString = "No Data";
			log.warn(e.toString());
			e.printStackTrace();
		}

		response.send(jsonString);
	}

}
