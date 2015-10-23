package sample.repository;

import java.util.HashMap;

import sample.domain.Robot;

import com.jdon.annotation.Component;
import com.jdon.annotation.Introduce;
import com.jdon.annotation.pointcut.Around;

@Component
@Introduce("modelCache")
public class RobotRepositoryInMEM implements RobotRepository {

	private final HashMap<String, Robot> memDB = new HashMap<String, Robot>();

	@Around
	public Robot find(String id) {
		return memDB.get(id);
	}

	// @OnEvent("saveme") is equals to
	// sample.event.consumer.write.SavemeListener
	public void save(Robot robot) {
		memDB.put(robot.getId(), robot);
	}

	public HashMap<String, Robot> getMemDB() {
		return memDB;
	}

}
