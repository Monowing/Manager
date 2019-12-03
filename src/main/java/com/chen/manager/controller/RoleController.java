package com.chen.manager.controller;


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
	
	@GetMapping("/index")
	public String index(){
		return "admin/role/index";
	}
	
	@PostMapping("/page")
	@ResponseBody
	public LayuiPageMode getPageData(Integer page,Integer limit,String keyword){
		System.out.println(page+ " " + limit);
		PageRequest pageRequest = PageRequest.of(page-1,limit,Direction.DESC, "id");
		Page<Role> pageRole = roleService.pageRole(keyword,pageRequest);
		
		List<RolePageVO> roleList = new ArrayList<RolePageVO>();

		List<Role> content = pageRole.getContent();
		for (Role role : content) {
			RolePageVO item = new RolePageVO();
			item.setId(role.getId());
			item.setName(role.getName());
			item.setDescription(role.getDescription());
			item.setInternalSign(role.getInternalSign());
			roleList.add(item);
		}
		
		
		LayuiPageMode result = new LayuiPageMode();
		result.setCode(0);
		result.setCount(pageRole.getTotalElements());
		result.setMsg("");
		result.setData(roleList);
		return result;
	}
	
	
	@GetMapping("/add")
	public String add(){
		return "admin/role/add";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public CommonResult add(Role role){
		System.out.println(role.toString());
		return new CommonResult().success(roleService.insertRole(role));
	}
	
	@GetMapping("/edit")
	public String edit(ModelMap model,Long id){
		model.addAttribute("roleMode",roleService.get(id).get());
		return "admin/role/edit";
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public CommonResult edit(Role role){
		System.out.println(role.toString());
		return new CommonResult().success(roleService.editRole(role));
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public CommonResult delete(String ids){
		System.out.println(ids);
		return roleService.deleteRole(BaseUtils.convertStringToLong(ids));
	}
}
