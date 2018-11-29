<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>

<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					APP 信息管理维护 <i class="fa fa-user"></i><small>${devUserSession.devName}
						- 您可以通过搜索或者其他的筛选项对APP的信息进行修改、删除等管理操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form method="post" action="/backend/appList.html">
					<input type="hidden" name="pageIndex" value="1" />
			    <ul>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input name="querySoftwareName" id="querySoftwareName" type="text" class="form-control col-md-7 col-xs-12" value="${querySoftwareName }">
							</div>
						</div>
					</li>

					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select name="queryStatus" class="form-control">
									<c:if test="${statusList != null }">
									   <option value="">--请选择--</option>
									   <c:forEach var="dataDictionary" items="${statusList}">
									   		<option <c:if test="${dataDictionary.valueId == queryStatus }">selected="selected"</c:if>
									   		value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select name="queryFlatformId" class="form-control">
									<c:if test="${flatFormList != null }">
									   <option value="">--请选择--</option>
									   <c:forEach var="dataDictionary" items="${flatFormList}">
									   		<option <c:if test="${dataDictionary.valueId == queryFlatformId }">selected="selected"</c:if>
									   		value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>

					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select id="queryCategoryLevel1" name="queryCategoryLevel1" class="form-control" >
									<c:if test="${categoryLevel1List != null }">
									   <option value="">--请选择--</option>
									   <c:forEach var="appCategory" items="${categoryLevel1List}">
									   		<option <c:if test="${appCategory.id == queryCategoryLevel1 }">selected="selected"</c:if>
									   		value="${appCategory.id}">${appCategory.categoryName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="hidden" name="categorylevel2list" id="categorylevel2list"/>
        						<select name="queryCategoryLevel2" id="queryCategoryLevel2" class="form-control">
<c:if test="${categoryLevel2List != null }">
									   <option value="">--请选择--</option>
									   <c:forEach var="appCategory" items="${categoryLevel2List}">
									   		<option <c:if test="${appCategory.id == queryCategoryLevel2 }">selected="selected"</c:if>
									   		value="${appCategory.id}">${appCategory.categoryName}</option>
									   </c:forEach>
									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
        						<select name="queryCategoryLevel3" id="queryCategoryLevel3" class="form-control">
        						<c:if test="${categoryLevel3List != null }">
                                									   <option value="">--请选择--</option>
                                									   <c:forEach var="appCategory" items="${categoryLevel3List}">
                                									   		<option <c:if test="${appCategory.id == queryCategoryLevel3 }">selected="selected"</c:if>
                                									   		value="${appCategory.id}">${appCategory.categoryName}</option>
                                									   </c:forEach>
                                									</c:if>
        						</select>
							</div>
						</div>
					</li>
					<li><button type="submit" class="btn btn-primary"> 查 &nbsp;&nbsp;&nbsp;&nbsp;询 </button>
					    <button type="button" class="btn btn-primary" id="cz"> 重 &nbsp;&nbsp;&nbsp;&nbsp;置 </button>
					</li>
				</ul>
			</form>
		</div>
	</div>
</div>
<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel">
		<div class="x_content">
			<p class="text-muted font-13 m-b-30"></p>
			<div id="datatable-responsive_wrapper"
				class="dataTables_wrapper form-inline dt-bootstrap no-footer">
				<div class="row">
					<div class="col-sm-12">
					<a href="/app/appinfoadd" class="btn btn-success btn-sm">新增APP基础信息</a>
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
							<thead>
								<tr role="row" >
									<th class="sorting_asc" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="First name: activate to sort column descending"
										aria-sort="ascending">软件名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										APK名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										软件大小(单位:M)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										所属平台</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										所属分类(一级分类、二级分类、三级分类)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										状态</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										下载次数</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										最新版本号</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 124px;"
										aria-label="Last name: activate to sort column ascending">
										操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="appInfo" items="${appInfoList }" varStatus="status">
									<tr role="row" class="odd" id="del${appInfo.id}">
										<td tabindex="0" class="sorting_1">${appInfo.softwareName}</td>
										<td>${appInfo.APKName }</td>
										<td>${appInfo.softwareSize }</td>
										<td>${appInfo.flatformName }</td>
										<td>${appInfo.categoryLevel1Name } -> ${appInfo.categoryLevel2Name } -> ${appInfo.categoryLevel3Name }</td>
										<td><span id="appInfoStatus${appInfo.id}">${appInfo.statusName }</span></td>
										<td>${appInfo.downloads }</td>
										<td>${appInfo.versionNo }</td>
										<td>
                                        <button type="button" class="btn btn-default checkApp"
                                        											appinfoid="${appInfo.id }" versionNo="${appInfo.versionNo }" versionid="${appInfo.versionId}" status="${appInfo.status }"
                                        											statusname="${appInfo.statusName }"
                                        											data-toggle="tooltip" data-placement="top" title="" data-original-title="查看并审核APP">审核</button>


										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<div class="dataTables_info" id="datatable-responsive_info"
							role="status" aria-live="polite">共${count }条记录
							${currentPage}/${currentCount }页</div>
					</div>
					<div class="col-sm-7">
						<div class="dataTables_paginate paging_simple_numbers"
							id="datatable-responsive_paginate">
							<ul class="pagination">
								<c:if test="${currentPage > 1}">
									<li class="paginate_button previous"><a
										href="/backend/appList.html?currentPage=1&querySoftwareName=${querySoftwareName}&queryStatus=${queryStatus}&queryFlatformId=${queryFlatformId}&queryCategoryLevel1=${queryCategoryLevel1}&queryCategoryLevel2=${queryCategoryLevel2}&queryCategoryLevel3=${queryCategoryLevel3}"
										aria-controls="datatable-responsive" data-dt-idx="0"
										tabindex="0">首页</a>
									</li>
									<li class="paginate_button "><a
										href="/backend/appList.html?currentPage=${currentPage-1}&querySoftwareName=${querySoftwareName}&queryStatus=${queryStatus}&queryFlatformId=${queryFlatformId}&queryCategoryLevel1=${queryCategoryLevel1}&queryCategoryLevel2=${queryCategoryLevel2}&queryCategoryLevel3=${queryCategoryLevel3}"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">上一页</a>
									</li>

									</c:if>
									<c:forEach var="tc" varStatus="vs" begin="1" end="${currentCount}">
                                                                    	<li class="paginate_button ">
                                                                    	<a href=""
                                                                           aria-controls="datatable-responsive" data-dt-idx="1" tabindex="0">
                                                                           ${tc}</a>
                                                                         </li>
                                                                    </c:forEach>
								<c:if test="${currentPage< currentCount }">
									<li class="paginate_button "><a
										href="/backend/appList.html?currentPage=${currentPage+1}&querySoftwareName=${querySoftwareName}&queryStatus=${queryStatus}&queryFlatformId=${queryFlatformId}&queryCategoryLevel1=${queryCategoryLevel1}&queryCategoryLevel2=${queryCategoryLevel2}&queryCategoryLevel3=${queryCategoryLevel3}"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">下一页</a>
									</li>
									<li class="paginate_button next"><a
					 					href="/backend/appList.html?currentPage=${currentCount}&querySoftwareName=${querySoftwareName}&queryStatus=${queryStatus}&queryFlatformId=${queryFlatformId}&queryCategoryLevel1=${queryCategoryLevel1}&queryCategoryLevel2=${queryCategoryLevel2}&queryCategoryLevel3=${queryCategoryLevel3}"
										aria-controls="datatable-responsive" data-dt-idx="7"
										tabindex="0">最后一页</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
</div>
<%@include file="common/footer.jsp"%>
<script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<%--<script src="${pageContext.request.contextPath }/statics/localjs/appinfolist.js"></script>--%>
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
            })
$(".checkApp").on("click", function () {
    var obj = $(this);
    var status = obj.attr("status");
    var vid = obj.attr("versionNo");
    if (status == "1" && vid != "" && vid != null) {//待审核状态下才可以进行审核操作
        window.location.href = "check?aid=" + obj.attr("appinfoid") + "&vid=" + obj.attr("versionid");
    } else if (vid == "" || vid == null) {
        alert("该APP应用没有上传最新版本,不能进行审核操作！");
    } else if (status != "1") {
        alert("该APP应用的状态为：【" + obj.attr("statusname") + "】,不能进行审核操作！");
    }
});

    </script>