package com.zhou.medical.manager.api.system;

import com.zhou.medical.common.controller.BaseController;
import com.zhou.medical.common.entity.MessageCode;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.Results;
import com.zhou.medical.common.entity.account.ContractedHospitals;
import com.zhou.medical.common.entity.account.DoctorsTeam;
import com.zhou.medical.common.util.FileFilterUtils;
import com.zhou.medical.common.util.UploadFilesUtils;
import com.zhou.medical.log.annotation.SystemControllerLog;
import com.zhou.medical.manager.client.account.ContractedHospitalsFeignClient;
import com.zhou.medical.manager.client.account.DoctorsTeamFeignClient;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生档案管理
 *
 * @author wsm
 */
@Controller
@RequestMapping("/sys/hospital")
public class SystemHospitalController extends BaseController {

    // 输出日志
    private static Logger log = Logger.getLogger(SystemHospitalController.class);


    @Resource
    private DoctorsTeamFeignClient doctorsTeamFeignClient;

    @Resource
    private ContractedHospitalsFeignClient contractedHospitalsFeignClient;


    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    @SystemControllerLog("manager")
    public String manager() {
        return "/sys/hospital/hospital";
    }

    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/findUserPage")
    @ResponseBody
    @SystemControllerLog("findUserPage")
    public Map<String, Object> findUserPage(HttpServletResponse response, String keyword,
                                            @RequestParam(value = "page", defaultValue = "1") Long page,
                                            @RequestParam(value = "rows", defaultValue = "10") Long rows) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            if (StringUtils.isNotBlank(keyword)) {
                // java : 字符解码
                keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
            }
            map.put("keyword", keyword);
            Pager<DoctorsTeam> doctorsTeamPage = doctorsTeamFeignClient.selectKeyWordBySys("selectKeyWordBySys", getPageIndex(page),getPageSize(rows), map);
            map.put("total", doctorsTeamPage.getTotalCount());
            map.put("rows", doctorsTeamPage.getList());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("total", 0);
            map.put("rows", "");
        }

        return map;
    }

    /**
     * 详情医生注册审批页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/queryPage")
    @SystemControllerLog("queryPage")
    public String queryPage(int id, Model model) {
        try {
            DoctorsTeam doctorsTeam = doctorsTeamFeignClient.findById("selectByPrimaryKey", id);
            model.addAttribute("doctorsTeam", doctorsTeam);
            System.out.println(doctorsTeam.getHospitalIMG());
            if (StringUtils.isNotBlank(doctorsTeam.getHospitalIMG())) {
                model.addAttribute("isHospitalIMG", 1);
            } else {
                model.addAttribute("isHospitalIMG", 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/sys/hospital/hospitalQuery";
    }

    @RequestMapping("/addPage")
    @SystemControllerLog("addPage")
    public String addPage() {
        return "/sys/hospital/hospitalAdd";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog("add")
    public Results<Map<String, Object>> add(HttpServletRequest request, DoctorsTeam doctorsTeam_,
                                            MultipartFile file) {
        Results<Map<String, Object>> results = new Results<Map<String, Object>>();
        if (!file.isEmpty() && !FileFilterUtils.isMediaFileType(file.getOriginalFilename())) {
            results.setCode(MessageCode.CODE_404);
            results.setMessage("不支持该文件格式上传");
            return results;
        }

        String prefix = "hospitalIMG_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_";
        // 保存图片
        String fileUrl = UploadFilesUtils.saveFile(null, request, file, prefix);

        DoctorsTeam doctorsTeam = new DoctorsTeam();
        doctorsTeam.setName(doctorsTeam_.getName());
        doctorsTeam.setArea(doctorsTeam_.getArea());
        doctorsTeam.setCityName(doctorsTeam_.getCityName());
        doctorsTeam.setCreatetime(new Date());
        doctorsTeam.setHospitalAddr(doctorsTeam_.getHospitalAddr());
        doctorsTeam.setHospitalIMG(fileUrl);
        doctorsTeam.setHospitalPhone(doctorsTeam_.getHospitalPhone());
        doctorsTeam.setIntroduction(doctorsTeam_.getIntroduction());
        doctorsTeam.setProvince(doctorsTeam_.getProvince());

        doctorsTeamFeignClient.insert(doctorsTeam);

        results.setCode(MessageCode.CODE_200);
        results.setMessage(MessageCode.MESSAGE_200);
        results.setData(null);

        return results;
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @SystemControllerLog("delete")
    public Results<Map<String, Object>> delete(int id) {
        Results<Map<String, Object>> result = new Results<Map<String, Object>>();
        try {
            DoctorsTeam doctorsTeam = doctorsTeamFeignClient.findById("selectByPrimaryKey", id);
            String hospitalName = doctorsTeam.getName();
            doctorsTeamFeignClient.delete("deleteByPrimaryKey", doctorsTeam);
            List<ContractedHospitals> contractedHospitals = contractedHospitalsFeignClient.getList("selectByHospitalName", hospitalName);
            if (contractedHospitals != null && contractedHospitals.size() > 0) {
                contractedHospitalsFeignClient.delete("deleteByPrimaryKey", contractedHospitals.get(0));
            }
            result.setCode(MessageCode.CODE_200);
            result.setMessage("成功");
        } catch (Exception e) {
            result.setCode(MessageCode.CODE_404);
            result.setMessage(e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 编辑页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editPage")
    @SystemControllerLog("editPage")
    public String editPage(int id, Model model) {
        try {
            DoctorsTeam doctorsTeam = doctorsTeamFeignClient.findById("selectByPrimaryKey", id);
            model.addAttribute("doctorsTeam", doctorsTeam);
            System.out.println(doctorsTeam.getHospitalIMG());
            if (StringUtils.isNotBlank(doctorsTeam.getHospitalIMG())) {
                model.addAttribute("isHospitalIMG", 1);
            } else {
                model.addAttribute("isHospitalIMG", 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/sys/hospital/hospitalEdit";
    }

    /**
     * 编辑医生档案
     * @param request
     * @param doctorsTeam_
     * @param file
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    @SystemControllerLog("edit")
    public Results<Map<String, Object>> edit(HttpServletRequest request, DoctorsTeam doctorsTeam_,
                                             MultipartFile file) {
        Results<Map<String, Object>> results = new Results<Map<String, Object>>();
        if (!file.isEmpty() && !FileFilterUtils.isMediaFileType(file.getOriginalFilename())) {
            results.setCode(MessageCode.CODE_404);
            results.setMessage("不支持该文件格式上传");
            return results;
        }

        String prefix = "hospitalIMG_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_";
        // 保存图片
        String fileUrl = UploadFilesUtils.saveFile(null, request, file, prefix);

        DoctorsTeam doctorsTeam = doctorsTeamFeignClient.findById("selectByPrimaryKey", doctorsTeam_.getId());
        doctorsTeam.setName(doctorsTeam_.getName());
        doctorsTeam.setArea(doctorsTeam_.getArea());
        doctorsTeam.setCityName(doctorsTeam_.getCityName());
        doctorsTeam.setCreatetime(doctorsTeam_.getCreatetime());
        doctorsTeam.setUpdatetime(new Date());
        doctorsTeam.setHospitalAddr(doctorsTeam_.getHospitalAddr());
        doctorsTeam.setHospitalIMG(fileUrl);
        doctorsTeam.setHospitalPhone(doctorsTeam_.getHospitalPhone());
        doctorsTeam.setIntroduction(doctorsTeam_.getIntroduction());
        doctorsTeam.setProvince(doctorsTeam_.getProvince());

        doctorsTeamFeignClient.update("updateByPrimaryKeySelective", doctorsTeam);

        results.setCode(MessageCode.CODE_200);
        results.setMessage(MessageCode.MESSAGE_200);
        results.setData(null);

        return results;
    }

}
