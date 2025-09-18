package ltw.vn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"ltw.vn"})
@ComponentScan
public class LtwWeek4Application {

	public static void main(String[] args) {
		SpringApplication.run(LtwWeek4Application.class, args);
	}

}
