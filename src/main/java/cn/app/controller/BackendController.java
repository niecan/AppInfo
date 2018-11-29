package cn.app.controller;

        import cn.app.pojo.AppInfo;
        import cn.app.pojo.Category;
        import cn.app.pojo.Dictionary;
        import cn.app.pojo.Version;
        import cn.app.service.AppInfoService;
        import cn.app.service.CategoryService;
        import cn.app.service.DictionaryService;
        import cn.app.service.VersionService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.ResponseBody;

        import javax.servlet.http.HttpServletRequest;
        import java.util.List;

@Controller
@RequestMapping(value="backend")
public class BackendController {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private VersionService versionService;

    @RequestMapping(value="/appList.html")
    public String getAppList(HttpServletRequest request, @RequestParam(value="currentPage",required = false)String currentPage,
                             @RequestParam(value = "querySoftwareName",required = false) String softwareName, @RequestParam(value="queryStatus",required = false) String status, @RequestParam(value="queryFlatformId",required = false) String flatformId, @RequestParam(value="queryCategoryLevel1",required =false) String categoryLevel1, @RequestParam(value="queryCategoryLevel2",required = false) String categoryLevel2, @RequestParam(value = "queryCategoryLevel3",required = false) String categoryLevel3,
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
        return "backend/applist";
    }

    @RequestMapping(value="getCategory.json")
    @ResponseBody
    public List<Category> getCategory(String parentId){
        return categoryService.getCategoryList(parentId);

    }
    @RequestMapping(value="check")
    public String check(@RequestParam(value="aid")Integer aid,@RequestParam(value="vid")Integer vid,HttpServletRequest request){
        AppInfo appInfo=appInfoService.selById(aid);
        Version version=versionService.selById(vid);
        request.setAttribute("appInfo",appInfo);
        request.setAttribute("appVersion",version);
        return "backend/appcheck";
    }
    @RequestMapping(value="updateStatus")
    public String updateStatus(AppInfo appInfo){
        if(appInfoService.updateStatus(appInfo)>0){
            return "redirect:/backend/appList.html";
        }
        return "";
    }
}
