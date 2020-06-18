package ucb.edu.bo.sabicovid19;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SaBiCovid19ApplicationTests {

	@Test
	void contextLoads() {
		String temp = "/start";
		log.info("GG" + temp.indexOf(' '));
		String nickname = temp.substring(0, temp.indexOf(' '));
		String content = temp.substring(temp.indexOf(' ') + 1);
		log.info("NICKNAME " + nickname);
		log.info("Content " + content);
	}

}
