package top.yinjinbiao.video.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 懒得写sql的测试controller
 * @author 1
 *
 */
@RestController
@RequestMapping("/userRelated")
public class TestController {
	
	/**
	 * 生成用户所有能访问的菜单
	 * @return
	 */
	@GetMapping("/userMenus")
	public List<String> test(){
		List<String> list = new ArrayList<>();
		list.add("admin");
		return list;
		
	}

}
