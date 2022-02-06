package org.jointheleague.api.level7.chipmunk.WISAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class WisAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WisAnalyzerApplication.class, args);
	}

}
