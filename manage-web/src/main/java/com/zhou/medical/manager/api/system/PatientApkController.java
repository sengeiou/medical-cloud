package com.zhou.medical.manager.api.system;

import com.zhou.medical.common.entity.MessageCode;
import com.zhou.medical.common.entity.Pager;
import com.zhou.medical.common.entity.Results;
import com.zhou.medical.common.entity.operation.PackageVersionPatient;
import com.zhou.medical.common.util.FileFilterUtils;
import com.zhou.medical.common.util.UploadFilesUtils;
import com.zhou.medical.manager.client.operation.PatientApkFeignClient;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/sys/pav/patientApk")
public class PatientApkController {

    private static final String PACKAGE_NAME = "com.cmcc.patient";

    @Autowired
    PatientApkFeignClient patientApkFeignClient;

    /**
     * 添加单个apk
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public Results<PackageVersionPatient> addPackage(HttpServletRequest request, String parame,
                                                     MultipartFile file) {
        Results<PackageVersionPatient> results = new Results<PackageVersionPatient>();

        String path = request.getSession().getServletContext().getRealPath("/");
        String fileName = file.getOriginalFilename();
        boolean isApk = FileFilterUtils.getFileTypeForMimeType(fileName, "APK");
        if (!isApk) {
            results.setCode(MessageCode.CODE_501);
            results.setMessage("请上传APK文件");
            return results;
        }
        //保存
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
                results.setCode(MessageCode.CODE_501);
                results.setMessage("包名不一致");
                if (targetFile != null && targetFile.exists()) {
                    targetFile.delete();
                }
                return results;
            }

            PackageVersionPatient packageVersionPatient_ = patientApkFeignClient.findByParam("selectByStatus_1", new PackageVersionPatient());
            if (packageVersionPatient_ != null) {
                if (apkInfo.getVersionCode() <= packageVersionPatient_.gethPackagecode()) {
                    results.setCode(MessageCode.CODE_501);
                    results.setMessage("请上传高版本APK");
                    if (targetFile != null && targetFile.exists()) {
                        targetFile.delete();
                    }
                    return results;
                }
                patientApkFeignClient.update("updateStatusTo0", new PackageVersionPatient());
            }
            String serverUrl = UploadFilesUtils.formUploadFileToServer(null, null, targetFile);
            PackageVersionPatient packageVersionPatient = new PackageVersionPatient();
            packageVersionPatient.sethPackagecode(Integer.valueOf("" + apkInfo.getVersionCode()));
            packageVersionPatient.sethPackageversion(apkInfo.getVersionName());
            packageVersionPatient.sethSize(Integer.valueOf("" + targetFile.length()));
            packageVersionPatient.sethPackageurl(targetFile.getPath());
            packageVersionPatient.sethStatus(1);
            packageVersionPatient.sethCreatetime(new Date());

            packageVersionPatient.sethServerurl(serverUrl);
            patientApkFeignClient.insert(packageVersionPatient);

            results.setCode(MessageCode.CODE_200);
            results.setMessage(MessageCode.MESSAGE_200);
            results.setData(packageVersionPatient);
        } catch (Exception e) {
            e.printStackTrace();
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
    public String manager() {
        return "/packageVersion/patientApk";
    }

    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {

        return "/packageVersion/patientApkAdd";
    }

    /**
     * 获取apklist
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findPage(HttpServletRequest request, PackageVersionPatient packageVersionPatient,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows) {

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Pager<PackageVersionPatient> pagers = patientApkFeignClient.getList("getList", page, rows, packageVersionPatient);
            map.put("total", pagers.getTotalCount());
            map.put("rows", pagers.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取新版本的apk
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getLP")
    @ResponseBody
    public Results<PackageVersionPatient> getLatestPackage(HttpServletRequest request) {
        Results<PackageVersionPatient> results = new Results<PackageVersionPatient>();
        try {
            PackageVersionPatient packageVersionPatient = patientApkFeignClient.findByParam("selectByStatus_1", new PackageVersionPatient());
            results.setCode(MessageCode.CODE_200);
            results.setMessage(MessageCode.MESSAGE_200);
            results.setData(packageVersionPatient);
        } catch (Exception e) {
            e.printStackTrace();
            results.setCode(MessageCode.CODE_404);
            results.setMessage(MessageCode.MESSAGE_404);
        }
        return results;
    }

    /**
     * 获取新版本的apk
     *
     * @param resp
     */
    @RequestMapping(value = "downloadApk", method = RequestMethod.GET)
    public void downloadApk(HttpServletResponse resp) {
        try {
            PackageVersionPatient packageVersionPatient = patientApkFeignClient.findByParam("selectByStatus_1", new PackageVersionPatient());
            resp.sendRedirect(packageVersionPatient.gethServerurl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
