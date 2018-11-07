package com.zhou.medical.manager.api.system;

import com.zhou.medical.common.entity.MessageCode;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.Results;
import com.zhou.medical.common.entity.operation.SystemBanner;
import com.zhou.medical.common.util.FileFilterUtils;
import com.zhou.medical.common.util.UploadFilesUtils;
import com.zhou.medical.manager.client.operation.SystemBannerFeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * 
 * @author zhouzhou
 *
 */
@Controller
@RequestMapping("/sys/systemBanner")
public class SystemBannerController {
	
	@Resource
	private SystemBannerFeignClient systemBannerFeignClient;
	
	/**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "/sys/banner/systemBannerAdd";
    }
	
	
	/**
	 * 新增
	 * @param systemBanner
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Results<String> add(HttpServletRequest request, SystemBanner systemBanner, MultipartFile file){
		Results<String> results = new Results<String>();
		try {
			if (!file.isEmpty() && !FileFilterUtils.isImageFileType(file.getOriginalFilename())) {
				results.setCode(MessageCode.CODE_404);
				results.setMessage("不支持该文件格式上传");
				return results;
			}
			String prefix =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ "_";
			if (!file.isEmpty()) {
				String fileUrl = UploadFilesUtils.saveFile(null,request, file, prefix); // 保存图片
				systemBanner.setImgSrc(fileUrl);
			}
			systemBanner.setCreatetime(new Date());
			systemBanner.setUpdatetime(new Date());
			systemBanner.setFlag(0);
			systemBannerFeignClient.insert(systemBanner);
			results.setCode(MessageCode.CODE_200);
			results.setMessage("添加成功");
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.setMessage(e.getMessage());
			results.setCode(MessageCode.CODE_501);
			return results;
		}
	}
	
	/**
	 * 
	 * @return
	 */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
    	 return "/sys/banner/systemBanner";
    }

	/**
	 *
	 * @param systemBanner
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "findPage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findPage(SystemBanner systemBanner,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows){
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Pager<SystemBanner> pagers = systemBannerFeignClient.findPage("findPage", page, rows, systemBanner);
			map.put("total", pagers.getTotalCount());
			map.put("rows", pagers.getList());
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 编辑页面
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(int id, Model model) {
		try {
			SystemBanner systemBanner = systemBannerFeignClient.findById("selectByPrimaryKey", id);
			model.addAttribute("systemBanner", systemBanner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sys/banner/systemBannerEdit";
	}
	
	/**
	 * 修改
	 * @param systemBanner
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Results<String> edit(HttpServletRequest request, SystemBanner systemBanner, MultipartFile file){
		Results<String> results = new Results<String>();
		try {
			if (!file.isEmpty() && !FileFilterUtils.isImageFileType(file.getOriginalFilename())) {
				results.setCode(MessageCode.CODE_501);
				results.setMessage("不支持该文件格式上传");
				return results;
			}
			if (!file.isEmpty()) {
				String prefix =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ "_";
				String fileUrl = UploadFilesUtils.saveFile(null,request, file, prefix); // 保存图片
				systemBanner.setImgSrc(fileUrl);
			}
			systemBanner.setUpdatetime(new Date());;
			systemBannerFeignClient.update("updateByPrimaryKeySelective", systemBanner);
			results.setCode(MessageCode.CODE_200);
			results.setMessage("修改成功");
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.setMessage(e.getMessage());
			results.setCode(MessageCode.CODE_501);
			return results;
		}
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Results<String> delete(Integer id){
		Results<String> results = new Results<String>();
		try {
			SystemBanner systemBanner = new SystemBanner();
			systemBanner.setId(id);
			systemBannerFeignClient.delete("deleteByPrimaryKey", systemBanner);
			results.setCode(MessageCode.CODE_200);
			results.setMessage("删除成功");
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			results.setMessage(e.getMessage());
			results.setCode(MessageCode.CODE_501);
			return results;
		}
	}
	
	
	/**
	 * 
	* @Title: reject 
	* @Description: TODO 
	* @param @param id
	* @param @return    设定文件 
	* @return Results<Map<String,Object>>    返回类型 
	* @throws
	 */
	@RequestMapping("/reject")
	@ResponseBody
	public Results<Map<String, Object>> reject(int id) {
		Results<Map<String, Object>> result = new Results<Map<String, Object>>();
		try {
			SystemBanner systemBanner = systemBannerFeignClient.findById("selectByPrimaryKey", id);
			systemBanner.setFlag(0);
			systemBanner.setUpdatetime(new Date());
			systemBannerFeignClient.update("updateByPrimaryKey", systemBanner);
			result.setCode(MessageCode.CODE_200);
			result.setMessage("成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(MessageCode.CODE_404);
			result.setMessage(e.getMessage());
			return result;
		}
	}
	
	
	@RequestMapping("/pass")
	@ResponseBody
	public Results<Map<String, Object>> pass(int id, Model model) {
		Results<Map<String, Object>> result = new Results<Map<String, Object>>();
		try {
			SystemBanner systemBanner = systemBannerFeignClient.findById("selectByPrimaryKey", id);
			
			systemBanner.setFlag(1);
			systemBanner.setUpdatetime(new Date());
			systemBannerFeignClient.update("updateByPrimaryKey", systemBanner);
			result.setCode(MessageCode.CODE_200);
			result.setMessage("成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(MessageCode.CODE_404);
			result.setMessage(e.getMessage());
			return result;
		}
	}

}
