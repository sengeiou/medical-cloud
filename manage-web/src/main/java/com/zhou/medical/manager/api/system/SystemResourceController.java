package com.zhou.medical.manager.api.system;

import com.zhou.medical.common.entity.MessageCode;
import com.zhou.medical.common.entity.Results;
import com.zhou.medical.common.entity.Tree;
import com.zhou.medical.common.entity.operation.SystemResource;
import com.zhou.medical.log.annotation.SystemControllerLog;
import com.zhou.medical.manager.client.operation.SystemResourceFeignClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统资源
 *
 * @author zds
 *
 */
@Controller
@RequestMapping("/sys/systemResource")
public class SystemResourceController {

	// 输出日志
	private static Logger log = Logger.getLogger(SystemResourceController.class);

    @Autowired
    private SystemResourceFeignClient systemResourceFeignClient;

    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    @SystemControllerLog("manager")
    public String manager() {
        return "/sys/admin/resource";
    }

    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/findTreeGrid", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog("findTreeGrid")
    public List<SystemResource> findTreeGrid() {
        List<SystemResource> treeGrid = systemResourceFeignClient.getList("selectAll", new SystemResource());

        return treeGrid;
    }

    /**
     * 跳转新增页面
     *
     * @return
     */
    @RequestMapping("/addPage")
    @SystemControllerLog("addPage")
    public String addPage() {
        return "/sys/admin/resourceAdd";
    }

    /**
     * 新增资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @SystemControllerLog("add")
    public Results<Map<String, Object>> add(SystemResource resource) {
    	Results<Map<String, Object>> results = new Results<Map<String, Object>>();
        System.out.println("systemResource.getName()4444444444444444:"+resource.getName());
        try {
        	resource.setCreatetime(new Date());
            systemResourceFeignClient.insert(resource);

            results.setCode(MessageCode.CODE_200);
			results.setMessage(MessageCode.MESSAGE_200);
            return results;
        } catch (RuntimeException e) {
        	results.setCode(MessageCode.CODE_501);
			results.setMessage(MessageCode.MESSAGE_501);
            return results;
        }
    }

    /**
     * 列出所有菜单树
     *
     * @return
     */
    @RequestMapping("/showAllTree")
    @ResponseBody
    @SystemControllerLog("showAllTree")
    public List<Tree> showAllTree() {
        return systemResourceFeignClient.findAllTree();
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/showAllTrees", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog("showAllTrees")
    public List<Tree> showAllTrees() {
        return systemResourceFeignClient.findAllTrees();
    }

    /**
     * 编辑资源跳转页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    @SystemControllerLog("editPage")
    public String editPage(Model model, int id) {
        SystemResource systemResource = systemResourceFeignClient.findById("selectByPrimaryKey", id);
		model.addAttribute("systemResource", systemResource);
        return "/sys/admin/resourceEdit";
    }

    /**
     * 编辑资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @SystemControllerLog("edit")
    public Results<Map<String, Object>> edit(SystemResource resource) {
    	Results<Map<String, Object>> results = new Results<Map<String, Object>>();

        try {
            resource.setUpdatetime(new Date());
            systemResourceFeignClient.update("updateByPrimaryKeySelective", resource);

            results.setCode(MessageCode.CODE_200);
			results.setMessage(MessageCode.MESSAGE_200);
            return results;
        } catch (RuntimeException e) {
        	results.setCode(MessageCode.CODE_501);
			results.setMessage(MessageCode.MESSAGE_501);
            return results;
        }
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @SystemControllerLog("delete")
    public Results<Map<String, Object>> delete(int id) {
    	Results<Map<String, Object>> results = new Results<Map<String, Object>>();

        try {
        	SystemResource systemResource = systemResourceFeignClient.findById("selectByPrimaryKey", id);
			if (systemResource != null) {
				//先删除子菜单
//				systemResourceService.delete("deleteResourceByPid", systemResource);
				//再删除父菜单
                systemResourceFeignClient.delete("deleteByPrimaryKey", systemResource);
			}

            results.setCode(MessageCode.CODE_200);
			results.setMessage(MessageCode.MESSAGE_200);
            return results;
        } catch (RuntimeException e) {
        	results.setCode(MessageCode.CODE_501);
			results.setMessage(MessageCode.MESSAGE_501);
            return results;
        }
    }

}
