package com.wwyl.controller.settings;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wwyl.controller.BaseController;
import com.wwyl.entity.settings.SystemConfig;
import com.wwyl.service.settings.SystemConfigService;

/**
 * @author liujian
 */
@Controller
@RequestMapping("/settings/system-config")
public class SystemConfigController extends BaseController {

	@Autowired
	SystemConfigService systemConfigService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return indexPage;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {

		return formPage;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@ModelAttribute("systemConfig") @Valid SystemConfig systemConfig, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {

			return formPage;
		}
		systemConfigService.save(systemConfig);
		return indexRedirect;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<SystemConfig> list() {
		return systemConfigService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public SystemConfig getSystemConfig(@PathVariable Long id) {
		SystemConfig systemConfig = systemConfigService.findOne(id);
		return systemConfig;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		SystemConfig systemConfig = systemConfigService.findOne(id);

		model.addAttribute("systemConfig", systemConfig);
		return formPage;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(  SystemConfig systemConfig , ModelMap model) {
	 
		systemConfigService.save(systemConfig);
		return indexRedirect;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable Long id, ModelMap model) {
		systemConfigService.delete(id);
		return indexRedirect;
	}

}
