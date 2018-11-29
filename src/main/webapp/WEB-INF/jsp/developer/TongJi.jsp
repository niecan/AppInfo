<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
  <div id="chartmain" style="width:600px; height:500px;"></div>
<%@include file="common/footer.jsp" %>
<script type="text/javascript" src="/statics/js/echarts.min.js"></script>
<script type="text/javascript" src="/statics/js/jquery.min.js"></script>
<script>
$(function(){
var names=[];
var nums=[];
    $.ajax({
            type:"get",
            url:"/app/tongJi.json",
            dataType:"json",
            success:function(data){
            for(var i=0;i<data.length;i++){
                names.push(data[i].softwareName);
                nums.push(data[i].downloads);
            }
             //指定图标的配置和数据
                    var option = {
                        title:{
                            text:'ECharts 数据统计'
                        },
                        tooltip:{},
                        legend:{
                            data:['app名称']
                        },
                        xAxis:[
                            {
                            data:names,
                            axisLabel:{
                               interval:0,
                               rotate:0,
                               formatter:function(val){
                                    return val.split("").join("\n"); //横轴信息文字竖直显示
                               },
                            }
                            }
                        ],
                        grid:{
                        x:40,
                        x2:100,
                        y2:150,
                        },
                        yAxis:{
                            type:'value'
                        },
                        series:[{
                            name:'下载量',
                            type:'bar',
                            data:nums
                        }]
                    };
                    //初始化echarts实例
                    var myChart = echarts.init(document.getElementById('chartmain'));

                    //使用制定的配置项和数据显示图表
                    myChart.setOption(option);
            }
        })
})
</script>