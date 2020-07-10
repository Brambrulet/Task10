package end.of.study.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import end.of.study.springboot.entity.User;
import end.of.study.springboot.service.UserService;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringbootApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() throws Exception {
		User user = userService.getById(3);

		mockMvc.perform(get("/people/3")).andExpect(status().isOk())
			.andExpect(content().string(StringContains.containsString(user.getPassword())));
	}
}
