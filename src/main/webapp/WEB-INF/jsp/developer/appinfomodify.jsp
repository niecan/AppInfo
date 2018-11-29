<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<c:if test="${!empty isUpdate}">
<script>
alert("更新成功");
</script>
</c:if>
<div class="row">
  <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>修改APP基础信息 <i class="fa fa-user"></i><small>${devUserSession.devName}</small></h2>
             <div class="clearfix"></div>
      </div>
      <div class="x_content">
        <form class="form-horizontal form-label-left" action="/app/updateAppInfoSave" method="post" enctype="multipart/form-data">
          <input type="hidden" name="id" id="id" value="${appInfo.id}">
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">软件名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="softwareName" class="form-control col-md-7 col-xs-12" 
               data-validate-length-range="20" data-validate-words="1" 
               name="softwareName" value="${appInfo.softwareName}" required="required"
               placeholder="请输入软件名称" type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APK名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="APKName" type="text" class="form-control col-md-7 col-xs-12" 
              name="APKName" value="${appInfo.APKName}" readonly="readonly">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">支持ROM <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="supportROM" class="form-control col-md-7 col-xs-12" 
              	name="supportROM" value="${appInfo.supportROM}" required="required"
              	data-validate-length-range="20" data-validate-words="1" 
              	placeholder="请输入支持的ROM" type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">界面语言 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="interfaceLanguage" class="form-control col-md-7 col-xs-12" 
              data-validate-length-range="20" data-validate-words="1"  required="required"
              name="interfaceLanguage" value="${appInfo.interfaceLanguage}"
              placeholder="请输入软件支持的界面语言" type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">软件大小 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="softwareSize" name="softwareSize" value="${appInfo.softwareSize}" required="required"
              data-validate-minmax="10,500"  placeholder="请输入软件大小，单位为Mb" class="form-control col-md-7 col-xs-12">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">下载次数 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="downloads" name="downloads" value="${appInfo.downloads}" required="required"
              data-validate-minmax="10,500"  placeholder="请输入下载次数" class="form-control col-md-7 col-xs-12">
            </div>
          </div>
          <div class="form-group">
          							<label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
          							<div class="col-md-6 col-sm-6 col-xs-12">
          								<select name="queryFlatformId" class="form-control">
          									<c:if test="${flatFormList != null }">
          									   <option value="">--请选择--</option>
          									   <c:forEach var="dataDictionary" items="${flatFormList}">
          									   		<option <c:if test="${dataDictionary.valueId == appInfo.flatformId }">selected="selected"</c:if>
          									   		value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
          									   </c:forEach>
          									</c:if>
                  						</select>
          							</div>
          						</div>
          
          <div class="form-group">
          							<label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
          							<div class="col-md-6 col-sm-6 col-xs-12">
          								<select id="queryCategoryLevel1" name="queryCategoryLevel1" class="form-control" >
          									<c:if test="${categoryLevel1List != null }">
          									   <option value="">--请选择--</option>
          									   <c:forEach var="appCategory" items="${categoryLevel1List}">
          									   		<option <c:if test="${appCategory.id == appInfo.categoryLevel1 }">selected="selected"</c:if>
          									   		value="${appCategory.id}">${appCategory.categoryName}</option>
          									   </c:forEach>
          									</c:if>
                  						</select>
          							</div>
          						</div>
          
         <div class="form-group">
         							<label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类</label>
         							<div class="col-md-6 col-sm-6 col-xs-12">
         							<input type="hidden" name="categorylevel2list" id="categorylevel2list"/>
                 						<select name="queryCategoryLevel2" id="queryCategoryLevel2" class="form-control">
                 						<option value="${appInfo.categoryLevel2}">${appInfo.categoryLevel2Name}</option>
                 						</select>
         							</div>
         						</div>
          
         <div class="form-group">
         							<label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类</label>
         							<div class="col-md-6 col-sm-6 col-xs-12">
                 						<select name="queryCategoryLevel3" id="queryCategoryLevel3" class="form-control">
                 						<option value="${appInfo.categoryLevel3}">${appInfo.categoryLevel3Name}</option>
                 						</select>
         							</div>
         						</div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APP状态 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            	<input id="statusName" type="text" class="form-control col-md-7 col-xs-12" 
              	name="statusName" value="${appInfo.statusName}" readonly="readonly">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <textarea id="appInfo" name="appInfo" required="required"
              placeholder="请输入本软件的相关信息，本信息作为软件的详细信息进行软件的介绍。" class="form-control col-md-7 col-xs-12">
              ${appInfo.appInfo}</textarea>
            </div>
          </div>
           <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LOGO图片 <span class="required">*</span> </label>
            <div class="col-md-6 col-sm-6 col-xs-12" >
				<div id="uploadfile" style="display: none">
				<input id="a_logoPicPath"  type="file" class="form-control col-md-7 col-xs-12" name="a_logoPicPath">
				<p><span style="color:red;font-weight: bold;">*注：1、大小不得超过500k.2、图片格式：jpg、png、jpeg、pneg</span></p>
				</div>
				<div id="logoFile"><img src="/statics/uploadfiles/${appInfo.logoLocPath}" height="100" width="100"/>
                                    <font id="aclick" style="cursor:pointer">删除</font>
                </div>
                <div id="updateImg">

                </div>
				${fileUploadError }
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
            	<c:if test="${appInfo.status == 3}">
            	 	<button id="send" type="submit" name="status" value="1" class="btn btn-success">保存并再次提交审核</button>
            	</c:if>
              <button id="send" type="submit" class="btn btn-success">保存</button>
              <button type="button" class="btn btn-primary" id="back">返回</button>
              <br/><br/>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%@include file="common/footer.jsp"%>
<%--<script src="${pageContext.request.contextPath }/statics/localjs/appinfomodify.js"></script>--%>
<script>
      $("#queryCategoryLevel1").change(function(){
        var parentId = $("#queryCategoryLevel1").val();
        $.ajax({
            type:"get",
            url:"/app/getCategory.json",
            data:{parentId:parentId},
            dataType:"json",
            success:function(data){
               $("#queryCategoryLevel2").html("");
               $("#queryCategoryLevel3").html("");
                            var options = "<option value=\"\">--请选择--</option>";
                            for (var i = 0; i < data.length; i++) {
                                options += "<option value=\"" + data[i].id + "\">" + data[i].categoryName + "</option>";
                            }
                            $("#queryCategoryLevel2").html(options);
            }
        })
      });
       $("#queryCategoryLevel2").change(function(){
              var parentId = $("#queryCategoryLevel2").val();
              $.ajax({
                  type:"get",
                  url:"/app/getCategory.json",
                  data:{parentId:parentId},
                  dataType:"json",
                  success:function(data){
                     $("#queryCategoryLevel3").html("");
                                  var options = "<option value=\"\">--请选择--</option>";
                                  for (var i = 0; i < data.length; i++) {
                                      options += "<option value=\"" + data[i].id + "\">" + data[i].categoryName + "</option>";
                                  }
                                  $("#queryCategoryLevel3").html(options);
                  }
              })
            })
            $("#cz").click(function(){
                window.location.href="appList.html";
            });
            $("#aclick").click(function(){
                $("#logoFile").remove();
                $("#abc").remove();
                $("#updateImg").html("<input type=\"file\" class=\"form-control col-md-7 col-xs-12\" name=\"a_logoPicPath\"  required=\"required\" id=\"a_logoPicPath\"/>");
            })
    </script>