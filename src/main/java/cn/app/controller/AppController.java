package cn.app.controller;

import cn.app.pojo.*;
import cn.app.service.AppInfoService;
import cn.app.service.CategoryService;
import cn.app.service.DictionaryService;
import cn.app.service.VersionService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VersionService versionService;
    @RequestMapping(value="/appList.html")
    public String getAppList(HttpServletRequest request,@RequestParam(value="currentPage",required = false)String currentPage,
                             @RequestParam(value = "querySoftwareName",required = false) String softwareName,@RequestParam(value="queryStatus",required = false) String status,@RequestParam(value="queryFlatformId",required = false) String flatformId,@RequestParam(value="queryCategoryLevel1",required =false) String categoryLevel1,@RequestParam(value="queryCategoryLevel2",required = false) String categoryLevel2,@RequestParam(value = "queryCategoryLevel3",required = false) String categoryLevel3,
                             @RequestParam(value = "cantUpdate",required = false) String cantUpdate){
        List<Dictionary> statusList=dictionaryService.getDictionaryList("APP_STATUS");
        List<Dictionary> flatFormList=dictionaryService.getDictionaryList("APP_FLATFORM");
        List<Category> categoryList1=categoryService.getCategoryList(null);
        int currentPageNo=1;
        if(currentPage!=null&&!currentPage.equals("")){
            currentPageNo=Integer.valueOf(currentPage);
        }
        int pageSize=5;
        int count=appInfoService.appCount(softwareName,status,flatformId,categoryLevel1,categoryLevel2,categoryLevel3);
        int currentCount=(count+pageSize-1)/pageSize;
        System.out.println(status);
        List<AppInfo> appInfoList=appInfoService.getAppInfoList(softwareName,status,flatformId,categoryLevel1,categoryLevel2,categoryLevel3,(currentPageNo-1)*pageSize,pageSize);
        request.setAttribute("currentCount",currentCount);
        request.setAttribute("count",count);
        request.setAttribute("currentPage",currentPageNo);
        request.setAttribute("appInfoList",appInfoList);
        request.setAttribute("statusList",statusList);
        request.setAttribute("flatFormList",flatFormList);
        request.setAttribute("categoryLevel1List",categoryList1);
        request.setAttribute("querySoftwareName",softwareName);
        request.setAttribute("queryStatus",status);
        request.setAttribute("queryFlatformId",flatformId);
        request.setAttribute("queryCategoryLevel1",categoryLevel1);
        request.setAttribute("queryCategoryLevel2",categoryLevel2);
        request.setAttribute("queryCategoryLevel3",categoryLevel3);

        if(categoryLevel2 != null && !categoryLevel2.equals("")){
           List<Category> categoryLevel2List = categoryService.getCategoryList(categoryLevel1);
           request.setAttribute("categoryLevel2List", categoryLevel2List);
        }
        if(categoryLevel3!= null && !categoryLevel3.equals("")){
            List<Category> categoryLevel3List = categoryService.getCategoryList(categoryLevel2);
            request.setAttribute("categoryLevel3List", categoryLevel3List);
        }
        if(cantUpdate!=null&&!cantUpdate.equals("")){
            request.setAttribute("cantUpdate",cantUpdate);
        }
        return "developer/appinfolist";
    }

    @RequestMapping(value="getCategory.json")
    @ResponseBody
    public List<Category> getCategory(String parentId){
        return categoryService.getCategoryList(parentId);
    }

    @RequestMapping(value="appinfoadd")
    public String appinfoAdd(HttpServletRequest request,@RequestParam(value="status",required = false)String status){
        List<Dictionary> flatFormList=dictionaryService.getDictionaryList("APP_FLATFORM");
        List<Category> categoryList1=categoryService.getCategoryList(null);
        request.setAttribute("flatFormList",flatFormList);
        request.setAttribute("categoryLevel1List",categoryList1);
        if(status!=null&&!status.equals("")){
            System.out.println(status);
            request.setAttribute("status",status);
        }
        return "developer/appinfoadd";
    }
    @RequestMapping(value = "addSave")
    public String addSave(AppInfo appInfo, HttpSession session, @RequestParam(value="a_logoPicPath")MultipartFile mdf, HttpServletRequest request){
        if(!mdf.isEmpty()){
            String path=request.getServletContext().getRealPath("/statics/uploadfiles");
            String fileName=mdf.getOriginalFilename();
            File file =new File(path,fileName);
            try {
                mdf.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appInfo.setLogoLocPath(fileName);
            appInfo.setCreatedBy(((DevUser)session.getAttribute("devUserSession")).getId());
            appInfo.setCreationDate(new Date());
            appInfo.setDevId(((DevUser)session.getAttribute("devUserSession")).getId());
            if(appInfoService.add(appInfo)>0){
                System.out.println("成功");
                return "redirect:/app/appinfoadd?status=true";
            }else{
                System.out.println("失败");
            }
        }
        return "redirect:/app/appinfoadd?status=false";
    }
    @RequestMapping("/selAPK")
    @ResponseBody
    public int selAPK(@RequestParam(value="APKName",required = false) String APKName){
        if(appInfoService.selAPK(APKName)==null){
            return 0;
        }
        return 1;
    }
    @RequestMapping("/update")
    public String updateAppInfo(@RequestParam(value="aid",required = false)Integer id,@RequestParam(value="isUpdate",required = false)String isUpdate,HttpServletRequest request){
        AppInfo appInfo=null;
        if(id>0){
             appInfo=appInfoService.selById(id);
            if(appInfo.getStatus()==4){
                return "redirect:/app/appList.html?cantUpdate=false";
            }
        }
        List<Dictionary> flatFormList=dictionaryService.getDictionaryList("APP_FLATFORM");
        List<Category> categoryList1=categoryService.getCategoryList(null);
        request.setAttribute("flatFormList",flatFormList);
        request.setAttribute("categoryLevel1List",categoryList1);
        request.setAttribute("appInfo",appInfo);
        if(isUpdate!=null&&!isUpdate.equals("")){
            request.setAttribute("isUpdate",isUpdate);
        }
        return "developer/appinfomodify";
    }
    @RequestMapping(value="/updateAppInfoSave")
    public String updateAppInfoSave(@RequestParam(value="a_logoPicPath",required = false)MultipartFile mdf,AppInfo appInfo,HttpServletRequest request,HttpSession session){
        if(!mdf.isEmpty()&&mdf!=null){
            String path=request.getServletContext().getRealPath("/statics/uploadfiles");
            String fileName=mdf.getOriginalFilename();
            File file =new File(path,fileName);
            try {
                mdf.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            appInfo.setLogoLocPath(fileName);
        }
        if(appInfo.getStatus()==3){
            appInfo.setStatus(1);
        }
        appInfo.setModifyBy(((DevUser)session.getAttribute("devUserSession")).getId());
        appInfo.setModifyDate(new Date());
        if(appInfoService.updateAppInfo(appInfo)>0){
            System.out.println("更新成功");
            return "redirect:/app/update?aid="+appInfo.getId()+"&isUpdate=true";
        }
        return "";
    }
    @RequestMapping(value="getVersionList")
    public String getVersionList(@RequestParam(value="aid",required = false)Integer appId,HttpServletRequest request,@RequestParam(value="isAdd",required = false)String isAdd){
        List<Version> versionList=versionService.getVersionList(appId);
        request.setAttribute("appVersionList",versionList);
        if(isAdd!=null&&!isAdd.equals("")){
            request.setAttribute("isAdd",isAdd);
        }
        request.setAttribute("appId",appId);
        return "developer/appversionadd";
    }
    @RequestMapping(value="addVersion")
    public String addVersion(@RequestParam(value="a_downloadLink")MultipartFile mdf,Version version,HttpServletRequest request,HttpSession session){
        if(!mdf.isEmpty()){
            String path=request.getServletContext().getRealPath("/statics/uploadfiles");
            String fileName=mdf.getOriginalFilename();
            File file =new File(path,fileName);
            try {
                mdf.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            version.setApkFileName(fileName);
            version.setCreatedBy(((DevUser)session.getAttribute("devUserSession")).getId());
            version.setCreationDate(new Date());
            if(versionService.addVersion(version)>0){
                AppInfo appInfo=new AppInfo();
                appInfo.setId(version.getAppId());
                appInfo.setVersionId(versionService.selByVersionNo(version.getVersionNo(),version.getAppId()));
                appInfoService.updateAppInfo(appInfo);
                return "redirect:/app/getVersionList?isAdd=true&aid="+version.getAppId();
            }

        }
        return "";
    }
    @RequestMapping(value="updateVersion")
    public String upadteVersion(@RequestParam(value="vid",required = false)Integer vid,@RequestParam(value = "aid")Integer aid,HttpServletRequest request,@RequestParam(value="isUpdate",required = false)String isUpdate){
        List<Version> versionList=versionService.getVersionList(aid);
        Version version=versionService.selById(vid);
        request.setAttribute("versionList",versionList);
        request.setAttribute("appVersion",version);
        if(isUpdate!=null&&!isUpdate.equals("")){
            request.setAttribute("isUpdate",isUpdate);
        }
        return "/developer/appversionmodify";
    }

    @RequestMapping(value="/updateVersionSave")
    public String updateVersionSave(Version version,@RequestParam(value="attach",required = false)MultipartFile mdf,HttpServletRequest request,HttpSession session){
        if(!mdf.isEmpty()&&mdf!=null){
            String path=request.getServletContext().getRealPath("/statics/uploadfiles");
            String fileName=mdf.getOriginalFilename();
            File file =new File(path,fileName);
            try {
                mdf.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            version.setApkFileName(fileName);
        }
        version.setModifyDate(new Date());
        version.setModifyBy(((DevUser)session.getAttribute("devUserSession")).getId());
        if(versionService.updateVersion(version)>0){
            return "redirect:/app/updateVersion?vid="+version.getId()+"&aid="+version.getAppId()+"&isUpdate=true";
        }
        return "";
    }

    @RequestMapping(value="appInfoView")
    public String appInfoView(@RequestParam(value="aid")Integer id,HttpServletRequest request){
        AppInfo appInfo=appInfoService.selById(id);
        List<Version> versionList=versionService.getVersionList(id);
        request.setAttribute("appInfo",appInfo);
        request.setAttribute("appVersionList",versionList);
        return "developer/appinfoview";
    }

    @RequestMapping(value="delApp.json")
    @ResponseBody
    public Boolean delApp(@RequestParam(value="id")Integer id){
        System.out.println("进来了");
        if(appInfoService.delAppInfo(id)>0){
            if(versionService.delVersion(id)>0){
                return true;
            }
        }
        return false;
    }
    @RequestMapping(value="statusUp.json")
    @ResponseBody
    public Boolean statusUp(Integer id){
        if(appInfoService.updateStatusUp(id)>0){
            return true;
        }
        return false;
    }
    @RequestMapping(value="statusDown.json")
    @ResponseBody
    public Boolean statusDown(Integer id){
        if(appInfoService.updateStatusDown(id)>0){
            return true;
        }
        return false;
    }
    @RequestMapping(value="getAll")
    public String getAll() throws IOException {
        List<AppInfo> appInfoList=appInfoService.getAll();
        //创建一个EXCEL
        Workbook wb = new HSSFWorkbook();
        CellStyle style;
        //创建一个SHEET
        Sheet sheet1 = wb.createSheet("软件清单");
        String[] title = {"编号","软件名称","APK名称","软件大小","所属平台","所属分类","状态","下载次数","最新版本号"};
        int i=0;
        //创建一行
        Row row = sheet1.createRow((short)0);
        //填充标题
        for (String s:title){
            Cell cell = row.createCell(i);
            cell.setCellValue(s);
            i++;
        }

        for(int j=0;j<appInfoList.size();j++){
            Row row1 = sheet1.createRow((short)j+1);
            //下面是填充数据
            AppInfo appInfo=appInfoList.get(j);
            row1.createCell(0).setCellValue(appInfo.getId());
            row1.createCell(1).setCellValue(appInfo.getSoftwareName());
            row1.createCell(2).setCellValue(appInfo.getAPKName());
            row1.createCell(3).setCellValue(appInfo.getSoftwareSize());
            row1.createCell(4).setCellValue(appInfo.getFlatformName());
            row1.createCell(5).setCellValue(appInfo.getCategoryLevel1Name()+"->"+appInfo.getCategoryLevel2Name()+"->"+appInfo.getCategoryLevel3Name());
            row1.createCell(6).setCellValue(appInfo.getStatusName());
            row1.createCell(7).setCellValue(appInfo.getDownloads());
            row1.createCell(8).setCellValue(appInfo.getVersionNo());
        }
        FileOutputStream fileOut = new FileOutputStream("e:\\test1.xls");
        wb.write(fileOut);
        fileOut.close();
        return "redirect:/app/appList.html";
    }
    @RequestMapping("TongJi")
    public String tj(){
        return "developer/TongJi";
    }
    @RequestMapping("tongJi.json")
    @ResponseBody
    public List<AppInfo> getTongJi(){
        List<AppInfo> appInfoList=appInfoService.getAll();
        return  appInfoList;
    }
}
