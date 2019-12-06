package com.chen.manager.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.manager.entity.Role;
import com.chen.manager.service.RoleService;
import com.chen.manager.utils.BaseUtils;
import com.chen.manager.viewmodel.CommonResult;
import com.chen.manager.viewmodel.LayuiPageMode;
import com.chen.manager.viewmodel.RolePageVO;

/**
 * 系统——角色
 * 
 * created at 2019-11-05
 * 
 * @author MonoWing
 *
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * 页面跳转——角色列表页
	 * 
	 * @return
	 */
	@GetMapping("/index")
	public String index() {
		return "admin/role/index";
	}

	/**
	 * 获取角色分页数据
	 * 
	 * @param page
	 *            当前页数
	 * @param limit
	 *            每页显示数量
	 * @param keyword
	 *            搜索关键字
	 * @return
	 */
	@PostMapping("/page")
	@ResponseBody
	public LayuiPageMode getPageData(Integer page, Integer limit, String keyword) {
		System.out.println(page + " " + limit);
		PageRequest pageRequest = PageRequest.of(page - 1, limit,
				Direction.DESC, "id");
		Page<Role> pageRole = roleService.pageRole(keyword, pageRequest);

		List<RolePageVO> roleList = new ArrayList<RolePageVO>();

		List<Role> content = pageRole.getContent();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Role role : content) {
			RolePageVO item = new RolePageVO();
			item.setId(role.getId());
			item.setName(role.getName());
			item.setDescription(role.getDescription());
			item.setInternalSign(role.getInternalSign());
			item.setCreateDate(sdf.format(role.getCreateTime()));
			roleList.add(item);
		}

		LayuiPageMode result = new LayuiPageMode();
		result.setCode(0);
		result.setCount(pageRole.getTotalElements());
		result.setMsg("");
		result.setData(roleList);
		return result;
	}

	/**
	 * 页面跳转——角色新增页面
	 * 
	 * @return
	 */
	@GetMapping("/add")
	public String add() {
		return "admin/role/add";
	}

	/**
	 * 新增角色
	 * 
	 * @param role
	 *            角色信息
	 * @return
	 */
	@PostMapping("/add")
	@ResponseBody
	public CommonResult add(Role role) {
		System.out.println(role.toString());
		roleService.insertRole(role);
		return new CommonResult().success();
	}

	/**
	 * 页面跳转——角色编辑页面
	 * 
	 * @param model
	 * @param id
	 *            角色ID
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(ModelMap model, Long id) {
		model.addAttribute("roleMode", roleService.get(id).get());
		return "admin/role/edit";
	}

	/**
	 * 编辑角色
	 * 
	 * @param role
	 *            角色信息
	 * @return
	 */
	@PostMapping("/edit")
	@ResponseBody
	public CommonResult edit(Role role) {
		System.out.println(role.toString());
		roleService.editRole(role);
		return new CommonResult().success();
	}

	/**
	 * 删除角色
	 * 
	 * @param ids
	 *            角色ID的拼接字符串，用“，”分隔
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody
	public CommonResult delete(String ids) {
		System.out.println(ids);
		return roleService.deleteRole(BaseUtils.convertStringToLong(ids));
	}
}
