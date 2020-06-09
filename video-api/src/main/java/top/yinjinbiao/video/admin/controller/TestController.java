package top.yinjinbiao.video.admin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRelated")
public class TestController {

	@GetMapping("/userMenus")
	public List<String> userMenus(){
		return Arrays.asList("admin");
	}
}
