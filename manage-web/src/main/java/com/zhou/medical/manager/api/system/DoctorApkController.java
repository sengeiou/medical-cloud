package com.zhou.medical.manager.api.system;

import com.zhou.medical.common.entity.MessageCode;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.Results;
import com.zhou.medical.common.entity.operation.PackageVersionDoctor;
import com.zhou.medical.common.util.FileFilterUtils;
import com.zhou.medical.common.util.UploadFilesUtils;
import com.zhou.medical.log.annotation.SystemControllerLog;
import com.zhou.medical.manager.client.operation.DoctorApkFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adminstrator
 * @ClassName: 包版本Controller
 * @Description: TODO
 * @date 2017年4月10日 上午11:50:27
 */
@Controller
@RequestMapping("/sys/pav/doctorApk")
@Api(tags="包版本")
public class DoctorApkController {

    private static final String PACKAGE_NAME = "com.cmcc.doctor";


    @Autowired
    DoctorApkFeignClient doctorApkFeignClient;

    /**
     * 添加单个apk
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "addPackage", notes = "新增包")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog("addPackage")
    public Results<PackageVersionDoctor> addPackage(HttpServletRequest request, String parame, MultipartFile file) {
        Results<PackageVersionDoctor> results = new Results<PackageVersionDoctor>();
        String path = request.getSession().getServletContext().getRealPath("/");
        String fileName = file.getOriginalFilename();
        boolean isApk = FileFilterUtils.getFileTypeForMimeType(fileName, "APK");
        if (!isApk) {
            results.setCode(MessageCode.CODE_501);
            results.setMessage("请上传APK文件");
            return results;
        }
        File targetPath = new File(path + "/" + "upload" + "/" + "apk");
        File targetFile = new File(targetPath.getPath(),fileName);
        //保存  
        try {
            if (!targetFile.exists()) {
                targetPath.mkdirs();
                targetFile.createNewFile();
                file.transferTo(targetFile);
            }
            ApkFile apkFile = new ApkFile(new File(targetFile.getPath()));
            ApkMeta apkInfo = apkFile.getApkMeta();
            if (!PACKAGE_NAME.equals(apkInfo.getPackageName())) {
                boolean isDirectoryCreated = targetFile.delete();
                if (!isDirectoryCreated) {
                    targetFile.delete();
                }
                results.setCode(MessageCode.CODE_501);
                results.setMessage("包名不一致");
                targetFile.delete();
                return results;
            }

            PackageVersionDoctor packageVersionDoctor_ = doctorApkFeignClient.findByParam("selectByStatus_1", new PackageVersionDoctor());
            if (packageVersionDoctor_ != null) {
                if (apkInfo.getVersionCode() <= packageVersionDoctor_.gethPackagecode()) {
                    results.setCode(MessageCode.CODE_501);
                    results.setMessage("请上传高版本APK");
                    targetFile.delete();
                    return results;
                }
                doctorApkFeignClient.update("updateStatusTo0", new PackageVersionDoctor());
            }

            String serverUrl = UploadFilesUtils.formUploadFileToServer(null, null, targetFile);
            PackageVersionDoctor packageVersionDoctor = new PackageVersionDoctor();
            packageVersionDoctor.sethPackagecode(Integer.valueOf(""+apkInfo.getVersionCode()));
            packageVersionDoctor.sethPackageversion(apkInfo.getVersionName());
            packageVersionDoctor.sethSize(Integer.valueOf("" + targetFile.length()));
            packageVersionDoctor.sethPackageurl(targetFile.getPath());
            packageVersionDoctor.sethStatus(1);
            packageVersionDoctor.sethCreatetime(new Date());

            packageVersionDoctor.sethServerurl(serverUrl);
            doctorApkFeignClient.insert(packageVersionDoctor);

            results.setCode(MessageCode.CODE_200);
            results.setMessage(MessageCode.MESSAGE_200);
            results.setData(packageVersionDoctor);
        } catch (Exception e) {
            e.printStackTrace();
            //没有任何数据
            results.setCode(MessageCode.CODE_404);
            results.setMessage(MessageCode.MESSAGE_404);
        } finally {
            if (targetFile != null && targetFile.exists()) {
                targetFile.delete();
            }
        }

        return results;
    }

    /**
     * APK管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    @SystemControllerLog("manager")
    public String manager() {
        return "/packageVersion/doctorApk";
    }

    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    @SystemControllerLog("addPage")
    public String addPage() {

        return "/packageVersion/doctorApkAdd";
    }

    /**
     * 获取apklist
     * @param packageVersionDoctor
     * @param page
     * @param rows
     * @return
     */
    @ApiOperation(value = "findPage", notes = "findPage")
    @RequestMapping(value = "findPage", method = RequestMethod.POST)
    @ApiImplicitParam(name = "page",value = "分页", dataType = "integer", paramType = "query")
    @ResponseBody
//    @TestAnn("获取apklist")
    @SystemControllerLog("获取apklist")
    public Map<String, Object> findPage(PackageVersionDoctor packageVersionDoctor,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Pager<PackageVersionDoctor> pagers = doctorApkFeignClient.getList("getList", page, rows, packageVersionDoctor);
            map.put("total", pagers.getTotalCount());
            map.put("rows", pagers.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 获取新版本的apk
     * @return
     */
    @RequestMapping(value = "getLP")
    @ResponseBody
    @SystemControllerLog("getLP")
    public Results<PackageVersionDoctor> getLatestPackage() {
        Results<PackageVersionDoctor> results = new Results<PackageVersionDoctor>();
        try {
            PackageVersionDoctor packageVersionDoctor = doctorApkFeignClient.findByParam("selectByStatus_1", new PackageVersionDoctor());
            results.setCode(MessageCode.CODE_200);
            results.setMessage(MessageCode.MESSAGE_200);
            results.setData(packageVersionDoctor);
        } catch (Exception e) {
            e.printStackTrace();
            //没有任何数据
            results.setCode(MessageCode.CODE_404);
            results.setMessage(MessageCode.MESSAGE_404);
        }
        return results;
    }


}
